/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Henrique
 */
public class BoxInfo {
    
    public void BoxInfo(Alert.AlertType typeAlert,String title,String content){
        try{
             Alert alert = new Alert(typeAlert);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }catch(Exception ex){
            System.err.println(ex);
        }
    }
    
    public boolean BoxChoice(Alert.AlertType typeAlert,String title,String content,String option1,String option2){
         if (typeAlert.equals(Alert.AlertType.CONFIRMATION)) {
            Alert alert = new Alert(typeAlert);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);

            ButtonType buttonTypeYes = new ButtonType(option1);
            ButtonType buttonTypeNot = new ButtonType(option2);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNot);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }
    
}
