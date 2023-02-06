package db7.bankingsystem.service;

import db7.bankingsystem.domain.UserId;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class UserDto {

    private Long userId;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private Date birthday;

    private String job;

}
