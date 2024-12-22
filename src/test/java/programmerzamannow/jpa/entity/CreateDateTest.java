package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import java.time.LocalDateTime;
import java.util.Calendar;

public class CreateDateTest {

    @Test
    void dateType(){
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //Manipulate Database
        Category category = new Category();
        category.setName("Toyota Supra");
        category.setDescription("Merupakan mobil bekas");
        category.setCreatedAt(Calendar.getInstance());
        category.setUpdatedAt(LocalDateTime.now());

        entityManager.persist(category);

        entityTransaction.commit();
        entityManager.close();
    }

}
