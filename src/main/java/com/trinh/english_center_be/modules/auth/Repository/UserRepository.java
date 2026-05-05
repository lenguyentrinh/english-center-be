package com.trinh.english_center_be.modules.auth.Repository;

import com.trinh.english_center_be.modules.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsername(String username);
    boolean existByUsername(String username);
}
