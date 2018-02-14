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
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Status;
import java.util.List;
import javafx.scene.control.Alert;
import util.BoxInfo;
import util.Message;
import util.Util;

/**
 *
 * @author Daniel
 */
public class EncomendaNegocio {

    BoxInfo boxInfo = new BoxInfo();

    public static EncomendaException validar(Encomenda encomenda) throws Exception {
        ClienteDAO clienteN = new ClienteDAO();

        EncomendaException ex = new EncomendaException();

        Util util = new Util();

        if (encomenda.getClienteVO().getCpfCnpj().length() == 0) {
            ex.setClienteVo(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.remetentePreencher"));
        } else {
            Cliente cliente;
            System.out.println(encomenda.getClienteVO().getCpfCnpj());
            cliente = clienteN.GetByCPFCNPJ(encomenda.getClienteVO().getCpfCnpj());
            if (cliente == null) {
                ex.setClienteVo(Boolean.TRUE);
                ex.setMsg(Message.message("err.msg.remetenteInvalido"));
            } else {
                encomenda.setClienteVO(cliente);
            }

        }

        if (encomenda.getCpfCnpjDestinatario().length() == 0) {
            ex.setCpfCnpjDestinatario(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.cpfCnpjPreencherDestinatario"));
        } else {
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

        if (encomenda.getPeso() == 0) {
            ex.setPeso(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.pesoPreencher"));
        }

        if (encomenda.getNumNotaFiscal() == 0) {
            ex.setNumNotaFiscal(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.numNotaFiscalPreencher"));
        }

        if (encomenda.getValorDeclarado() == 0) {
            ex.setValorDeclarado(Boolean.TRUE);
            ex.setMsg(Message.message("err.msg.valorDeclaradoPreencher"));
        }

        if (encomenda.getCidadeDestino() == null) {
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
                            //System.out.println("Entrei aqui");
                            //System.out.println(encomenda.getCpfCnpjDestinatario());
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

    public List<Encomenda> searchEncomendaEntrega(Cidade cidade, Status status) throws Exception {
        List<Encomenda> result;
        EncomendaDAO encomendaList = new EncomendaDAO();
        try {
            result = encomendaList.GetAllEntrega(cidade, status);
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Encomenda> searchEncomenda(String content, String type) {
        List<Encomenda> result;
        try {
            EncomendaDAO encomendaList = new EncomendaDAO();
            if (content.length() > 0) {
                if (type.equalsIgnoreCase("cpfCnpj")) {
                    result = encomendaList.GetAllCPFCNPJDestinatario(content);
                    return result;
                } else {
                    result = encomendaList.GetAllByPlano(content);
                    return result;
                }
            } else {
                result = encomendaList.GetAllEnc();
                return result;

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Encomenda> searchEncomenda2(int aux, Cidade cidadeOrigem, Status status, Status status2) {
        List<Encomenda> result;
        try {
            EncomendaDAO encomendaList = new EncomendaDAO();
            result = encomendaList.GetAllEncCitStatus(cidadeOrigem, status);
            if (aux == 1) {
                return result;

            } else {
                List<Encomenda> result2;
                result2 = encomendaList.GetAllEncCitStatus(cidadeOrigem, status2);
                result.addAll(result);
                return result;

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public int excluirEncomenda(Encomenda encomenda) {

        try {
            GenericoDAO dao = new GenericoDAO();
            dao.remove(Encomenda.class, encomenda);
        } catch (Exception ex) {

            boxInfo.BoxInfo(Alert.AlertType.ERROR, Message.message("err.msg.exclusãoRegistro"), ex.getMessage());
            System.out.println(ex.getMessage());
            return -1;
        }

        return 1;
    }

    public List<Encomenda> searchEncomendabyID(int id) {
        List<Encomenda> result;
        try {
            EncomendaDAO encomendaList = new EncomendaDAO();
            if (id > 0) {
                result = encomendaList.GetAllById(id);
                return result;

            } else {
                result = encomendaList.GetAllEnc();
                return result;

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
