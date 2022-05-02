package hu.unideb.inf.notfound.controller;
import hu.unideb.inf.notfound.model.JpaProductDAO;
import hu.unideb.inf.notfound.model.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class FXML_loginController {

    @FXML
    private TextField loginName;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginBtn;

    @FXML
    private Label loginStatus;

    @FXML
    void loginHandler(ActionEvent event) throws IOException {
        loginBtn.setDisable(true);
        loginStatus.setText("Bejelentkezés...");
        try (ProductDAO pDAO = new JpaProductDAO(loginName.getText(), loginPassword.getText()))
        {
            FXML_mainController.setDao(pDAO);
            Parent main_parent = FXMLLoader.load(getClass().getResource("/view/fxml/FXML_main.fxml"));
            Scene main_scene = new Scene(main_parent);
            Stage inventory = (Stage)((Node)event.getSource()).getScene().getWindow();
            inventory.setScene(main_scene);
            inventory.setTitle("Termékek");
            inventory.show();
        }
        catch (Exception e) {
            loginBtn.setDisable(false);
            loginStatus.setText(e.getMessage());
            e.printStackTrace();
        }
    }
}
