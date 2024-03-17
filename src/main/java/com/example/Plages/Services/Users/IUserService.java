package com.example.Plages.Services.Users;

import com.example.Plages.Models.Role;
import com.example.Plages.Models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    
    public Optional<User> GetUser(Long Id);
    public User AddUser(User user);
    public Optional<User> GetUserByEmail(String email);
    public void DeleteUser(User user);
    public List<User> findByRole(Role role);
}