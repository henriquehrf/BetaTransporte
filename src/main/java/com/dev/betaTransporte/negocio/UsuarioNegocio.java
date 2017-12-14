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
import util.BoxInfo;
import util.Criptografia;
import util.Message;
import util.Util;

/**
 *
 * @author Henrique
 */
public class UsuarioNegocio {

    BoxInfo box = new BoxInfo();

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

    private static UsuarioException validar(Usuario usuario) {

        UsuarioException ex = new UsuarioException();
        Util util = new Util();

        if (usuario.getTipoFuncionario() == 3) {
            ex.setTipoFuncionario(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.tipoFuncionario"));
        }

        if (usuario.getNome().length() == 0) {
            ex.setNome(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.nomePreencher"));
        }
        if (usuario.getNome().length() > 100) {
            ex.setNome(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.nomeMaior"));
        }

//        if (usuario.getTelefone().length() > 0 && usuario.getTelefone().length() < 14) {
//            ex.setTelefoneFixo(Boolean.TRUE);
//            ex.setMsg(Message.message("err.msg.telefoneInvalido"));
//        }
//        if (usuario.getTelefone().length() > 0 && usuario.getCelular().length() < 15) {
//            ex.setTelefoneCelular(Boolean.TRUE);
//            ex.setMsg(Message.message("err.msg.celularInvalido"));
//        }
//        if (usuario.getTelCelular().length() == 0) {
//            ex.setTelefoneCelular(Boolean.TRUE);
//            ex.setMsg(Message.message("err.msg.celularPreencher"));
//        }
//        if (usuario.getEmail().length() == 0) {
//            ex.setEmail(Boolean.TRUE);
//            ex.setMsg(Message.message("err.msg.emailPreencher"));
//        }
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
}
