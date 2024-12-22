package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

public class EmbeddedTest {

    @Test
    void embedded() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        Name name = new Name();
        name.setTitle("Mr.");
        name.setFirstName("Farid");
        name.setMiddleName("Abdul");
        name.setLastName("Rahman");

        Member member = new Member();
        member.setName(name);
        member.setEmail("Btawda@example.com");

        entityManager.persist(member); // Use persist instead of merge for new entity

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void embeddedId() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        DepartmentId departmentId = new DepartmentId();
        departmentId.setCompanyId("pnz");
        departmentId.setDepartmentId("tech");

        Department department = new Department();
        department.setId(departmentId);
        department.setName("IT");

        entityManager.persist(department);

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close(); // Close EntityManagerFactory
    }


}
