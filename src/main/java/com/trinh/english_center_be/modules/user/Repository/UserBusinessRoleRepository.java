package com.trinh.english_center_be.modules.user.Repository;

import com.trinh.english_center_be.modules.user.entity.UserBusinessRole;
import com.trinh.english_center_be.modules.user.entity.UserBusinessRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBusinessRoleRepository extends JpaRepository<UserBusinessRole, UserBusinessRoleId> {

    @Query("SELECT ubr FROM UserBusinessRole ubr JOIN FETCH ubr.businessRole br JOIN FETCH br.roles WHERE ubr.id.userId = :userId")
    List<UserBusinessRole> findByUserIdWithRoles(@Param("userId") Long userId);

    boolean existsByIdUserIdAndIdBusinessRoleId(Long userId, Long businessRoleId);
}
