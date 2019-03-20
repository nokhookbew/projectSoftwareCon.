package fxml;

import Model.StudentSubject;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.util.ArrayList;

public class MainController {
    private String year, sem;
    @FXML private Label username, yearAndSem;
    @FXML private Button addButton, dropButton, backButton;
    @FXML private TableView<String> tableID;
    @FXML private TableColumn<String, String> idColum;
    @FXML private TableColumn<String, String> nameColum;
    @FXML private TableColumn<String, String> creditColum;
    @FXML private TableColumn<String, String> statusColum;
    @FXML private Label a, b, c, d, e, f, g;
    @FXML private Label id1, id2, id3, id4, id5,id6,id7;

    StudentSubject studentSubject= new StudentSubject();
//    ListOfSubject listOfSubject = new ListOfSubject();
    ArrayList<Label> labelsSub = new ArrayList<>();
    ArrayList<Label> labelsID = new ArrayList<>();
//    PassSubject passSubject = PassSubject.getInstance();
//    StudyingSubject studyingSubject = StudyingSubject.getInstance();
    String SEP = System.getProperty("file.separator");
    File toJar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    String path = toJar.getParentFile().getAbsolutePath();
    String pathToJar;

    public MainController() throws IOException {

    }

    public void setName(String name){
        username.setText(name);
    }

    public void setYearAndSem(String year, String sem){
        this.year = year;
        this.sem = sem;
        yearAndSem.setText("Year: "+ year + " Sem: " + sem );
    }

    public String getYear(){
        return this.year;
    }

    public String getSem(){
        return this.sem;
    }


    private void update() {
        tableID.getItems().clear();
        tableID.setItems(FXCollections.observableArrayList(studentSubject.getListOfSubject().getKeyset()));

        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;


        try {
            File file = new File(pathToJar);
            if (file.createNewFile()){
                System.out.println(SEP);
            }



            System.out.println(pathToJar);
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);


            objectOutputStream.writeObject(studentSubject);


            objectOutputStream.close();
            fileOutputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
//        setColor();
    }
    private void initColumn(){
        idColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        nameColum.setCellValueFactory(cellData -> new SimpleStringProperty(studentSubject.getListOfSubject().getSubject(cellData.getValue()).getName()));
        creditColum.setCellValueFactory(cellData -> new SimpleStringProperty(studentSubject.getListOfSubject().getSubject(cellData.getValue()).getCredit()+""));
        statusColum.setCellValueFactory(cellData -> new SimpleStringProperty(studentSubject.getListOfSubject().getSubject(cellData.getValue()).getStatus()));

    }

