package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionTest {

    @Test
    void create() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        Name name = new Name();
        name.setTitle("Mr.");
        name.setFirstName("Randi");
        name.setMiddleName("Kurniawan");
        name.setLastName("Tanjung");

        Member member = new Member();
        member.setEmail("RkoQ5@example.com");
        member.setName(name);

        member.setHobbies(new ArrayList<>());
        member.getHobbies().add("Coding");
        member.getHobbies().add("Reading");

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close(); // Close EntityManagerFactory
    }

    @Test
    void update() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        Member member = entityManager.find(Member.class, "2");
        member.getHobbies().add("Swimming");
        entityManager.merge(member);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updateSkills() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        Member member = entityManager.find(Member.class, "2");
        member.setSkills(new HashMap<>());
        member.getSkills().put("Java", 100);
        member.getSkills().put("PHP", 80);
        member.getSkills().put("JavaScript", 80);
        entityManager.merge(member);


        entityTransaction.commit();
        entityManager.close();
    }
}
