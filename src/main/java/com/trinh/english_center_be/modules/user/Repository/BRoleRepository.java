package com.trinh.english_center_be.modules.user.Repository;

import com.trinh.english_center_be.modules.user.entity.BusinessRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BRoleRepository extends JpaRepository<BusinessRole, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    List<BusinessRole> findByActiveTrue();
}
