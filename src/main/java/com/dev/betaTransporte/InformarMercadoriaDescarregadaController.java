/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
/**
 *
 * @author Daniel
 */
public class InformarMercadoriaDescarregadaController {

    @FXML
    private StackPane stpInformarMercadoriaDescarregada;

    @FXML
    private TableColumn<?, ?> tbcCodigoReceber;

    @FXML
    private TableColumn<?, ?> tbcNotaFiscalRecebida;

    @FXML
    private TableView<?> tbvReceber;

    @FXML
    private TableView<?> tbvRecebida;

    @FXML
    private TableColumn<?, ?> tbcCpfCnpjReceber;

    @FXML
    private RadioButton rbdCpfCnpj;

    @FXML
    private Button btnSalvar;

    @FXML
    private RadioButton rbdNotaFiscal;

    @FXML
    private TableColumn<?, ?> tbcNotaFiscalReceber;

    @FXML
    private TableColumn<?, ?> tbcCpfCnpjRecebida;

    @FXML
    private TableColumn<?, ?> tbcCodigoRecebida;

    @FXML
    private RadioButton rbdCodigo;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnMaior;

    @FXML
    private Button btnMenor;

    @FXML
    private Label lblInfoTable;

    @FXML
    private TextField txtPesquisarEncomendaReceber;

    @FXML
    void SalvarOnAction(ActionEvent event) {

    }

    @FXML
    void SalvarOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void CancelarOnAction(ActionEvent event) {

    }

    @FXML
    void CancelarOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void tbvReceberOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void tbvReceberOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void MaiorOnAction(ActionEvent event) {

    }

    @FXML
    void MenorOnAction(ActionEvent event) {

    }

    @FXML
    void tbvRecebidaOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void tbvRecebidaOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtPesquisarEncomendaReceberOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void rbdCodigoOnAction(ActionEvent event) {

    }

    @FXML
    void rbdNotaFiscalOnAction(ActionEvent event) {

    }

    @FXML
    void rbdCpfCnpjOnAction(ActionEvent event) {

    }

}

