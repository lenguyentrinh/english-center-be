package com.trinh.english_center_be.modules.user.entity;

import com.trinh.english_center_be.shared.BaseEntity;
import com.trinh.english_center_be.shared.enums.Roles;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", unique = true, length = 30)
    private Roles role;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_role_id")
    private BusinessRole businessRole;
}
