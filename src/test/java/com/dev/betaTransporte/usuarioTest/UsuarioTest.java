///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.dev.betaTransporte.usuarioTest;
//
//import com.dev.betaTransporte.negocio.UsuarioNegocio;
//import com.dev.betaTransporte.negocio.exception.ClienteException;
//import com.dev.betaTransporte.negocio.exception.UsuarioException;
//import com.dev.betaTransporte.vo.Usuario;
//import com.dev.betaTransporteENUM.Sexo;
//import java.util.Date;
//import static org.junit.Assert.assertEquals;
//import org.junit.Test;
//import util.Message;
//
///**
// *
// * @author Adam
// */
//public class UsuarioTest {
//
//    Usuario usuario = new Usuario();
//    UsuarioException ex = new UsuarioException();
//    UsuarioNegocio negocio = new UsuarioNegocio();
//    UsuarioException expected = new UsuarioException();
//
//    public UsuarioTest() {
//        usuario.setTipoFuncionario(0);
//        usuario.setNome("Nome Teste");
//        usuario.setLogin("123");
//        usuario.setSenha("96CAE35CE8A9B0244178BF28E4966C2CE1B8385723A96A6B838858CDD6CA0A1E");
//    }
//
//    @Test
//    public void nomeInvalido() {
//        usuario.setNome("");
//        ex = new UsuarioException();
//        ex.setNome(Boolean.TRUE);
//        ex.setMsg(Message.message("err.msg.nomePreencher"));
//        expected = negocio.validar(usuario);
//        assertEquals(ex.getMsg(), expected.getMsg());
//        assertEquals(ex.getNome(), expected.getNome());
//    }
//
//    @Test
//    public void funcionarioInvalido() {
//        usuario.setTipoFuncionario(3);
//        ex = new UsuarioException();
//        ex.setTipoFuncionario(Boolean.TRUE);
//        ex.setMsg(Message.message("err.msg.tipoFuncionario"));
//        expected = negocio.validar(usuario);
//        assertEquals(ex.getMsg(), expected.getMsg());
//        assertEquals(ex.getTipoFuncionario(), expected.getTipoFuncionario());
//    }
//
//}
