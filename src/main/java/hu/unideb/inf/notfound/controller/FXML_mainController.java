package hu.unideb.inf.notfound.controller;

import hu.unideb.inf.notfound.model.CsvImporter;
import hu.unideb.inf.notfound.model.JpaProductDAO;
import hu.unideb.inf.notfound.model.ProductDAO;
import hu.unideb.inf.notfound.model.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class FXML_mainController implements Initializable {

    private ProductDAO dao;
    private ObservableList<Products> products;
    private long tableLastClick;
    private int tableLastIndex = -1;

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
    }

    @FXML
    void addItem(ActionEvent event) {
        openProductWindow(null);
    }

    void openProductWindow(Products toModify) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXML_product.fxml"));
            Parent productBox = (Parent) loader.load();
            FXML_productController controller = loader.getController();
            controller.setDao(this.dao);
            controller.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Termék felvétele");
            stage.setScene(new Scene(productBox));
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void removeItem(ActionEvent event) {

    }

    public void setDao(ProductDAO p) {
        this.dao = p;
        updateTable();
    }

    public ProductDAO getDao() {
        return dao;
    }

    public void updateTable() {
        products = FXCollections.observableList(dao.getProducts());
        mainTable.setItems(products);
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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sikeres importálás!");
            alert.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hiba a csv importálása közben!");
            alert.showAndWait();
        }

    }

    @FXML
    void mainTableSelect(MouseEvent event)
    {
        int index = mainTable.getSelectionModel().getSelectedIndex();
        if (System.currentTimeMillis() - tableLastClick < 2000 && index == tableLastIndex) {
            if (index > -1) {
                openProductWindow(products.get(index));
            }
        }
        else {
            tableLastClick = System.currentTimeMillis();
            tableLastIndex = index;
        }
    }
}
