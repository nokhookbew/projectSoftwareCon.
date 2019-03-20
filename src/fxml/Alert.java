package fxml;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Alert {

    public static void alert(String text){
        Stage window  = new Stage();
        window.setWidth(600);
        window.setHeight(300);
        Label label = new Label();
        label.setText(text);
        VBox board = new VBox(10);
        board.setAlignment(Pos.CENTER);
        board.getChildren().addAll(label);
        label.setAlignment(Pos.CENTER);
        Scene scene = new Scene(board);
        window.setScene(scene);
        window.show();
        System.out.println(text);
    }

}