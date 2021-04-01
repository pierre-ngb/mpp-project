package ui;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;




public class MenuController {

    @FXML
    void checkoutBookAction(ActionEvent event) {
    	
    	System.out.println("clicked");
    	try {
    		
			Start.setRoot("CheckOut");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void manageBookAction(ActionEvent event) {
    	
    	System.out.println("clicked");
    	try {
			Start.setRoot("LibrarianView");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void manageMemberAction(ActionEvent event) {

    }

    @FXML
    void manageUserAction(ActionEvent event) {

    }
    }