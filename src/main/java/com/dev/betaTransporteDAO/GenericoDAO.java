/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporteDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author Henrique
 */
public class GenericoDAO<T extends EntidadeBase> {
    
    public EntityManager getEM(){
        EntityManagerFactory factory;
        try{
            factory = Persistence.createEntityManagerFactory("BetaTransportePU"); 
            return factory.createEntityManager();
        }catch(Exception ex){
            System.err.println(ex);
        }
        return null;
    }
}
