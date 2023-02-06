package db7.bankingsystem.service;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class HistoryDto {

    private Long historyId;

    private Long accountId;

    private Timestamp useDate;

    private String depositType;

    private String content;

    private Integer money;

}
