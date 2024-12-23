package programmerzamannow.jpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.entity.Category;

public class GenerateValueTest {

    @Test
    void generatedValue() {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Category category = new Category();
        category.setName("Avanza");
        category.setDescription("Merupakan mobil bekas");

        entityManager.persist(category);
        Assertions.assertNotNull(category.getId());

        entityTransaction.commit();
        entityManager.close();
    }
}
