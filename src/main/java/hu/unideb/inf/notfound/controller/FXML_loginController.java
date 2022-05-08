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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.hibernate.service.spi.ServiceException;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXML_main.fxml"));
            Parent mainParent = loader.load();

            FXML_mainController mainController = loader.getController();
            mainController.setDao(pDAO);

            Scene mainScene = new Scene(mainParent);
            Stage stage = new Stage();
            stage.setTitle("Termékek");
            stage.setScene(mainScene);
            stage.show();

            Stage original = (Stage)loginName.getScene().getWindow();
            original.close();
        }
        catch (Exception e) {
            loginBtn.setDisable(false);
            loginStatus.setText("Hibás felhasználónév és/vagy jelszó!");
            e.printStackTrace();
        }
    }
}
