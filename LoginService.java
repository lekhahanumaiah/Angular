package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.LoginRepository;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;
import java.util.List;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // 🔐 Authenticate user by email and password
    public Optional<User> authenticateUser(String email, String password) {
        Optional<User> userOptional = loginRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    // 🔍 Find user by email
    public Optional<User> findByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    // 🆕 Create new user
    public void createUser(User user) {
        loginRepository.save(user);
    }

    // ✅ Check if email already exists
    public boolean existsByEmail(String email) {
        return loginRepository.existsByEmail(email);
    }

    // 🔍 Find users by role
    public List<User> findUsersByRole(String role) {
        return loginRepository.findByRole(role);
    }

    // 🔍 Find users by name
    public List<User> findUsersByName(String name) {
        return loginRepository.findByName(name);
    }

    // 🔍 Find user by email and role
    public Optional<User> findByEmailAndRole(String email, String role) {
        return loginRepository.findByEmailAndRole(email, role);
    }

    public Optional<User> findUserById(Long id) {
        return loginRepository.findById(id);
    }
}
