package org.ceva;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.ceva.entities.Product;
import org.ceva.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        EntityManager em =  null;
// Persistence.createEntityManagerFactory("my-persistence-unit")
        try (EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>())) {

            // Entity Manager representa el context de la app. Nos permite controlar las transacciones
            em = emf.createEntityManager();

            em.getTransaction().begin();
            Product p = new Product();
            p.setId(2L);
            p.setName("HP Probook");
            em.persist(p); // add al contexto -> esto no es un insert
            em.getTransaction().commit();
        }
    }
}
