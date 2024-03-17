package com.example.Plages.Controllers;

import com.example.Plages.DALs.UserRepositories.IUserRepository;
import com.example.Plages.Dtos.ClientDto;
import com.example.Plages.Models.*;
import com.example.Plages.Services.Pays.IPaysService;
import com.example.Plages.Services.Users.IAuthService;
import com.example.Plages.Services.Users.IUserService;
import com.example.Plages.exception.TokenRefreshException;
import com.example.Plages.payload.requests.*;
import com.example.Plages.payload.responses.JwtResponse;
import com.example.Plages.payload.responses.MessageResponse;
import com.example.Plages.payload.responses.TokenRefreshResponse;
import com.example.Plages.security.jwt.JwtUtils;
import com.example.Plages.security.services.RefreshTokenService;
import com.example.Plages.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IAuthService authService;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    IPaysService paysService;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    IUserRepository userRepo;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ClientDto ClientDto) {
        if (authService.existsByUsername(ClientDto.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (authService.existsByEmail(ClientDto.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        Optional<Pays> paysOptional = paysService.GetPaysById(ClientDto.PaysId);
        Pays pays = paysOptional.get();
        User user = new User(ClientDto.getUsername(), ClientDto.getEmail(),ClientDto.getPassword(), ClientDto.getNom(), ClientDto.getPrenom(),
                ClientDto.getAdresseDeFacturation(),ClientDto.getPhone(),pays);

        Set<String> strRoles = ClientDto.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = authService.findByName(ERole.ROLE_CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = authService.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "client" -> {
                        Role modRole = authService.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        authService.saveUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signout/{username}")
    public ResponseEntity<?> logoutUser(@PathVariable String username) {
        User user = authService.findByUsername(username).get();
        refreshTokenService.deleteByUserId(user.getId());
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }

    @PostMapping("/forgetpassword")
    public ResponseEntity<?> forgetPassword(@RequestBody ForgetPassword fp) {
        return ResponseEntity.ok(new MessageResponse(authService.forgetPassword(fp.getEmail())));
    }

    @PutMapping("/resetpassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest rpr) {
        return ResponseEntity.ok(new MessageResponse(authService.resetPassword(rpr.getToken(), rpr.getPassword())));
    }
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/date")
    public String getCurrentDateTime() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        String CurrentDateTime = currentDate.format(formatter);
        return CurrentDateTime;
    }

    @GetMapping("/getusersbyRole")
    public ResponseEntity<List<User>> getUsersByRole(@RequestBody Role role) {
        List<User> users = authService.findByRole(role);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }
    @GetMapping("/getallUser/{role}")
    public ResponseEntity<List<User>> GetAllUser(@PathVariable ERole role) {
        Optional<Role> role1 = authService.findByName(role) ;
        List<User> users = userRepo.findByRoles(role1.get());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/getuser/{id}")
    public ResponseEntity<User> GetAllUser(@PathVariable Long id) {
        Optional<User> user = authService.GetUser(id);

        return user.map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

