

package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import utils.Utils;

import java.util.Random;

import business.Address;
import business.Author;
import business.LibraryMember;
import business.SystemController;
import javafx.event.ActionEvent;


public class AddNewMemberController {

    @FXML
    private TextField userIdFld;

    @FXML
    private TextField firstNameFld;

    @FXML
    private TextField lastNameFld;
    
    @FXML
    private TextField phoneFld;
    
    @FXML
    private TextField streetFld;
    
    @FXML
    private TextField cityFld;
    
    @FXML
    private TextField stateFld;
    @FXML
    private TextField zipFld;
 
    

    @FXML
    private ComboBox<Author> comboBox = new ComboBox<>();
    
    @FXML
    private TextField noCopyFld;

    @FXML
    private Button authorBtn;

    @FXML
    void addAuthorBtnAction(ActionEvent event) {

    }
    
    
    private Stage dialogStage;
   
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void addMemberAction(ActionEvent event) {

    	  validatingFields();

    		if (validateMessage.length() > 0) {
    			
    		
    			Utils.makeAlert(validateMessage,"Data Missing",AlertType.ERROR);
    			return;
    		}
    		
    		Address adres = new Address(streetFld.getText().trim(), cityFld.getText().trim(), stateFld.getText().trim(), zipFld.getText().trim());
    		
    		
    		LibraryMember member = new LibraryMember(userIdFld.getText().trim(), firstNameFld.getText().trim(), 
    				lastNameFld.getText().trim(), phoneFld.getText().trim(), adres);
//    		for(int i = 0; i < Integer.valueOf(noCopyFld.getText()); i++) {
//    			book1.addCopy();
//    		}
    		
    		
    		new SystemController().saveNewMember(member);
    		this.dialogStage.close();
    		

    		
//    		Book book1 = new Book(isbnFld.getText().trim(), titleFld.getText().trim(), 
//    				Integer.valueOf(checkOutDurationFld.getText()), authors);
//    		for(int i = 0; i < Integer.valueOf(noCopyFld.getText()); i++) {
//    			book1.addCopy();
//    		}
//    		new SystemController().saveBook(book1);
//    		this.dialogStage.close();
    		
    }

    @FXML
    void comboBtnAction(ActionEvent event) {

    }

    @FXML
    public void initialize() {
    	
    	userIdFld.setEditable(false);
   	 Random r = new Random();
   	 
   	 int a = r.nextInt(10000);;
   	userIdFld.setText(""+ a);
//    	new SystemController().allBooks().forEach(b->{
//    		
//    		final ObservableList<Author> authors = FXCollections.observableList(b.getAuthors());
//    		comboBox.itemsProperty().setValue(authors);
//    	});
//    	convertComboDisplay();
//    	selectAuthor();
//    	Utils.numberOnly(noCopyFld);
//    	Utils.numberOnly(checkOutDurationFld);
    	    	
    }
    
    @FXML
    public void selectAuthor() {
    	
//    	authors = new ArrayList<Author>();
//    	comboBox.getSelectionModel().selectedItemProperty().addListener((observable,oldValu,newValue)->{
//    		
//    		String authorsName = newValue.getFirstName() + " " + newValue.getLastName();
//    		authorFld.setText(authorsName);
//    		authors.add(newValue);
//    	});
    }
    
//    private void convertComboDisplay() {
//    	comboBox.setConverter(new StringConverter<Author>() {
//    		
//    		@Override
//    		public String toString(Author author) {
//    			return author.getFirstName() + " "+ author.getLastName();
//    		}
//    		
//    		@Override
//    		public Author fromString(final String string) {
//    			return comboBox.getItems().stream().filter(
//    					auth -> auth.getLastName().equals(string))
//    					.findFirst().orElse(null);
//    		}
//    	});
//    }
    

    String validateMessage = "" ;
    public void validatingFields() {
    	validateMessage = "";
    	
    	if (userIdFld.getText().length() < 1) {
    		validateMessage = "Enter User ID\n";
    	}
    	if (firstNameFld.getText().length() < 1) {
    		validateMessage = validateMessage +"Enter First Name\n";
    		 
    	}
    	
    	if (lastNameFld.getText().length() < 1) {
    		validateMessage = validateMessage +"Enter Last Name\n";
    		 
    	}
    	
    	if (phoneFld.getText().length() < 1) {
    		validateMessage = validateMessage +"Enter Phone\n";
    		 
    	}
    	
    	
    	if (streetFld.getText().length() < 1) {
    		validateMessage = validateMessage +"Enter Street\n";
    		 
    	}
    	
    	if (cityFld.getText().length() < 1) {
    		validateMessage = validateMessage +"Enter City\n";
    		 
    	}
    	
    	if (stateFld.getText().length() < 1) {
    		validateMessage = validateMessage +"Enter State\n";
    		 
    	}
    	
    	if (zipFld.getText().length() < 1) {
    		validateMessage = validateMessage +"Enter Zip\n";
    		 
    	}
    }
 
 

  

    
    
    
}
