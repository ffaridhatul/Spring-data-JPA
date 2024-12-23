package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.List;

public class QueryLanguageTest {

    @Test
    void join() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //managed Entity
        for(int i = 0; i < 10; i++) {
            Brand brand = new Brand();
            brand.setId("Brand " + i);
            brand.setName("Brand " + i);
            entityManager.persist(brand);
        }

        entityTransaction.commit();
        entityManager.close();


    }

    @Test
    void Test() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //managed Entity
        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.id", Brand.class);
        query.setMaxResults(5);

        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();


    }

    @Test
    void namedQuery() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //managed Entity
        TypedQuery<Brand> query = entityManager.createNamedQuery("Brand.findAllByName", Brand.class);
        query.setParameter("name", "samsung");

        List<Brand> brands = query.getResultList();
        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();


    }
}
