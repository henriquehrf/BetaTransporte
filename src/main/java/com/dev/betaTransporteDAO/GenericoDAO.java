/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporteDAO;


import javafx.scene.control.Alert;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import util.BoxInfo;
import util.GetMessage;



/**
 *
 * @author Henrique
 */
public class GenericoDAO<T extends EntidadeBase> {
    
    BoxInfo box = new BoxInfo();
    GetMessage msg = new GetMessage();
    
    public EntityManager getEM(){
        EntityManagerFactory factory;
        try{
            factory = Persistence.createEntityManagerFactory("BetaTransportePU"); 
            return factory.createEntityManager();
        }catch(Exception ex){
            System.err.println(ex);
            box.BoxInfo(Alert.AlertType.ERROR, msg.getMessage("err.title.BD"), msg.getMessage("err.msg.BD"));
        }
        return null;
    }
    
     public T save(Class<T> clazz, T t) {
        EntityManager em = getEM();

        try {
            em.getTransaction().begin();
            if (t.getId() == null) {
                em.persist(t); // executa insert
            } else {
                if (!em.contains(t)) {
                    if (em.find(clazz, t.getId()) == null) {                      
                       box.BoxInfo(Alert.AlertType.ERROR, msg.getMessage("err.title"), msg.getMessage("err.msg.update"));
                    }
                }
                t = em.merge(t); // executa update
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            box.BoxInfo(Alert.AlertType.ERROR, msg.getMessage("err.title"), msg.getMessage("err.msg.save"));
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return t;
    }

    public void remove(Class<T> clazz, T tt) {
        EntityManager em = getEM();

        T t = em.find(clazz, tt.getId());

        try {
            em.getTransaction().begin();
            em.remove(t);  // executa o delete
            em.getTransaction().commit();
        } catch (Exception ex) {
             box.BoxInfo(Alert.AlertType.ERROR, msg.getMessage("err.title"), msg.getMessage("err.msg.remove"));
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
    }
    
     public T getById(Class<T> clazz, T tt) {
        EntityManager em = getEM();
        T t = null;

        try {
            t = em.find(clazz, tt.getId()); // execulta o select no banco de dados

        } catch (Exception ex) {
             box.BoxInfo(Alert.AlertType.ERROR, msg.getMessage("err.title"), msg.getMessage("err.msg.getById"));
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return t;
    }
}

