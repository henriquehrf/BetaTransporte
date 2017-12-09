/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.negocio.exception;

/**
 *
 * @author Henrique
 */
public class LoginException {

    public Boolean getUsuario() {
        return usuario;
    }

    public void setUsuario(Boolean usuario) {
        this.usuario = usuario;
    }

    public Boolean getSenha() {
        return senha;
    }

    public void setSenha(Boolean senha) {
        this.senha = senha;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        if (msg.length() > 0) {
            if (this.msg.length() == 0) {
                this.msg = msg;
            } else {
                this.msg = this.msg + "\n" + msg;
            }

        }
    }
    

    public LoginException() {
        this.usuario = false;
        this.senha = false;
        this.msg = "";
    }

    private Boolean usuario;
    private Boolean senha;

    private String msg;

}
