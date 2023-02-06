package db7.bankingsystem.service;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CardDto {
    private Long cardId;

    private Timestamp date;

    private Integer max;

    private Timestamp lastUse;

    private String cardType;

    private Long accountId;

    private Long userId;

    private String name;

    private String email;

    private String phoneNumber;
}
