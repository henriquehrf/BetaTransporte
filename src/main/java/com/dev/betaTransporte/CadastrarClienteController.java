/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporteDAO.GenericoDAO;
import com.dev.betaTransporteENUM.Sexo;
import com.dev.betaTransporteVO.Cliente;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import org.eclipse.persistence.logging.SessionLog;
import util.BoxInfo;
import util.GetMessage;
import util.Mask;
import util.Navegation;
import util.Util;

/**
 * FXML Controller class
 *
 * @author Henrique
 */
public class CadastrarClienteController implements Initializable {

   @FXML
    private RadioButton rdbNaoAplica;

    @FXML
    private TextField txtTelefoneCelular;

    @FXML
    private TextField txtNomeRazaoSocial;

    @FXML
    private DatePicker dtpDataNascimento;

    @FXML
    private TextField txtTelefoneFixo;

    @FXML
    private TextField txtEmail;

    @FXML
    private RadioButton rdbMasculino;

    @FXML
    private RadioButton rdbFemino;

//    @FXML
//    private ToggleGroup Sexo;

    @FXML
    private TextField txtCpfCnpj;
    
    @FXML
    private BorderPane bdpCadastroCliente;
    
    
    @FXML
    private StackPane stpCadastrarCliente;
    
    Util util = new Util();
    BoxInfo box = new BoxInfo();
    GetMessage msg= new GetMessage();
    Mask mask = new Mask();
    

    private Cliente getCliente(){
        Cliente cliente = new Cliente();
        cliente.setCpfCnpj(this.txtCpfCnpj.getText());
        cliente.setEmail(this.txtEmail.getText());
        cliente.setNome(this.txtNomeRazaoSocial.getText());
        cliente.setTelCelular(this.txtTelefoneCelular.getText());
        cliente.setTelFixo(this.txtTelefoneFixo.getText());
        
        if(this.rdbFemino.isSelected()){
            cliente.setSexo(Sexo.F);
        }
        if(this.rdbMasculino.isSelected()){
            cliente.setSexo(Sexo.M);
        }
        if(this.rdbNaoAplica.isSelected()){
            cliente.setSexo(Sexo.N);
        }
        cliente.setDataCadastro(new Date());
        
        cliente.setDataNascimento(util.LocalDate_To_Date(this.dtpDataNascimento.getValue()));
        
        
        return cliente;
    }
    
    @FXML
    void onSave(ActionEvent event) {
       
        Cliente cliente=getCliente();
        GenericoDAO genericoDao = new GenericoDAO();
        
        genericoDao.save(Cliente.class, cliente);
        
        this.box.BoxInfo(Alert.AlertType.INFORMATION, msg.getMessage("suss.title.Insert"), msg.getMessage("suss.msg.Insert"));
        
        onCancel(event);
    }

    @FXML
    void onCancel(ActionEvent event) {
        Navegation node = new Navegation();
        node.getFather(this.stpCadastrarCliente);
    }  
  
    @FXML
     void onChangeCPFCNPJ() {
        String txt = mask.CpfCnpj(this.txtCpfCnpj.getText());
        this.txtCpfCnpj.setText(txt);
        this.txtCpfCnpj.positionCaret(txt.length());
    }
     
    @FXML
    void onChangeTelefoneFixo(){
        String txt = mask.TelefoneFixo(this.txtTelefoneFixo.getText());
        this.txtTelefoneFixo.setText(txt);
        this.txtTelefoneFixo.positionCaret(txt.length());
    }
    
    @FXML
    void onChangeTelefoneCelular(){
        String txt = mask.TelefoneCelular(this.txtTelefoneCelular.getText());
        this.txtTelefoneCelular.setText(txt);
        this.txtTelefoneCelular.positionCaret(txt.length());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//         this.txtCpfCnpj.focusedProperty().addListener(new ChangeListener<Boolean>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
//                if (t1) {
//                    System.out.println("Focus In");
//                } else {
//                    System.out.println("Focus Out");
//                }
//
//            }
//        });
    }    
    
}
