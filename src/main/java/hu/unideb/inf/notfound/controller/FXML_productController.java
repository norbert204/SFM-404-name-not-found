package hu.unideb.inf.notfound.controller;

import hu.unideb.inf.notfound.model.Product;
import hu.unideb.inf.notfound.model.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXML_productController {
    private  FXML_mainController mainController;

    private ProductDAO dao;
    private Product productToModify = null;

    @FXML
    private TextField productCode;

    @FXML
    private TextField productName;

    @FXML
    private TextField productCategory;

    @FXML
    private TextField productUnitPrice;

    @FXML
    private TextField productQuantity;

    @FXML
    private TextField productTotalPrice;

    @FXML
    private TextField productDescription;

    @FXML
    private TextField productLink;

    @FXML
    private Button apply;

    @FXML
    void addProduct(ActionEvent event) {
        try {
            Product product = (productToModify == null) ? new Product() : productToModify;
            product.setProductCode("125");
            product.setProductName(productName.getText());
            product.setProductCategory(productCategory.getText());
            product.setProductQuantity(Integer.parseInt(productQuantity.getText()));
            product.setProductUnitPrice(Integer.parseInt(productUnitPrice.getText()));
            product.setTotal_price(product.getTotal_price());
            product.setDescription(productDescription.getText());
            product.setLink(productLink.getText());

            dao.saveProduct(product);
            mainController.updateTable();

            Stage original = (Stage)productCategory.getScene().getWindow();
            original.close();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hiba a termék hozzáadása során.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void setDao(ProductDAO p) {
        this.dao = p;
    }

    public void setMainController( FXML_mainController controller  )
    {
        mainController = controller;
    }

    public void setProductToModify(Product product) {
        if (product != null) {
            this.productToModify = product;

            productName.setText(product.getProductName());
            productCategory.setText(product.getProductCategory());
            productQuantity.setText("" + product.getProductQuantity());
            productUnitPrice.setText("" + product.getProductUnitPrice());
            productTotalPrice.setText(""+product.getTotal_price());
            productDescription.setText(product.getDescription());
            productLink.setText(product.getLink());

            apply.setText("Módosítás");
        }
    }
}
