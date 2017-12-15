/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.encomendaTeste;

import com.dev.betaTransporte.negocio.EncomendaNegocio;
import com.dev.betaTransporte.negocio.exception.EncomendaException;
import com.dev.betaTransporte.vo.Encomenda;
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Plano;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import util.Message;

/**
 *
 * @author Daniel
 */
public class EncomendaTeste {
    Encomenda encomenda = new Encomenda();
    EncomendaException ex = new EncomendaException();
    EncomendaNegocio negocio = new EncomendaNegocio();
    EncomendaException expected = new EncomendaException();
    
    public EncomendaTeste() {
        encomenda.getClienteVO().setCpfCnpj("706.187.175-01");
        encomenda.setCpfCnpjDestinatario("569.842.211-70");
        encomenda.setAltura(5);
        encomenda.setCidadeDestino(Cidade.CUIABA);
        encomenda.setComprimento(5);
        encomenda.setDataCadastro(new Date());
        encomenda.setLargura(5);
        encomenda.setNumNotaFiscal(11111);
        encomenda.setPeso(53);
        encomenda.setPlano(Plano.BETA_PLATINIUN);
        encomenda.setValorDeclarado(10000);
    }
    
    @Test
    public void cpfClienteInexistente() throws Exception {
        encomenda.getClienteVO().setCpfCnpj("055.820.620-02");
        ex.setClienteVo(Boolean.TRUE);
        ex.setMsg(Message.message("err.msg.remetenteInvalido"));
        expected = negocio.validar(encomenda);
        assertEquals(ex.getMsg(), expected.getMsg());
        assertEquals(ex.getClienteVo(), expected.getClienteVo());
    }
    
    @Before
    public void refreshCpfCnpj() {
        encomenda.getClienteVO().setCpfCnpj("706.187.175-01");
    }
    
    @Test
    public void cidadeInexistente() throws Exception {
        encomenda.setLargura(0);
        ex = new EncomendaException();
        ex.setLargura(Boolean.TRUE);
        ex.setMsg(Message.message("err.msg.larguraPreencher"));
        expected = negocio.validar(encomenda);
        assertEquals(ex.getMsg(), expected.getMsg());
        assertEquals(ex.getLargura(), expected.getLargura());
    }
    
    @After
    public void refreshLargura() {
        encomenda.setLargura(2);
    }
    
    @Test
    public void PlanoVazio() throws Exception {
        encomenda.setPlano(null);
        ex = new EncomendaException();
        ex.setPlano(Boolean.TRUE);
        ex.setMsg(Message.message("err.msg.planoPreencher"));
        expected = negocio.validar(encomenda);
        assertEquals(ex.getMsg(), expected.getMsg());
        assertEquals(ex.getPlano(), expected.getPlano());
    }
}
