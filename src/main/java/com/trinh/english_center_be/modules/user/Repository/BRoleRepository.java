package com.trinh.english_center_be.modules.user.Repository;

import com.trinh.english_center_be.modules.user.entity.BusinessRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BRoleRepository extends JpaRepository<BusinessRole, Long> {
}
