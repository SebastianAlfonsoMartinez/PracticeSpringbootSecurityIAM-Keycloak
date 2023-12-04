package com.example.msusers.service;

import com.example.msusers.domain.User;
import com.example.msusers.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository repository;

    public Optional<User> findUserById(String userId){
        return repository.findById(userId);
    }

}
