/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Daniel
 */


public class CadastrarEncomendaController {

    @FXML
    private RadioButton rdbGOLD;

    @FXML
    private TextField txtPeso;

    @FXML
    private ComboBox<?> cmbCidadeDestino;

    @FXML
    private TextField txtCpfCnpjCliente;

    @FXML
    private StackPane stpCadastrarCliente;

    @FXML
    private TextField txtNuNotaFiscal;

    @FXML
    private RadioButton rdbCONV;

    @FXML
    private ToggleGroup Sexo;

    @FXML
    private BorderPane bdpCadastroCliente;

    @FXML
    private TextField txtLargura;

    @FXML
    private TextField txtCpfCnpjDestino;

    @FXML
    private RadioButton rdbPLATINA;

    @FXML
    private TextField txtValorDeclarado;

    @FXML
    private TextField txtComprimento;

    @FXML
    private TextField txtAltura;

    @FXML
    void onSave(ActionEvent event) {

    }

    @FXML
    void onKeyPressedSave(KeyEvent event) {

    }

    @FXML
    void onCancel(ActionEvent event) {

    }

    @FXML
    void onKeyPressedCancel(KeyEvent event) {

    }

    @FXML
    void onChangeCPFCNPJCliente() {

    }

    @FXML
    void onChangeCPFCNPJDestino() {

    }

    @FXML
    void onKeyPressedConv(KeyEvent event) {

    }

    @FXML
    void onKeyPressedGold(KeyEvent event) {

    }

    @FXML
    void onKeyPressedPlatiniun(KeyEvent event) {

    }

    @FXML
    void onChangeComprimento() {

    }

    @FXML
    void onChangeLargura() {

    }

    @FXML
    void onChangeAltura() {

    }

    @FXML
    void onChangePeso() {

    }

    @FXML
    void onChangeNuNotaFiscal() {

    }

    @FXML
    void onChangeValorDeclarado() {

    }

}
