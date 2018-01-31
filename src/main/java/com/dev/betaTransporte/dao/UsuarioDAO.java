package com.dev.betaTransporte.dao;

import com.dev.betaTransporte.vo.Usuario;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Adam
 * @param T
 */
public class UsuarioDAO extends GenericoDAO<EntidadeBase> {

    public UsuarioDAO() throws Exception {
        if (connection == null || !connection.isOpen()) {
            getEM();
        }
    }

    public Usuario GetUser(String Usuario) {

        try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }
            Query query = connection.createNamedQuery("Usuario.SelectUser", Usuario.class);
            query.setParameter("login", Usuario);
            return (Usuario) query.getSingleResult();

        } catch (Exception ex) {
            return null;
        }

    }
    
    

    
    public List<Usuario> GetAll() {

        try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }

            Query query = connection.createNamedQuery("Usuario.GetAllUser", Usuario.class);

            List<Usuario> Usuario = query.getResultList();
            return Usuario;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
    
    public List<Usuario> GetAllByNome(String nome) {
        try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }

            Query query = connection.createNamedQuery("Usuario.GetAllByNome2", Usuario.class);
            query.setParameter("Nome", nome);
            List<Usuario> usuario = query.getResultList();
            return usuario;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
    
    
    
    
    
    
}
