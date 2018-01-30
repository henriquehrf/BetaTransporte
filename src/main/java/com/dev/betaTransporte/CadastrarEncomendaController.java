/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.negocio.EncomendaNegocio;
import com.dev.betaTransporte.negocio.UsuarioNegocio;
import com.dev.betaTransporte.negocio.exception.EncomendaException;
import com.dev.betaTransporte.vo.Encomenda;
import com.dev.betaTransporte.vo.Usuario;
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Plano;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.BoxInfo;
import util.Loader;
import util.Mask;
import util.Message;
import util.Navegation;
import util.TestConnectionServer;
import util.Util;

/**
 *
 * @author Daniel
 */


public class CadastrarEncomendaController {

    @FXML
    private RadioButton rdbGOLD;

    @FXML
    private TextField txtPeso;

    @FXML
    private ComboBox<Cidade> cmbCidadeDestino;

    @FXML
    private TextField txtCpfCnpjCliente;

    @FXML
    private StackPane stpCadastrarEncomenda;

    @FXML
    private TextField txtNuNotaFiscal;

    @FXML
    private RadioButton rdbCONV;

    @FXML
    private ToggleGroup plano;

    @FXML
    private BorderPane bdpCadastroEncomenda;

    @FXML
    private TextField txtLargura;

    @FXML
    private TextField txtCpfCnpjDestino;

    @FXML
    private RadioButton rdbPLATINA;

    @FXML
    private TextField txtValorDeclarado;

    @FXML
    private TextField txtComprimento;

    @FXML
    private TextField txtAltura;

    private ExecutorService threadpool = Executors.newFixedThreadPool(3);
    Util util = new Util();
    BoxInfo box = new BoxInfo();
    Message msg = new Message();
    Mask mask = new Mask();
    Loader loading = new Loader();
    
    @FXML
    void initialize(){
        ObservableList<Cidade> combo= FXCollections.observableArrayList(Cidade.values());
        this.cmbCidadeDestino.setItems(combo);
        
        
    }
    private Encomenda getEncomenda() {
        
        Encomenda encomenda = new Encomenda();
        encomenda.setCpfCnpjDestinatario(this.txtCpfCnpjDestino.getText());
        encomenda.getClienteVO().setCpfCnpj(this.txtCpfCnpjCliente.getText());
        
        
        encomenda.setCidadeOrigem(UsuarioNegocio.user.getCidade());
        
        if ("".equals(this.txtComprimento.getText())){
            encomenda.setComprimento(0);
        }else{
            encomenda.setComprimento(Integer.parseInt(this.txtComprimento.getText()));
        }
        
        encomenda.setDataCadastro(new Date());
        
        if ("".equals(this.txtAltura.getText())){
            encomenda.setAltura(0);
        }else{
            encomenda.setAltura(Integer.parseInt(this.txtAltura.getText()));
        }
        
        encomenda.setCidadeDestino(this.cmbCidadeDestino.getValue());
        
        if ("".equals(this.txtLargura.getText())){
            encomenda.setLargura(0);
        }else{
            encomenda.setLargura(Integer.parseInt(this.txtLargura.getText()));
        }
        
        if ("".equals(this.txtNuNotaFiscal.getText())){
            encomenda.setNumNotaFiscal(0);
        }else{
            encomenda.setNumNotaFiscal(Integer.parseInt(this.txtNuNotaFiscal.getText()));
        }
        
        if ("".equals(this.txtPeso.getText())){
            encomenda.setPeso(0);
        }else{
            encomenda.setPeso(Float.parseFloat(this.txtPeso.getText()));
        }
        
        if ("".equals(this.txtValorDeclarado.getText())){
            encomenda.setValorDeclarado(0);
        }else{
            encomenda.setValorDeclarado((float) mask.Monetaria_To_Double(this.txtValorDeclarado.getText()));
        }
        
        if (this.rdbCONV.isSelected()) {
            encomenda.setPlano(Plano.BETA_CONV);
        }
        if (this.rdbGOLD.isSelected()) {
            encomenda.setPlano(Plano.BETA_GOLD);
        }
        if (this.rdbPLATINA.isSelected()) {
            encomenda.setPlano(Plano.BETA_PLATINIUN);
        }

        return encomenda;
    }

