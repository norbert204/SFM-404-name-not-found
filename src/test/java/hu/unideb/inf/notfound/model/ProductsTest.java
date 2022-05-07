package hu.unideb.inf.notfound.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductsTest {

    @Test
    void setProduct_code() {
        Product termek = new Product();
        termek.setProductCode("5");
        assertEquals("5",termek.getProductCode());
    }




    @Test
    void setProduct_name() {
        Product termek = new Product();
        termek.setProductName("cipo");
        assertEquals("cipo",termek.getProductName());

    }



    @Test
    void setQuantity() {
        Product termek = new Product();
        termek.setProductQuantity(10);
        assertEquals(10,termek.getProductQuantity());
    }



    @Test
    void setUnit_price() {
        Product termek = new Product();
        termek.setProductUnitPrice(30);
        assertEquals(30,termek.getProductUnitPrice());
    }


    @Test
    void setCategory() {
        Product termek = new Product();
        termek.setProductCategory("labbeli");
        assertEquals("labbeli",termek.getProductCategory());
    }


    @Test
    void setDescription() {
        Product termek = new Product();
        termek.setDescription("rovid leiras");
        assertEquals("rovid leiras",termek.getDescription());
    }


    @Test
    void setLink() {
        Product termek = new Product();
        termek.setLink("keplink");
        assertEquals("keplink",termek.getLink());
    }
}