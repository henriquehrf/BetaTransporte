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
import com.dev.betaTransporte.negocio.EncomendaNegocio;
import com.dev.betaTransporte.negocio.UsuarioNegocio;
import com.dev.betaTransporte.vo.Encomenda;
import com.dev.betaTransporteENUM.CategoriaCaminhao;
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Plano;
import com.dev.betaTransporteENUM.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

public class ConsultarEncomendaTransporteController {

    @FXML
    private TextField txtPeso;

    @FXML
    private Button btnCalcularRota;

    @FXML
    private TextField txtCustoEfetivo;

    @FXML
    private TableColumn<Encomenda, Date> tbcPrevisaoTransporte;

    @FXML
    private TableColumn<Encomenda, Cidade> tbcDestinoTransporte;

    @FXML
    private TableView<Encomenda> tbvAguardando;

    @FXML
    private TableColumn<Encomenda, Plano> tbcPlanoTransporte;

    @FXML
    private TableColumn<Encomenda, Long> tbcCodigoTransporte;

    @FXML
    private Button btnVoltar;

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
    private TextField txtPesquisarEncomendaReceber;

    @FXML
    private TextField txtDisponibilidade;

    @FXML
    private RadioButton rbdCodigoReceber;

    @FXML
    private TableColumn<Encomenda, Plano> tbcPlanoAguardando;

    @FXML
    private TableColumn<Encomenda, Cidade> tbcDestinoAguardando;

    @FXML
    private TableColumn<Encomenda, Date> tbcPrevisaoAguardando;

    @FXML
    private TableColumn<Encomenda, Long> tbcCodigoAguardando;

    @FXML
    private StackPane stpConsultarEncomendaTransporte;

    @FXML
    private Label lblInfoTable;

    @FXML
    private TableView<Encomenda> tbvTransporte;

