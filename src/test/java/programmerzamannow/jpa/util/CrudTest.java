package programmerzamannow.jpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Customer;

public class CrudTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();

    }

    @Test
    void insert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //Manipulate Database
        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("Farid");
        entityManager.persist(customer);


        entityTransaction.commit();
        entityManager.close();

    }
}
