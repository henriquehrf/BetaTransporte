/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.clienteTest;

import com.dev.betaTransporte.negocio.ClienteNegocio;
import com.dev.betaTransporte.negocio.exception.ClienteException;
import com.dev.betaTransporte.vo.Cliente;
import com.dev.betaTransporteENUM.Sexo;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import util.Message;

/**
 *
 * @author Henrique
 */
public class ClienteTest {

    Cliente cliente = new Cliente();
    ClienteException ex = new ClienteException();
    ClienteNegocio negocio = new ClienteNegocio();
    ClienteException expected = new ClienteException();

    public ClienteTest() {
        cliente.setCpfCnpj("569.842.211-70");
        cliente.setNome("Fulano");
        cliente.setEmail("fulano@gmail.com");
        cliente.setSexo(Sexo.M);
        cliente.setTelCelular("(65) 99999-9999");
        cliente.setTelFixo("(65) 9999-9999");
        Date date = new Date("08/12/1995");
        cliente.setDataNascimento(date);
    }

    @Test
    public void dtNascimento() {
        //------------------------------------
        expected = negocio.validar(cliente);
        assertEquals(ex.getMsg(), expected.getMsg());
        ex.setDtNascimento(Boolean.TRUE);
        ex.setMsg(Message.message("erro.msg.dtNasMenor"));
        cliente.setDataNascimento(new Date());
        expected = negocio.validar(cliente);
        assertEquals(ex.getMsg(), expected.getMsg());
        assertEquals(ex.getDtNascimento(), expected.getDtNascimento());
    }

    @After
    public void refreshDate() {
        Date date = new Date("08/12/1995");
        cliente.setDataNascimento(date);
    }

    @Test
    public void cpfInvalido() {
        cliente.setCpfCnpj("055.820.620-02");
        ex.setCpfCnpj(Boolean.TRUE);
        ex.setMsg(Message.message("err.msg.cpfCnpjInvalido"));
        expected = negocio.validar(cliente);
        assertEquals(ex.getMsg(), expected.getMsg());
        assertEquals(ex.getCpfCnpj(), expected.getCpfCnpj());
    }

    @Test
    public void cnpjInvalido() {
        cliente.setCpfCnpj("08.852.498/0001-20");
        ex.setCpfCnpj(Boolean.TRUE);
        ex.setMsg(Message.message("err.msg.cpfCnpjInvalido"));
        expected = negocio.validar(cliente);
        assertEquals(ex.getMsg(), expected.getMsg());
        assertEquals(ex.getCpfCnpj(), expected.getCpfCnpj());
    }

    @Before
    public void refreshCpfCnpj() {
        cliente.setCpfCnpj("215.441.777-98");
    }

    @Test
    public void celularInvalido() {
        cliente.setTelCelular("5454");
        ex = new ClienteException();
        ex.setTelefoneCelular(Boolean.TRUE);
        ex.setMsg(Message.message("err.msg.celularInvalido"));
        expected = negocio.validar(cliente);
        assertEquals(ex.getMsg(), expected.getMsg());
        assertEquals(ex.getTelefoneCelular(), expected.getTelefoneCelular());
    }

    @Before
    public void refreshCelularInvalido() {
        cliente.setTelCelular("(65) 99999-9999");
    }

    @Test
    public void emailInvalido() {
        cliente.setEmail("");
        ex = new ClienteException();
        ex.setEmail(Boolean.TRUE);
        ex.setMsg(Message.message("err.msg.emailPreencher"));
    }

}
