/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import static com.dev.betaTransporte.negocio.UsuarioNegocio.user;
import com.dev.betaTransporte.vo.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class MainController implements Initializable {

    @FXML
    private MenuItem mnuItemUsuario;

    @FXML
    private BorderPane bdpPrincipal;

    @FXML
    private MenuItem mnuItenCliente;

    @FXML
    private MenuItem mnuItenEncomenda;



    @FXML
    void ItemClienteOnAction(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastrarCliente.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            bdpPrincipal.setCenter(root);

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    @FXML
    void ItemUsuarioOnAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastrarUsuario.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            bdpPrincipal.setCenter(root);

        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    @FXML
    void ItemEncomendaOnAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastrarEncomenda.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            bdpPrincipal.setCenter(root);

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(user.getTipoFuncionario() == 1){
            mnuItemUsuario.setVisible(false);
        }
        if(user.getTipoFuncionario() == 2){
            mnuItemUsuario.setVisible(false);
            mnuItenCliente.setVisible(false);
        }
    }

}
