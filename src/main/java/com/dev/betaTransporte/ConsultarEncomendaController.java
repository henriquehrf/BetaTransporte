/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.negocio.EncomendaNegocio;
import com.dev.betaTransporte.vo.Encomenda;
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Plano;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import util.BoxInfo;
import util.Mask;
import util.Message;
import util.Navegation;

/**
 *
 * @author Daniel
 */
public class ConsultarEncomendaController {
    
    @FXML
    private RadioButton rdbGOLD;

    @FXML
    private TableColumn<Encomenda, String> tbcCpfCnpj;

    @FXML
    private TableColumn<Encomenda, Cidade> tbcCidadeDestino;

    @FXML
    private TableColumn<Encomenda, Plano> tbcPlano;

    @FXML
    private ToggleGroup TipoBusca;

    @FXML
    private Button btnCadastrar;

    @FXML
    private RadioButton rdbCfpCnpjDestinatario;

    @FXML
    private RadioButton rdbCONV;

    @FXML
    private StackPane stpConsultarEncomenda;

    @FXML
    private TableView<Encomenda> tbvPesquisa;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnAlterar;

    @FXML
    private TableColumn<Encomenda, Cidade> tbcCidadeOrigen;

    @FXML
    private Label lblInfoTable;

    @FXML
    private Button btnExcluir;

    @FXML
    private RadioButton rdbPLATINIUN;

    EncomendaNegocio encomendaNegocio = new EncomendaNegocio();
    BoxInfo boxInfo = new BoxInfo();
    Mask mask = new Mask();
    private ObservableList<Encomenda> EncomendaList = FXCollections.observableArrayList();
    Navegation navegation = new Navegation();
    
