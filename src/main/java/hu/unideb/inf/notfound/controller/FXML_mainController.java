package hu.unideb.inf.notfound.controller;

import hu.unideb.inf.notfound.model.ProductDAO;
import hu.unideb.inf.notfound.model.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class FXML_mainController implements Initializable {

    private static ProductDAO dao;

    @FXML
    private TableColumn<Products, String> categoryCol;

    @FXML
    private TableColumn<Products, String> descriptionCol;

    @FXML
    private TableColumn<Products, Integer> idCol;

    @FXML
    private TableColumn<Products, String> linkCol;

    @FXML
    private TableColumn<Products, String> nameCol;

    @FXML
    private TableColumn<Products, Integer> priceCol;

    @FXML
    private TableColumn<Products, Integer> quantityCol;

    @FXML
    private TableColumn<Products, Integer> valueCol;

    @FXML
    private TableView<Products> mainTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("product_code"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Products, String>("product_name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("unit_price"));
        valueCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("total_price"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Products, String>("category"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Products, String>("description"));
        linkCol.setCellValueFactory(new PropertyValueFactory<Products, String>("link"));

        // TODO: ezt a 2 sort átrakni egy frissítés metódusba
        ObservableList<Products> products = FXCollections.observableList(dao.getProducts());
        mainTable.setItems(products);
    }

    @FXML
    void addItem(ActionEvent event) {

    }

    @FXML
    void removeItem(ActionEvent event) {

    }

    public static void setDao(ProductDAO p) {
        dao = p;
    }
}
