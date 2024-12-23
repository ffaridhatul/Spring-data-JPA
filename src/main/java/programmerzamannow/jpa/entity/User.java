package programmerzamannow.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private String id;

    private String name;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private Credential credential;

    @OneToOne(mappedBy = "userId")
    private Wallet wallet;

    @ManyToMany
    @JoinTable(
            name = "users_like_products",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<Product> likes;

}
