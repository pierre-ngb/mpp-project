package ui;

import business.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class BookEditDialogController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField maxCheckField;
    @FXML
    private TextField isbnField;
  


    private Stage dialogStage;
    private boolean okClicked = false;
    private Book book;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	
    	
    	if(isInputValid()) {
    		book.setIsbn(isbnField.getText().trim());
    		book.setTitle(titleField.getText().trim());
    		book.setMaxCheckoutLength(Integer.valueOf(maxCheckField.getText()));
    		okClicked = true;
    		dialogStage.close();
    	}
    	
       
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
    	
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().length() == 0) {
            errorMessage += "No valid Title!\n"; 
        }
        if (isbnField.getText() == null || isbnField.getText().length() == 0) {
            errorMessage += "No valid ISBN!\n"; 
        }
        if (maxCheckField.getText() == null || maxCheckField.getText().length() == 0) {
            errorMessage += "No valid number of field!\n"; 
        }

      

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    public void setBook(Book book) {
    	this.book = book;
    	titleField.setText(book.getTitle());
    	isbnField.setText(book.getIsbn());
    	maxCheckField.setText(""+book.getMaxCheckoutLength());

    	
    	
    }
}