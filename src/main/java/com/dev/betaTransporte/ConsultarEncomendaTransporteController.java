/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

/**
 *
 * @author Daniel
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ConsultarEncomendaTransporteController {

    @FXML
    private TextField txtPeso;

    @FXML
    private Button btnCalcularRota;

    @FXML
    private RadioButton rbdDestinoRecebida;

    @FXML
    private TextField txtCustoEfetivo;

    @FXML
    private TextField txtDisponibilidade;

    @FXML
    private RadioButton rbdCodigoReceber;

    @FXML
    private TableColumn<?, ?> tbcPrevisaoTransporte;

    @FXML
    private TableColumn<?, ?> tbcPlanoAguardando;

    @FXML
    private TableColumn<?, ?> tbcDestinoTransporte;

    @FXML
    private TableView<?> tbvAguardando;

    @FXML
    private TableColumn<?, ?> tbcDestinoAguardando;

    @FXML
    private TableColumn<?, ?> tbcPlanoTransporte;

    @FXML
    private TableColumn<?, ?> tbcCodigoTransporte;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<?, ?> tbcPrevisaoAguardando;

    @FXML
    private TableColumn<?, ?> tbcCodigoAguardando;

    @FXML
    private TextField txtVolume;

    @FXML
    private RadioButton rbdDestinoReceber;

    @FXML
    private Button btnMaior;

    @FXML
    private Button btnMenor;

    @FXML
    private TextField txtCategoriaCaminhao;

    @FXML
    private RadioButton rbdCodigoRecebida;

    @FXML
    private TableView<?> tbvTransporte;

    @FXML
    void CalcularRotaOnAction(ActionEvent event) {

    }

    @FXML
    void CalcularRotaOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void VoltarOnAction(ActionEvent event) {

    }

    @FXML
    void VoltarOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void rbdCodigoReceberOnAction(ActionEvent event) {

    }

    @FXML
    void rbdDestinoReceberOnAction(ActionEvent event) {

    }

    @FXML
    void tbvAguardandoOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void tbvAguardandoOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void MaiorOnAction(ActionEvent event) {

    }

    @FXML
    void MenorOnAction(ActionEvent event) {

    }

    @FXML
    void rbdCodigoRecebidaOnAction(ActionEvent event) {

    }

    @FXML
    void rbdDestinoRecebidaOnAction(ActionEvent event) {

    }

    @FXML
    void tbvTransporteOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void tbvTransporteOnMouseClicked(MouseEvent event) {

    }

}

