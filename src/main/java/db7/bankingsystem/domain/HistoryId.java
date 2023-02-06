package db7.bankingsystem.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Embeddable
public class HistoryId implements Serializable {
    private Long historyId;
    private Long accountId;
    private Timestamp useDate;

    public HistoryId() {
    }

    public HistoryId(Long historyId, Long accountId, Timestamp useDate) {
        this.historyId = historyId;
        this.accountId = accountId;
        this.useDate = useDate;
    }
}