    void complete_erros(EncomendaException ex) {
        final String COR = "-fx-border-color:red";
        final String NORMAL = "-fx-border-color:darkgrey";
        final String NONE = "-fx-border-color:none";
        if (ex.getAltura()) {
            this.txtAltura.setStyle(COR);
        } else {
            this.txtAltura.setStyle(NORMAL);
        }
        if (ex.getCidadeDestino()) {
            this.cmbCidadeDestino.setStyle(COR);
        } else {
            this.cmbCidadeDestino.setStyle(NORMAL);
        }
        if (ex.getClienteVo()) {
            this.txtCpfCnpjCliente.setStyle(COR);
        } else {
            this.txtCpfCnpjCliente.setStyle(NORMAL);
        }
        if (ex.getComprimento()) {
            this.txtComprimento.setStyle(COR);
        } else {
            this.txtComprimento.setStyle(NORMAL);
        }
        if (ex.getPlano()) {
            this.rdbCONV.setStyle(COR);
            this.rdbGOLD.setStyle(COR);
            this.rdbPLATINA.setStyle(COR);
        } else {
            this.rdbCONV.setStyle(NONE);
            this.rdbGOLD.setStyle(NONE);
            this.rdbPLATINA.setStyle(NONE);
        }
        if (ex.getCpfCnpjDestinatario()) {
            this.txtCpfCnpjDestino.setStyle(COR);
        } else {
            this.txtCpfCnpjDestino.setStyle(NORMAL);
        }
        if (ex.getLargura()) {
            this.txtLargura.setStyle(COR);
        } else {
            this.txtLargura.setStyle(NORMAL);
        }
        
        if (ex.getNumNotaFiscal()) {
            this.txtNuNotaFiscal.setStyle(COR);
        } else {
            this.txtNuNotaFiscal.setStyle(NORMAL);
        }
        if (ex.getPeso()) {
            this.txtPeso.setStyle(COR);
        } else {
            this.txtPeso.setStyle(NORMAL);
        }
        
        if (ex.getValorDeclarado()) {
            this.txtValorDeclarado.setStyle(COR);
        } else {
            this.txtValorDeclarado.setStyle(NORMAL);
        }
        
        box.BoxInfo(Alert.AlertType.WARNING, Message.message("err.msg.cadastro"), ex.getMsg());
    }
    
    private void save() {
        Encomenda encomenda = getEncomenda();
        loading.start(stpCadastrarEncomenda);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                synchronized (stpCadastrarEncomenda) {
                    TestConnectionServer isOnline = new TestConnectionServer();
                    Future<Integer> future = threadpool.submit(isOnline);
                    try {

                        while (!future.isDone()) {
                            Thread.sleep(1);
                        }
                        if (future.isDone()) {
                            if (!isOnline.CONNECTION_SERVER) {
                                box.BoxInfo(Alert.AlertType.ERROR, Message.message("err.title.BD"), Message.message("erro.msg.offline"));
                                return;
                            }
                            EncomendaException ex = EncomendaNegocio.save(encomenda);
                            loading.stop(stpCadastrarEncomenda);

                            if (ex == null) {
                                threadpool.shutdown();
                                box.BoxInfo(Alert.AlertType.INFORMATION, msg.message("suss.title.Insert"), msg.message("suss.msg.Insert"));
                                cancel();
                            } else {
                                complete_erros(ex);
                            }
                        }

                    } catch (Exception ex) {
                        loading.stop(stpCadastrarEncomenda);
                        box.BoxInfo(Alert.AlertType.ERROR, Message.message("err.title"), ex.getMessage());
                        System.out.println(ex.getMessage());
                    } finally {
                        loading.stop(stpCadastrarEncomenda);
                    }
                }
            }
        });
    }
    
    private void cancel() {
        Navegation node = new Navegation();
        node.getFather(this.stpCadastrarEncomenda);
    }
    
    @FXML
    void onSave(ActionEvent event) {
        save();
    }

    @FXML
    void onCancel(ActionEvent event) {
        cancel();
    }
    
    @FXML
    void onKeyPressedSave(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            save();
        }
    }

    @FXML
    void onKeyPressedCancel(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            cancel();
        }
    }

    @FXML
    void onKeyCPFCNPJCliente() {
        String txt = mask.CpfCnpj(this.txtCpfCnpjCliente.getText());
        this.txtCpfCnpjCliente.setText(txt);
        this.txtCpfCnpjCliente.positionCaret(txt.length());
    }

    @FXML
    void onKeyCPFCNPJDestino() {
        String txt = mask.CpfCnpj(this.txtCpfCnpjDestino.getText());
        this.txtCpfCnpjDestino.setText(txt);
        this.txtCpfCnpjDestino.positionCaret(txt.length());
    }

    @FXML
    void onKeyPressedConv(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.rdbCONV.setSelected(true);
        }
    }

    @FXML
    void onKeyPressedGold(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.rdbGOLD.setSelected(true);
        }
    }

    @FXML
    void onKeyPressedPlatiniun(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.rdbPLATINA.setSelected(true);
        }
    }

    @FXML
    void onKeyComprimento() {
        String txt = mask.OnlyInt(this.txtComprimento.getText());
        this.txtComprimento.setText(txt);
        this.txtComprimento.positionCaret(txt.length());
    }

    @FXML
    void onKeyLargura() {
        String txt = mask.OnlyInt(this.txtLargura.getText());
        this.txtLargura.setText(txt);
        this.txtLargura.positionCaret(txt.length());
    }

    @FXML
    void onKeyAltura() {
        String txt = mask.OnlyInt(this.txtAltura.getText());
        this.txtAltura.setText(txt);
        this.txtAltura.positionCaret(txt.length());
    }

    @FXML
    void onKeyPeso() {
        String txt = mask.OnlyFloat(this.txtPeso.getText());
        this.txtPeso.setText(txt);
        this.txtPeso.positionCaret(txt.length());
    }

    @FXML
    void onKeyNuNotaFiscal() {
        String txt = mask.OnlyInt(this.txtNuNotaFiscal.getText());
        this.txtNuNotaFiscal.setText(txt);
        this.txtNuNotaFiscal.positionCaret(txt.length());
    }

    @FXML
    void onKeyValorDeclarado() {
        String txt = mask.Monetaria(this.txtValorDeclarado.getText());
        this.txtValorDeclarado.setText(txt);
        this.txtValorDeclarado.positionCaret(txt.length());
    }

}
