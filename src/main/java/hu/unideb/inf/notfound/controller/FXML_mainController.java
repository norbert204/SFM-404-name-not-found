package hu.unideb.inf.notfound.controller;

import hu.unideb.inf.notfound.model.CsvImporter;
import hu.unideb.inf.notfound.model.ProductDAO;
import hu.unideb.inf.notfound.model.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXML_mainController implements Initializable {

    private static ProductDAO dao;

    @FXML
    private AnchorPane mainPane;

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

    @FXML
    private Button mainCsv;

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

    @FXML
    void loadCsv(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("CSV", "*.csv");
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showOpenDialog((Stage) mainPane.getScene().getWindow());

        try {
            List<Products> products = CsvImporter.CsvImporter(file.getAbsolutePath());
            dao.saveCsvProduct(products);
        }
        catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hiba a csv importálása közben!");
            alert.showAndWait();
        }

    }
}
