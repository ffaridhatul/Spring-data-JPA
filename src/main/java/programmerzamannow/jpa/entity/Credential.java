package programmerzamannow.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "credentials")
@Data
public class Credential {

    @Id
    private String id;

    private String email;

    private String password;

    @OneToOne(mappedBy = "credential")
    private User user;
}
