package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

public class JoinTableTest {

    @Test
    void joinedTableInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentsGopay gopay = new PaymentsGopay();
        gopay.setGopayId("0899999999");
        gopay.setAmount(100_000L);
        entityManager.persist(gopay);

        PaymentCreditCard creditCard = new PaymentCreditCard();
        creditCard.setMaskedCard("4555-9999");
        creditCard.setAmount(500_000L);
        creditCard.setBank("BCA");
        entityManager.persist(creditCard);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentsGopay gopay = entityManager.find(PaymentsGopay.class, "1");
        PaymentCreditCard creditCard = entityManager.find(PaymentCreditCard.class, "1");


        entityTransaction.commit();
        entityManager.close();
    }
}
