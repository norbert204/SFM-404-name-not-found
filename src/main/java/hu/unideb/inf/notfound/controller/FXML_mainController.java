package hu.unideb.inf.notfound.controller;

import hu.unideb.inf.notfound.model.CsvImporter;
import hu.unideb.inf.notfound.model.Product;
import hu.unideb.inf.notfound.model.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXML_mainController implements Initializable {

    private ProductDAO dao;
    private ObservableList<Product> products;
    private long tableLastClick;
    private int tableLastIndex = -1;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableColumn<Product, Integer> idCol;

    @FXML
    private TableColumn<Product, String> nameCol;

    @FXML
    private TableColumn<Product, Integer> priceCol;

    @FXML
    private TableColumn<Product, Integer> quantityCol;

    @FXML
    private TableColumn<Product, Integer> valueCol;

    @FXML
    private TableColumn<Product, String> categoryCol;

    @FXML
    private TableColumn<Product, String> descriptionCol;

    @FXML
    private TableColumn<Product, String> linkCol;

    @FXML
    private TableView<Product> mainTable;

    @FXML
    private Button updateButton;

    @FXML
    private CheckBox mainInStock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("product_code"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("product_name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("unit_price"));
        valueCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("total_price"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        linkCol.setCellValueFactory(new PropertyValueFactory<Product, String>("link"));

        idCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(1.0/19.0));
        nameCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(2.0/19.0));
        quantityCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(2.0/19.0));
        priceCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(2.0/19.0));
        valueCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(2.0/19.0));
        categoryCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(2.0/19.0));
        descriptionCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(4.0/19.0));
        linkCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(3.885/19.0));


        idCol.setReorderable(false);
        nameCol.setReorderable(false);
        quantityCol.setReorderable(false);
        priceCol.setReorderable(false);
        valueCol.setReorderable(false);
        categoryCol.setReorderable(false);
        descriptionCol.setReorderable(false);
        linkCol.setReorderable(false);
    }

    @FXML
    void addItem(ActionEvent event) {
        openProductWindow(null);
    }

    void openProductWindow(Product toModify) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXML_product.fxml"));
            Parent productBox = (Parent) loader.load();
            FXML_productController controller = loader.getController();
            controller.setDao(this.dao);
            controller.setProductToModify(toModify);
            controller.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Termék felvétele");
            stage.setResizable(false);
            stage.setScene(new Scene(productBox));
            mainTable.getSelectionModel().setCellSelectionEnabled(true);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void removeItem(ActionEvent event) {
        int index = mainTable.getSelectionModel().getSelectedIndex();
        if (index > -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Biztos törli a kijelölt elemet?");
            var result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                dao.deleteProduct(products.get(index));
                updateTable();
            }
        }
    }

    public void setDao(ProductDAO p) {
        this.dao = p;
        updateTable();
    }

    public ProductDAO getDao() {
        return dao;
    }

    public void updateTable() {
        if (mainInStock.isSelected()) {
            products = FXCollections.observableList(dao.getProducts().stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList()));
        }
        else {
            products = FXCollections.observableList(dao.getProducts());
        }
        mainTable.setItems(products);
        mainTable.refresh();
    }

    @FXML
    void mainInStockCheck(ActionEvent event) {
        updateTable();
    }
    @FXML
    void loadCsv(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("CSV", "*.csv");
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showOpenDialog((Stage) mainPane.getScene().getWindow());

        try {
            List<Product> products = CsvImporter.CsvImporter(file.getAbsolutePath());
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

        updateTable();
    }

    @FXML
    void mainTableSelect(MouseEvent event)
    {
        int index = mainTable.getSelectionModel().getSelectedIndex();
//        if (System.currentTimeMillis() - tableLastClick < 2000 && index == tableLastIndex) {
//            if (index > -1) {
//                openProductWindow(products.get(index));
//            }
//        }
//        else {
//            tableLastClick = System.currentTimeMillis();
//            tableLastIndex = index;
//        }

        if (event.getClickCount() == 2 && index == tableLastIndex)
        {
            openProductWindow(products.get(index));
        }else{
            tableLastIndex = index;
        }
    }
}
