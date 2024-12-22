package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import java.time.LocalDateTime;
import java.util.Calendar;

public class UpdateDateTest {

    @Test
    void dateType(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //Manipulate Database
        Category category = entityManager.find(Category.class, 1L);

        category.setCreatedAt(Calendar.getInstance());
        category.setUpdatedAt(LocalDateTime.now());

        entityManager.merge(category);

        entityTransaction.commit();
        entityManager.close();
    }
}
