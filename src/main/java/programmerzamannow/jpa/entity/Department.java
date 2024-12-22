package programmerzamannow.jpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "departments")
public class Department {

    @EmbeddedId
    private DepartmentId id;

    private String name;
}
