/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Henrique
 */
public class Navegation {
    private static Node family;

    public static Node getFamily() {
        return family;
    }

    public static void setFamily(Node family) {
        Navegation.family = family;
    }
    
    public BorderPane getFather(Node node){
        Node aux = node.getParent();
        while(!(aux instanceof BorderPane)){
            aux=node.getParent();
        }
        ((BorderPane)aux).setCenter(null);
        return (BorderPane) aux;
    }
}
