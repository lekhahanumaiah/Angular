package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

class UserResponseDTO {
    private Long id;
    private String name;
    private String role;
    private String email;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
        this.email = user.getEmail();
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
}

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")  // allow Angular frontend
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // 🔐 Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        Optional<User> user = loginService.authenticateUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        if (user.isPresent()) {
            return ResponseEntity.ok(new UserResponseDTO(user.get()));
        } else {
            return ResponseEntity.status(401).body("Invalid email or password.");
        }
    }

    // 🔍 Get role by user ID
    @GetMapping("/users/role-by-id/{id}")
    public ResponseEntity<String> getUserRoleById(@PathVariable Long id) {
        return loginService.findUserById(id)
                .map(u -> ResponseEntity.ok(u.getRole()))
                .orElse(ResponseEntity.status(404).body("User not found"));
    }

    // 🔍 Get users by name
    @GetMapping("/users/name/{name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
        return ResponseEntity.ok(loginService.findUsersByName(name));
    }
}
