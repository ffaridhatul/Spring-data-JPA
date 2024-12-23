package programmerzamannow.jpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Customer;

public class DataTypeTest {

    @Test
    void dataType() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //Manipulate Database
        Customer customer = entityManager.find(Customer.class, "2"); // cari customer dengan id 2
        customer.setMarried(false); // ubah nilai married menjadi true

        entityManager.merge(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
