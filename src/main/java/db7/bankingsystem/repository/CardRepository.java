package db7.bankingsystem.repository;

import db7.bankingsystem.domain.Card;
import db7.bankingsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    public List<Card> findByAccountIdAccountId(Long accountId);

    public List<Card> findByUser_Id_UserId(Long userId);
}
