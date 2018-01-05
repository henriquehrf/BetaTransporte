/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.dao;

import static com.dev.betaTransporte.dao.GenericoDAO.connection;
import com.dev.betaTransporte.vo.Cliente;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Henrique
 *  * @param <T>
 */
public class ClienteDAO extends GenericoDAO<EntidadeBase> {

    public ClienteDAO() throws Exception {
        if (connection == null || !connection.isOpen()) {
            getEM();
        }
    }

    public Cliente GetByCPFCNPJ(String cpfCnpj) {

        try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }
            Query query = connection.createNamedQuery("Cliente.GetCPFCNPJ", Cliente.class);
            query.setParameter("cpfCnpj", cpfCnpj);
            return (Cliente) query.getSingleResult();

        } catch (Exception ex) {
            return null;
        }
    }

    public List<Cliente> GetAll() {

        try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }

            Query query = connection.createNamedQuery("Cliente.GetAll", Cliente.class);

            List<Cliente> cliente = query.getResultList();
            return cliente;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public List<Cliente> GetAllByNome(String nome) {
        try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }

            Query query = connection.createNamedQuery("Cliente.GetAllByNome", Cliente.class);
            query.setParameter("nome", nome);
            List<Cliente> cliente = query.getResultList();
            return cliente;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Cliente> GetAllByCPFCNPJ(String cpfCnpj) {
        try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }

            Query query = connection.createNamedQuery("Cliente.GetAllByCpfCnpj", Cliente.class);
            query.setParameter("cpfCnpj", cpfCnpj);
            List<Cliente> cliente = query.getResultList();
            return cliente;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
