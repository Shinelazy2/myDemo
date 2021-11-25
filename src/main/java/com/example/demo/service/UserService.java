package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save_(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role role = new Role();
        role.setId(1L);
        user.setEnabled(true);
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
