package com.example.msusers.controller;

import com.example.msusers.domain.User;
import com.example.msusers.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService service){
        this.userService = service;
    }

    @GetMapping()
    public ResponseEntity<User> findById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String userId = jwt.getClaim("sub");
        Optional<User> user = userService.findUserById(userId);

        return ResponseEntity.ok(user.get());
    }

}
