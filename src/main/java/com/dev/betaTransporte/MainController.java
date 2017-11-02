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
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class MainController implements Initializable {

    
    @FXML
    private MenuItem mnuItemCliente;

    @FXML
    void ItemClienteOnAction(ActionEvent event) {
        
        System.out.println("Hello World");

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
      
    }    
    
}
