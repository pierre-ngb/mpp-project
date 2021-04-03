package ui;

import business.Book;
import business.CheckoutRecordEntry;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.Utils;

public class OverdueBooksController {

	@FXML
	private TableView<CheckoutRecordEntry> entries;

	@FXML
	private TableColumn<CheckoutRecordEntry, String> isbnCol;

	@FXML
	private TableColumn<CheckoutRecordEntry, String> bookCol;

	@FXML
	private TableColumn<CheckoutRecordEntry, String> ncopyCol;

	@FXML
	private TableColumn<CheckoutRecordEntry, String> midCol;

	@FXML
	private TableColumn<CheckoutRecordEntry, String> dueCol;
	
//	private String isbn;
	
	 	private Stage dialogStage;
	    
	  	 
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	
	@FXML
	public void initialize() {
		String isbn = "";

		// create a text input dialog
		TextInputDialog td = new TextInputDialog("type ISBN");

		// setHeaderText
		td.setHeaderText("Enter the ISBN");
		td.showAndWait();
		isbn = td.getEditor().getText().trim();
		entries.setSelectionModel(null);
		isbnCol.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().getBook().getBook().getIsbn()) );
		bookCol.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().getBook().getBook().getTitle()) );
		ncopyCol.setCellValueFactory(cellData -> new SimpleStringProperty(
				""+cellData.getValue().getBook().getCopyNum()) );
		midCol.setCellValueFactory(cellData -> new SimpleStringProperty(
				""+cellData.getValue().getRecord().getMember().getMemberId()) );
		dueCol.setCellValueFactory(cellData -> new SimpleStringProperty(
				""+Utils.format(cellData.getValue().getDueDate())) );
		
		ControllerInterface ci = new SystemController();
		try {	
//			entries.getItems().addAll(ci.overdueBooks(isbn));
			ci.overdueBooks(isbn).forEach(t -> {
				entries.getItems().add(t);
			});
		} catch (LibrarySystemException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}


}
