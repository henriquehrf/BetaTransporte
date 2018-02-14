/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import static com.dev.betaTransporte.negocio.UsuarioNegocio.user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import util.Navegation;

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
    private MenuItem mnuItenConsultarEncomenda;

    @FXML
    private MenuItem mnuItenCliente;

    @FXML
    private MenuItem mnuItenEncomenda;

    @FXML
    private MenuItem mnuItemRegistraPagamentoEncomenda;

    @FXML
    private MenuItem mnuItenRastrearEncomenda;
    
    @FXML
    private MenuItem mnuItemEntrega;

    @FXML
    void ItemRastrearOnAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/RastrearEncomenda.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            bdpPrincipal.setCenter(root);

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    
    @FXML
    void mnuItemEntregaOnAction(ActionEvent event){
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/RegistrarRecebimentoEncomenda.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            bdpPrincipal.setCenter(root);

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    @FXML
    void ItemClienteOnAction(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/ConsultarCliente.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            bdpPrincipal.setCenter(root);

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    @FXML
    void ItemUsuarioOnAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/ConsultarUsuario.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            bdpPrincipal.setCenter(root);

        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    @FXML
    void ItemEncomendaOnAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/ConsultarEncomenda.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            bdpPrincipal.setCenter(root);

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    @FXML
    void ItemRegistraPagamentoEncomendaOnAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/RegistroPagamentoEncomenda.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
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
        Navegation nav = new Navegation();
        nav.setMain(bdpPrincipal);

        if (user.getFuncionario().equals(user.getFuncionario().Atendente)) {
            mnuItemUsuario.setVisible(false);
            mnuItenConsultarEncomenda.setVisible(false);
        }

        if (user.getFuncionario().equals(user.getFuncionario().CarregadorDescarregador)) {
            mnuItemUsuario.setVisible(false);
            mnuItenCliente.setVisible(false);
        }

    }

    @FXML
    void ConsultarEncomendaOnAction(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/ConsultarEncomendaTransporte.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            bdpPrincipal.setCenter(root);

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
