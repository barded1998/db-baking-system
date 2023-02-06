package db7.bankingsystem.service;

import db7.bankingsystem.domain.Account;
import db7.bankingsystem.domain.User;
import db7.bankingsystem.repository.AccountRepository;
import db7.bankingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public Account save(AccountDto accountDto) {
        Account account = new Account();
        User user = userRepository.findByIdUserId(accountDto.getUserId()).get();
        account.setUser(user);
        account.setAccountId(accountDto.getAccountId());
        account.setDepositBalance(accountDto.getDepositBalance());
        account.setAccountType(accountDto.getAccountType());
        account.setMakeDate(new Timestamp(System.currentTimeMillis()));
        Account save = accountRepository.save(account);
        return save;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(long id) {
        return accountRepository.findById(id);
    }

    public List<Account> findByUserId(Long userId) {
        return accountRepository.findByUserIdUserId(userId);}
}
