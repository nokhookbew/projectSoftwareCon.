package fxml;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private Button loginButton;
    @FXML private TextField username;
    @FXML private PasswordField password;

    @FXML
    public void nextButtonActionHandler(ActionEvent event) throws IOException {

        if(username.getText().equals("")) {
            Alert.alert("กรุณาใส่ Username");
//            throw new IllegalArgumentException("ต้องใส่usernameนะจ๊ะ");
            return;
        }
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("YearAndSem.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            stage.setScene(scene);
            String name = username.getText();
            YearAndSemController yearAndSemController = loader.getController();
            yearAndSemController.setName(name);

            stage.show();


        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
