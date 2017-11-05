/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;



/**
 *
 * @author  Henrique
 */

public class Mask {
    
    
    public String CpfCnpj(String txt){
        txt = txt.replaceAll("[^0-9]","");
        
        if(txt.length()<=11){
         txt=txt.replaceFirst("(\\d{3})(\\d)", "$1.$2");
         txt=txt.replaceFirst("(\\d{3})(\\d)", "$1.$2");
         txt=txt.replaceFirst("(\\d{3})(\\d)", "$1-$2");
        }else{
        txt=txt.replaceFirst("(\\d{2})(\\d)", "$1.$2");
        txt=txt.replaceFirst("(\\d{3})(\\d)", "$1.$2");
        txt=txt.replaceFirst("(\\d{3})(\\d)", "$1/$2");
        txt=txt.replaceFirst("(\\d{4})(\\d)", "$1-$2");
        }
        
         txt=txt.trim();    
         
         if(txt.length()>18){
           return txt.substring(0, 18);
         }else{
            return txt;
         }
    }

    public String TelefoneFixo(String txt){
        
        txt=txt.replaceAll("[^0-9]","");
        
        txt=txt.replaceFirst("(\\d{0})(\\d)", "$1($2");
        txt=txt.replaceFirst("(\\d{2})(\\d)", "$1) $2");
        txt=txt.replaceFirst("(\\d{4})(\\d)", "$1-$2");
        
        if(txt.length()>14){
            return txt.substring(0, 14);
        }else{
            return txt;
        }
    }
    
    public String TelefoneCelular(String txt){
        
        txt=txt.replaceAll("[^0-9]","");
        
        txt=txt.replaceFirst("(\\d{0})(\\d)", "$1($2");
        txt=txt.replaceFirst("(\\d{2})(\\d)", "$1) $2");
        txt=txt.replaceFirst("(\\d{5})(\\d)", "$1-$2");
        
        if(txt.length()>15){
            return txt.substring(0, 15);
        }else{
            return txt;
        }
        
    }
   
}