package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import java.time.LocalDateTime;

public class TablePerTableTest {

    @Test
    void insertTest() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = new Transaction();
        transaction.setId("t1");
        transaction.setBalance(1_000_000L);
        transaction.setCratedAt(LocalDateTime.now());
        entityManager.persist(transaction);

        TransactionDebit transactionDebit = new TransactionDebit();
        transactionDebit.setId("t2");
        transactionDebit.setCratedAt(LocalDateTime.now());
        transactionDebit.setBalance(2_000_000L);
        transactionDebit.setDebitAmount(1_000_000L);
        entityManager.persist(transactionDebit);

        TransactionCredit transactionCredit = new TransactionCredit();
        transactionCredit.setId("t3");
        transactionCredit.setCratedAt(LocalDateTime.now());
        transactionCredit.setBalance(1_000_000L);
        transactionCredit.setCreditAmount(1_000_000L);
        entityManager.persist(transactionCredit);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void findToChildTest() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TransactionDebit transactionDebit = entityManager.find(TransactionDebit.class, "t2");

        TransactionCredit transactionCredit = entityManager.find(TransactionCredit.class, "t3");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void findToChildParent() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = entityManager.find(Transaction.class, "t1");

        entityTransaction.commit();
        entityManager.close();
    }
}
