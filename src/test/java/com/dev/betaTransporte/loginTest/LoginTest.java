/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.loginTest;

import com.dev.betaTransporte.negocio.UsuarioNegocio;
import com.dev.betaTransporte.negocio.exception.LoginException;
import com.dev.betaTransporte.vo.Usuario;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Henrique
 */
public class LoginTest {

    UsuarioNegocio negocio = new UsuarioNegocio();
    Usuario usuario = new Usuario();
    LoginException ex = new LoginException();
    final String user = "daniel";
    final String password = "123";

    public LoginTest() {
        usuario.setLogin(user);
        usuario.setSenha(password);
    }

    @Test(timeout = 10000)
    public void validarTimeOut() {
        negocio.Login(usuario.getLogin(), usuario.getSenha());
    }

    @Test
    public void validarLogin() {
        LoginException result = negocio.Login(usuario.getLogin(), usuario.getSenha());
        assertEquals(ex.getMsg(), result.getMsg());
        assertEquals(ex.getUsuario(), result.getUsuario());
    }

    @Test
    public void validarLoginInvalido() {
        usuario.setLogin("-");
        ex.setUsuario(Boolean.TRUE);
        ex.setMsg("err.msg.loginInvalido");

        LoginException result = negocio.Login(usuario.getLogin(), usuario.getSenha());
        assertEquals(ex.getMsg(), result.getMsg());
        assertEquals(ex.getUsuario(), result.getUsuario());
    }

    @After
    public void refreshLogin() {
        usuario.setLogin(user);
    }

    @Test
    public void validarSenha() {
        usuario.setSenha("-");
        ex.setSenha(Boolean.TRUE);
        ex.setMsg("err.msg.senhaInvalida");

        LoginException result = negocio.Login(usuario.getLogin(), usuario.getSenha());
        assertEquals(ex.getMsg(), result.getMsg());
        assertEquals(ex.getSenha(), result.getSenha());
    }

}
