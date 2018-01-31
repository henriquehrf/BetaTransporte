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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

//
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import util.BoxInfo;
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
    private TableColumn<Usuario, Enum> tbcCidadeOrigem;
    
    @FXML
    private TextField txtPesquisa;

    @FXML
    void ExcluirOnAction(ActionEvent event) {
         excluir();
    }

    //Aqui
    private ObservableList<Usuario> UsuarioList = FXCollections.observableArrayList();

    @FXML
    Label lblInfoTable;

    UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
    BoxInfo boxInfo = new BoxInfo();
    public static Usuario usuarioAlter;
    
    
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
    void AlterarOnAction(ActionEvent event) {

        CadastrarUsuarioController.usuarioAlter = tbvPesquisa.getSelectionModel().getSelectedItem();
        editOrCreateUsuario();
    }
    
 
    
    
     void excluir() {
        Usuario usuario = tbvPesquisa.getSelectionModel().getSelectedItem();
        if (usuario != null) {
            if (boxInfo.BoxChoice(Alert.AlertType.CONFIRMATION, Message.message("conf.title.msg"), Message.message("conf.remocao.msg.part1") + " " + usuario.getNome() + " " + Message.message("conf.remocao.msg.part2"), Message.message("conf.opacao.confirmacao"), Message.message("conf.opcao.desitencia"))) {
                if (usuarioNegocio.excluirUsuario(usuario) > 0) {
                    completeTable(usuarioNegocio.searchUsuario("", ""));
                    this.txtPesquisa.setText("");
                    btnAlterar.setDisable(true);
                    btnExcluir.setDisable(true);
                }
            }
            
        }
    }
    
    

    
    
    void completeTable(List<Usuario> list) {

        UsuarioList.remove(0, UsuarioList.size());
        UsuarioList.addAll(list);
        // System.out.println("Aqui ->"+UsuarioList.get(0).getTipoFuncionario());
        this.tbcFuncionario.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("TipoFuncionario"));
        this.tbcNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Nome"));
        this.tbcCelular.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Celular"));
        this.tbcCidadeOrigem.setCellValueFactory(new PropertyValueFactory<Usuario, Enum>("Cidade"));

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

            completeTable(usuarioNegocio.searchUsuario("", ""));

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
