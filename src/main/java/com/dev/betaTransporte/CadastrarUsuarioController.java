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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class CadastrarUsuarioController implements Initializable {

    @FXML
    private BorderPane bdpCadastroUsuario;

    @FXML
    private ToggleGroup Funcionario;

    @FXML
    private StackPane stpCadastrarUsuario;

    @FXML
    void onSave(ActionEvent event) {

    }

    @FXML
    void onKeyPressedSave(ActionEvent event) {

    }

    @FXML
    void onCancel(ActionEvent event) {

    }

    @FXML
    void onKeyPressedCancel(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
