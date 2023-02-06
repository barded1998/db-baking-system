package db7.bankingsystem.repository;

import db7.bankingsystem.domain.Account;
import db7.bankingsystem.domain.History;
import db7.bankingsystem.domain.HistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, HistoryId> {
    public List<History> findAllByOrderByIdHistoryId();

    public List<History> findByIdAccountId(Long accountId);

    public Optional<History> findByIdHistoryId(Long historyId);

    public List<History> findByIdAccountIdAndIdUseDateIsAfter(Long accountId, Timestamp timestamp);

    public List<History> findByIdAccountIdAndIdUseDateGreaterThanEqual(Long accountId, Timestamp timestamp);
}
