package com.trinh.english_center_be.modules.user.Repository;

import com.trinh.english_center_be.modules.user.entity.BusinessRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRoleRepository extends JpaRepository<BusinessRole, Long> {
    Optional<BusinessRole> findByName(String name);
    boolean existsByName(String name);
    List<BusinessRole> findByActiveTrue();
}
