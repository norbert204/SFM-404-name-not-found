package hu.unideb.inf.notfound.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductsTest {

    @Test
    void setProduct_code() {
        Products termek = new Products();
        termek.setProduct_code("5");
        assertEquals("5",termek.getProduct_code());
    }




    @Test
    void setProduct_name() {
        Products termek = new Products();
        termek.setProduct_name("cipo");
        assertEquals("cipo",termek.getProduct_name());

    }



    @Test
    void setQuantity() {
        Products termek = new Products();
        termek.setQuantity(10);
        assertEquals(10,termek.getQuantity());
    }



    @Test
    void setUnit_price() {
        Products termek = new Products();
        termek.setUnit_price(30);
        assertEquals(30,termek.getUnit_price());
    }


    @Test
    void setCategory() {
        Products termek = new Products();
        termek.setCategory("labbeli");
        assertEquals("labbeli",termek.getCategory());
    }


    @Test
    void setDescription() {
        Products termek = new Products();
        termek.setDescription("rovid leiras");
        assertEquals("rovid leiras",termek.getDescription());
    }


    @Test
    void setLink() {
        Products termek = new Products();
        termek.setLink("keplink");
        assertEquals("keplink",termek.getLink());
    }
}