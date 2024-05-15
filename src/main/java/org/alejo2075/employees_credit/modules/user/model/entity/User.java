package org.alejo2075.employees_credit.modules.user.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.alejo2075.employees_credit.modules.user.model.Role;

import java.util.List;
import java.util.UUID;

/**
 * Entity class for user.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Email of the user.
     */
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    /**
     * Password of the user.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Full name of the user.
     */
    @Column(name = "full_name", length = 100)
    private String fullName;

    /**
     * Government ID of the user.
     */
    @Column(name = "gov_id", unique = true, length = 12)
    private String govId;

    /**
     * Roles assigned to the user.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
}