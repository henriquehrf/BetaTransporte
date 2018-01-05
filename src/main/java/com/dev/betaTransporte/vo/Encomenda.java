/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.vo;

import com.dev.betaTransporte.dao.EntidadeBase;
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Plano;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "Encomenda")
public class Encomenda implements EntidadeBase , Serializable {

    public Encomenda() {
        this.ClienteVO = new Cliente();
        this.Plano = null;
        this.CidadeDestino = null;
        this.Comprimento = 0;
        this.Largura = 0;
        this.Altura = 0;
        this.ValorDeclarado = 0;
        this.dataCadastro = null;
        this.cpfCnpjDestinatario = "";
        this.Peso = 0;
        this.NumNotaFiscal = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

  

    @OneToOne(fetch = FetchType.EAGER)
    private Cliente ClienteVO;

    public Cliente getClienteVO() {
        return ClienteVO;
    }

    public void setClienteVO(Cliente ClienteVO) {
        this.ClienteVO = ClienteVO;
    }
    
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Plano Plano;
    
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Cidade CidadeDestino;
    
    @Column(nullable = false)
    private int Comprimento;
    
    @Column(nullable = false)
    private int Largura;
    
    @Column(nullable = false)
    private int Altura;
    
    @Column(nullable = false)
    private float ValorDeclarado;
   
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
   
    @Column(length = 20,nullable = false)
    private String cpfCnpjDestinatario;
    
    @Column(nullable = false)
    private int Peso;
    
    @Column(nullable = false)
    private int NumNotaFiscal;
       
    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public Long getId() {
        return ID;
    }

    /**
     * @return the Plano
     */
    public Plano getPlano() {
        return Plano;
    }

    /**
     * @param Plano the Plano to set
     */
    public void setPlano(Plano Plano) {
        this.Plano = Plano;
    }

    /**
     * @return the CidadeDestino
     */
    public Cidade getCidadeDestino() {
        return CidadeDestino;
    }

    /**
     * @param CidadeDestino the CidadeDestino to set
     */
    public void setCidadeDestino(Cidade CidadeDestino) {
        this.CidadeDestino = CidadeDestino;
    }

    /**
     * @return the Comprimento
     */
    public int getComprimento() {
        return Comprimento;
    }

    /**
     * @param Comprimento the Comprimento to set
     */
    public void setComprimento(int Comprimento) {
        this.Comprimento = Comprimento;
    }

    /**
     * @return the Largura
     */
    public int getLargura() {
        return Largura;
    }

    /**
     * @param Largura the Largura to set
     */
    public void setLargura(int Largura) {
        this.Largura = Largura;
    }

    /**
     * @return the Altura
     */
    public int getAltura() {
        return Altura;
    }

    /**
     * @param Altura the Altura to set
     */
    public void setAltura(int Altura) {
        this.Altura = Altura;
    }

    /**
     * @return the ValorDeclarado
     */
    public float getValorDeclarado() {
        return ValorDeclarado;
    }

    /**
     * @param ValorDeclarado the ValorDeclarado to set
     */
    public void setValorDeclarado(float ValorDeclarado) {
        this.ValorDeclarado = ValorDeclarado;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the cpfCnpjDestinatario
     */
    public String getCpfCnpjDestinatario() {
        return cpfCnpjDestinatario;
    }

    /**
     * @param cpfCnpjDestinatario the cpfCnpjDestinatario to set
     */
    public void setCpfCnpjDestinatario(String cpfCnpjDestinatario) {
        this.cpfCnpjDestinatario = cpfCnpjDestinatario;
    }

    /**
     * @return the Peso
     */
    public int getPeso() {
        return Peso;
    }

    /**
     * @param Peso the Peso to set
     */
    public void setPeso(int Peso) {
        this.Peso = Peso;
    }

    /**
     * @return the NumNotaFiscal
     */
    public int getNumNotaFiscal() {
        return NumNotaFiscal;
    }

    /**
     * @param NumNotaFiscal the NumNotaFiscal to set
     */
    public void setNumNotaFiscal(int NumNotaFiscal) {
        this.NumNotaFiscal = NumNotaFiscal;
    }
    
}
