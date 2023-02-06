package db7.bankingsystem.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@Embeddable
public class UserId implements Serializable {
        private Long userId;
        private String name;
        private String email;
        private String phoneNumber;

        public UserId() {
        }

        public UserId(Long userId, String name, String email, String phoneNumber) {
                this.userId = userId;
                this.name = name;
                this.email = email;
                this.phoneNumber = phoneNumber;
        }
}
