/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.dao;


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
}
