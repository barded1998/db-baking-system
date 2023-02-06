package db7.bankingsystem.service;

import db7.bankingsystem.domain.Account;
import db7.bankingsystem.domain.Card;
import db7.bankingsystem.domain.History;
import db7.bankingsystem.domain.HistoryId;
import db7.bankingsystem.repository.AccountRepository;
import db7.bankingsystem.repository.CardRepository;
import db7.bankingsystem.repository.HistoryRepository;
import db7.bankingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class TransactionService {
    private final AccountRepository accountRepository;
    private final HistoryRepository historyRepository;
    private final CardRepository cardRepository;
    private long sequence;

    public TransactionService(AccountRepository accountRepository, HistoryRepository historyRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.historyRepository = historyRepository;
        this.cardRepository = cardRepository;
        sequence = historyRepository.count();
    }

    public History deposit(long accountId, int money) {
        Account account = accountRepository.findById(accountId).get();
        account.setDepositBalance(account.getDepositBalance() + money);
        History history = new History();
        history.setAccount(account);
        history.setId(new HistoryId(++sequence, accountId, new Timestamp(System.currentTimeMillis())));
        history.setDepositType("입금");
        history.setContent("계좌 번호 " + accountId + "에 " + money + "원 입금됨.");
        history.setMoney(money);
        return historyRepository.save(history);
    }

    public History withdrawal(long accountId, int money) {
        Account account = accountRepository.findById(accountId).get();
        account.setDepositBalance(account.getDepositBalance() - money);
        History history = new History();
        history.setAccount(account);
        history.setId(new HistoryId(++sequence, accountId, new Timestamp(System.currentTimeMillis())));
        history.setDepositType("출금");
        history.setContent("계좌 번호 " + accountId + "에 " + money + "원 출금");
        history.setMoney(money);
        return historyRepository.save(history);
    }

    public History transfer(long fromAccountId, long toAccountId, int money) {
        Account fromAccount = accountRepository.findById(fromAccountId).get();
        Account toAccount = accountRepository.findById(toAccountId).get();
        fromAccount.setDepositBalance(fromAccount.getDepositBalance() - money);
        toAccount.setDepositBalance(toAccount.getDepositBalance() + money);

        History history = new History();
        history.setAccount(fromAccount);
        history.setId(new HistoryId(++sequence, fromAccountId, new Timestamp(System.currentTimeMillis())));
        history.setDepositType("송금");
        history.setContent("계좌 번호 " +fromAccountId + " 부터 " + "계좌 번호 " + toAccountId +"로 "+ money + "원 송금");
        history.setMoney(money);
        return historyRepository.save(history);
    }

    public History cardPayment(long cardId, int money) {
        Card card = cardRepository.findById(cardId).get();
        Account account = accountRepository.findById(card.getAccountId().getAccountId()).get();
        account.setDepositBalance(account.getDepositBalance() - money);
        card.setLastUse(new Timestamp(System.currentTimeMillis()));

        History history = new History();
        history.setAccount(account);
        history.setId(new HistoryId(++sequence, card.getAccountId().getAccountId(), new Timestamp(System.currentTimeMillis())));
        history.setDepositType("카드");
        history.setContent("카드" + cardId + "에서 " + money + "원 사용");
        history.setMoney(money);
        return historyRepository.save(history);
    }
}
