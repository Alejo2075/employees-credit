package org.alejo2075.employees_credit.modules.user.repository;

import org.alejo2075.employees_credit.modules.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Finds a user by email.
     *
     * @param email the email of the user
     * @return an Optional containing the user if found, or empty if not found
     */
    Optional<User> findByEmail(String email);
}