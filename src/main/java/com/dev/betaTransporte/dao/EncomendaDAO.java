/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.dao;

import com.dev.betaTransporte.vo.Encomenda;
import com.dev.betaTransporteENUM.Plano;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Daniel
 */
public class EncomendaDAO extends GenericoDAO<EntidadeBase>{
    public EncomendaDAO() throws Exception {
        if (connection == null) {
            getEM();
        }
    } 
    
    public List<Encomenda> GetAllCPFCNPJDestinatario(String cpfCnpj) {
        try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }
            Query query = connection.createNamedQuery("Encomenda.GetAllCPFCNPJDestinatario", Encomenda.class);
            query.setParameter("cpfCnpj", cpfCnpj);
            List<Encomenda> encomenda = query.getResultList();
            return encomenda;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public List<Encomenda> GetAllEnc() {

        try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }

            Query query = connection.createNamedQuery("Encomenda.GetAllEnc", Encomenda.class);

            List<Encomenda> encomenda = query.getResultList();
            return encomenda;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
    
    public List<Encomenda> GetAllByPlano(String plano) {
        try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }

            Query query = connection.createNamedQuery("Encomenda.GetAllByPlano", Encomenda.class);
            if (plano.equals("0")){
                query.setParameter("plano", Plano.BETA_CONV);
            }else if(plano.equals("1")){
                query.setParameter("plano", Plano.BETA_GOLD);
            }else{
                query.setParameter("plano", Plano.BETA_PLATINIUN);
            }
            List<Encomenda> encomenda = query.getResultList();
            return encomenda;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
