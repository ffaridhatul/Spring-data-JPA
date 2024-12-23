package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

public class OneToManyTest {

    @Test
    void oneToManyInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Manipulate Database
            Brand brand = new Brand();
            brand.setId("samsung");
            brand.setName("Samsung");
            brand.setDescription("Leading electronics brand");

            Product product1 = new Product();
            product1.setId("p1");
            product1.setName("Samsung Galaxy 1");
            product1.setBrand(brand);
            product1.setPrice(1_000_000L);

            Product product2 = new Product();
            product2.setId("p2");
            product2.setName("Samsung Galaxy 2");
            product2.setBrand(brand);
            product2.setPrice(2_000_000L);

            // Add products to the brand
            brand.getProducts().add(product1);
            brand.getProducts().add(product2);

            // Persist brand (cascade ensures products are saved)
            entityManager.persist(brand);

            transaction.commit();
        } catch (Exception e) {
            // Jika terjadi exception, rollback transaksi
            if (transaction.isActive()) {
                transaction.rollback();
            }

            // Cetak stack trace untuk detail kesalahan
            e.printStackTrace();
            throw e; // Rethrow exception agar test tetap ditandai gagal
        } finally {
            // Pastikan EntityManager ditutup
            entityManager.close();
        }
    }
}
