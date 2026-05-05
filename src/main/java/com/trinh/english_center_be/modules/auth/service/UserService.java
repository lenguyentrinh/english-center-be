package com.trinh.english_center_be.modules.auth.service;

import com.trinh.english_center_be.modules.auth.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails findUserByUsername(String name);
}
