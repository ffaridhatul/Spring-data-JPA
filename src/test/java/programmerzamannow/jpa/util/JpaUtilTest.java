package programmerzamannow.jpa.util;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaUtilTest {

    @Test
    void create() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        Assertions.assertNotNull(entityManagerFactory);
    }
}