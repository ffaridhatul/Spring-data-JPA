package programmerzamannow.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Embedded
    private Name name;

    private String email;

    @ElementCollection
    @CollectionTable(name = "hobbies", joinColumns =
    @JoinColumn(name = "member_id",
    referencedColumnName = "id"))
    @Column(name = "name")
    private List<String> hobbies;

    @ElementCollection
    @CollectionTable(name = "skills", joinColumns =
    @JoinColumn(name = "member_id",
    referencedColumnName = "id"))
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, Integer> skills;

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (name.getTitle() != null) {
            fullName.append(name.getTitle()).append(" ");
        }
        if (name.getFirstName() != null) {
            fullName.append(name.getFirstName()).append(" ");
        }
        if (name.getMiddleName() != null) {
            fullName.append(name.getMiddleName()).append(" ");
        }
        if (name.getLastName() != null) {
            fullName.append(name.getLastName());
        }
        return fullName.toString().trim();
    }

}
