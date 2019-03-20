package fxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class YearAndSemController {
    @FXML private Label username;
    @FXML private ChoiceBox year, sem;
    @FXML private Button submit;
    private ObservableList<String> numYear = FXCollections.observableArrayList("1","2","3","4");
    private ObservableList<String> numSem = FXCollections.observableArrayList("1","2");

    public void initialize(){
        year.setItems(numYear);
        sem.setItems(numSem);
    }

    public void setName(String name){
        username.setText(name);
    }

    @FXML
    public void nextButtonActionHandler(ActionEvent event) throws IOException {
        String y = (String) year.getValue();
        String s = (String) sem.getValue();


        Stage stage = (Stage) submit.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainPage.fxml"));
        Scene scene = new Scene(loader.load(), 1080, 720);
        stage.setScene(scene);
        stage.centerOnScreen();
        MainController mainController = loader.getController();
        mainController.setName(username.getText());
        mainController.setYearAndSem(y, s);
        mainController.readFileYearAndSem(y, s);

        stage.show();


    }



}
