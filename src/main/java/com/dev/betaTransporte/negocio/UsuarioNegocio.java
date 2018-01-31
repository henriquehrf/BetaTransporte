/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.negocio;

import com.dev.betaTransporte.dao.GenericoDAO;
import com.dev.betaTransporte.dao.UsuarioDAO;
import com.dev.betaTransporte.negocio.exception.LoginException;
import com.dev.betaTransporte.negocio.exception.UsuarioException;
import com.dev.betaTransporte.vo.Usuario;
import java.util.List;
import javafx.scene.control.Alert;
import util.BoxInfo;
import util.Criptografia;
import util.Message;
import util.Util;

/**
 *
 * @author Henrique
 */
public class UsuarioNegocio {

    BoxInfo boxInfo = new BoxInfo();

    public static Usuario user = null;

    public LoginException Login(String Usuario, String Senha) {

        UsuarioDAO dao;
        LoginException loginEx = new LoginException();
        Criptografia senhaCrip = new Criptografia(Usuario.toLowerCase(), Senha);

        try {
            dao = new UsuarioDAO();
            user = dao.GetUser(Usuario);

            if (user == null) {

                loginEx.setUsuario(Boolean.TRUE);
                loginEx.setMsg("err.msg.loginInvalido");
            }
            if (user != null) {
                if (!user.getSenha().equalsIgnoreCase(senhaCrip.getSenha_criptografada())) {
                    loginEx.setMsg("err.msg.senhaInvalida");
                    loginEx.setSenha(Boolean.TRUE);
                }
            }
        } catch (Exception ex) {
            loginEx.setMsg(ex.getMessage());
        }
        return loginEx;
    }

    public static UsuarioException validar(Usuario usuario) {

        UsuarioException ex = new UsuarioException();
        Util util = new Util();

        if (usuario.getFuncionario() == null) {
            ex.setFuncionario(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.tipoFuncionario"));
        }

        if (usuario.getNome() != null) {
            if (usuario.getNome().length() == 0) {
                ex.setNome(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.nomePreencher"));
            }
            if (usuario.getNome().length() > 100) {
                ex.setNome(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.nomeMaior"));
            }
        } else {
            ex.setNome(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.nomePreencher"));
        }

        if (usuario.getCidade() == null) {
            ex.setCidade(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.cidade"));
        }

        return ex;
    }

    public static UsuarioException save(Usuario usuario) {
        UsuarioException usu_ex = validar(usuario);
        if (usu_ex.getMsg().trim().length() > 0) {
            return usu_ex;
        } else {
            try {
                Thread t = new Thread() {
                    GenericoDAO dao = new UsuarioDAO();

                    public void run() {
                        try {
                            dao.save(Usuario.class, usuario);
                        } catch (Exception ex) {
                            usu_ex.setMsg(ex.getMessage());
                        }
                    }
                };
                t.start();

            } catch (Exception ex) {

                usu_ex.setMsg(ex.getMessage());
                return usu_ex;
            }
            return null;
        }
    }

    public List<Usuario> searchUsuario(String content, String type) {
        List<Usuario> result;
        try {
            UsuarioDAO usuarioList = new UsuarioDAO();
            if (content.length() > 0) {
                result = usuarioList.GetAllByNome(content);
                return result;

            } else {
                result = usuarioList.GetAll();
                return result;

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public int excluirUsuario(Usuario usuario) {

        try {
            GenericoDAO dao = new GenericoDAO();
            dao.remove(Usuario.class, usuario);
        } catch (Exception ex) {

            boxInfo.BoxInfo(Alert.AlertType.ERROR, Message.message("err.msg.exclusãoRegistro"), ex.getMessage());
            System.out.println(ex.getMessage());
            return -1;
        }

        return 1;
    }

}
