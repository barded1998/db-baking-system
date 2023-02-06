package db7.bankingsystem.service;

import db7.bankingsystem.domain.Card;
import db7.bankingsystem.repository.AccountRepository;
import db7.bankingsystem.repository.CardRepository;
import db7.bankingsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private long sequence;

    public CardService(CardRepository cardRepository, UserRepository userRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        sequence = cardRepository.findAll().size();
    }

    public Card save(CardDto cardDto) {
        Card card = new Card();
        card.setCardId(++sequence);
        card.setDate(new Timestamp(System.currentTimeMillis()));
        card.setMax(cardDto.getMax());
        card.setCardType(cardDto.getCardType());
        card.setAccountId(accountRepository.findById(cardDto.getAccountId()).get());
        card.setUser(accountRepository.findById(cardDto.getAccountId()).get().getUser());
        Card save = cardRepository.save(card);
        return save;
    }

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public Optional<Card> findById(long id) {
        return cardRepository.findById(id);
    }

    public List<Card> findByAccountId(long accountId) {
        return cardRepository.findByAccountIdAccountId(accountId);}

    public List<Card> findByUserId(long userId) {
        return cardRepository.findByUser_Id_UserId(userId);}
}
