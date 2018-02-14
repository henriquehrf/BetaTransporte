/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.negocio.EncomendaNegocio;
import com.dev.betaTransporte.vo.Encomenda;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import util.Navegation;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class RastrearEncomendaController implements Initializable {

    @FXML
    private TableColumn<Encomenda, Long> tbcCodigo;
    
    @FXML
    private TableColumn<Encomenda, Enum> tbcSituacao;

    @FXML
    private StackPane stpRastrearEncomenda;

    @FXML
    private TableView<Encomenda> tbvPesquisa;

    @FXML
    private Button btnVoltar;

    //Aqui
    Navegation navegation = new Navegation();
    EncomendaNegocio encomendaNegocio = new EncomendaNegocio();
    private ObservableList<Encomenda> EncomendaList = FXCollections.observableArrayList();
    @FXML
    private TextField txtPesquisa;

    @FXML
    void VoltarOnAction(ActionEvent event) {
        navegation.getFather(stpRastrearEncomenda);
    }
    
     


    void completeTable(List<Encomenda> list) {

        EncomendaList.remove(0, EncomendaList.size());
        EncomendaList.addAll(list);

        this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<Encomenda, Long>("Id"));
        this.tbcSituacao.setCellValueFactory(new PropertyValueFactory<Encomenda, Enum>("Status"));
        tbvPesquisa.setItems(EncomendaList);


    }
    
    @FXML
    void isPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            completeTable(encomendaNegocio.searchEncomendabyID(Integer.parseInt(this.txtPesquisa.getText())));
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            
              completeTable(encomendaNegocio.searchEncomendabyID(0));



        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
