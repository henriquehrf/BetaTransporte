/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.vo;

import com.dev.betaTransporte.dao.EntidadeBase;
import com.dev.betaTransporteENUM.Cidade;
import com.dev.betaTransporteENUM.Plano;
import com.dev.betaTransporteENUM.Status;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "Encomenda")
@NamedQueries({
    @NamedQuery(name = "Encomenda.GetAllCPFCNPJDestinatario", 
    query = "SELECT m FROM Encomenda m WHERE UPPER(m.cpfCnpjDestinatario) LIKE UPPER(:cspfCnpj) ORDER BY m.cpfCnpjDestinatario ASC")
    ,
    @NamedQuery(name = "Encomenda.GetAllEnc", 
    query = "SELECT m FROM Encomenda m ORDER BY m.cpfCnpjDestinatario ASC")
    ,
    @NamedQuery(name = "Encomenda.GetAllEncCitStatus", 
    query = "SELECT m FROM Encomenda m WHERE (m.CidadeOrigem = :cidadeOrigem) AND (m.Status = :status) ORDER BY (m.Plano)")
    ,
    @NamedQuery(name = "Encomenda.GetAllByPlano", 
    query = "SELECT m FROM Encomenda m WHERE m.Plano = :plano ORDER BY m.cpfCnpjDestinatario ASC")})
public class Encomenda implements EntidadeBase , Serializable {

    public Encomenda() {
        this.ClienteVO = new Cliente();
        this.Plano = null;
        this.CidadeDestino = null;
        this.CidadeOrigem = null;
        this.Comprimento = 0;
        this.Largura = 0;
        this.Altura = 0;
        this.ValorDeclarado = 0;
        this.dataCadastro = null;
        this.cpfCnpjDestinatario = "";
        this.Peso = 0;
        this.NumNotaFiscal = 0;
        this.Status=Status.Encomenda_aguardando_transporte_na_cidade_de_origem;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status Status;

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
    @Enumerated(EnumType.ORDINAL)
    private Cidade CidadeOrigem;
    
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
    private float Peso;
    
    @Column(nullable = false)
    private int NumNotaFiscal;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Rota id_rota= null;

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status Status) {
        this.Status = Status;
    }

    public Rota getId_rota() {
        return id_rota;
    }
    
    public String getRotaCodigo(){
        return ""+id_rota.getId();
    }
    
    public String getCodigo(){
        return ""+ID;
    }

    public void setId_rota(Rota id_rota) {
        this.id_rota = id_rota;
    }
       
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
    

    public String getDataEntregaString(){
        Date dataAux=this.dataCadastro;
        /*if (getPlano()==Plano.BETA_CONV){
            dataAux.setDate(dataAux.getDate() + 21);
            dataAux.setMonth(dataAux.getMonth());
            dataAux.setYear(dataAux.getYear());
            // 15 dias uteis
        }else if (getPlano()==Plano.BETA_GOLD){
            dataAux.setDate(dataAux.getDate() + 7);
            dataAux.setMonth(dataAux.getMonth());
            dataAux.setYear(dataAux.getYear());
           // 15 dias uteis//7 dias uteis são 9 ou 11 depende do dia da semana
        }else{
            dataAux.setDate(dataAux.getDate() + 2);
            dataAux.setMonth(dataAux.getMonth());
            dataAux.setYear(dataAux.getYear());
           // 15 dias uteis//2 dias uteis são 2 ou 4depende do dia da semana
        } */
         return new SimpleDateFormat("dd/MM/yyyy").format(dataAux);
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
    public float getPeso() {
        return Peso;
    }

    /**
     * @param Peso the Peso to set
     */
    public void setPeso(float Peso) {
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

    /**
     * @return the CidadeOrigem
     */
    public Cidade getCidadeOrigem() {
        return CidadeOrigem;
    }

    /**
     * @param CidadeOrigem the CidadeOrigem to set
     */
    public void setCidadeOrigem(Cidade CidadeOrigem) {
        this.CidadeOrigem = CidadeOrigem;
    }
    public int volume (){
        return this.Altura*this.Largura*this.Comprimento;
    }
    
}
