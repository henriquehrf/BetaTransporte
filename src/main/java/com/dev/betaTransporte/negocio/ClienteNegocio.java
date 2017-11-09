/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.negocio;

import com.dev.betaTransporte.negocio.exception.ClienteException;
import com.dev.betaTransporte.vo.ClienteVO;

/**
 *
 * @author Henrique
 */
public  class ClienteNegocio {
    
    private static ClienteException validar(ClienteVO cliente){
        
        ClienteException ex = new ClienteException();
        
        if(cliente.getCpfCnpj().length()<11){
            ex.setCpfCnpj(Boolean.TRUE);
            ex.setMsg("CPF invalido");
        }
        if(cliente.getNome().length()== 0){
            ex.setNomeRazaoSocial(Boolean.TRUE);
            ex.setMsg("Falta preencher o nome");
        }
        if(cliente.getNome().length()> 100){
            ex.setNomeRazaoSocial(Boolean.TRUE);
            ex.setMsg("Nome grande demais");
        }
        if(cliente.getSexo()==null){
            ex.setSexo(Boolean.TRUE);
            ex.setMsg("Falta selecionar sexo");
        }
        if(cliente.getCpfCnpj().length()<=14 && cliente.getDataNascimento()==null){
            ex.setDtNascimento(Boolean.TRUE);
            ex.setMsg("Falta selecionar data nascimento");
        }
        if(cliente.getTelFixo().length() >0 && cliente.getTelFixo().length()<14){
            ex.setTelefoneFixo(Boolean.TRUE);
            ex.setMsg("Telefone invalido");
        }
        if(cliente.getTelCelular().length()>0 && cliente.getTelCelular().length()<15){
            ex.setTelefoneCelular(Boolean.TRUE);
            ex.setMsg("Celular invalido");
        }
        if(cliente.getTelCelular().length()==0){
             ex.setTelefoneCelular(Boolean.TRUE);
            ex.setMsg("falta preencher o celular");
        }
        if(cliente.getEmail().length()>0){
            
        }
        
        return ex;
    }
    
    public static ClienteException save(ClienteVO cliente){
        ClienteException ex = validar(cliente);
        if(ex.getMsg().trim().length()>0){
            return ex; 
        }else{
            System.out.println("Passou!");
            return null;
        }
        
        
    }
}
