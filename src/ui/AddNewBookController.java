
package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import business.Author;
import business.Book;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class AddNewBookController {

	private List<Author> authors;
	@FXML
	private TextField isbnFld;

	@FXML
	private TextField titleFld;

	@FXML
	private TextField checkOutDurationFld;

	@FXML
	private TextField authorFld;

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
	void addBookAction(ActionEvent event) {

		validatingFields();

		if (validateMessage.length() > 0) {

			alertMessage();
			return;
		}

		Book book1 = new Book(isbnFld.getText().trim(), titleFld.getText().trim(),
				Integer.valueOf(checkOutDurationFld.getText()), authors);
		for (int i = 0; i < Integer.valueOf(noCopyFld.getText()); i++) {
			book1.addCopy();
		}
		new SystemController().saveBook(book1);
		this.dialogStage.close();

	}

	@FXML
	void comboBtnAction(ActionEvent event) {

	}

	@FXML
	public void initialize() {

		List<Author> aut = new ArrayList<>();

		new SystemController().allBooks().forEach(b -> {

			aut.addAll(b.getAuthors());
		});

		final ObservableList<Author> authors = FXCollections
				.observableList(aut.stream().distinct().collect(Collectors.toList()));
		comboBox.itemsProperty().setValue(authors);
		convertComboDisplay();
		selectAuthor();
		Utils.numberOnly(noCopyFld);
		Utils.numberOnly(checkOutDurationFld);

	}

	@FXML
	public void selectAuthor() {

		authors = new ArrayList<Author>();
		comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValu, newValue) -> {

			String authorsName = newValue.getFirstName() + " " + newValue.getLastName();

			authors.add(newValue);
			if (authors.size() > 1) {
				String s = authors.stream().map(a -> a.getFirstName() + " " + a.getLastName())
						.collect(Collectors.joining(",")).toString();
				authorFld.setText(s);
			} else {
				authorFld.setText(authorsName);
			}
		});
	}

	private void convertComboDisplay() {
		comboBox.setConverter(new StringConverter<Author>() {

			@Override
			public String toString(Author author) {
				return author.getFirstName() + " " + author.getLastName();
			}

			@Override
			public Author fromString(final String string) {
				return comboBox.getItems().stream().filter(auth -> auth.getLastName().equals(string)).findFirst()
						.orElse(null);
			}
		});
	}

	String validateMessage = "";

	public void validatingFields() {
		validateMessage = "";

		if (isbnFld.getText().length() < 1) {
			validateMessage = "Enter ISBN\n";
		}
		if (titleFld.getText().length() < 1) {
			validateMessage = validateMessage + "Enter Title\n";

		}

		if (checkOutDurationFld.getText().length() < 1) {
			validateMessage = validateMessage + "Enter Duration\n";

		}

		if (noCopyFld.getText().length() < 1) {
			validateMessage = validateMessage + "Enter Number Copies\n";

		}

		if (authorFld.getText().length() < 1) {
			validateMessage = validateMessage + "Enter Author\n";

		}
	}

	public void alertMessage() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Data Missing");
		alert.setContentText(validateMessage);
		alert.showAndWait();

	}

}
