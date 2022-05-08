package hu.unideb.inf.notfound.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void setProduct_code() {
        Product termek = new Product();
        termek.setProductCode("5");
        assertEquals("5",termek.getProduct_code());
    }




    @Test
    void setProduct_name() {
        Product termek = new Product();
        termek.setProductName("cipo");
        assertEquals("cipo",termek.getProduct_name());

    }



    @Test
    void setQuantity() {
        Product termek = new Product();
        termek.setQuantity(10);
        assertEquals(10,termek.getQuantity());
    }



    @Test
    void setUnit_price() {
        Product termek = new Product();
        termek.setUnit_price(30);
        assertEquals(30,termek.getUnit_price());
    }


    @Test
    void setCategory() {
        Product termek = new Product();
        termek.setCategory("labbeli");
        assertEquals("labbeli",termek.getCategory());
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