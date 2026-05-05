package com.trinh.english_center_be.modules.auth.service;

import com.trinh.english_center_be.modules.auth.dto.LoginRequest;
import com.trinh.english_center_be.modules.auth.dto.SignupRequest;
import com.trinh.english_center_be.modules.user.entity.Role;
import com.trinh.english_center_be.modules.user.entity.User;
import com.trinh.english_center_be.modules.user.service.RoleService;
import com.trinh.english_center_be.modules.user.service.UserService;
import com.trinh.english_center_be.shared.config.JwtTokenProvider;
import com.trinh.english_center_be.shared.enums.Roles;
import com.trinh.english_center_be.shared.enums.UserStatus;
import com.trinh.english_center_be.shared.exception.InvalidCredentialException;
import com.trinh.english_center_be.shared.exception.ResourceNotFoundException;
import com.trinh.english_center_be.shared.exception.UnauthorizedException;
import com.trinh.english_center_be.shared.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String authenticate(LoginRequest loginRequest) {
        var user = userService.findByUsername(loginRequest.username())
                .orElseThrow(()-> new UsernameNotFoundException(StringUtil.USERNAME_NOT_FOUND));

        if(user.getStatus() != UserStatus.ACTIVE) throw new UnauthorizedException(StringUtil.USER_ACCOUNT_NOT_ACTIVE);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

            return jwtTokenProvider.generateToken(authentication);
        } catch (AuthenticationException ex) {
            throw new InvalidCredentialException();
        }
    }

    @Override
    public void signup(SignupRequest request) {
        if(userService.existByUserName(request.username())) throw new RuntimeException(StringUtil.USERNAME_EXITS);

        Role roleDefault = roleService.findRoleByRoleName(Roles.STUDENT).orElseThrow(()-> new ResourceNotFoundException(StringUtil.STUDENT_ROLE));

        String encodePassword = passwordEncoder.encode(request.password());

        User user = User.builder()
                .email(request.email())
                .phone(request.phone())
                .status(UserStatus.ACTIVE)
                .role(roleDefault)
                .username(request.username())
                .fullName(request.fullName())
                .password(encodePassword)
                .build();

        userService.save(user);
    }
}
