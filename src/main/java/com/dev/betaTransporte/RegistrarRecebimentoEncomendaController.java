/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.gmaps.GMapsMatrix;
import com.dev.betaTransporte.gmaps.MatrizCidade;
import com.dev.betaTransporte.negocio.EncomendaNegocio;
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Status;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class RegistrarRecebimentoEncomendaController implements Initializable {

    @FXML
    private TableView<?> tbvPrincipal;

    @FXML
    private TableColumn<?, ?> tbcCodigo;

    @FXML
    private ToggleGroup filter;

    @FXML
    private TableColumn<?, ?> TbcCliente;

    @FXML
    private TableColumn<?, ?> tbcCPFCNPJ;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnSelecionar;

    @FXML
    private Button btnVoltar;

    @FXML
    private RadioButton rdbCodigo;

    @FXML
    private RadioButton rdbNome;

    @FXML
    private TableColumn<?, ?> tbcNF;

    @FXML
    private RadioButton rdbCpfCnpj;

    @FXML
    void btnSelecionarOnAction(ActionEvent event) {

    }

    @FXML
    void btnSelecionarOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void btnVoltarOnAction(ActionEvent event) {

    }

    @FXML
    void btnVoltarOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtPesquisaOnKeyPressed(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EncomendaNegocio encomenda = new EncomendaNegocio();

       encomenda.searchEncomendaEntrega(Cidade.ALTA_FLORESTA, Status.Encomenda_descarregada_na_cidade_destino_e_disponivel_para_o_cliente_retirar);
    }

}
