package db7.bankingsystem.repository;

import db7.bankingsystem.domain.Account;
import db7.bankingsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public List<Account> findByUserIdUserId(long userId);
}
