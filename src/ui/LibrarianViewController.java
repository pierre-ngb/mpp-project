package ui;

import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Collectors;

import business.Book;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LibrarianViewController {

	@FXML
	private TableView<Book> tableView;

	@FXML
	private TableColumn<Book, String> col;
	@FXML
	private Label titlelbl;

	@FXML
	private Label isbnlbl;

	@FXML
	private Label authorlbl;

	@FXML
	private Label copyLbl;

	@FXML
	private Label checkLbl1;

	@FXML
	private Label availabilityLbl;

	@FXML
	private Button newBtn;
	@FXML
	private Button copyBook;

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
		col.setCellValueFactory(new PropertyValueFactory<>("Title"));
		ControllerInterface c = new SystemController();
		c.allBooks().forEach(t -> {
			tableView.getItems().add(t);
		});
		displayBookInfo(null);

		tableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> displayBookInfo(newValue));
	}

	public void displayBookInfo(Book book) {
		if (book == null) {
			titlelbl.setText("");
			authorlbl.setText("");
			checkLbl1.setText("");
			isbnlbl.setText("");
			titlelbl.setText("");
			copyLbl.setText("");
			availabilityLbl.setText("");

		} else {

			titlelbl.setText(book.getTitle());

			if (book.getAuthors().size() > 1) {
				String s = book.getAuthors().stream().map(a -> a.getFirstName() + " " + a.getLastName())
						.collect(Collectors.joining(",")).toString();
				authorlbl.setText(s);
			} else {
				String authorsName = book.getAuthors().get(0).getFirstName() + " "
						+ book.getAuthors().get(0).getLastName();
				authorlbl.setText(authorsName);
			}

			checkLbl1.setText("" + book.getMaxCheckoutLength());
			isbnlbl.setText(book.getIsbn());
			copyLbl.setText("" + book.getNumCopies());
			availabilityLbl.setText(Boolean.toString(book.isAvailable()));

		}
	}

	@FXML
	void checkbtnAction(ActionEvent event) {

		Book book = tableView.getSelectionModel().getSelectedItem();
		if (book != null) {
			boolean ok = editBook(book);

			if (ok) {
//      	    	displayBookInfo(book);
				new SystemController().saveBook(book);
				initViewBook();
				tableView.getSelectionModel().select(book);
				displayBookInfo(book);

			}
		} else {
			validateMessage = "No Book Selected";
			alertMessage();
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

	String validateMessage = "";

	@FXML
	void deletebtnAction(ActionEvent event) {

		Book book = tableView.getSelectionModel().getSelectedItem();
		if (book != null) {

			new SystemController().deleteBook(book);
			initViewBook();

		} else {
			validateMessage = "No Book Selected";
			alertMessage();
		}

	}

	@FXML
	void editbtnAction(ActionEvent event) {

		Book book = tableView.getSelectionModel().getSelectedItem();
		if (book != null) {
			boolean ok = editBook(book);

			if (ok) {
//    	    	displayBookInfo(book);
				new SystemController().saveBook(book);
				initViewBook();
				tableView.getSelectionModel().select(book);
				displayBookInfo(book);

			}
		} else {
			validateMessage = "No Book Selected";
			alertMessage();
		}

	}

	public boolean editBook(Book book) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Start.class.getResource("BookEditDialog" + ".fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Edit book");
			stage.initModality(Modality.WINDOW_MODAL);

			stage.initOwner(Start.getPrimaryStage());
			Scene newScene = new Scene(page);
			stage.setScene(newScene);

			BookEditDialogController con = loader.getController();
			con.setDialogStage(stage);
			con.setBook(book);

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

		addNewbook();
		initialize();

	}

	@FXML
	public void addCopy() {
		Book book = tableView.getSelectionModel().getSelectedItem();
		String isbn = "";
		if (book == null) {
			// create a text input dialog
			TextInputDialog td = new TextInputDialog("type ISBN");

			// setHeaderText
			td.setHeaderText("Enter the ISBN");
			td.showAndWait();

			isbn = td.getEditor().getText().trim();
		} else {
			isbn = book.getIsbn();
		}
		
		ControllerInterface ci = new SystemController();
		try {
			ci.addBookCopy(isbn);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(Start.getPrimaryStage());
			alert.setTitle("Success");
			alert.setContentText("Copy Added Succesfully");
			alert.showAndWait();
			initViewBook();

		} catch (LibrarySystemException e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(Start.getPrimaryStage());
			alert.setTitle("Error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	public void addNewbook() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Start.class.getResource("AddNewBook" + ".fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Add new book");
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