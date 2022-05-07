package hu.unideb.inf.notfound.controller;

import hu.unideb.inf.notfound.model.JpaProductDAO;
import hu.unideb.inf.notfound.model.ProductDAO;
import hu.unideb.inf.notfound.model.Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FXML_productController {
    @FXML
    private Button apply;

    @FXML
    private TextField category;

    @FXML
    private TextField product_name;

    @FXML
    private TextField quantity;

    @FXML
    private TextField unit_price;
    private FXML_productController loginName;

    @FXML
    void addProduct(ActionEvent event) {


        Products product = new Products();
        product.setProduct_name(product_name.getText());
        product.setCategory(category.getText());
        product.setQuantity(Integer.parseInt(quantity.getText()));
        product.setUnit_price(Integer.parseInt(unit_price.getText()));
    }
}
