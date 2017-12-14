/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.vo;

import com.dev.betaTransporte.dao.EntidadeBase;
import com.dev.betaTransporteENUM.Sexo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Henrique
 */
@Entity
@Table(name = "Cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.GetCPFCNPJ", query = "SELECT m FROM Cliente m WHERE m.cpfCnpj = :cpfCnpj"),})
public class Cliente implements EntidadeBase, Serializable {

    public Cliente() {
        this.idCliente = null;
        this.nome = null;
        this.cpfCnpj = null;
        this.dataNascimento = null;
        this.telFixo = null;
        this.telCelular = null;
        this.dataCadastro = null;
        this.email = null;
        this.sexo = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idCliente;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelFixo() {
        return telFixo;
    }

    public void setTelFixo(String telFixo) {
        this.telFixo = telFixo;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String cpfCnpj;

    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;

    @Column(length = 20, nullable = true)
    private String telFixo;

    @Column(length = 20, nullable = false)
    private String telCelular;

    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;

    @Column(length = 100, nullable = true)
    private String email;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Override
    public Long getId() {
        return idCliente;
    }
}
