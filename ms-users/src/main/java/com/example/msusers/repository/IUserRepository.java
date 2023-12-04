package com.example.msusers.repository;

import com.example.msusers.domain.User;
import java.util.Optional;

public interface IUserRepository {
    Optional<User> findById (String userName);
}
