/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.negocio.exception;

/**
 *
 * @author Adam
 */
public class UsuarioException {

    public Boolean getTipoFuncionario() {
        return TipoFuncionario;
    }

    public void setTipoFuncionario(Boolean TipoFuncionario) {
        this.TipoFuncionario = TipoFuncionario;
    }

    public Boolean getNome() {
        return nome;
    }

    public void setNome(Boolean nome) {
        this.nome = nome;
    }

    public Boolean getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(Boolean telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public Boolean getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(Boolean telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
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

    public UsuarioException() {
        this.TipoFuncionario = false;
        this.nome = false;
        this.telefoneFixo = false;
        this.telefoneCelular = false;
        this.email = false;
        this.msg = "";
    }

    private Boolean TipoFuncionario;
    private Boolean nome;
    private Boolean telefoneFixo;
    private Boolean telefoneCelular;
    private Boolean email;

    private String msg;

}
