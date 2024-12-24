package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import java.time.LocalDateTime;

public class ManagedEntityTest {

    @Test
    void insertEntity() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId("Nokia 3310");
        brand.setName("Nokia 3310");
        brand.setDescription("new Nokia");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(brand);

        brand.setName("Nokia 3310 Pro");


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void findEntity() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //managed Entity
        Brand brand = entityManager.find(Brand.class, "Apple");
        brand.setDescription("Iphone Indonesia");


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void detach() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //managed Entity
        Brand brand = entityManager.find(Brand.class, "Apple");
        entityManager.detach(brand);
        brand.setDescription("Iphone Indonesia");


        entityTransaction.commit();
        entityManager.close();

        //ketika manager entity di selesai commit atau  di close maka
        //statusnya berubah menjadi unmanaged
    }
}
