package com.example.Plages.Services.Users;

import com.example.Plages.DALs.UserRepositories.IUserRepository;
import com.example.Plages.Models.Role;
import com.example.Plages.Models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements IUserService {
    
    private final IUserRepository UserRepository;
    
    @Autowired
    public UserService(IUserRepository userRepository){
        UserRepository = userRepository;
    }

    @Override
    public Optional<User> GetUser(Long Id) {
        return UserRepository.findById(Id);
    }

    @Override
    public User AddUser(User user) {
        return UserRepository.save(user);
    }
    
    @Override
    public Optional<User> GetUserByEmail(String email){
        return this.UserRepository.findByEmail(email);
    }

    @Override
    public void DeleteUser(User user) {
        this.UserRepository.delete(user);
    }
    public List<User> findByRole(Role role) {
        return UserRepository.findByRoles(role);
    }
}