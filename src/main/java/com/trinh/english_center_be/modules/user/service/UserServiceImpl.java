package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.Repository.UserRepository;
import com.trinh.english_center_be.modules.user.entity.User;
import com.trinh.english_center_be.shared.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findUsername(username);
    }

    @Override
    public boolean existByUserName(String username) {
        return userRepository.existByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean isUserActive(Long id) {
        return userRepository.findById(id).map(user -> user.getStatus() == UserStatus.ACTIVE).orElse(false);
    }
}
