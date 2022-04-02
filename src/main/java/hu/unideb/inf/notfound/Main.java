package hu.unideb.inf.notfound;

import hu.unideb.inf.notfound.model.JpaProductDAO;
import hu.unideb.inf.notfound.model.ProductDAO;
import hu.unideb.inf.notfound.model.Products;
import javafx.application.Application;
import static hu.unideb.inf.notfound.model.SqlManager.connect;

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

    public static void main(String[] args) throws Exception {

        launch(args);
        //connect(); ez feleslegesnek tűnik
        try (ProductDAO pDAO = new JpaProductDAO("username@sfmnotfound","Password1");)
        {


            Products p = new Products();
            p.setProduct_code(15);
            p.setProduct_name("TestJPA");
            p.setQuantity(2);
            p.setUnit_price(1000);
            p.setTotal_price(2000);
            p.setCategory("test");
            p.setDescription("test");
            p.setLink("test");
            pDAO.saveProduct(p);

            //aDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
