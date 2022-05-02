package hu.unideb.inf.notfound;

import hu.unideb.inf.notfound.model.JpaProductDAO;
import hu.unideb.inf.notfound.model.ProductDAO;
import hu.unideb.inf.notfound.model.Products;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/fxml/FXML_login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);


    }

}
