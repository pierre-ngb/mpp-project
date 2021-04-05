package ui;

import java.io.IOException;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import utils.Utils;


public class LoginViewController {

	 @FXML private Button loginBtn;
	 @FXML private TextField userNameFld;
	 @FXML private TextField passwordFld;

	 
	    String validateMessage = "" ;
	    public void validatingFields() {
	    	validateMessage = "";
	    	
	    	if (userNameFld.getText().length() < 1) {
	    		validateMessage = "Enter Username\n";
	    	}
	    	if (passwordFld.getText().length() < 1) {
	    		validateMessage = validateMessage +"Enter Password\n";
	    		 
	    	}
	    	
	    }
	 
	 
	 public void loginButtonAction(ActionEvent event) {

    
    validatingFields();

	if (validateMessage.length() > 0) {
	    System.out.println("blocked");

		   Utils.makeAlert(validateMessage,"Data Missing",AlertType.ERROR);

		return;
	}

	try {
		ControllerInterface c = new SystemController();

		c.login(userNameFld.getText().trim(), passwordFld.getText().trim());


	if (SystemController.currentAuth == Auth.LIBRARIAN){
		
	}else if (SystemController.currentAuth == Auth.LIBRARIAN) {
		
	}else {
		
	}
 
} catch(LoginException ex) {
	validateMessage = ex.getMessage();
	   Utils.makeAlert(validateMessage,"Validation",AlertType.ERROR);


	System.out.println(ex.getMessage());
	return;
}
	


			try {
				Start.setRoot("Menu");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	

	}
	 

}




