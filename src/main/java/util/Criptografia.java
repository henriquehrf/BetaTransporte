/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.MessageDigest;

/**
 *
 * @author PET Autonet
 */
public class Criptografia {

    /** Realiza o calculo da criptografia, técnica SHA-256.  **/
    public Criptografia(String usuario,String senha) {
        try {
            String value = usuario + senha;
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(value.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
           
            senha_criptografada = hexString.toString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String senha_criptografada;

    public String getSenha_criptografada() {
        return senha_criptografada;
    }

}