    @FXML
    void initialize() {
        try {
            
            this.rdbCfpCnpjDestinatario.setSelected(true);
            this.txtPesquisa.setDisable(false);
            
            this.btnAlterar.setDisable(true);
            this.btnExcluir.setDisable(true);
            
            tbcCpfCnpj.setStyle(" -fx-alignment:center");
            tbcCidadeDestino.setStyle(" -fx-alignment:center");

            completeTable(encomendaNegocio.searchEncomenda("", ""));

            this.tbvPesquisa.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                    if (t1) {
                        if (tbvPesquisa.getSelectionModel().getSelectedItem() != null) {
                            btnAlterar.setDisable(false);
                            btnExcluir.setDisable(false);
                        } else {
                            btnAlterar.setDisable(true);
                            btnExcluir.setDisable(true);
                        }
                    } else {
                        if (tbvPesquisa.getSelectionModel().getSelectedItem() != null) {
                            btnAlterar.setDisable(false);
                            btnExcluir.setDisable(false);
                        } else {
                            btnAlterar.setDisable(true);
                            btnExcluir.setDisable(true);
                        }
                    }

                }

            });

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    void completeTable(List<Encomenda> list) {

        EncomendaList.remove(0, EncomendaList.size());
        EncomendaList.addAll(list);
        this.tbcCpfCnpj.setCellValueFactory(new PropertyValueFactory<Encomenda, String>("cpfCnpjDestinatario"));
        this.tbcCidadeDestino.setCellValueFactory(new PropertyValueFactory<Encomenda, Cidade>("cidadeDestino"));
        this.tbcCidadeOrigen.setCellValueFactory(new PropertyValueFactory<Encomenda, Cidade>("cidadeOrigem"));
        this.tbcPlano.setCellValueFactory(new PropertyValueFactory<Encomenda, Plano>("plano"));

        tbvPesquisa.setItems(EncomendaList);

        if (EncomendaList.size() == 0) {
            lblInfoTable.setText(Message.message("lblTableInfo1"));
        } else if (EncomendaList.size() == 1) {
            lblInfoTable.setText(Message.message("lblTableInfo2") + " " + EncomendaList.size() + " " + Message.message("lblTableInfo3"));
        } else {
            lblInfoTable.setText(Message.message("lblTableInfo5") + " " + EncomendaList.size() + " " + Message.message("lblTableInfo4"));
        }
    }
    
    void editOrCreateEncomenda() {
        try {
            CadastrarEncomendaController.NextPage = 0;
            Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastrarEncomenda.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            navegation.getMain().setCenter(root);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    void excluir() {
        Encomenda encomenda = tbvPesquisa.getSelectionModel().getSelectedItem();
        if (encomenda != null) {
            if (boxInfo.BoxChoice(Alert.AlertType.CONFIRMATION, Message.message("conf.title.msg"), Message.message("conf.remocao.msg.part1") + " " + encomenda.getId() + " " + Message.message("conf.remocao.msg.part2"), Message.message("conf.opacao.confirmacao"), Message.message("conf.opcao.desitencia"))) {
                if (encomendaNegocio.excluirEncomenda(encomenda) > 0) {
                    completeTable(encomendaNegocio.searchEncomenda("", ""));
                    this.txtPesquisa.setText("");
                    btnAlterar.setDisable(true);
                    btnExcluir.setDisable(true);
                }
            }

        }
    }
    
    @FXML
    void CadastrarOnAction(ActionEvent event) {
        editOrCreateEncomenda();
    }

    @FXML
    void CadastraronKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            editOrCreateEncomenda();
        }
    }

    @FXML
    void AlterarOnAction(ActionEvent event) {
        CadastrarEncomendaController.encomendaAlter = tbvPesquisa.getSelectionModel().getSelectedItem();
        editOrCreateEncomenda();
    }

    @FXML
    void AlteraronKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            CadastrarEncomendaController.encomendaAlter = tbvPesquisa.getSelectionModel().getSelectedItem();
            editOrCreateEncomenda();
        }
    }

    @FXML
    void ExcluirOnAction(ActionEvent event) {
        excluir();
    }

    @FXML
    void ExcluironKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            excluir();
        }
    }

    @FXML
    void VoltarOnAction(ActionEvent event) {
        navegation.getFather(stpConsultarEncomenda);
    }

    @FXML
    void VoltaronKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            navegation.getFather(stpConsultarEncomenda);
        }
    }

    @FXML
    void onKeySelected(KeyEvent event) {
        if (tbvPesquisa.getSelectionModel().getSelectedItem() != null) {
            btnAlterar.setDisable(false);
            btnExcluir.setDisable(false);
        } else {
            btnAlterar.setDisable(true);
            btnExcluir.setDisable(true);
        }
    }

    @FXML
    void onMouseSelected(MouseEvent event) {
        if (tbvPesquisa.getSelectionModel().getSelectedItem() != null) {
            btnAlterar.setDisable(false);
            btnExcluir.setDisable(false);
        } else {
            btnAlterar.setDisable(true);
            btnExcluir.setDisable(true);
        }
    }

    @FXML
    void isPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (this.rdbCfpCnpjDestinatario.isSelected()) {
                this.rdbCONV.setSelected(false);
                this.rdbGOLD.setSelected(false);
                this.rdbPLATINIUN.setSelected(false);
                this.btnAlterar.setDisable(true);
                this.btnExcluir.setDisable(true);
                completeTable(encomendaNegocio.searchEncomenda(this.txtPesquisa.getText(), "cpfCnpj"));
            }
        }
    }

    @FXML
    void onChangePesquisa() {
        String txt = mask.CpfCnpj(this.txtPesquisa.getText());
        this.txtPesquisa.setText(txt);
        this.txtPesquisa.positionCaret(txt.length());
    }

    @FXML
    void rdbCpfCnpjDestinatarioOnAction(ActionEvent event) {
        this.txtPesquisa.setDisable(false);
        this.rdbCONV.setSelected(false);
        this.rdbGOLD.setSelected(false);
        this.rdbPLATINIUN.setSelected(false);
        this.txtPesquisa.setText("");
        this.btnAlterar.setDisable(true);
        this.btnExcluir.setDisable(true);
        completeTable(encomendaNegocio.searchEncomenda("", ""));
    }

    @FXML
    void rdbConvOnAction(ActionEvent event) {
        if (this.rdbCONV.isSelected()) {
            this.txtPesquisa.setDisable(true);
            this.rdbGOLD.setSelected(false);
            this.rdbPLATINIUN.setSelected(false);
            this.rdbCfpCnpjDestinatario.setSelected(false);
            this.txtPesquisa.setText("");
            this.btnAlterar.setDisable(true);
            this.btnExcluir.setDisable(true);
            completeTable(encomendaNegocio.searchEncomenda("0", "plano"));
        }
    }

    @FXML
    void rdbGoldOnAction(ActionEvent event) {
        if (this.rdbGOLD.isSelected()) {
            this.txtPesquisa.setDisable(true);
            this.rdbCONV.setSelected(false);
            this.rdbCfpCnpjDestinatario.setSelected(false);
            this.rdbPLATINIUN.setSelected(false);
            this.txtPesquisa.setText("");
            this.btnAlterar.setDisable(true);
            this.btnExcluir.setDisable(true);
            completeTable(encomendaNegocio.searchEncomenda("1", "plano"));
        }
    }

    @FXML
    void rdbPlatiniuumOnAction(ActionEvent event) {
        if (this.rdbPLATINIUN.isSelected()) {
            this.rdbCONV.setSelected(false);
            this.rdbGOLD.setSelected(false);
            this.rdbCfpCnpjDestinatario.setSelected(false);
            this.txtPesquisa.setText("");
            this.txtPesquisa.setDisable(true);
            this.btnAlterar.setDisable(true);
            this.btnExcluir.setDisable(true);
            completeTable(encomendaNegocio.searchEncomenda("2", "plano"));
        }
    }
}
