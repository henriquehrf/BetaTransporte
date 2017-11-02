/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Henrique
 */
public class GetMessage {
    
    public static String getMessage(String message){
        ResourceBundle rb;
        try{
            Locale ptBR = new Locale("pt","BR");
            rb= ResourceBundle.getBundle("docs/i18N_pt_BR");
            return rb.getString(message);
        }catch(Exception ex){
            System.err.println(message);
        }
        
        return null;
    }
}
