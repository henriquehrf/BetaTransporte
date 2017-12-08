package com.dev.betaTransporte.dao;

/**
 *
 * @author Adam
 */
public class UsuarioDAO extends GenericoDAO<EntidadeBase> {

    public UsuarioDAO() throws Exception {
        if (connection == null) {
            getEM();
        }
    }

}
