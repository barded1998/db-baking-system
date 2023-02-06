package db7.bankingsystem.repository;

import db7.bankingsystem.domain.User;
import db7.bankingsystem.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UserId> {
    public Optional<User> findByIdUserId(Long userId);
}
