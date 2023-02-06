package db7.bankingsystem.repository;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class HistorySearchCond {
    private Long id;
    private Timestamp useDate;
    private Long accountId;
}
