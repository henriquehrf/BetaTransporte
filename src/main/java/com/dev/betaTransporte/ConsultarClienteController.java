/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.negocio.ClienteNegocio;
import com.dev.betaTransporte.vo.Cliente;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
 * FXML Controller class
 *
 * @author Henrique
 */
public class ConsultarClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tbvPesquisa;

    @FXML
    private TableColumn<Cliente, String> tbcNome;

    @FXML
    private TableColumn<Cliente, String> tbcCelular;

    @FXML
    private TableColumn<Cliente, String> tbcCpfCnpj;

    @FXML
    private TableColumn<Cliente, String> tbcEmail;

    @FXML
    private StackPane stpConsultarCliente;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnVoltar;

    @FXML
    private ToggleGroup TipoBusca;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private RadioButton rdbNome;

    @FXML
    private RadioButton rdbCfpCnpj;

    @FXML
    Label lblInfoTable;

    private ObservableList<Cliente> ClienteList = FXCollections.observableArrayList();

    BoxInfo boxInfo = new BoxInfo();

    ClienteNegocio clienteNegocio = new ClienteNegocio();
    Mask mask = new Mask();

    Navegation navegation = new Navegation();

    void excluir() {
        Cliente cliente = tbvPesquisa.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            if (boxInfo.BoxChoice(Alert.AlertType.CONFIRMATION, Message.message("conf.title.msg"), Message.message("conf.remocao.msg.part1") + " " + cliente.getNome() + " " + Message.message("conf.remocao.msg.part2"), Message.message("conf.opacao.confirmacao"), Message.message("conf.opcao.desitencia"))) {
                if (clienteNegocio.excluirCliente(cliente) > 0) {
                    completeTable(clienteNegocio.searchCliente("", ""));
                    this.txtPesquisa.setText("");
                    btnAlterar.setDisable(true);
                    btnExcluir.setDisable(true);
                }
            }

        }
    }

    void editOrCreateCliente() {
        try {
            CadastrarClienteController.NextPage = 0;
            Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastrarCliente.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            navegation.getMain().setCenter(root);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    void completeTable(List<Cliente> list) {

        ClienteList.remove(0, ClienteList.size());
        ClienteList.addAll(list);
        this.tbcCpfCnpj.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpfCnpj"));
        this.tbcNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        this.tbcEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
        this.tbcCelular.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telCelular"));

        tbvPesquisa.setItems(ClienteList);

        if (ClienteList.size() == 0) {
            lblInfoTable.setText(Message.message("lblTableInfo1"));
        } else if (ClienteList.size() == 1) {
            lblInfoTable.setText(Message.message("lblTableInfo2") + " " + ClienteList.size() + " " + Message.message("lblTableInfo3"));
        } else {
            lblInfoTable.setText(Message.message("lblTableInfo5") + " " + ClienteList.size() + " " + Message.message("lblTableInfo4"));
        }
    }

    @FXML
    void CadastrarOnAction(ActionEvent event) {

        editOrCreateCliente();

    }

    @FXML
    void CadastraronKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            editOrCreateCliente();
        }

    }

    @FXML
    void AlterarOnAction(ActionEvent event) {

        CadastrarClienteController.clienteAlter = tbvPesquisa.getSelectionModel().getSelectedItem();
        editOrCreateCliente();
    }

    @FXML
    void AlteraronKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            CadastrarClienteController.clienteAlter = tbvPesquisa.getSelectionModel().getSelectedItem();
            editOrCreateCliente();
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

        navegation.getFather(stpConsultarCliente);

    }

    @FXML
    void VoltaronKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            navegation.getFather(stpConsultarCliente);
        }

    }

    @FXML
    void onChangePesquisa() {
        if (this.rdbCfpCnpj.isSelected()) {
            String txt = mask.CpfCnpj(this.txtPesquisa.getText());
            this.txtPesquisa.setText(txt);
            this.txtPesquisa.positionCaret(txt.length());
        }
    }

    @FXML
    void rdbNomeOnAction(ActionEvent event) {
        this.txtPesquisa.setText("");
        completeTable(clienteNegocio.searchCliente("", ""));
    }

    @FXML
    void rdbCpfCnpjOnAction(ActionEvent event) {
        this.txtPesquisa.setText("");
        completeTable(clienteNegocio.searchCliente("", ""));
    }

    @FXML
    void isPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            if (this.rdbNome.isSelected()) {
                completeTable(clienteNegocio.searchCliente("%" + this.txtPesquisa.getText() + "%", "nome"));
            }
            if (this.rdbCfpCnpj.isSelected()) {
                completeTable(clienteNegocio.searchCliente("%" + this.txtPesquisa.getText() + "%", "cpfCnpj"));
            }

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            this.rdbNome.setSelected(true);
            btnAlterar.setDisable(true);
            btnExcluir.setDisable(true);
            
            tbcCpfCnpj.setStyle(" -fx-alignment:center");
            tbcCelular.setStyle(" -fx-alignment:center");

            completeTable(clienteNegocio.searchCliente("", ""));

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

}
