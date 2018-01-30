/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.vo.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;



//

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
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
    private TableColumn<Usuario, String> tbcFuncionario;

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
    private TableColumn<?, ?> tbcCelular;

    @FXML
    private TableColumn<?, ?> tbcEmail;

    

    @FXML
    void AlterarOnAction(ActionEvent event) {

    }

    @FXML
    void ExcluirOnAction(ActionEvent event) {

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
    }    
    
}
