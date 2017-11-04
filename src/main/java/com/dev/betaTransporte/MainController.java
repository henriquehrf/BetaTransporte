/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporteDAO.GenericoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import util.Navegation;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class MainController implements Initializable {

    
    @FXML
    private MenuItem mnuItemCliente;
    
    @FXML
    private BorderPane bdpPrincipal;

    @FXML
    void ItemClienteOnAction(ActionEvent event) {
        
        System.out.println("Hello World");
        
        try{
            
             Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastrarCliente.fxml"),ResourceBundle.getBundle("docs/i18N_pt_BR")); 
             bdpPrincipal.setCenter(root);
             
        }catch(Exception ex){
            System.err.println(ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        Navegation.setFamily(bdpPrincipal);
    }    
    
}
