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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import util.Mask;
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

    private ObservableList<Cliente> ClienteList = FXCollections.observableArrayList();

    ClienteNegocio clienteNegocio = new ClienteNegocio();
    Mask mask = new Mask();

    Navegation navegation = new Navegation();

    void completeTable(List<Cliente> list) {

        ClienteList.remove(0, ClienteList.size());
        ClienteList.addAll(list);
        this.tbcCpfCnpj.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpfCnpj"));
        this.tbcNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        this.tbcEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
        this.tbcCelular.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telCelular"));

        tbvPesquisa.setItems(ClienteList);

    }

    @FXML
    void CadastrarOnAction(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastrarCliente.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            navegation.getMain().setCenter(root);
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    @FXML
    void CadastraronKeyPressed(ActionEvent event) {

    }

    @FXML
    void AlterarOnAction(ActionEvent event) {

    }

    @FXML
    void AlteraronKeyPressed(ActionEvent event) {

    }

    @FXML
    void ExcluirOnAction(ActionEvent event) {

    }

    @FXML
    void ExcluironKeyPressed(ActionEvent event) {

    }

    @FXML
    void VoltarOnAction(ActionEvent event) {

        navegation.getFather(stpConsultarCliente);

    }

    @FXML
    void VoltaronKeyPressed(KeyEvent event) {
        navegation.getFather(stpConsultarCliente);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            this.rdbNome.setSelected(true);
            btnAlterar.setDisable(true);
            btnExcluir.setDisable(true);

            completeTable(clienteNegocio.searchCliente("", ""));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
