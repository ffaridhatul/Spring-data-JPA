package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.HashMap;
import java.util.HashSet;

public class EntityRelationshipTest {

    @Test
    void oneToOnePersist() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        Credential credential = new Credential();
        credential.setId("fh");
        credential.setEmail("farid.zikri@example.com");
        credential.setPassword("password");
        entityManager.persist(credential);

        User user = new User();
        user.setId("fh");
        user.setName("Farid");

        entityManager.persist(user);

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void oneToOneFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        User user = entityManager.find(User.class, "fh");
        Assertions.assertNotNull(user.getCredential());
        Assertions.assertNotNull(user.getWallet());

        Assertions.assertEquals("farid.zikri@example.com", user.getCredential().getEmail());
        Assertions.assertEquals("password", user.getCredential().getPassword());
        Assertions.assertEquals(1_000_000L, user.getWallet().getBalance());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneJoinColumn() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        User user = entityManager.find(User.class, "fh");

        Wallet wallet = new Wallet();
        wallet.setUserId(user);
        wallet.setBalance(1_000_000L);
        entityManager.persist(wallet);

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void manyToManyJoinColumn() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        User user = entityManager.find(User.class, "fh");
        user.setLikes(new HashSet<>());

        Product product1 = entityManager.find(Product.class, "p1");
        Product product2 = entityManager.find(Product.class, "p2");
        user.getLikes().add(product1);
        user.getLikes().add(product2);

        entityManager.merge(user);

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void manyToManyUpdate() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        User user = entityManager.find(User.class, "fh");
        Product product = null;
        for (Product p : user.getLikes()) {
            product = p;
            break;
        }

        user.getLikes().remove(product);
        entityManager.merge(user);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void fetch() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulate Database
        Product product = entityManager.find(Product.class, "p1");
        Brand brand = product.getBrand();
        Assertions.assertNotNull(brand);

        entityTransaction.commit();
        entityManager.close();
    }
}
