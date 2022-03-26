package notfound.hu.unideb.inf.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FXML_loginController {

    @FXML
    private Button loginButton;

    @FXML
    private Label welcomeText;

    @FXML
    void loginHandler(ActionEvent event) throws IOException {
        Parent main_parent = FXMLLoader.load(getClass().getResource("/view/fxml/FXML_main.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage inventory = (Stage)((Node)event.getSource()).getScene().getWindow();
        inventory.setScene(main_scene);
        inventory.show();
    }

}