    Navegation navegation = new Navegation();
    EncomendaNegocio encomendaNegocio = new EncomendaNegocio();
    private List<Encomenda> EncomendaListAux = new ArrayList<>();
    private ObservableList<Encomenda> EncomendaList1 = FXCollections.observableArrayList();
    public static ObservableList<Encomenda> EncomendaList2 = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        this.btnCalcularRota.setDisable(true);
        this.rbdCodigoReceber.setSelected(true);
        this.rbdDestinoReceber.setSelected(false);
        this.txtCategoriaCaminhao.setEditable(false);
        this.txtCustoEfetivo.setEditable(false);
        this.txtDisponibilidade.setEditable(false);
        this.txtPeso.setEditable(false);
        this.txtVolume.setEditable(false);
        EncomendaList2 = FXCollections.observableArrayList();
        try {
            tbcCodigoAguardando.setStyle(" -fx-alignment:center");
            tbcCodigoTransporte.setStyle(" -fx-alignment:center");
            tbcPlanoAguardando.setStyle(" -ffx-alignment:center");
            tbcPlanoTransporte.setStyle(" -fx-alignment:center");
            EncomendaList1.remove(0, EncomendaList1.size());
            EncomendaList1.addAll(encomendaNegocio.searchEncomenda2(centroDistribuicao(), UsuarioNegocio.user.getCidade(), Status.Encomenda_aguardando_transporte_na_cidade_de_origem, Status.Encomenda_descarregada_no_centro_de_distribuição_e_aguardando_o_despacho_para_a_cidade_destino));
            completeTableAguardando(EncomendaList1);

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

    void completeTableAguardando(List<Encomenda> list) {

        ObservableList<Encomenda> dados = FXCollections.observableArrayList();

        dados.addAll(list);
        this.tbcCodigoAguardando.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tbcDestinoAguardando.setCellValueFactory(new PropertyValueFactory<>("cidadeDestino"));
        this.tbcPlanoAguardando.setCellValueFactory(new PropertyValueFactory<>("plano"));
        this.tbcPrevisaoAguardando.setCellValueFactory(new PropertyValueFactory<>("dataEntregaString"));
        tbvAguardando.setItems(dados);
        tbvAguardando.refresh();

        if (dados.size() == 0) {
            lblInfoTable.setText(Message.message("lblTableInfo1"));
        } else if (EncomendaList1.size() == 1) {
            lblInfoTable.setText(Message.message("lblTableInfo2") + " " + dados.size() + " " + Message.message("lblTableInfo3"));
        } else {
            lblInfoTable.setText(Message.message("lblTableInfo5") + " " + dados.size() + " " + Message.message("lblTableInfo4"));
        }
    }

    void completeTableTransporte() {
        this.tbcCodigoTransporte.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tbcDestinoTransporte.setCellValueFactory(new PropertyValueFactory<>("cidadeDestino"));
        this.tbcPlanoTransporte.setCellValueFactory(new PropertyValueFactory<>("plano"));
        this.tbcPrevisaoTransporte.setCellValueFactory(new PropertyValueFactory<>("dataEntregaString"));
        tbvTransporte.setItems(EncomendaList2);
        
        if (EncomendaList2.isEmpty()) {
            this.btnCalcularRota.setDisable(true);
            this.txtCategoriaCaminhao.setText("");
            this.txtCustoEfetivo.setText("");
            this.txtDisponibilidade.setText("");
            this.txtPeso.setText("");
            this.txtVolume.setText("");
        }else{
            float peso=0;
            int volume=0;
            this.txtDisponibilidade.setText("Sim");
            for (Encomenda vo : EncomendaList2) {
                peso=peso+vo.getPeso();
                volume=volume+vo.volume();
            }
            this.txtPeso.setText(""+peso+"kg");
            this.txtVolume.setText(""+volume+"cm³");
            if (peso<500 && volume<800000){
                this.txtCategoriaCaminhao.setText(""+CategoriaCaminhao.PEQUENO.name().trim());
                this.btnMaior.setDisable(false);
            }else if (peso<1000 && volume<1600000){
                this.txtCategoriaCaminhao.setText(""+CategoriaCaminhao.MEDIO.name().trim());
                this.btnMaior.setDisable(false);
            }else if (peso<13200 && volume<2750000){
                this.txtCategoriaCaminhao.setText(""+CategoriaCaminhao.GRANDE.name().trim());
                this.btnMaior.setDisable(false);
            }else{
                this.txtCategoriaCaminhao.setText(""+CategoriaCaminhao.GRANDE.name().trim());
                this.btnMaior.setDisable(true);
            }
        }
    }

    @FXML
    void CalcularRotaOnAction(ActionEvent event) {

        try {
            // CadastrarClienteController.NextPage = 0;
            Parent root = FXMLLoader.load(getClass().getResource("/gui/CalcularRotaTransporte.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            navegation.getMain().setCenter(root);
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    @FXML
    void CalcularRotaOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                // CadastrarClienteController.NextPage = 0;
                Parent root = FXMLLoader.load(getClass().getResource("/gui/CalcularRotaTransporte.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
                navegation.getMain().setCenter(root);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }

    @FXML
    void VoltarOnAction(ActionEvent event) {
        navegation.getFather(stpConsultarEncomendaTransporte);
    }

    @FXML
    void VoltarOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            navegation.getFather(stpConsultarEncomendaTransporte);
        }
    }

    @FXML
    void txtPesquisarEncomendaReceberOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String value = txtPesquisarEncomendaReceber.getText();
            EncomendaListAux = new ArrayList<>();
            if (this.rbdCodigoReceber.isSelected()){
                for (Encomenda vo : EncomendaList1) {
                    if (vo.getId().toString().contains(value)) {
                        EncomendaListAux.add(vo);
                    }
                }

                if (!txtPesquisarEncomendaReceber.getText().isEmpty()) {
                    completeTableAguardando(EncomendaListAux);
                }else{
                    completeTableAguardando(EncomendaList1);
                }
            }else{
                for (Encomenda vo : EncomendaList1) {
                    if (vo.getCidadeDestino().name().trim().contains(value.toUpperCase().trim())) {
                        EncomendaListAux.add(vo);
                    }
                }

                if (!txtPesquisarEncomendaReceber.getText().isEmpty()) {
                    completeTableAguardando(EncomendaListAux);

                }else{
                    completeTableAguardando(EncomendaList1);
                }
            }
        }
    }

    @FXML
    void rbdCodigoReceberOnAction(ActionEvent event) {
        this.rbdCodigoReceber.setSelected(true);
        this.rbdDestinoReceber.setSelected(false);
    }

    @FXML
    void rbdDestinoReceberOnAction(ActionEvent event) {
        this.rbdCodigoReceber.setSelected(false);
        this.rbdDestinoReceber.setSelected(true);
    }

    @FXML
    void tbvAguardandoOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void tbvAguardandoOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void MaiorOnAction(ActionEvent event) {
        if (tbvAguardando.getSelectionModel().getSelectedItem() != null) {
            this.btnCalcularRota.setDisable(false);
            for (int i = 0; i < EncomendaList1.size(); i++) {
                if (EncomendaList1.get(i).getId() == tbvAguardando.getSelectionModel().getSelectedItem().getId()) {
                    EncomendaList2.add(EncomendaList1.get(i));
                    EncomendaList1.remove(EncomendaList1.get(i));
                    i = EncomendaList1.size() + 5;
                    completeTableAguardando(EncomendaList1);
                    completeTableTransporte();
                }
            }
        }
    }

    @FXML
    void MenorOnAction(ActionEvent event) {
        if (tbvTransporte.getSelectionModel().getSelectedItem() != null) {
            for (int i = 0; i < EncomendaList2.size(); i++) {
                if (EncomendaList2.get(i).getId() == tbvTransporte.getSelectionModel().getSelectedItem().getId()) {
                    EncomendaList1.add(EncomendaList2.get(i));
                    EncomendaList2.remove(EncomendaList2.get(i));
                    i = EncomendaList2.size() + 5;
                    completeTableAguardando(EncomendaList1);
                    completeTableTransporte();
                }
            }
        }
    }

    @FXML
    void tbvTransporteOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void tbvTransporteOnMouseClicked(MouseEvent event) {

    }

}