    public void initialize() throws IOException {

        Platform.runLater(() -> {
            pathToJar = path + SEP + username.getText()+".txt";

            FileInputStream fileInputStream;
            ObjectInputStream objectInputStream;
            try {
                File file = new File(pathToJar);
                if (file.createNewFile()){
                    System.out.println(SEP);
                } else {
                    fileInputStream = new FileInputStream(file);
                    objectInputStream = new ObjectInputStream(fileInputStream);
                    studentSubject = (StudentSubject) objectInputStream.readObject();


                    objectInputStream.close();
                    fileInputStream.close();
                }
            } catch (EOFException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            initColumn();
            update();
        });
        labelsSub.add(a);
        labelsSub.add(b);
        labelsSub.add(c);
        labelsSub.add(d);
        labelsSub.add(e);
        labelsSub.add(f);
        labelsSub.add(g);
        labelsID.add(id1);
        labelsID.add(id2);
        labelsID.add(id3);
        labelsID.add(id4);
        labelsID.add(id5);
        labelsID.add(id6);
        labelsID.add(id7);


    }
//
//    public void alertScence(){
//        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.WARNING);
//    }

    public void setColor(){
        statusColum.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override
            public TableCell<String, String> call(TableColumn<String, String> param) {
                return new TableCell<String, String>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        String color ;
                        if(item.equals("ยังไม่ได้ลงทะเบียน"))
                            color = "Red";
                        else if (item.equals(studentSubject.getStudyingSubject().getStatus()))
                            color = "Yellow";
                        else
                            color = "Green";

                        setStyle(String.format("-fx-background-color: %s" ,color));
                        setText(item);
                    }
                };
            }
        });
    }

    public void readFileYearAndSem(String year, String sem) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("Year"+getYear()+"Sem"+getSem()+".txt");
        if (is == null)
            System.out.println("error");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String st ;
        int i = 0;
        while ((st = br.readLine()) != null) {

            String[] inputFile = st.split(",",3);
            if (inputFile.length == 3) {
                labelsSub.get(i).setText(" " + inputFile[1]);
                labelsID.get(i).setText(inputFile[0]);
//                labelsSub.get(i).setBorder();
                if (inputFile[2].equals("h")) {
                    labelsID.get(i).setStyle("-fx-text-fill: red");
                    labelsSub.get(i).setStyle("-fx-text-fill: red; -fx-background-color: rgba(128,128,128,0.41)");
                }else if (inputFile[2].equals("m")) {
                    labelsID.get(i).setStyle("-fx-text-fill: #4f69ff");
                    labelsSub.get(i).setStyle("-fx-text-fill: #4f69ff; -fx-background-color: rgba(128,128,128,0.41)");
                }else {
                    labelsID.get(i).setStyle("-fx-text-fill: green");
                    labelsSub.get(i).setStyle("-fx-text-fill: green; -fx-background-color: rgba(128,128,128,0.41)");
                }
                i++;
            }
        }
        br.close();
     }


    @FXML
    public void handleButtonAdd(ActionEvent event){


        String selectedItem = tableID.getSelectionModel().getSelectedItem();

        System.out.println(studentSubject.getPassSubject().getSize());
        if(studentSubject.getListOfSubject().getSubject(selectedItem).getPreInArray(0).equals("null")) {
            Alert.alert(studentSubject.getListOfSubject().getSubject(selectedItem).getName() + " ลงทะเบียนเรียนเรียบร้อย");
            studentSubject.getStudyingSubject().addId(selectedItem);
            studentSubject.getListOfSubject().getSubject(selectedItem).setStatus(studentSubject.getStudyingSubject().getStatus());
            System.out.println(studentSubject.getListOfSubject().getSubject(selectedItem).getStatus());
        } else if (studentSubject.getPassSubject().getSize()>0 &&
                studentSubject.getListOfSubject().getSubject(studentSubject.getListOfSubject().getSubject(selectedItem).getPreInArray(0)).getStatus().equals(studentSubject.getPassSubject().getStatus()) &&
                studentSubject.getListOfSubject().getSubject(selectedItem).getPreInArray(0).equals(studentSubject.getPassSubject().getId(studentSubject.getListOfSubject().getSubject(selectedItem).getPreInArray(0)))
        ){
            studentSubject.getStudyingSubject().addId(selectedItem);
            studentSubject.getListOfSubject().getSubject(selectedItem).setStatus(studentSubject.getStudyingSubject().getStatus());
            Alert.alert(studentSubject.getListOfSubject().getSubject(selectedItem).getName() + " ลงทะเบียนเรียนเรียบร้อย");
        } else {
            String name = " ";
            for (int i =0 ; i < studentSubject.getListOfSubject().getSubject(selectedItem).getSizeOfPre();i++){
                name = name + studentSubject.getListOfSubject().getSubject(studentSubject.getListOfSubject().getSubject(selectedItem).getPreInArray(i)).getName() + "\n";
            }
            Alert.alert(studentSubject.getListOfSubject().getSubject(selectedItem).getName() + " ลงไม่ได้ ต้องผ่าน " +
                    name);
//                    studentSubject.getListOfSubject().getSubject(studentSubject.getListOfSubject().getSubject(selectedItem).getPreInArray(0)).getName());
//            throw new IllegalArgumentException("ยังไม่ผ่านตัวพื้นฐาน ลงไม่ได้นะจ๊ะ");
            return;
        }
        update();
    }


    @FXML
    public void handleButtonDrop(ActionEvent event){
        String selectedItem = tableID.getSelectionModel().getSelectedItem();
        System.out.println("DROP");
        if (studentSubject.getStudyingSubject().getSize()>0 && studentSubject.getListOfSubject().getSubject(selectedItem).getStatus().equals(studentSubject.getStudyingSubject().getStatus()) &&
                studentSubject.getStudyingSubject().getId(selectedItem).equals(selectedItem)) {
            studentSubject.getStudyingSubject().removeId(selectedItem);
            studentSubject.getListOfSubject().getSubject(selectedItem).setStatus("ยังไม่ได้ลงทะเบียน");
            Alert.alert(studentSubject.getListOfSubject().getSubject(selectedItem).getName() +" ยกเลิกการลงทะเบียนเรียบร้อย");
        }
        else {
            Alert.alert(studentSubject.getListOfSubject().getSubject(selectedItem).getName() + " ดรอปไม่ได้นะจ๊ะ เพราะไม่ได้ลงเรียน ");
//            throw new IllegalArgumentException("ยังไม่ได้ลงจะดรอปได้ไง");
            return;
        }
        update();
    }


    @FXML
    public void handleButtonPass(ActionEvent event){
        String selectedItem = tableID.getSelectionModel().getSelectedItem();
        System.out.println(studentSubject.getStudyingSubject().getSize());
        if (studentSubject.getStudyingSubject().getSize()>0 &&
                studentSubject.getListOfSubject().getSubject(selectedItem).getStatus().equals(studentSubject.getStudyingSubject().getStatus()) &&
                studentSubject.getStudyingSubject().getId(selectedItem).equals(selectedItem) ) {
            studentSubject.getPassSubject().addPassId(selectedItem);
            studentSubject.getStudyingSubject().removeId(selectedItem);
            studentSubject.getListOfSubject().getSubject(selectedItem).setStatus(studentSubject.getPassSubject().getStatus());
            Alert.alert(studentSubject.getListOfSubject().getSubject(selectedItem).getName() + " ผ่านแล้ว ดีใจด้วย");
        } else {
            Alert.alert(studentSubject.getListOfSubject().getSubject(selectedItem).getName() + " ผ่านไม่ได้นะจ๊ะ เพราะไม่ได้ลงเรียน");
//            throw new IllegalArgumentException("ยังไม่ได้ลงจะผ่านได้ไง");
            return;
        }
        update();

    }

    @FXML
    public void handleButtonBackPage(ActionEvent event) throws IOException {

        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("YearAndSem.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600 , 400);
            stage.setScene(scene);
            stage.centerOnScreen();
            YearAndSemController yearAndSemController = fxmlLoader.getController();
            yearAndSemController.setName(username.getText());

            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
        update();

    }
}
