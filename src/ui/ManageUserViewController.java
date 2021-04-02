package ui;
import java.io.IOException;
import java.util.Iterator;

import business.Book;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageUserViewController {

    @FXML
    private TableView<LibraryMember> tableView;

    @FXML
    private TableColumn<LibraryMember, String> firstName;
	@FXML
	private TableColumn<LibraryMember, String> lastName;
    @FXML
    private Label userNamelbl;

    @FXML
    private Label passwordlbl;

    @FXML
    private Label previlagesbl;

    @FXML
    private Label copyLbl;

    @FXML
    private Label checkLbl1;
    
    @FXML
    private Label availabilityLbl;

    @FXML
    private Button newBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button deletBtn;

    @FXML
    private Button checkBtn;


    @FXML
    private void initialize() {
    	initViewBook();
    }
    
    public void initViewBook() {
    	
    	tableView.getItems().clear();
    	
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

    	ControllerInterface c = new SystemController();
    	c.allMembers().forEach(t->{
    		tableView.getItems().add(t);
    	});
    	displayBookInfo(null);
    	
    	tableView.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->
    	displayBookInfo(newValue));
    }
  
    public void displayBookInfo(LibraryMember member) {
    	if (member == null) {
    		userNamelbl.setText("");
    		passwordlbl.setText("");
    		previlagesbl.setText("");
    	
    		
    	}else {
    		
    		userNamelbl.setText(member.getFirstName() + " " + member.getLastName());
    	
    		passwordlbl.setText("*******");
    		previlagesbl.setText("****");

    		
    	}
    }
    
    
    @FXML
    void checkbtnAction(ActionEvent event) {
    	
    	/*
    	LibraryMember member = tableView.getSelectionModel().getSelectedItem();
        if(member != null) {
      	  boolean ok = editBook(member);
      	  
      	  if(ok) {
//      	    	displayBookInfo(book);
      	    	new SystemController().saveBook(member);
      	    	initViewBook();
      	    	tableView.getSelectionModel().select(member);
      	    	displayBookInfo(member);

      	  }
        }else {
      	  validateMessage = "No Book Selected";
  		  alertMessage();
        }*/
    	

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
    String validateMessage = "";
    @FXML
    void deletebtnAction(ActionEvent event) {
    	
    	   LibraryMember member = tableView.getSelectionModel().getSelectedItem();
    	   
    	      if(member != null) {
    	    	  
    	    	    	new SystemController().deleteMember(member);
    	    	    	initViewBook();
    	    	    	


    	      }else {
    	    	  validateMessage = "No Book Selected";
	    		  alertMessage();
    	      }

    }

    @FXML
    void editbtnAction(ActionEvent event) {

    /*  LibraryMember member = tableView.getSelectionModel().getSelectedItem();
      
      if(member != null) {
    	  boolean ok = editBook(member);
    	  
    	  if(ok) {
//    	    	displayBookInfo(book);
    	    	new SystemController().saveBook(member);
    	    	initViewBook();
    	    	tableView.getSelectionModel().select(member);
    	    	displayBookInfo(member);

    	  }
      }else {
    	  validateMessage = "No Book Selected";
		  alertMessage();
      }

    */

    }
    
    

    public boolean editBook(LibraryMember member) {
    	try {
      	  FXMLLoader loader = new FXMLLoader();
      	  loader.setLocation(Start.class.getResource("BookEditDialog" + ".fxml"));
  		AnchorPane page = (AnchorPane)loader.load();
  		Stage stage = new Stage();
  		stage.setTitle("Edit book");
  		stage.initModality(Modality.WINDOW_MODAL);
  		
  		stage.initOwner(Start.getPrimaryStage());
  		Scene newScene = new Scene(page);
  		stage.setScene(newScene);
  		
  		BookEditDialogController con = loader.getController();
//  		  con.setDialogStage(stage);
//  		  con.setBook(member);
  		
  		stage.showAndWait();
  		
  		return con.isOkClicked();
  		
  		
  	} catch (IOException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		return false;
  	}
    }
    
    @FXML
    void newBtnAction(ActionEvent event) {
    	
    	addNewMember();
    	initialize();
    	
    }
    
    public void addNewMember() {
    	
    	try {
        	  FXMLLoader loader = new FXMLLoader();
        	  loader.setLocation(Start.class.getResource("NewUser" + ".fxml"));
    		AnchorPane page = (AnchorPane)loader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Add new member");
    		stage.initModality(Modality.WINDOW_MODAL);
    		
    		stage.initOwner(Start.getPrimaryStage());
    		Scene newScene = new Scene(page);
    		stage.setScene(newScene);
    		
    		AddNewBookController con = loader.getController();
    		  con.setDialogStage(stage);
    		
    		stage.showAndWait();
    		
    		
//    		return con.isOkClicked();
    		
    		
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
//    		return false;
    	}
    	
    }
    
    public void alertMessage() {
	  	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Alert");
	    alert.setHeaderText("Select book you want to delete!");
	    alert.setContentText(validateMessage);
	    alert.showAndWait();

}
    }