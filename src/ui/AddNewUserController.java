 

package ui;

 


 

import java.io.Serializable;
import java.net.URL;

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


 


import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import business.SystemController;
import dataaccess.Auth;
import dataaccess.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class AddNewUserController implements Initializable {


    @FXML
    private TextField idFld;
    @FXML
    private TextField passwordFld;
    
    String selectedRule = "";
    
    @FXML
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
     Auth authorization = Auth.ADMIN; 
    
 
    if(selectedRule.equals("LIBRARIAN")) {
    	authorization = Auth.LIBRARIAN;

    	
    }else if (selectedRule.equals("ADMIN")) {
    	authorization = Auth.ADMIN;
    }else {
    	authorization = Auth.BOTH;
    }
    
    
    User user = new User(idFld.getText(),passwordFld.getText().toString(),authorization);
    new SystemController().saveUser(user);
    dialogStage.close();

    }


    @FXML

    void comboBtnAction(ActionEvent event) {

    	
	    System.out.println(event.toString());

    }

    @FXML

    public void initialize() {


  
    



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

     

    }




	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		  System.out.println("yoyoyoyoyoyooyoyooy");

		    idFld.setEditable(false);
		     Random r = new Random();

		      int a = r.nextInt(10000);;
		      idFld.setText(""+ a);

   	 ObservableList<String> data = FXCollections.observableArrayList("LIBRARIAN", "ADMIN", "BOTH");
        comboBox.setItems(data);
        
    	comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValu, newValue) -> {

			selectedRule = newValue;

		
		});
		
	}

}