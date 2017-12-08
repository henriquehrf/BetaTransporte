package com.dev.betaTransporte;

import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.BoxInfo;
import util.Message;

public class MainApp extends Application {

    Message msg = new Message();
    BoxInfo box = new BoxInfo();
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            Scene InitialScene = new Scene(root);
            InitialScene.getStylesheets().add("/styles/Styles.css");
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);

            stage.setTitle(msg.message("TituloSistema"));
            stage.getIcons().add(new Image("./img/IMG_01_LogoBetaTransportePNG.PNG"));
            stage.setScene(InitialScene);

//            scene.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
//                public void handle(WindowEvent ev) {
//                    dao.connection.close();
//                }
//            });
            primaryStage = stage;
            primaryStage.show();
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
