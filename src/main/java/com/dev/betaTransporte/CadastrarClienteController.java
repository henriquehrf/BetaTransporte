/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.Navegation;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class CadastrarClienteController implements Initializable {

   @FXML
    private RadioButton rdbNaoAplica;

    @FXML
    private TextField txtTelefoneCelular;

    @FXML
    private TextField txtNomeRazaoSocial;

    @FXML
    private DatePicker dtpDataNascimento;

    @FXML
    private TextField txtTelefoneFixo;

    @FXML
    private TextField txtEmail;

    @FXML
    private RadioButton rdbMasculino;

    @FXML
    private RadioButton rdbFemino;

    @FXML
    private ToggleGroup Sexo;

    @FXML
    private TextField txtCpfCnpj;
    
    @FXML
    private BorderPane bdpCadastroCliente;
    
    
    @FXML
    private StackPane stpCadastrarCliente;
    

    @FXML
    void onSave(ActionEvent event) {

    }

    @FXML
    void onCancel(ActionEvent event) {
        Navegation node = new Navegation();
        node.getFather(this.stpCadastrarCliente);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
