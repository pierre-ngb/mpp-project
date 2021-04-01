package ui;

import java.io.IOException;

import business.Book;
import business.SystemController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AllBooksController {
	
	@FXML
	private TableView<Book> allBooks;
	@FXML
	private TableColumn<Book, String> isbnCol;
	@FXML
	private TableColumn<Book, String> titleCol;
	@FXML
	private TableColumn<Book, String> durationCol;
	@FXML
	private TableColumn<Book, String> availabilityCol;
	@FXML
	private TableColumn<Book, String> copiesCol;
	
	public static Book returnBook;
	
    private Stage dialogStage;

	
	    /**
	     * Sets the stage of this dialog.
	     * 
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	
	@FXML
	public void initialize() {
		allBooks.getItems().clear();
		
		isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
		durationCol.setCellValueFactory(new PropertyValueFactory<>("maxCheckoutLength"));
		availabilityCol.setCellValueFactory(cellData -> 
		new SimpleStringProperty(Boolean.toString(cellData.getValue().isAvailable())));
		
		copiesCol.setCellValueFactory(cellData -> 
		new SimpleStringProperty(""+cellData.getValue().getCopyNums()));
//		copiesCol.setCellValueFactory(new PropertyValueFactory<>("No. Copies"));
		
		SystemController ci = new SystemController();
		ci.allBooks().forEach(t->{
    		allBooks.getItems().add(t);
    	});
		
		allBooks.setRowFactory(rf -> {
			TableRow<Book> row = new TableRow<Book>();
			row.setOnMouseClicked(evt -> {
				if(evt.getClickCount() == 2 && (!row.isEmpty())) {
					returnBook = row.getItem();
					System.out.println(returnBook);
					closed();
					
				}
			});
			return row;
		});
	}
	
	public void closed() {
		
        dialogStage.close();

	}
	
	

}
