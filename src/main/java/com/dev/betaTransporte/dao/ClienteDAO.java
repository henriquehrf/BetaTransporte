/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.dao;

import static com.dev.betaTransporte.dao.GenericoDAO.connection;
import com.dev.betaTransporte.vo.Cliente;
import javax.persistence.Query;


/**
 *
 * @author Henrique
 *  * @param <T>
 */
public class ClienteDAO extends GenericoDAO<EntidadeBase>{
    

    public ClienteDAO() throws Exception {
        if (connection == null) {
            getEM();
        }
    } 
    
        
    public Cliente GetByCPFCNPJ(String cpfCNPJF) {

        try {
            if (connection == null) {
                getEM();
            }
            Query query = connection.createNamedQuery("cliente.SelectUser", Cliente.class);
            query.setParameter("login", cpfCNPJF);
            return (Cliente) query.getSingleResult();

        } catch (Exception ex) {
            return null;
        }
    }
    
}
