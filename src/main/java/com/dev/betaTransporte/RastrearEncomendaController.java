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
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import util.Navegation;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class RastrearEncomendaController implements Initializable {

    @FXML
    private StackPane stpRastrearEncomenda;

    @FXML
    private Button btnVoltar;

    Navegation navegation = new Navegation();

    @FXML
    void VoltarOnAction(ActionEvent event) {
       navegation.getFather(stpRastrearEncomenda);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
