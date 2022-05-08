package hu.unideb.inf.notfound.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
        properties.put("javax.persistence.jdbc.url", "jdbc:sqlserver://sfmnotfound.database.windows.net:1433;database=SFM;user=username@sfmnotfound;password=Password1;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;characterEncoding=utf8");
        properties.put("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put("javax.persistence.jdbc.user", username);
        properties.put("javax.persistence.jdbc.password", password);
        entityFactory = Persistence.createEntityManagerFactory("notfound-jpa", properties);
        entityManager = entityFactory.createEntityManager();
    }

    @Override
    public void saveProduct(Product p) {
        entityManager.getTransaction().begin();
        entityManager.merge(p);
        entityManager.getTransaction().commit();
    }
    @Override
    public void saveCsvProduct(List <Product> termeklista) {
        entityManager.getTransaction().begin();

        for (Product product:termeklista) {
            entityManager.persist(product);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteProduct(Product p) {
        entityManager.getTransaction().begin();
        entityManager.remove(p);
        entityManager.getTransaction().commit();
    }

    /* @Override
     public void updateProduct(Product p) {
         entityManager.getTransaction().begin();
         entityManager.persist(p);                       // ez most nem kell
         entityManager.getTransaction().commit();
     }
 */
    @Override
    public List<Product> getProducts() {

        TypedQuery<Product> query = entityManager.createQuery(
                "SELECT p FROM Product p", Product.class);
        List<Product> products = query.getResultList();
        return products;
    }

    @Override
    public void close() throws Exception {
        //entityManager.close();
        //entityFactory.close();


    }
}