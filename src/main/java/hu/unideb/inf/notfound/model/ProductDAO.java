package hu.unideb.inf.notfound.model;

import java.util.List;

public interface ProductDAO extends AutoCloseable{
    void saveProduct(Product p);
    void saveCsvProduct(List<Product> p);
    void deleteProduct(Product p);
    //void updateProduct(Product p); ez most nem kell
    List<Product> getProducts();
}