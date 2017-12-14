/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte;

import com.dev.betaTransporte.negocio.UsuarioNegocio;
import com.dev.betaTransporte.negocio.exception.ClienteException;
import com.dev.betaTransporte.negocio.exception.UsuarioException;
import com.dev.betaTransporte.vo.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.BoxInfo;
import util.Criptografia;
import util.Loader;
import util.Mask;
import util.Message;
import util.Navegation;
import util.SeparaNome;
import util.TestConnectionServer;
import util.Util;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class CadastrarUsuarioController implements Initializable {

    @FXML
    private RadioButton rdbAtendente;

    @FXML
    private TextField txtTelefoneCelular;

    @FXML
    private BorderPane bdpCadastroUsuario;

    @FXML
    private ToggleGroup Funcionario;

    @FXML
    private StackPane stpCadastrarUsuario;

    @FXML
    private TextField txtTelefoneFixo;

    @FXML
    private RadioButton rdbCarregador;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private RadioButton rdbDescarregador;

    @FXML
    void onSave(ActionEvent event) {
        save();
    }

    @FXML
    void onKeyPressedSave(KeyEvent event) {

    }

    private void cancel() {
        Navegation node = new Navegation();
        node.getFather(this.stpCadastrarUsuario);
    }

    @FXML
    void onCancel(ActionEvent event) {
        cancel();
    }

    @FXML
    void onKeyPressedCancel(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            cancel();
        }

    }

    private ExecutorService threadpool = Executors.newFixedThreadPool(3);
    Util util = new Util();
    BoxInfo box = new BoxInfo();
    Message msg = new Message();
    Mask mask = new Mask();
    Loader loading = new Loader();

    private Usuario getUsuario() {
        Usuario usuario = new Usuario();
        if (this.rdbAtendente.isSelected()) {
            usuario.setTipoFuncionario(1);
        }
        if (this.rdbCarregador.isSelected()) {
            usuario.setTipoFuncionario(2);
        }
        if (this.rdbDescarregador.isSelected()) {
            usuario.setTipoFuncionario(2);
        }
        usuario.setNome(this.txtNome.getText());
        usuario.setTelefone(this.txtTelefoneFixo.getText());
        usuario.setCelular(this.txtTelefoneCelular.getText());
        usuario.setEmail(this.txtEmail.getText());

        //Código responsável por quebrar o nome para gerar o login = nome.sobrenome
        SeparaNome nomeSeparado = new SeparaNome(this.txtNome.getText().toLowerCase());
        //Código responsável por criptografar a senha do usuário
        Criptografia senha_criptografada = new Criptografia(nomeSeparado.getLogin(), nomeSeparado.getLogin());
        usuario.setLogin(nomeSeparado.getLogin());
        usuario.setSenha(senha_criptografada.getSenha_criptografada());
        //

        return usuario;

    }

    void complete_erros(UsuarioException ex) {
        final String COR = "-fx-border-color:red";
        final String NORMAL = "-fx-border-color:darkgrey";
        final String NONE = "-fx-border-color:none";



        if (ex.getTipoFuncionario()) {
            this.rdbAtendente.setStyle(COR);
            this.rdbCarregador.setStyle(COR);
            this.rdbDescarregador.setStyle(COR);
        } else {
            this.rdbAtendente.setStyle(NONE);
            this.rdbCarregador.setStyle(NONE);
            this.rdbDescarregador.setStyle(NONE);
        }
        if (ex.getNome()) {
            this.txtTelefoneCelular.setStyle(COR);
        } else {
            this.txtTelefoneCelular.setStyle(NORMAL);
        }

        box.BoxInfo(Alert.AlertType.WARNING, Message.message("err.msg.cadastro"), ex.getMsg());
    }

    private void save() {
        Usuario usuario = getUsuario();
        loading.start(stpCadastrarUsuario);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                synchronized (stpCadastrarUsuario) {
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
                            UsuarioException ex = UsuarioNegocio.save(usuario);
                            loading.stop(stpCadastrarUsuario);

                            if (ex == null) {
                                threadpool.shutdown();
                                box.BoxInfo(Alert.AlertType.INFORMATION, msg.message("suss.title.Insert"), msg.message("suss.msg.Insert"));
                                cancel();
                            } else {
                                complete_erros(ex);
                            }
                        }

                    } catch (Exception ex) {
                        loading.stop(stpCadastrarUsuario);
                        box.BoxInfo(Alert.AlertType.ERROR, Message.message("err.title"), ex.getMessage());
                        System.out.println(ex.getMessage());
                    } finally {
                        loading.stop(stpCadastrarUsuario);
                    }
                }
            }
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
