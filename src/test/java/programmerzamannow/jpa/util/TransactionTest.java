package programmerzamannow.jpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    @Test
    void transaction() {
        EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        Assertions.assertNotNull(et);

        try {
            et.begin();
            //Manipulasi Database
            et.commit();
        }catch (Throwable throwable) {
            et.rollback();
        }


        em.close();
    }
}
