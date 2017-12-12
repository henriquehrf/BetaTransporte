/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.negocio.exception;

/**
 *
 * @author Daniel
 */
public class EncomendaException {
    private Boolean ClienteVo;
    private Boolean plano;
    private Boolean CidadeDestino;
    private Boolean Comprimento;
    private Boolean Largura;
    private Boolean Altura;
    private Boolean ValorDeclarado;
    private Boolean cpfCnpjDestinatario;
    private Boolean Peso;
    private Boolean NumNotaFiscal;
    
    private String msg;
    
    public EncomendaException() {
        this.ClienteVo = false;
        this.plano = false;
        this.CidadeDestino = false;
        this.Comprimento = false;
        this.Largura = false;
        this.Altura = false;
        this.ValorDeclarado = false;
        this.cpfCnpjDestinatario = false;
        this.Peso = false;
        this.NumNotaFiscal = false;
        this.msg = "";
    }

    /**
     * @return the ClienteVo
     */
    public Boolean getClienteVo() {
        return ClienteVo;
    }

    /**
     * @param ClienteVo the ClienteVo to set
     */
    public void setClienteVo(Boolean ClienteVo) {
        this.ClienteVo = ClienteVo;
    }

    /**
     * @return the plano
     */
    public Boolean getPlano() {
        return plano;
    }

    /**
     * @param plano the plano to set
     */
    public void setPlano(Boolean plano) {
        this.plano = plano;
    }

    /**
     * @return the CidadeDestino
     */
    public Boolean getCidadeDestino() {
        return CidadeDestino;
    }

    /**
     * @param CidadeDestino the CidadeDestino to set
     */
    public void setCidadeDestino(Boolean CidadeDestino) {
        this.CidadeDestino = CidadeDestino;
    }

    /**
     * @return the Comprimento
     */
    public Boolean getComprimento() {
        return Comprimento;
    }

    /**
     * @param Comprimento the Comprimento to set
     */
    public void setComprimento(Boolean Comprimento) {
        this.Comprimento = Comprimento;
    }

    /**
     * @return the Largura
     */
    public Boolean getLargura() {
        return Largura;
    }

    /**
     * @param Largura the Largura to set
     */
    public void setLargura(Boolean Largura) {
        this.Largura = Largura;
    }

    /**
     * @return the Altura
     */
    public Boolean getAltura() {
        return Altura;
    }

    /**
     * @param Altura the Altura to set
     */
    public void setAltura(Boolean Altura) {
        this.Altura = Altura;
    }

    /**
     * @return the ValorDeclarado
     */
    public Boolean getValorDeclarado() {
        return ValorDeclarado;
    }

    /**
     * @param ValorDeclarado the ValorDeclarado to set
     */
    public void setValorDeclarado(Boolean ValorDeclarado) {
        this.ValorDeclarado = ValorDeclarado;
    }

    /**
     * @return the cpfCnpjDestinatario
     */
    public Boolean getCpfCnpjDestinatario() {
        return cpfCnpjDestinatario;
    }

    /**
     * @param cpfCnpjDestinatario the cpfCnpjDestinatario to set
     */
    public void setCpfCnpjDestinatario(Boolean cpfCnpjDestinatario) {
        this.cpfCnpjDestinatario = cpfCnpjDestinatario;
    }

    /**
     * @return the Peso
     */
    public Boolean getPeso() {
        return Peso;
    }

    /**
     * @param Peso the Peso to set
     */
    public void setPeso(Boolean Peso) {
        this.Peso = Peso;
    }

    /**
     * @return the NumNotaFiscal
     */
    public Boolean getNumNotaFiscal() {
        return NumNotaFiscal;
    }

    /**
     * @param NumNotaFiscal the NumNotaFiscal to set
     */
    public void setNumNotaFiscal(Boolean NumNotaFiscal) {
        this.NumNotaFiscal = NumNotaFiscal;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        if (msg.length() > 0) {
            if (this.msg.length() == 0) {
                this.msg = msg;
            } else {
                this.msg = this.msg + "\n" + msg;
            }

        }
    }
}
