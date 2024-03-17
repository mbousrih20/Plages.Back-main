package com.example.Plages.Services.Users;

import com.example.Plages.DALs.UserRepositories.IUserRepository;
import com.example.Plages.DALs.UserRepositories.RoleRepository;
import com.example.Plages.Models.ERole;
import com.example.Plages.Models.Role;
import com.example.Plages.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
@Service
public class AuthService implements IAuthService {
    @Autowired
    IUserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    PasswordEncoder encoder;
    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }
    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepo.findByName(name);
    }

    @Override
    public User saveUser(User user) {
        user.setCreationDate(new Date(System.currentTimeMillis()));
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user); }
    @Override
    public Optional<User> GetUser(Long Id) {
        return userRepo.findById(Id);
    }
    @Override
    public List<User> findByRole(Role role) {
        return userRepo.findByRoles(role);
    }
    @Override
    public String forgetPassword(String email) {

        Optional<User> userOptional = userRepo.findByEmail(email);

        if (!userOptional.isPresent()) {
            return "Invalid email id.";
        }

        User user = userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user = userRepo.save(user);

        return user.getToken();
    }

    @Override
    public String resetPassword(String token, String password) {

        Optional<User> userOptional = userRepo.findByToken(token);

        if (!userOptional.isPresent()) {
            return "Invalid token.";
        }

        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";

        }

        User user = userOptional.get();
        user.setPassword(encoder.encode(password));
        user.setToken(null);
        user.setTokenCreationDate(null);

        userRepo.save(user);

        return "Your password successfully updated.";
    }
    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID())
                .append(UUID.randomUUID()).toString();
    }
    private boolean isTokenExpired(LocalDateTime tokenCreationDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }

}

