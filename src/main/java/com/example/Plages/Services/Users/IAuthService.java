package com.example.Plages.Services.Users;

import com.example.Plages.Models.ERole;
import com.example.Plages.Models.Role;
import com.example.Plages.Models.User;

import java.util.List;
import java.util.Optional;

public interface IAuthService {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
    Optional<Role> findByName(ERole name);
    public Optional<User> GetUser(Long Id);
    User saveUser(User user);

    String forgetPassword(String email);
    public List<User> findByRole(Role role);
    String resetPassword(String token, String password);
}
