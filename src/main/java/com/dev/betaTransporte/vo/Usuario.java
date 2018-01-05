/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.vo;

import com.dev.betaTransporte.dao.EntidadeBase;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Henrique
 */
@Entity
@Table(name = "Usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.SelectUser", query = "SELECT m FROM Usuario m WHERE m.Login = :login"),})
public class Usuario implements EntidadeBase, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;

    @Override
    public Long getId() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public int getTipoFuncionario() {
        return TipoFuncionario;
    }

    public void setTipoFuncionario(int TipoFuncionario) {
        this.TipoFuncionario = TipoFuncionario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 20, nullable = false)
    private int TipoFuncionario;

    @Column(length = 100, nullable = false)
    private String Nome;

    @Column(length = 20, nullable = true)
    private String Telefone;

    @Column(length = 20, nullable = true)
    private String Celular;

    @Column(length = 100, nullable = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String Login;

    @Column(length = 100, nullable = false)
    private String Senha;

}
