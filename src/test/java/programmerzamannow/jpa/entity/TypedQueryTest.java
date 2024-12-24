package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import java.util.List;

public class TypedQueryTest {

    @Test
    void createSelectQuery() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //managed Entity
        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }
        entityTransaction.commit();
        entityManager.close();


    }

    @Test
    void whereClause() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //managed Entity
        TypedQuery<Member> query = entityManager.createQuery("select m from Member m where m.name.firstName = :firstName" +
                " and m.name.lastName = :lastName", Member.class);


        query.setParameter("firstName", "Farid");
        query.setParameter("lastName", "Rahman");
        List<Member> members = query.getResultList();

        for (Member member : members) {
            System.out.println(member.getId() + " : " + member.getFullName());
        }

        entityTransaction.commit();
        entityManager.close();


    }

    @Test
    void join() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //managed Entity
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join p.brand b where b.name = :brandName order by p.name DESC", Product.class);
        query.setParameter("brandName", "samsung");
        List<Product> products = query.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + " : " + product.getName());
        }
        entityTransaction.commit();
        entityManager.close();


    }
}
