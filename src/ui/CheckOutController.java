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

import business.Author;
import business.Book;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;



public class CheckOutController {

    @FXML
    private ComboBox<Book> comboBook;

    @FXML
    private ComboBox<?> memberCombo;

    @FXML
    private TextField durationFld;

    @FXML
    private DatePicker checkoutDate;

    @FXML
    private DatePicker returnDate;
    
    @FXML
    private Button findBook;
    
    @FXML
    private TextField bookNameFld;


    @FXML
    private TextField memberNameFld;

    @FXML
    void checkoutBtnAction(ActionEvent event) {

    	
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
    			bookNameFld.setText(con.returnBook.getTitle());
    			
    		}
    		
    		
    		
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


//    	final ObservableList<Book> books = FXCollections.observableList(new SystemController().allBooks());
//		comboBook.itemsProperty().setValue(books);
//    	convertComboDisplay();
    	
    	
//    	Utils.numberOnly(noCopyFld);
//    	Utils.numberOnly(checkOutDurationFld);
    	    	
    }
    
	private void convertComboDisplay() {
    	comboBook.setConverter(new StringConverter<Book>() {
    		
    		@Override
    		public String toString(Book book) {
    			return book.getTitle() ;
    		}
    		
    		@Override
    		public Book fromString(final String string) {
    			return comboBook.getItems().stream().filter(
    					b -> b.getTitle().equals(string))
    					.findFirst().orElse(null);
    		}
    	});
    }
    
   

}
