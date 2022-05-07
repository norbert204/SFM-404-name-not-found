package hu.unideb.inf.notfound.controller;

import hu.unideb.inf.notfound.model.JpaProductDAO;
import hu.unideb.inf.notfound.model.ProductDAO;
import hu.unideb.inf.notfound.model.Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXML_productController {
    private  FXML_mainController mainController;

    private ProductDAO dao;
    private Products productToModify = null;
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
        try {
            Products product = (productToModify.equals(null)) ? new Products() : productToModify;
            //product.setProduct_code("254");
            product.setProduct_name(product_name.getText());
            product.setCategory(category.getText());
            product.setQuantity(Integer.parseInt(quantity.getText()));
            product.setUnit_price(Integer.parseInt(unit_price.getText()));

            dao.saveProduct(product);
            mainController.updateTable();

            Stage original = (Stage)category.getScene().getWindow();
            original.close();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hiba a termék hozzáadása során.");
            alert.showAndWait();
        }
    }

    public void setDao(ProductDAO p) {
        this.dao = p;
    }

    public void setMainController( FXML_mainController controller  )
    {
        mainController = controller;
    }

    public void setProductToModify(Products product) {
        if (product != null) {
            this.productToModify = product;

            apply.setText("Módosítás");
            category.setText(product.getCategory());
            product_name.setText(product.getProduct_name());
            quantity.setText("" + product.getQuantity());
            unit_price.setText("" + product.getUnit_price());
        }
    }
}
