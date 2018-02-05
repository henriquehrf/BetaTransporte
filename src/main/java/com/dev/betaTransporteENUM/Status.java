/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporteENUM;

/**
 *
 * @author Daniel
 */
public enum Status {
    Encomenda_aguardando_transporte_na_cidade_de_origem,
    Encomenda_em_transporte_para_a_cidade_de_destino,
    Encomenda_em_transporte_para_o_centro_de_distribuição,
    Encomenda_descarregada_no_centro_de_distribuição_e_aguardando_o_despacho_para_a_cidade_destino,
    Encomenda_descarregada_na_cidade_destino_e_disponivel_para_o_cliente_retirar,
    Encomenda_retirada_pelo_cliente
}
