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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class CalcularRotaTransporteController implements Initializable {
    
    
       @FXML
    private TableColumn<?, ?> tbcCpfCnpj;

    @FXML
    private TableColumn<?, ?> tbcNumNotaFiscal;

    @FXML
    private TableColumn<?, ?> tbcDestino;

    @FXML
    private TableColumn<?, ?> tbcTempo;

    @FXML
    private TextField txtGaragem;

    @FXML
    private TableColumn<?, ?> tbcCodigo;

    @FXML
    private TextField txtDestinoFinal;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<?, ?> tbcDistancia;

    @FXML
    private TableColumn<?, ?> tbcRota;

    @FXML
    private TextField txtTotalItem;

    @FXML
    private TableColumn<?, ?> tbcRotaEncomenda;

    @FXML
    private TableColumn<?, ?> tbcOrigemRota;

    @FXML
    private TableView<?> tbvListaEncomenda;

    @FXML
    private TableColumn<?, ?> tbcDestinoRota;

    @FXML
    private TextField txtTempo;

    @FXML
    private Button btnGerarRelatorio;

    @FXML
    private TableView<?> tbvRotas;

    @FXML
    void btnGerarRelatorioOnAction(ActionEvent event) {

    }

    @FXML
    void btnGerarRelatorioOnKeyPressed(ActionEvent event) {

    }

    @FXML
    void btnVoltarOnAction(ActionEvent event) {

    }

    @FXML
    void btnVoltarOnKeyPressed(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
