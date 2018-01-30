/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.negocio.UsuarioNegocio;
import com.dev.betaTransporte.vo.Usuario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

//
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import util.Message;
import util.Navegation;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class ConsultarUsuarioController implements Initializable {

    @FXML
    private Label lblTable;

    @FXML
    private TableColumn<Usuario, Integer> tbcFuncionario;

    @FXML
    private StackPane stpConsultarUsuario;

    @FXML
    private TableView<Usuario> tbvPesquisa;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnAlterar;

    @FXML
    private TableColumn<Usuario, String> tbcNome;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn<Usuario, String> tbcCelular;

    @FXML
    private TableColumn<Usuario, Enum> tbcCidade;

    @FXML
    void AlterarOnAction(ActionEvent event) {

    }

    @FXML
    void ExcluirOnAction(ActionEvent event) {

    }

    //Aqui
    private ObservableList<Usuario> UsuarioList = FXCollections.observableArrayList();

    @FXML
    Label lblInfoTable;

    UsuarioNegocio UsuarioNegocio = new UsuarioNegocio();

    void completeTable(List<Usuario> list) {

//        UsuarioList.remove(0, UsuarioList.size());
        UsuarioList.addAll(list);
        // System.out.println("Aqui ->"+UsuarioList.get(0).getTipoFuncionario());
        this.tbcFuncionario.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("tipoFuncionario"));
        this.tbcNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
        this.tbcCelular.setCellValueFactory(new PropertyValueFactory<Usuario, String>("celular"));
        this.tbcCidade.setCellValueFactory(new PropertyValueFactory<Usuario, Enum>("cidade"));

        tbcFuncionario.setCellFactory(tc -> new TableCell<Usuario, Integer>() {
            String conversor;

            @Override
            protected void updateItem(Integer value, boolean empty) {
                super.updateItem(value, empty);
                if (value == null || empty) {
                    setText("");
                } else {
                    if (value == 0) {
                        setText("Administrador");
                    }
                    if (value == 1) {
                        setText("Atendente");
                    }
                    if (value == 2) {
                        setText("Carregador/Descarrgador");
                    }
                }
            }
        }
        );

        tbvPesquisa.setItems(UsuarioList);

        if (UsuarioList.size() == 0) {
            lblInfoTable.setText(Message.message("lblTableInfo1"));
        } else if (UsuarioList.size() == 1) {
            lblInfoTable.setText(Message.message("lblTableInfo2") + " " + UsuarioList.size() + " " + Message.message("lblTableInfo3"));
        } else {
            lblInfoTable.setText(Message.message("lblTableInfo5") + " " + UsuarioList.size() + " " + Message.message("lblTableInfo4"));
        }
    }

    //Ok
    @FXML
    void VoltarOnAction(ActionEvent event) {
        navegation.getFather(stpConsultarUsuario);
    }

    @FXML
    void CadastrarOnAction(ActionEvent event) {
        editOrCreateUsuario();
    }

    void editOrCreateUsuario() {
        try {
            CadastrarUsuarioController.NextPage = 0;
            Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastrarUsuario.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
            navegation.getMain().setCenter(root);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    Navegation navegation = new Navegation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            // TODO

            btnAlterar.setDisable(true);
            btnExcluir.setDisable(true);

            tbcCelular.setStyle(" -fx-alignment:center");

            completeTable(UsuarioNegocio.searchUsuario("", ""));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
