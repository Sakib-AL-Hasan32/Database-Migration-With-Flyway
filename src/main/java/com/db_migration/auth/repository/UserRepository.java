package com.db_migration.auth.repository;

import jakarta.persistence.QueryHint;
import org.hibernate.jpa.HibernateHints;
import org.springframework.data.jpa.repository.JpaRepository;
import com.db_migration.auth.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @QueryHints({@QueryHint(name = HibernateHints.HINT_FLUSH_MODE, value = "COMMIT")})
    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsernameForAudit(@Param("username") String username);
}