package db7.bankingsystem.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
public class History {

    @EmbeddedId
    private HistoryId id;

    private String depositType;

    private String content;

    private Integer money;

    @MapsId("accountId")
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    public History() {
    }

    public History(HistoryId id, Account account) {
        this.id = id;
        this.account = account;
    }
}
