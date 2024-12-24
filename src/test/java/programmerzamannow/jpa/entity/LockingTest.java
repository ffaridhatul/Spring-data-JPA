package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import java.time.LocalDateTime;

public class LockingTest {

    @Test //optimistic
    void createTest() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId("Apple");
        brand.setName("Iphone 11");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        brand.setDescription("Leading electronics brand");
        entityManager.persist(brand);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updatedTestVer1() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "Apple");
        brand.setName("Iphone 12 Pro");
        brand.setUpdatedAt(LocalDateTime.now());

        entityManager.persist(brand);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updatedTestVer2() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "Apple");
        brand.setName("Iphone 12");
        brand.setUpdatedAt(LocalDateTime.now());

        Thread.sleep(10 * 1000);

        entityManager.persist(brand);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void pessimisticLockingDemo1() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "Apple", LockModeType.PESSIMISTIC_WRITE);
        brand.setName("Iphone 12");
        brand.setUpdatedAt(LocalDateTime.now());

        Thread.sleep(10 * 1000);

        entityManager.persist(brand);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void pessimisticLockingDemo2() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "Apple", LockModeType.PESSIMISTIC_WRITE);
        brand.setName("Iphone 12");
        brand.setUpdatedAt(LocalDateTime.now());


        entityManager.persist(brand);


        entityTransaction.commit();
        entityManager.close();
    }
}
