/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import util.Message;
import util.TestConnectionServer;

/**
 *
 * @author Henrique
 * @param <T>
 */
public class GenericoDAO<T extends EntidadeBase> {

    Message msg = new Message();
    public static EntityManager connection;

    public GenericoDAO() throws Exception {
        if (connection == null) {
            getEM();
        }
    }

    protected void getEM() throws Exception {

        EntityManagerFactory factory = null;

        try {

            factory = Persistence.createEntityManagerFactory("BetaTransportePU");
            connection = factory.createEntityManager();

        } catch (Exception ex) {
            System.err.println(ex);
            throw new Exception(msg.message("err.msg.BD"));
        }
    }

    public T save(Class<T> clazz, T t) throws Exception {

        getEM();
        if (!TestConnectionServer.CONNECTION_SERVER) {
            throw new Exception(Message.message("erro.msg.offline"));
        } else {
            try {
                connection.getTransaction().begin();
                if (t.getId() == null) {
                    connection.persist(t); // executa insert
                    connection.flush();
                } else {
                    if (!connection.contains(t)) {
                        if (connection.find(clazz, t.getId()) == null) {
                            throw new Exception(msg.message("err.msg.update"));
                        }
                    }
                    t = connection.merge(t); // executa update
                }
                this.connection.getTransaction().commit();
            } catch (Exception ex) {
                connection.getTransaction().rollback();
                throw new Exception(msg.message("err.msg.save"));
            } finally {
                this.connection.close();
            }
        }

        return t;
    }

    public void remove(Class<T> clazz, T tt) throws Exception {

        getEM();
        T t = connection.find(clazz, tt.getId());

        try {
            connection.getTransaction().begin();
            connection.remove(t);  // executa o delete
            connection.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            connection.getTransaction().rollback();
            throw new Exception(msg.message("err.msg.remove"));
        } finally {
            connection.close();
        }
    }

    public T getById(Class<T> clazz, T tt) throws Exception {
        T t = null;

        try {
            t = connection.find(clazz, tt.getId()); // execulta o select no banco de dados

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new Exception(msg.message("err.msg.getById"));
        } finally {
            connection.close();
        }
        return t;
    }
}
