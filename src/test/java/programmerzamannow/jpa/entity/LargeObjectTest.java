package programmerzamannow.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import programmerzamannow.jpa.util.JpaUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class LargeObjectTest {

    @Test
    void largeObject() throws IOException {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // Manipulasi Database
        Images images = new Images();
        images.setName("Contoh image 2");
        images.setDescription("Contoh deskripsi image 2");

        // Membaca gambar dari resources menggunakan InputStream
        try (InputStream inputStream = getClass().getResourceAsStream("/images/sample.jpg")) {
            if (inputStream == null) {
                throw new IOException("Resource tidak ditemukan: /images/sample.jpg");
            }
            byte[] bytes = inputStream.readAllBytes();
            images.setImage(bytes);
        }

        entityManager.persist(images);
        entityTransaction.commit();
        entityManager.close();
    }
}
