/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.negocio.ClienteNegocio;
import com.dev.betaTransporte.negocio.exception.ClienteException;
import com.dev.betaTransporteENUM.Sexo;
import com.dev.betaTransporte.vo.Cliente;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.BoxInfo;
import util.Loader;
import util.Message;
import util.Mask;
import util.Navegation;
import util.TestConnectionServer;
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
    
    public static int NextPage;
    public static Cliente clienteAlter;
    
    private ExecutorService threadpool = Executors.newFixedThreadPool(3);
    Util util = new Util();
    BoxInfo box = new BoxInfo();
    Message msg = new Message();
    Mask mask = new Mask();
    Loader loading = new Loader();
    
    Navegation navegation = new Navegation();
    
    private Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpfCnpj(this.txtCpfCnpj.getText());
        cliente.setEmail(this.txtEmail.getText().toLowerCase());
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
        box.BoxInfo(Alert.AlertType.WARNING, Message.message("err.msg.cadastro"), ex.getMsg());
    }
    
    private void save() {
        Cliente cliente = getCliente();
        
        if (clienteAlter != null) {
            cliente.setIdCliente(clienteAlter.getIdCliente());
        }
        
        loading.start(stpCadastrarCliente);
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                synchronized (stpCadastrarCliente) {
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
                            ClienteException ex = ClienteNegocio.save(cliente);
                            loading.stop(stpCadastrarCliente);
                            
                            if (ex == null) {
                                threadpool.shutdown();
                                box.BoxInfo(Alert.AlertType.INFORMATION, msg.message("suss.title.Insert"), msg.message("suss.msg.Insert"));
                                cancel();
                            } else {
                                complete_erros(ex);
                            }
                        }
                        
                    } catch (Exception ex) {
                        loading.stop(stpCadastrarCliente);
                        box.BoxInfo(Alert.AlertType.ERROR, Message.message("err.title"), ex.getMessage());
                        System.out.println(ex.getMessage());
                    } finally {
                        loading.stop(stpCadastrarCliente);
                    }
                }
            }
        });
    }
    
    @FXML
    void onSave(ActionEvent event) {
        save();
    }
    
    void completeInfo(Cliente cliente) {
        this.txtCpfCnpj.setText(cliente.getCpfCnpj());
        this.txtNomeRazaoSocial.setText(cliente.getNome());
        this.txtEmail.setText(cliente.getEmail());
        this.txtTelefoneCelular.setText(cliente.getTelCelular());
        this.txtTelefoneFixo.setText(cliente.getTelFixo());
        if (cliente.getSexo() == Sexo.F) {
            this.rdbFemino.setSelected(true);
        } else if (cliente.getSexo() == Sexo.M) {
            this.rdbMasculino.setSelected(true);
        } else {
            this.rdbNaoAplica.setSelected(true);
        }
        
        this.dtpDataNascimento.setValue(util.Date_To_LocalDate(cliente.getDataNascimento()));
        
        if (cliente.getCpfCnpj().length() > 14) {
            this.dtpDataNascimento.setDisable(true);
            this.dtpDataNascimento.setValue(null);
            this.rdbFemino.setDisable(true);
            this.rdbMasculino.setDisable(true);
            this.rdbNaoAplica.setSelected(true);
            this.rdbFemino.setSelected(false);
            this.rdbMasculino.setSelected(false);
        }
    }
    
    private void cancel() {
        if (NextPage == 0) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/gui/ConsultarCliente.fxml"), ResourceBundle.getBundle("docs/i18N_pt_BR"));
                navegation.getMain().setCenter(root);
                clienteAlter = null;
            } catch (Exception ex) {
                System.err.println(ex);
            }
            
        }
    }
    
    @FXML
    void onCancel(ActionEvent event) {
        cancel();
    }
    
    @FXML
    void onChangeCPFCNPJ() {
        String txt = mask.CpfCnpj(this.txtCpfCnpj.getText());
        this.txtCpfCnpj.setText(txt);
        this.txtCpfCnpj.positionCaret(txt.length());
        
        if (txt.length() > 14) {
            this.rdbFemino.setDisable(true);
            this.rdbMasculino.setDisable(true);
            this.rdbNaoAplica.setSelected(true);
            this.rdbNaoAplica.setDisable(false);
            this.dtpDataNascimento.setDisable(true);
            this.dtpDataNascimento.setValue(null);
        } else {
            this.rdbFemino.setDisable(false);
            this.rdbNaoAplica.setDisable(true);
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
    
    @FXML
    void onKeyPressedFeminino(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.rdbFemino.setSelected(true);
        }
    }
    
    @FXML
    void onKeyPressedMasculino(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.rdbMasculino.setSelected(true);
        }
    }
    
    @FXML
    void onKeyPressedNAplica(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.rdbNaoAplica.setSelected(true);
        }
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (clienteAlter != null) {
            completeInfo(clienteAlter);
        }
        // exemplo de evento na perda do foco
        //loading.start(stpCadastrarCliente);
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
