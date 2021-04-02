 

package ui;

 


 

import java.io.Serializable;


 

import javafx.fxml.FXML;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;

import javafx.scene.control.Alert.AlertType;

import javafx.stage.Stage;

import javafx.util.StringConverter;

import utils.Utils;


 

import java.io.IOException;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import java.util.Random;


 

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


 

import business.Author;

import business.Book;

import business.ControllerInterface;

import business.LoginException;

import business.SystemController;

import dataaccess.Auth;

import dataaccess.User;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;


 


 

public class AddNewUserController {


 

    @FXML

    private TextField idFld;


 

    @FXML

    private TextField passwordFld;


 

    private ComboBox<String> comboBox = new ComboBox<>();


 

    

 

    

    

    private Stage dialogStage;

   

    

    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;

    }


 

    @FXML

    void addUserAction(ActionEvent event) {


 

      validatingFields();


 

    if (validateMessage.length() > 0) {

     

    Utils.makeAlert(validateMessage,"Data Missing",AlertType.ERROR);

    return;

    }

     

     

    final Auth authorization = Auth.ADMIN;


 

    User user = new User(idFld.getText(),passwordFld.getText().toString(),authorization);


 

    new SystemController().saveUser(user);

    dialogStage.close();

 

    }


 

    @FXML

    void comboBtnAction(ActionEvent event) {


 

    }


 

    @FXML

    public void initialize() {

     

    System.out.println("yoyoyoyoyoyooyoyooy");

   

    idFld.setEditable(false);

     Random r = new Random();

     

        int a = r.nextInt(10000);;


 

     idFld.setText(""+ a);

      


 

   

  ObservableList<String> data = FXCollections.observableArrayList("LIBRARIAN", "ADMIN", "BOTH");

  comboBox.setItems(data);

     

//        ObservableList<String> data = FXCollections.observableArrayList(ar);

////        comboBox = new ComboBox<>(data);

//        

// comboBox.itemsProperty().setValue(data);


 

//        comboBox.setOnAction((event) -> {

//         

//            int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();

//            Object selectedItem = comboBox.getSelectionModel().getSelectedItem();

//

//            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);

//            System.out.println("   ComboBox.getValue(): " + comboBox.getValue());

//        });

//

//     

//    new SystemController().allBooks().forEach(b->{

//     

//     

//     

//    final ObservableList<Author> authors = FXCollections.observableList(b.getAuthors());

//    comboBox.itemsProperty().setValue(authors);

//    });

//    convertComboDisplay();

         

    }

    


 

    

    private void convertComboDisplay() {

//    comboBox.setConverter(new StringConverter<Author>() {

//     

//    @Override

//    public String toString(Author author) {

//    return author.getFirstName() + " "+ author.getLastName();

//    }

//     

//    @Override

//    public Author fromString(final String string) {

//    return comboBox.getItems().stream().filter(

//    auth -> auth.getLastName().equals(string))

//    .findFirst().orElse(null);

//    }

//    });

    }

    


 

    String validateMessage = "" ;

    public void validatingFields() {

    validateMessage = "";

     

    if (idFld.getText().length() < 1) {

    validateMessage = "Enter ID\n";

    }

    if (passwordFld.getText().length() < 1) {

    validateMessage = validateMessage +"Enter Password\n";

     

    }

     

//    if (checkOutDurationFld.getText().length() < 1) {

//    validateMessage = validateMessage +"Enter Duration\n";

//     

//    }

     

   

    }

 

 


 

  


 

    

    

    

}