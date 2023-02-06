package db7.bankingsystem.repository;

import db7.bankingsystem.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
public class RepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    HistoryRepository historyRepository;

    Timestamp timestamp = new Timestamp(1998, 11, 23, 11, 11, 11, 11);

    @BeforeEach
    void beforeEach() {

        //User
        User user = new User();
        user.setId(new UserId(1L, "alice" ,"google", ":1000"));
//        user.setUserId(1L);
//        user.setName("alice");
//        user.setEmail("alice@google.com");
//        user.setPhoneNumber("10010000");
        user.setAddress("Republic of Korea, .....");
        user.setBirthday(timestamp);
        log.info("user={}", user);
        userRepository.save(user);

        Account account = new Account();
        account.setAccountId(1L);
        account.setDepositBalance(1000);
        account.setAccountType("VIP");
        account.setMakeDate(timestamp);
        account.setUser(user);
        log.info("account={}", account);
        accountRepository.save(account);

        Card card = new Card();
        card.setCardId(1L);
        card.setMax(1000);
        card.setCardType("VIP");
        card.setDate(timestamp);
        card.setLastUse(timestamp);
        card.setUser(user);
        log.info("card={}", card);
        cardRepository.save(card);

        HistoryId historyId = new HistoryId(1L, 1L, timestamp);
        History history = new History(historyId,account);
        history.setMoney(1000);
        history.setDepositType("출금");
        history.setContent("Alice가 출금함.");
        log.info("history={}", history);
        historyRepository.save(history);
    }

    @Test
    void userRepositoryTest() {
        User user = userRepository.findByIdUserId(1L).get();
        log.info("user={}",user);

        Account account = accountRepository.findByUser_Id_UserId(1L).get(0);
        log.info("account={}",account);

        History history = historyRepository.findByIdAccountId(1L).get(0);
        log.info("history={}", history);
    }
}