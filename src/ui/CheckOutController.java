package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import utils.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import business.Author;
import business.Book;
import business.ControllerInterface;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;



public class CheckOutController {

     @FXML
    private TextField durationFld;

    @FXML
    private TextField checkoutDate;

    @FXML
    private TextField returnDate;
    
    @FXML
    private Button findBook;
    
    @FXML
    private TextField bookNameFld;

    @FXML
    private TextField memberNameFld;
    
    private Book book;
    private LibraryMember member;

    @FXML
    void checkoutBtnAction(ActionEvent event) {

    	ControllerInterface ci = new SystemController();
    	try {
    		if(isInputValid()) {
			ci.checkout(book, member);
			Utils.makeAlert("Checkout Done successfuly", "Checkout", AlertType.CONFIRMATION)
			.show();
			
			clearField();
    		}
			
		} catch (LibrarySystemException e) {
			Utils.makeAlert(e.getMessage(), "Checkout", AlertType.ERROR).show();
			clearField();
		}
    	
    }
    
    @FXML
    void findBookAction(ActionEvent event) {
    	
    	try {
        	  FXMLLoader loader = new FXMLLoader();
        	  loader.setLocation(Start.class.getResource("AllBooks" + ".fxml"));
    		AnchorPane page = (AnchorPane)loader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Select Book");
    		stage.initModality(Modality.WINDOW_MODAL);
    		
    		stage.initOwner(Start.getPrimaryStage());
    		Scene newScene = new Scene(page);
    		stage.setScene(newScene);
    		
    		AllBooksController con = loader.getController();
    		  con.setDialogStage(stage);
    		
    		stage.showAndWait();
    		if(con.returnBook != null) {
    			book = con.returnBook;
    			bookNameFld.setText(con.returnBook.getTitle());
    		    LocalDate dt = Utils.parse(LocalDate.now().format(
    					DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    		    String st = Utils.format(dt);
    		    checkoutDate.setText(st);
    		    LocalDate lt = LocalDate.now().plusDays(con.returnBook.getMaxCheckoutLength());
    			returnDate.setText(Utils.format(lt));
    		}  		
    		
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    
    }
    

    @FXML
    void backBtnAction(ActionEvent event) {
    	
    	try {
			Start.setRoot("Menu");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @FXML
    void findMemberAction(ActionEvent event) { 	
    	
    	try {
        	  FXMLLoader loader = new FXMLLoader();
        	  loader.setLocation(Start.class.getResource("AllMembers" + ".fxml"));
    		AnchorPane page = (AnchorPane)loader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Select Member");
    		stage.initModality(Modality.WINDOW_MODAL);
    		
    		stage.initOwner(Start.getPrimaryStage());
    		Scene newScene = new Scene(page);
    		stage.setScene(newScene);
    		
    		AllMembersController m = loader.getController();
    		m.setDialogStage(stage);
    		
    		stage.showAndWait();
    		if(m.getLibraryMember() != null) {
    			memberNameFld.setText(m.getLibraryMember().getFirstName() + " " + m.getLibraryMember().getLastName());
    			member = m.getLibraryMember();
    		}
    		
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    
    }

    private void clearField() {
    	bookNameFld.setText("");
    	memberNameFld.setText("");
    	checkoutDate.setText("");
    	returnDate.setText("");
    	book = null;
    	member = null;
    }
    
    private boolean isInputValid() {
    	String errorMessage = "";

        if (bookNameFld.getText() == null || bookNameFld.getText().length() == 0) {
            errorMessage += "Please select a book!\n"; 
        }
        if (memberNameFld.getText() == null || memberNameFld.getText().length() == 0) {
            errorMessage += "Please select a Member!\n"; 
        }
        if (checkoutDate.getText() == null || checkoutDate.getText().length() == 0) {
            errorMessage += "No valid checkout date!\n"; 
        }

        if (returnDate.getText() == null || returnDate.getText().length() == 0) {
            errorMessage += "No valid due date!\n"; 
        } 
       
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = Utils.makeAlert(errorMessage, "Invalid Fields", AlertType.ERROR);
            alert.showAndWait();
            
            return false;
        }
    }
   

    @FXML
    public void initialize() {
    	
    	bookNameFld.setEditable(false);
    	memberNameFld.setEditable(false);
    	checkoutDate.setEditable(false);
    	returnDate.setEditable(false);
    	clearField();
    	Utils.numberOnly(durationFld);
//    	Utils.numberOnly(checkoutDate);
    	    	
    }
     
   

}
