/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.dao.GenericoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import util.BoxInfo;
import util.Message;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Hyperlink hypEsqueceuSenha;

    @FXML
    private Button btnCancelar;

    @FXML
    private StackPane stcLogin;

    @FXML
    private Button btnEntrar;
    
    Message msg = new Message();
    BoxInfo box = new BoxInfo();
    EntityManager em;
    GenericoDAO dao;

    @FXML
    void onActionLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Main.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setMaximized(true);
            scene.getStylesheets().add("/styles/Styles.css");
             stage.setTitle(msg.message("TituloSistema"));
            stage.getIcons().add(new Image("./img/IMG_01_LogoBetaTransportePNG.PNG"));
            stage.setScene(scene);
            MainApp.primaryStage.close();
            stage.show();

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    @FXML
    void onActionSair(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
