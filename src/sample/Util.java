package sample;

import javafx.scene.control.Button;

public class Util {

    public static void enlightenButtonTwice(Button btn){
        btn.setOnMouseEntered(e -> {
            btn.setStyle("-fx-background-color: #577280");
        });
        btn.setOnMouseExited(e -> {
            btn.setStyle("-fx-background-color: #F9C784");
        });

    }



    public static void enlightenButtonNormal(Button btn){
        btn.setOnMouseEntered(e -> {
            btn.setStyle("-fx-background-color: #577280");
        });
        btn.setOnMouseExited(e -> {
            btn.setStyle("");
        });

    }


}
