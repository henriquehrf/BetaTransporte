/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Henrique
 */
public class Loader {

    protected static ImageView img = null;

    public void start(StackPane root) {

        Image image = new Image("/img/Spinner.gif");
        if (img == null) {
            img = new ImageView(image);
            img.toFront();
            root.getChildren().addAll(img);
            root.setAlignment(img, Pos.CENTER);
        }
    }

    public void stop(StackPane root) {
        root.getChildren().remove(img);
        img = null;
    }
}
