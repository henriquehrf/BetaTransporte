/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.negocio.EncomendaNegocio;
import com.dev.betaTransporte.negocio.UsuarioNegocio;
import com.dev.betaTransporte.negocio.exception.EncomendaException;
import com.dev.betaTransporte.vo.Encomenda;
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import util.Message;
import util.Navegation;
/**
 *
 * @author Daniel
 */
public class InformarMercadoriaDescarregadaController {

    @FXML
    private StackPane stpInformarMercadoriaDescarregada;

    @FXML
    private TableColumn<Encomenda, Long> tbcCodigoReceber;

    @FXML
    private TableColumn<Encomenda, Integer> tbcNotaFiscalRecebida;

    @FXML
    private TableView<Encomenda> tbvReceber;

    @FXML
    private TableView<Encomenda> tbvRecebida;

    @FXML
    private TableColumn<Encomenda, String> tbcCpfCnpjReceber;

    @FXML
    private RadioButton rbdCpfCnpj;

    @FXML
    private Button btnSalvar;

    @FXML
    private RadioButton rbdNotaFiscal;

    @FXML
    private TableColumn<Encomenda, Integer> tbcNotaFiscalReceber;

    @FXML
    private TableColumn<Encomenda, String> tbcCpfCnpjRecebida;

    @FXML
    private TableColumn<Encomenda, Long> tbcCodigoRecebida;

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

    Navegation navegation = new Navegation();
    EncomendaNegocio encomendaNegocio = new EncomendaNegocio();
    private List<Encomenda> EncomendaListAux = new ArrayList<>();
    private ObservableList<Encomenda> EncomendaList1 = FXCollections.observableArrayList();
    public static ObservableList<Encomenda> EncomendaList2 = FXCollections.observableArrayList();
    
