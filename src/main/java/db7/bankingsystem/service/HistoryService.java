package db7.bankingsystem.service;

import db7.bankingsystem.domain.Account;
import db7.bankingsystem.domain.History;
import db7.bankingsystem.domain.HistoryId;
import db7.bankingsystem.domain.User;
import db7.bankingsystem.repository.AccountRepository;
import db7.bankingsystem.repository.HistoryRepository;
import db7.bankingsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private long sequence;

    public HistoryService(HistoryRepository historyRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.historyRepository = historyRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        sequence = historyRepository.count();
    }

    public History save(HistoryDto historyDto) {
        History history = new History();
        history.setAccount(accountRepository.findById(historyDto.getAccountId()).get());
        history.setId(new HistoryId(++sequence, accountRepository.findById(historyDto.getAccountId()).get().getAccountId(), new Timestamp(System.currentTimeMillis())));
        history.setDepositType(historyDto.getDepositType());
        history.setContent(historyDto.getContent());
        history.setMoney(historyDto.getMoney());
        History save = historyRepository.save(history);
        return save;
    }

    public List<History> findAll() {
        return historyRepository.findAllByOrderByIdHistoryId();
    }


    public List<History> findByUserId(long userId) {
        User user = userRepository.findByIdUserId(userId).get();
        if (user == null) {
            return historyRepository.findAll();
        }
        List<Account> accounts = accountRepository.findByUserIdUserId(userId);
        List<History> histories = new ArrayList<>();
        for (Account account : accounts) {
            List<History> byIdAccountId = historyRepository.findByIdAccountId(account.getAccountId());
            for (History history : byIdAccountId) {
                histories.add(history);
            }
        }
        return histories;
    }

    public Optional<History> findByHistoryId(long id) {
        return historyRepository.findByIdHistoryId(id);
    }
}
