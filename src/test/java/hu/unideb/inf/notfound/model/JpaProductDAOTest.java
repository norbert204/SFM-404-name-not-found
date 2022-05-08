package hu.unideb.inf.notfound.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class JpaProductDAOTest {

    private ProductDAO ProductDAOMock;
    private Product product ;


    @BeforeEach
    void setUp() {
        ProductDAOMock = mock(ProductDAO.class);
        product = new Product();
    }

    @Test
    void checkSaveProduct() {

        List<Product> products = new ArrayList<>();
        product.setProductCode("5");
        product.setProductName("termék");
        product.setUnit_price(200);
        product.setQuantity(1);
        product.setCategory("kategória");
        product.setDescription("leírás");
        product.setLink("link");

        ProductDAOMock.saveProduct(product);
        products.add(product);

        assert ProductDAOMock.getProducts().containsAll(products);

    }


    @Test
    void checkDeleteProduct() {
    }

    @Test
    void checkGetProducts() {




    }

    @Test
    void chechkClose() {
    }
}