    @FXML
    void initialize() {
        this.rbdCodigo.setSelected(true);
        this.btnSalvar.setDisable(true);
        try {
            EncomendaList1.remove(0, EncomendaList1.size());
            EncomendaList1.addAll(encomendaNegocio.searchEncomenda3(centroDistribuicao(), UsuarioNegocio.user.getCidade(), Status.Encomenda_em_transporte_para_a_cidade_de_destino, Status.Encomenda_em_transporte_para_o_centro_de_distribuição));
            completeTableReceber(EncomendaList1);            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int centroDistribuicao() {
        if (UsuarioNegocio.user.getCidade() == Cidade.CUIABA) {
            return 0;
        } else {
            return 1;
        }
    }
    
    void completeTableReceber(List<Encomenda> list) {

        ObservableList<Encomenda> dados = FXCollections.observableArrayList();

        dados.addAll(list);
        this.tbcCodigoReceber.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tbcNotaFiscalReceber.setCellValueFactory(new PropertyValueFactory<>("numNotaFiscal"));
        this.tbcCpfCnpjReceber.setCellValueFactory(new PropertyValueFactory<>("cpfCnpjCliente"));
        tbvReceber.setItems(dados);
        tbvReceber.refresh();

        if (dados.size() == 0) {
            lblInfoTable.setText(Message.message("lblTableInfo1"));
        } else if (EncomendaList1.size() == 1) {
            lblInfoTable.setText(Message.message("lblTableInfo2") + " " + dados.size() + " " + Message.message("lblTableInfo3"));
        } else {
            lblInfoTable.setText(Message.message("lblTableInfo5") + " " + dados.size() + " " + Message.message("lblTableInfo4"));
        }
    }
    
    void completeTableRecebida() {
        this.tbcCodigoRecebida.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tbcNotaFiscalRecebida.setCellValueFactory(new PropertyValueFactory<>("numNotaFiscal"));
        this.tbcCpfCnpjRecebida.setCellValueFactory(new PropertyValueFactory<>("cpfCnpjCliente"));
        tbvRecebida.setItems(EncomendaList2);
        
        if (EncomendaList2.isEmpty()) {
            this.btnSalvar.setDisable(true);
        }else{
            this.btnSalvar.setDisable(false);
        }
    }
    
    @FXML
    void SalvarOnAction(ActionEvent event) {
        for (Encomenda vo: EncomendaList2){
            if (vo.getStatus().equals(Status.Encomenda_em_transporte_para_a_cidade_de_destino)){
                vo.setStatus(Status.Encomenda_descarregada_na_cidade_destino_e_disponivel_para_o_cliente_retirar);
            }else if (vo.getStatus().equals(Status.Encomenda_em_transporte_para_o_centro_de_distribuição)){
                vo.setStatus(Status.Encomenda_descarregada_no_centro_de_distribuição_e_aguardando_o_despacho_para_a_cidade_destino);
            }
            try {
                EncomendaNegocio.save(vo);
            } catch (Exception ex) {
                Logger.getLogger(InformarMercadoriaDescarregadaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        navegation.getFather(stpInformarMercadoriaDescarregada);
    }

    @FXML
    void SalvarOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void CancelarOnAction(ActionEvent event) {
        navegation.getFather(stpInformarMercadoriaDescarregada);
    }

    @FXML
    void CancelarOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            navegation.getFather(stpInformarMercadoriaDescarregada);
        }
    }

    @FXML
    void tbvReceberOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void tbvReceberOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void MaiorOnAction(ActionEvent event) {
        if (tbvReceber.getSelectionModel().getSelectedItem() != null) {
            this.btnSalvar.setDisable(false);
            for (int i = 0; i < EncomendaList1.size(); i++) {
                if (EncomendaList1.get(i).getId() == tbvReceber.getSelectionModel().getSelectedItem().getId()) {
                    EncomendaList2.add(EncomendaList1.get(i));
                    EncomendaList1.remove(EncomendaList1.get(i));
                    i = EncomendaList1.size() + 5;
                    completeTableReceber(EncomendaList1);
                    completeTableRecebida();
                }
            }
        }
    }

    @FXML
    void MenorOnAction(ActionEvent event) {
        if (tbvRecebida.getSelectionModel().getSelectedItem() != null) {
            for (int i = 0; i < EncomendaList2.size(); i++) {
                if (EncomendaList2.get(i).getId() == tbvRecebida.getSelectionModel().getSelectedItem().getId()) {
                    EncomendaList1.add(EncomendaList2.get(i));
                    EncomendaList2.remove(EncomendaList2.get(i));
                    i = EncomendaList2.size() + 5;
                    completeTableReceber(EncomendaList1);
                    completeTableRecebida();
                }
            }
        }
    }

    @FXML
    void tbvRecebidaOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void tbvRecebidaOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtPesquisarEncomendaReceberOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String value = txtPesquisarEncomendaReceber.getText();
            EncomendaListAux = new ArrayList<>();
            if (this.rbdCodigo.isSelected()){
                for (Encomenda vo : EncomendaList1) {
                    if (vo.getId().toString().contains(value)) {
                        EncomendaListAux.add(vo);
                    }
                }

                if (!txtPesquisarEncomendaReceber.getText().isEmpty()) {
                    completeTableReceber(EncomendaListAux);
                }else{
                    completeTableReceber(EncomendaList1);
                }
            }else if (this.rbdNotaFiscal.isSelected()){
                for (Encomenda vo : EncomendaList1) {
                    if (String.valueOf(vo.getNumNotaFiscal()).contains(value)) {
                        EncomendaListAux.add(vo);
                    }
                }

                if (!txtPesquisarEncomendaReceber.getText().isEmpty()) {
                    completeTableReceber(EncomendaListAux);

                }else{
                    completeTableReceber(EncomendaList1);
                }
            }else{
                for (Encomenda vo : EncomendaList1) {
                    if (vo.getCpfCnpjCliente().toString().contains(value)) {
                        EncomendaListAux.add(vo);
                    }
                }

                if (!txtPesquisarEncomendaReceber.getText().isEmpty()) {
                    completeTableReceber(EncomendaListAux);

                }else{
                    completeTableReceber(EncomendaList1);
                }
            }
        }
    }

    @FXML
    void rbdCodigoOnAction(ActionEvent event) {
        this.rbdCpfCnpj.setSelected(false);
        this.rbdNotaFiscal.setSelected(false);  
    }

    @FXML
    void rbdNotaFiscalOnAction(ActionEvent event) {
        this.rbdCodigo.setSelected(false);
        this.rbdCpfCnpj.setSelected(false);
    }

    @FXML
    void rbdCpfCnpjOnAction(ActionEvent event) {
        this.rbdCodigo.setSelected(false);
        this.rbdNotaFiscal.setSelected(false);
    }

}

