/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Henrique
 */
public class Loader {

    Boolean enableLoader = false;

    public void start() {
      enableLoader=true;
        System.out.println("rodando");
       
    }
    public void stop(){
        enableLoader=false;
        System.out.println("parou");
    }
}
