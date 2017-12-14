/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Adam
 */
public class SeparaNome {

    public SeparaNome(String nome) {

        try {
            String[] nomeSepardo = nome.split("[\\W]");
            String primeiroNome = null, ultimoNome = null;

            for (int i = 0; i < nomeSepardo.length; i++) {
                primeiroNome = nomeSepardo[0];
                ultimoNome = nomeSepardo[i];
            }

            login = primeiroNome + "." + ultimoNome;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private String login;

    public String getLogin() {
        return login;
    }

}
