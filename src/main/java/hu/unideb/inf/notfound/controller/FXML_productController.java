package hu.unideb.inf.notfound.controller;

import hu.unideb.inf.notfound.model.Product;
import hu.unideb.inf.notfound.model.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.security.MessageDigest;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class FXML_productController implements Initializable {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };
        productUnitPrice.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        productUnitPrice.setText("");
    }
    @FXML
    void addProduct(ActionEvent event) {
        try {
            if (productToModify == null && dao.getProducts().stream().filter(p -> p.getProduct_code().equals(productCode.getText())).count() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Termék ezzel a kóddal már létezik!");
                alert.showAndWait();
                return;
            }
            if (productCode.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Adjon meg a terméknek kódot!");
                alert.showAndWait();
                return;
            }
            Product product = (productToModify == null) ? new Product() : productToModify;
            product.setProductCode(productCode.getText());
            product.setProductName(productName.getText());
            product.setCategory(productCategory.getText());
            product.setQuantity(Integer.parseInt(productQuantity.getText()));
            product.setUnit_price(Integer.parseInt(productUnitPrice.getText()));
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

            productCode.setText(product.getProduct_code());
            productName.setText(product.getProduct_name());
            productCategory.setText(product.getCategory());
            productQuantity.setText("" + product.getQuantity());
            productUnitPrice.setText("" + product.getUnit_price());
            productTotalPrice.setText(""+product.getTotal_price());
            productDescription.setText(product.getDescription());
            productLink.setText(product.getLink());

            apply.setText("Módosítás");
        }
    }
}
