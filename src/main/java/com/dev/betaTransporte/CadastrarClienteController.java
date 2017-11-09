/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.dao.GenericoDAO;
import com.dev.betaTransporte.negocio.ClienteNegocio;
import com.dev.betaTransporte.negocio.exception.ClienteException;
import com.dev.betaTransporteENUM.Sexo;
import com.dev.betaTransporte.vo.ClienteVO;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.BoxInfo;
import util.Message;
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
    Message msg = new Message();
    Mask mask = new Mask();

    private ClienteVO getCliente() {
        ClienteVO cliente = new ClienteVO();
        cliente.setCpfCnpj(this.txtCpfCnpj.getText());
        cliente.setEmail(this.txtEmail.getText());
        cliente.setNome(this.txtNomeRazaoSocial.getText());
        cliente.setTelCelular(this.txtTelefoneCelular.getText());
        cliente.setTelFixo(this.txtTelefoneFixo.getText());

        if (this.rdbFemino.isSelected()) {
            cliente.setSexo(Sexo.F);
        }
        if (this.rdbMasculino.isSelected()) {
            cliente.setSexo(Sexo.M);
        }
        if (this.rdbNaoAplica.isSelected()) {
            cliente.setSexo(Sexo.N);
        }
        cliente.setDataCadastro(new Date());

        if (this.dtpDataNascimento.getValue() != null) {
            cliente.setDataNascimento(util.LocalDate_To_Date(this.dtpDataNascimento.getValue()));
        }

        return cliente;
    }

    void complete_erros(ClienteException ex) {
        final String COR = "-fx-border-color:red";
        final String NORMAL = "-fx-border-color:darkgrey";
        final String NONE = "-fx-border-color:none";
        if (ex.getCpfCnpj()) {
            this.txtCpfCnpj.setStyle(COR);
        } else {
            this.txtCpfCnpj.setStyle(NORMAL);
        }
        if (ex.getDtNascimento()) {
            this.dtpDataNascimento.setStyle(COR);
        } else {
            this.dtpDataNascimento.setStyle(NORMAL);
        }
        if (ex.getEmail()) {
            this.txtEmail.setStyle(COR);
        } else {
            this.txtEmail.setStyle(NORMAL);
        }
        if (ex.getNomeRazaoSocial()) {
            this.txtNomeRazaoSocial.setStyle(COR);
        } else {
            this.txtNomeRazaoSocial.setStyle(NORMAL);
        }
        if (ex.getSexo()) {
            this.rdbFemino.setStyle(COR);
            this.rdbMasculino.setStyle(COR);
            this.rdbNaoAplica.setStyle(COR);
        } else {
            this.rdbFemino.setStyle(NONE);
            this.rdbMasculino.setStyle(NONE);
            this.rdbNaoAplica.setStyle(NONE);
        }
        if (ex.getTelefoneCelular()) {
            this.txtTelefoneCelular.setStyle(COR);
        } else {
            this.txtTelefoneCelular.setStyle(NORMAL);
        }
        if (ex.getTelefoneFixo()) {
            this.txtTelefoneFixo.setStyle(COR);
        } else {
            this.txtTelefoneFixo.setStyle(NORMAL);
        }
        box.BoxInfo(Alert.AlertType.WARNING, "Erro de Preenchimento de Cadastro", ex.getMsg());
        System.out.println(ex.getMsg());
    }

    @FXML
    void onSave(ActionEvent event) {

        ClienteVO cliente = getCliente();
        ClienteException ex = ClienteNegocio.save(cliente);

        if (ex == null) {
            this.box.BoxInfo(Alert.AlertType.INFORMATION, msg.message("suss.title.Insert"), msg.message("suss.msg.Insert"));
            onCancel(event);
        } else {
            complete_erros(ex);
        }
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
        
        if(txt.length()>14){
            this.rdbFemino.setDisable(true);
            this.rdbMasculino.setDisable(true);
            this.rdbNaoAplica.setSelected(true);
            this.dtpDataNascimento.setDisable(true);
        }else{
             this.rdbFemino.setDisable(false);
            this.rdbMasculino.setDisable(false);
            this.rdbNaoAplica.setSelected(false);
            this.dtpDataNascimento.setDisable(false);
        }
    }

    @FXML
    void onChangeTelefoneFixo() {
        String txt = mask.TelefoneFixo(this.txtTelefoneFixo.getText());
        this.txtTelefoneFixo.setText(txt);
        this.txtTelefoneFixo.positionCaret(txt.length());
    }

    @FXML
    void onChangeTelefoneCelular() {
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
