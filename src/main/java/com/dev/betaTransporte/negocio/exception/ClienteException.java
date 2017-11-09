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
public class ClienteException {

    public Boolean getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(Boolean cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Boolean getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(Boolean nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public Boolean getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Boolean dtNascimento) {
        this.dtNascimento = dtNascimento;
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

    public ClienteException() {
        this.cpfCnpj = false;
        this.nomeRazaoSocial = false;
        this.sexo = false;
        this.dtNascimento = false;
        this.telefoneFixo = false;
        this.telefoneCelular = false;
        this.email = false;
        this.msg = "";
    }

    private Boolean cpfCnpj;
    private Boolean nomeRazaoSocial;
    private Boolean sexo;
    private Boolean dtNascimento;
    private Boolean telefoneFixo;
    private Boolean telefoneCelular;
    private Boolean email;

    private String msg;

}
