package com.trinh.english_center_be.modules.auth.Repository;

import com.trinh.english_center_be.modules.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
