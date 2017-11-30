/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public BorderPane getFather(Node node) {
        Node aux = node.getParent();
        while (!(aux instanceof BorderPane)) {
            aux = node.getParent();
        }
        if (aux.getId().equalsIgnoreCase("bdpPrincipal")) {
            ImageView img = new ImageView(new Image("./img/IMG_01_LogoBetaTransportePNG.PNG"));
            img.setFitHeight(250);
            img.setFitWidth(600);
            ((BorderPane) aux).setCenter(img);
            return (BorderPane) aux;
        }
        ((BorderPane) aux).setCenter(null);
        return (BorderPane) aux;
    }
}
