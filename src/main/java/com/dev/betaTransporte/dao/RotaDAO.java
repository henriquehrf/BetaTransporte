/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.dao;

import static com.dev.betaTransporte.dao.GenericoDAO.connection;
import com.dev.betaTransporte.vo.Rota;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Henrique
 */
public class RotaDAO  extends GenericoDAO<EntidadeBase>{
    
    
     public RotaDAO() throws Exception {
        if (connection == null || !connection.isOpen()) {
            getEM();
        }
    }
    
    
     public List<Rota> GetAll() {
         
          try {
            if (connection == null || !connection.isOpen()) {
                getEM();
            }

            Query query = connection.createNamedQuery("Rota.GetAllRota", Rota.class);

            List<Rota> rotas = query.getResultList();
            return rotas;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
         
     }
    
}
