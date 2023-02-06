package db7.bankingsystem.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
public class Card {
    @Id
    private Long cardId;

    private Timestamp date;

    private Integer max;

    private Timestamp lastUse;

    private String cardType;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account accountId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "email"),
        @JoinColumn(name = "name"),
        @JoinColumn(name = "phoneNumber"),
        @JoinColumn(name = "userId")
    })
    private User user;
}
