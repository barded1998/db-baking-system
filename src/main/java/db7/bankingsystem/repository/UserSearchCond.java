package db7.bankingsystem.repository;

import lombok.Data;

import javax.persistence.Id;
import java.sql.Timestamp;

@Data
public class UserSearchCond {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}

