/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.dom4j.util.NodeComparator;

/**
 *
 * @author Henrique
 */

/** Requer um stackPane, o resultado desta classe é o efeito de loading para o usuário**/
public class Loader {

    ImageView img;

    public void start(StackPane root) {
        Image image = new Image("/img/Spinner.gif");
        img = new ImageView(image);
        img.toFront();

        root.getChildren().addAll(img);
        root.setAlignment(img, Pos.CENTER);

//        ObservableList<Node> workingCollection = FXCollections.observableArrayList(
//                root.getChildren()
//        );
//        Collections.sort(workingCollection, new NodeComparator());
//
//        root.getChildren().setAll(workingCollection);

    }

    public void stop(StackPane root) {
        root.getChildren().remove(img);
        img = null;
    }
}
