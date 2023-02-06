package db7.bankingsystem.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
public class User {

    @EmbeddedId
    private UserId id;

    private String address;

    private Date birthday;

    private String job;

    public User() {
    }
}
