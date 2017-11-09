package com.dev.betaTransporte;

import com.dev.betaTransporte.dao.GenericoDAO;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import util.BoxInfo;
import util.Message;

public class MainApp extends Application {

    Message msg = new Message();
    BoxInfo box = new BoxInfo();

    @Override
    public void start(Stage stage) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Main.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            GenericoDAO dao = new GenericoDAO();
            EntityManager em = dao.connection;
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setMaximized(true);

            stage.setTitle(msg.message("TituloSistema"));
            // stage.getIcons().add(new Image("../img/IMG_01_LogoBetaTransportePNG.png"));
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            box.BoxInfo(Alert.AlertType.ERROR, msg.message("err.title"), ex.getMessage());
        }

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
