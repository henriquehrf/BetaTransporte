package com.dev.betaTransporte;

import com.dev.betaTransporteDAO.GenericoDAO;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import util.GetMessage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Main.fxml"),ResourceBundle.getBundle("docs/i18N_pt_BR"));
        
        GetMessage msg = new GetMessage();
        
        GenericoDAO dao =new GenericoDAO();
        
        EntityManager em =   dao.getEM();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setMaximized(true);
       
        stage.setTitle(msg.getMessage("TituloSistema"));
        stage.setScene(scene);
        stage.show();
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
