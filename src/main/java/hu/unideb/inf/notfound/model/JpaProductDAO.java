package hu.unideb.inf.notfound.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JpaProductDAO implements ProductDAO{

    private EntityManagerFactory entityFactory;
    private EntityManager entityManager;

    public JpaProductDAO(String username, String password) {
        /*
        Login felületen megadott adatok miatt nem lehet teljesen hasznosítani a persistence.xml-t.
        A felhasználó által megadott adatokat egy HashMapben kell átadni
         */
        Map<String, Object> properties = new HashMap<>();
        //properties.put("javax.persistence.jdbc.url", "");
        //properties.put("javax.persistence.jdbc.driver", "");
        properties.put("javax.persistence.jdbc.user", username);
        properties.put("javax.persistence.jdbc.password", password);
        entityFactory = Persistence.createEntityManagerFactory("notfound-jpa", properties);
        entityManager = entityFactory.createEntityManager();
    }

    @Override
    public void saveProduct(Products p) {

    }

    @Override
    public void deleteProduct(Products p) {

    }

    @Override
    public void updateProduct(Products p) {

    }

    @Override
    public List<Products> getProducts() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}