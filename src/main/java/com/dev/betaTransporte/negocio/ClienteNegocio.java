/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.negocio;

import com.dev.betaTransporte.dao.ClienteDAO;
import com.dev.betaTransporte.dao.GenericoDAO;
import com.dev.betaTransporte.negocio.exception.ClienteException;
import com.dev.betaTransporte.vo.Cliente;
import util.Message;
import util.Util;

/**
 *
 * @author Henrique
 */
public class ClienteNegocio {

    public static ClienteException validar(Cliente cliente) {

        ClienteException ex = new ClienteException();
        Util util = new Util();

        if (cliente.getCpfCnpj() != null) {
            if (cliente.getCpfCnpj().length() == 0) {
                ex.setCpfCnpj(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.cpfCnpjPreencher"));
            }
            if (cliente.getCpfCnpj().length() > 0) {
                if (!util.ValidarCPFCNPJ(cliente.getCpfCnpj())) {
                    ex.setCpfCnpj(Boolean.TRUE);
                    ex.setMsg(Message.message("err.msg.cpfCnpjInvalido"));
                }
            }
            if (cliente.getCpfCnpj().length() == 14) {
                if (!util.ValidarIdade(cliente.getDataNascimento())) {
                    ex.setDtNascimento(Boolean.TRUE);
                    ex.setMsg(Message.message("erro.msg.dtNasMenor"));
                }
            }
            if (cliente.getCpfCnpj().length() <= 14 && cliente.getDataNascimento() == null) {
                ex.setDtNascimento(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.dtNascimento"));
            }
        } else {
            ex.setCpfCnpj(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.cpfCnpjPreencher"));
        }
        if (cliente.getNome() != null) {
            if (cliente.getNome().length() == 0) {
                ex.setNomeRazaoSocial(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.nomePreencher"));
            }
            if (cliente.getNome().length() > 100) {
                ex.setNomeRazaoSocial(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.nomeMaior"));
            }
        } else {
            ex.setNomeRazaoSocial(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.nomePreencher"));
        }

        if (cliente.getSexo() == null) {
            ex.setSexo(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.sexoPreencher"));
        }
        if (cliente.getTelFixo() != null) {
            if (cliente.getTelFixo().length() > 0 && cliente.getTelFixo().length() < 14) {
                ex.setTelefoneFixo(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.telefoneInvalido"));
            }
        }
        if (cliente.getTelCelular() != null) {
            if (cliente.getTelCelular().length() > 0 && cliente.getTelCelular().length() < 15) {
                ex.setTelefoneCelular(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.celularInvalido"));
            }
            if (cliente.getTelCelular().length() == 0) {
                ex.setTelefoneCelular(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.celularPreencher"));
            }
        } else {
            ex.setTelefoneCelular(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.celularPreencher"));
        }

        if (cliente.getEmail() != null) {
            if (cliente.getEmail().length() == 0) {
                ex.setEmail(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.emailPreencher"));
            }
        } else {
            ex.setEmail(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.emailPreencher"));
        }

        return ex;
    }

    public static ClienteException save(Cliente cliente) {
        ClienteException cli_ex = validar(cliente);
        if (cli_ex.getMsg().trim().length() > 0) {
            return cli_ex;
        } else {
            try {
                Thread t = new Thread() {
                    GenericoDAO dao = new ClienteDAO();

                    public void run() {
                        try {
                            dao.save(Cliente.class, cliente);
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
