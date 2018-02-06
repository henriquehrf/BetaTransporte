/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.vo;

import com.dev.betaTransporte.dao.EntidadeBase;
import com.dev.betaTransporteENUM.Cidade;
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
@Table(name = "Rota")
@NamedQueries({
    @NamedQuery(name = "Rota.GetAllRota", query = "SELECT r FROM Rota r")
})
public class Rota implements EntidadeBase, Serializable {

    public Long getId_Rota() {
        return id_Rota;
    }

    public void setId_Rota(Long id_Rota) {
        this.id_Rota = id_Rota;
    }

    public Cidade getOrigem() {
        return origem;
    }

    public void setOrigem(Cidade origem) {
        this.origem = origem;
    }

    public Cidade getDestino() {
        return destino;
    }

    public void setDestino(Cidade destino) {
        this.destino = destino;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public Cidade getGaragem() {
        return garagem;
    }

    public void setGaragem(Cidade garagem) {
        this.garagem = garagem;
    }

    @Override
    public Long getId() {
        return id_Rota;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_Rota;

    @Column(nullable = false)
    private Cidade origem;
    @Column(nullable = false)
    private Cidade destino;

    @Column(nullable = false)
    private int tempo;

    @Column(nullable = false)
    private float distancia;

    @Column(nullable = false)
    private Cidade garagem;
    
    
    public String getCodigoRota(){
        return ""+id_Rota;
    }
    
    public String getDistanciaFormat(){
              
        return distancia+" KM";
    }
    
    public String getTempoFormat(){
        int horas = tempo;
        int min;
        horas=horas/60;
        min = tempo-(horas*60);
        
        return horas+" h e "+min+" min";
    }
    
    
    
    
    
    

    public Rota() {
        this.id_Rota = null;
        this.origem = null;
        this.destino = null;
        this.tempo = 0;
        this.distancia = 0;
        this.garagem = null;
    }

}
