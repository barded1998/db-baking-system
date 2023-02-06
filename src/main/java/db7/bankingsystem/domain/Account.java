package db7.bankingsystem.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
public class Account {
    @Id
    private Long accountId;

    private Integer depositBalance;

    private String accountType;

    private Timestamp makeDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "email"),
        @JoinColumn(name = "name"),
        @JoinColumn(name = "phoneNumber"),
        @JoinColumn(name = "userId")
    })
    private User user;

}
