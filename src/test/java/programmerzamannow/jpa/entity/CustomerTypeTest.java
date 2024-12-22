package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import static org.junit.jupiter.api.Assertions.*;

class EnumTypeTest {

    @Test
    void createTest() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("4");
        customer.setName("Ronaldi");
        customer.setPrimaryEmail("Btawda@example.com");
        customer.setAge((byte) 23);
        customer.setMarried(false);
        customer.setType(CustomerType.PREMIUM);
        customer.setFullName("Ronaldi Saputra"); //column transient

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}