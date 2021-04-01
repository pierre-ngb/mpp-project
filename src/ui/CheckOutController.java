package ui;



import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import utils.Utils;
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
    void checkoutBtnAction(ActionEvent event) {

    }
    

    @FXML
    public void initialize() {
    	
    	
//    	new SystemController().allBooks().forEach(b->{
//    		
//    		final ObservableList<Book> books = FXCollections.observableList(b);
//    		comboBook.itemsProperty().setValue(b);
//    		
//    	});
    	final ObservableList<Book> books = FXCollections.observableList(new SystemController().allBooks());
		comboBook.itemsProperty().setValue(books);
    	
//    	convertComboDisplay();
//    	selectAuthor();
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
