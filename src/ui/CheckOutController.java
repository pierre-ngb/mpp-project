package ui;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    	ci.checkout(book, member);
    	ci.allMembers().forEach(m -> {
    		System.out.println(m);
    		if(m.getRecord() != null) {
    			System.out.println("Checkout records");
    			m.getRecord().getRecords().forEach(System.out::println);
    		}
    	});
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


    @FXML
    public void initialize() {
    	
    	bookNameFld.setEditable(false);
    	memberNameFld.setEditable(false);
    	checkoutDate.setEditable(false);
    	returnDate.setEditable(false);
    	
    	Utils.numberOnly(durationFld);
//    	Utils.numberOnly(checkoutDate);
    	    	
    }
     
   

}
