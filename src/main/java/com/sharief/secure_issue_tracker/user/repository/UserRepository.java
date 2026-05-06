package com.sharief.secure_issue_tracker.user.repository;
import com.sharief.secure_issue_tracker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
