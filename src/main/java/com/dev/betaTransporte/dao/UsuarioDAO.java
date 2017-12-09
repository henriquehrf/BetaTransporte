package com.dev.betaTransporte.dao;

import com.dev.betaTransporte.vo.Usuario;
import javax.persistence.Query;

/**
 *
 * @author Adam
 * @param T
 */
public class UsuarioDAO extends GenericoDAO<EntidadeBase> {

    public UsuarioDAO() throws Exception {
        if (connection == null) {
            getEM();
        }
    }

    public Usuario GetUser(String Usuario) {

        try {
            if (connection == null) {
                getEM();
            }
            Query query = connection.createNamedQuery("Usuario.SelectUser", Usuario.class);
            query.setParameter("login", Usuario);
            return (Usuario) query.getSingleResult();

        } catch (Exception ex) {
            return null;
        }

    }
}
