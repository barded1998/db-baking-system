package db7.bankingsystem.service;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountDto {

    private Long accountId;

    private Integer depositBalance;

    private String accountType;

    private Timestamp makeDate;

    private Long userId;

    private String name;

    private String email;

    private String phoneNumber;
}
