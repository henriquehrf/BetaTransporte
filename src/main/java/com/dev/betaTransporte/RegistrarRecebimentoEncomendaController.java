/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.gmaps.GMapsMatrix;
import com.dev.betaTransporte.gmaps.MatrizCidade;
import com.dev.betaTransporte.negocio.EncomendaNegocio;
import com.dev.betaTransporte.vo.Encomenda;
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Status;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.Mask;
import util.Navegation;
import util.Util;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class RegistrarRecebimentoEncomendaController implements Initializable {

    @FXML
    private TableView<Encomenda> tbvPrincipal;

    @FXML
    private TableColumn<Encomenda, String> tbcCodigo;

    @FXML
    private ToggleGroup filter;

    @FXML
    private TableColumn<Encomenda, String> TbcCliente;

    @FXML
    private TableColumn<Encomenda, String> tbcCPFCNPJ;

    @FXML
    private StackPane stpEncomendaEntrega;

    @FXML
    private BorderPane bdpConsultarEncomenda;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnSelecionar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnRetornar;

    @FXML
    private DatePicker dtpRecebimento;

    @FXML
    private TextField txtNotaFiscal;

    @FXML
    private TextField txtDestinatario;

    @FXML
    private BorderPane bdpEntregarEncomenda;

    @FXML
    private TextField txtRemetente;

    @FXML
    private RadioButton rdbCodigo;

    @FXML
    private RadioButton rdbNome;

    @FXML
    private TableColumn<Encomenda, String> tbcNF;

    @FXML
    private RadioButton rdbCpfCnpj;

    ObservableList<Encomenda> EncomendaList = FXCollections.observableArrayList();
    List<Encomenda> TodasEncomendas = null;
    Mask mask = new Mask();
    String cpfCnpjMask = "";

    @FXML
    void btnSelecionarOnAction(ActionEvent event) {

        if (tbvPrincipal.getSelectionModel().getSelectedItem() != null) {
            bdpConsultarEncomenda.setVisible(false);
            bdpEntregarEncomenda.setVisible(true);

            Encomenda encomenda = tbvPrincipal.getSelectionModel().getSelectedItem();
            Date date = new Date();
            Util util = new Util();
            txtDestinatario.setText(encomenda.getCpfCnpjDestinatario());
            txtRemetente.setText(encomenda.getClienteVO().getCpfCnpj());
            txtNotaFiscal.setText(""+encomenda.getNumNotaFiscal());
            dtpRecebimento.setValue(util.Date_To_LocalDate(date));
        }

    }

    @FXML
    void btnSelecionarOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void btnSalvarOnAction(ActionEvent event) {

    }

    @FXML
    void btnSalvarOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void btnRetornarOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void btnRetornarOnAction(ActionEvent event) {

    }

    @FXML
    void btnVoltarOnAction(ActionEvent event) {

        Navegation navegation = new Navegation();
        navegation.getFather(stpEncomendaEntrega);
    }

    @FXML
    void btnVoltarOnKeyPressed(KeyEvent event) {

    }

    void formartTxtPesquisa() {
        if (rdbCodigo.isSelected()) {
            txtPesquisa.setTextFormatter((new TextFormatter<>(c
                    -> {
                if (c.getControlNewText().isEmpty()) {
                    return c;
                }
                c.setText(mask.OnlyInt(c.getText()));
                return c;
            })));
        }
        if (rdbCpfCnpj.isSelected()) {

            txtPesquisa.setTextFormatter((new TextFormatter<>(c
                    -> {
                if (c.getControlNewText().isEmpty()) {
                    return c;
                }
                cpfCnpjMask += c.getText();
                if (c.getText().equals("")) {
                    cpfCnpjMask = cpfCnpjMask.substring(0, c.getControlNewText().length());
                }
                return c;
            })));
        }
        if (rdbNome.isSelected()) {
            txtPesquisa.setTextFormatter((new TextFormatter<>(c
                    -> {
                if (c.getControlNewText().isEmpty()) {
                    return c;
                }
                c.setText(c.getText());
                return c;
            })));

        }
    }

    @FXML
    void rdbCodigoOnAction(ActionEvent event) {
        formartTxtPesquisa();
    }

    @FXML
    void rdbCpfCnpjOnAction(ActionEvent event) {
        formartTxtPesquisa();
    }

    @FXML
    void rdbNomeOnAction(ActionEvent event) {
        formartTxtPesquisa();
    }

    @FXML
    void txtPesquisaOnKeyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            List<Encomenda> parcial = new ArrayList<>();
            if (rdbCodigo.isSelected()) {
                for (Encomenda vo : TodasEncomendas) {
                    String id = "" + vo.getId();
                    if (id.contains(txtPesquisa.getText())) {
                        parcial.add(vo);
                    }
                }
                completeTable(parcial);
                return;
            }
            if (rdbCpfCnpj.isSelected()) {
                for (Encomenda vo : TodasEncomendas) {
                    if (vo.getCpfCnpjDestinatario().contains(txtPesquisa.getText())) {
                        parcial.add(vo);
                    }
                }
                completeTable(parcial);
                return;
            }
            if (rdbNome.isSelected()) {
                for (Encomenda vo : TodasEncomendas) {
                    if (vo.getNomeCliente().contains(txtPesquisa.getText())) {
                        parcial.add(vo);
                    }
                }
                completeTable(parcial);
                return;
            }
        }

        if (rdbCpfCnpj.isSelected()) {
            String txt = mask.CpfCnpj(txtPesquisa.getText());
            txtPesquisa.setText(txt);
            this.txtPesquisa.positionCaret(txt.length());
        }

    }

    void completeTable(List<Encomenda> list) {

        EncomendaList.clear();
        EncomendaList.addAll(list);
        this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<Encomenda, String>("Id"));
        this.tbcNF.setCellValueFactory(new PropertyValueFactory<Encomenda, String>("NumNotaFiscal"));
        this.tbcCPFCNPJ.setCellValueFactory(new PropertyValueFactory<Encomenda, String>("CpfCnpjDestinatario"));
        this.TbcCliente.setCellValueFactory(new PropertyValueFactory<Encomenda, String>("NomeCliente"));
        tbvPrincipal.setItems(EncomendaList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EncomendaNegocio encomenda = new EncomendaNegocio();
        bdpConsultarEncomenda.setVisible(true);
        bdpEntregarEncomenda.setVisible(false);

        rdbCodigo.setSelected(true);
        formartTxtPesquisa();

        new Thread() {
            @Override
            public void run() {
                try {
                    TodasEncomendas = encomenda.searchEncomendaEntrega(Cidade.CUIABA, Status.Encomenda_descarregada_na_cidade_destino_e_disponivel_para_o_cliente_retirar);
                    completeTable(TodasEncomendas);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }.start();

    }

}
