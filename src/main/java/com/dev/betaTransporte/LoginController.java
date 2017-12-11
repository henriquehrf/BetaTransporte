/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.dao.GenericoDAO;
import com.dev.betaTransporte.negocio.UsuarioNegocio;
import com.dev.betaTransporte.negocio.exception.LoginException;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    void complete_erros(LoginException ex) {
        final String COR = "-fx-border-color:red";
        final String NORMAL = "-fx-border-color:darkgrey";
        final String NONE = "-fx-border-color:none";

        if (ex.getUsuario()) {
            this.txtLogin.setStyle(COR);
        } else {
            this.txtLogin.setStyle(NORMAL);
        }

        if (ex.getSenha()) {
            this.txtSenha.setStyle(COR);
        } else {
            this.txtSenha.setStyle(NORMAL);
        }
        if (ex.getMsg().length() > 0) {
            box.BoxInfo(Alert.AlertType.WARNING, Message.message("err.title.login"), Message.message(ex.getMsg()));
        }

    }

    void Login() {
        UsuarioNegocio authenticated = new UsuarioNegocio();

        try {

            LoginException isValid = authenticated.Login(txtLogin.getText(), txtSenha.getText());

            complete_erros(isValid);

            if (isValid.getMsg().length() == 0) {

//                Parent root = FXMLLoader.load(getClass().getResource("/gui/Main.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
                Parent root = FXMLLoader.load(getClass().getResource("/gui/Main_Adm.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setMaximized(true);
                scene.getStylesheets().add("/styles/Styles.css");
                stage.setTitle(msg.message("TituloSistema"));
                stage.getIcons().add(new Image("./img/IMG_01_LogoBetaTransportePNG.PNG"));
                stage.setScene(scene);
                MainApp.primaryStage.close();
                stage.show();

            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    void onActionLogin(ActionEvent event) {
        Login();
    }

    @FXML
    void onActionSair(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void inputLogin(KeyEvent event) {
        if (txtLogin.getText().length() > 0 && txtSenha.getText().length() > 0) {
            btnEntrar.setDisable(false);
        } else {
            btnEntrar.setDisable(true);
        }
        if (event.getCode() == KeyCode.ENTER) {
            Login();
        }
    }

    @FXML
    void inputSenha(KeyEvent event) {
        if (txtLogin.getText().length() > 0 && txtSenha.getText().length() > 0) {
            btnEntrar.setDisable(false);
        } else {
            btnEntrar.setDisable(true);
        }
        if (event.getCode() == KeyCode.ENTER) {
            Login();
        }
    }

    @FXML
    void onKeyPressedSair(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.exit(0);
        }
    }

    @FXML
    void onKeyPressedLogin(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Login();

        }

    }

    void esqueceuSenha() {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/henriquehrf/BetaTransporte"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void onKeyPressedEsqSenha(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

            esqueceuSenha();
        }
    }

    @FXML
    void hypOnAction(ActionEvent event) {
        esqueceuSenha();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnEntrar.setDisable(true);

    }

}
