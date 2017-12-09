/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.negocio;

import com.dev.betaTransporte.dao.UsuarioDAO;
import com.dev.betaTransporte.negocio.exception.LoginException;
import com.dev.betaTransporte.vo.Usuario;
import util.BoxInfo;
import util.Criptografia;

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
}
