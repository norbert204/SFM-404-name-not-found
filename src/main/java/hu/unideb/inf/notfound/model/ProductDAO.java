package hu.unideb.inf.notfound.model;

import java.util.List;

public interface ProductDAO extends AutoCloseable{
    void saveProduct(Products p);
    void saveCsvProduct(List<Products> p);
    void deleteProduct(Products p);
    //void updateProduct(Products p); ez most nem kell
    List<Products> getProducts();
}