package programmerzamannow.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    private String id;

    private String name;

    @Column(name = "primary_email")
    private String primaryEmail;

    private Byte age;

    private Boolean married;

    @Enumerated(EnumType.STRING)
    private CustomerType type;

    @Transient
    private String fullName;
}
