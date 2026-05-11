package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    boolean existByUserName(String username);
    Optional<User> findById(Long id);
    User save(User user);
    boolean isUserActive(Long id);
}
