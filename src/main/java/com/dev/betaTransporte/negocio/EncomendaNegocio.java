/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.negocio;

import com.dev.betaTransporte.dao.ClienteDAO;
import com.dev.betaTransporte.dao.EncomendaDAO;
import com.dev.betaTransporte.dao.GenericoDAO;
import com.dev.betaTransporte.negocio.exception.EncomendaException;
import com.dev.betaTransporte.vo.Cliente;
import com.dev.betaTransporte.vo.Encomenda;
import util.Message;
import util.Util;

/**
 *
 * @author Daniel
 */
public class EncomendaNegocio {
    
    
    private static EncomendaException validar(Encomenda encomenda) throws Exception {
        ClienteDAO clienteN = new ClienteDAO();
        
        EncomendaException ex = new EncomendaException();
        
        Util util = new Util();

        if (encomenda.getClienteVO().getCpfCnpj().length() == 0) {
            ex.setClienteVo(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.remetentePreencher"));
        }else{
            Cliente cliente;
            System.out.println(encomenda.getClienteVO().getCpfCnpj());
            cliente= clienteN.GetByCPFCNPJ(encomenda.getClienteVO().getCpfCnpj());
            if (cliente==null){
                ex.setClienteVo(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.remetenteInvalido"));
            }
            
        }
        
        if (encomenda.getCpfCnpjDestinatario().length() == 0) {
            ex.setCpfCnpjDestinatario(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.cpfCnpjPreencherDestinatario"));
        }else {
            if (!util.ValidarCPFCNPJ(encomenda.getCpfCnpjDestinatario())) {
                ex.setCpfCnpjDestinatario(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.cpfCnpjInvalidoDestinatario"));
            }
        }

        if (encomenda.getPlano() == null) {
            ex.setPlano(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.planoPreencher"));
        }
        
        if (encomenda.getComprimento() == 0) {
            ex.setComprimento(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.comprimentoPreencher"));
        }
        
        if (encomenda.getAltura() == 0) {
            ex.setAltura(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.alturaPreencher"));
        }
        
        if (encomenda.getLargura() == 0) {
            ex.setLargura(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.larguraPreencher"));
        }
        
        if (encomenda.getPeso()== 0) {
            ex.setPeso(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.pesoPreencher"));
        }
        
        
        if (encomenda.getNumNotaFiscal() ==0) {
            ex.setNumNotaFiscal(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.numNotaFiscalPreencher"));
        }
        
        if (encomenda.getValorDeclarado()==0) {
            ex.setValorDeclarado(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.valorDeclaradoPreencher"));
        }
        
        if (encomenda.getCidadeDestino()==null) {
            ex.setCidadeDestino(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.cidadeDestinoPreencher"));
        }
        
        return ex;
    }
    
    public static EncomendaException save(Encomenda encomenda) throws Exception {
        EncomendaException cli_ex = validar(encomenda);
        if (cli_ex.getMsg().trim().length() > 0) {
            return cli_ex;
        } else {
            try {
                Thread t = new Thread() {
                    GenericoDAO dao = new EncomendaDAO();

                    public void run() {
                        try {
                            System.out.println("Entrei aqui");
                            System.out.println(encomenda.getCpfCnpjDestinatario());
                            dao.save(Encomenda.class, encomenda);
                        } catch (Exception ex) {
                            cli_ex.setMsg(ex.getMessage());
                        }
                    }
                };
                t.start();

            } catch (Exception ex) {

                cli_ex.setMsg(ex.getMessage());
                return cli_ex;
            }
            return null;
        }

    }
    
}