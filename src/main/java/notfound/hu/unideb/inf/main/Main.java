package notfound.hu.unideb.inf.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/fxml/FXML_login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        System.out.println("Hello world");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
