package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Utils;

import java.io.IOException;
import business.Book;
import business.ControllerInterface;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;
import javafx.event.ActionEvent;

public class CheckOutController {

	@FXML
	private TextField isbn;

	@FXML
	private TextField memberId;

	@FXML
	private Button findBook;

	@FXML
	private TextField bookNameFld;

	@FXML
	private TextField memberNameFld;

	private Book book;
	private LibraryMember member;

	private Stage dialogStage;
	private boolean okClicked = false;

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	void checkoutBtnAction(ActionEvent event) {

		ControllerInterface ci = new SystemController();
		try {
			if (isInputValid() > 0) {
				String isbnBook;
				String idMember;
				if(isInputValid() == 1) {
					isbnBook = book.getIsbn();
					idMember = member.getMemberId();
				}
				else {
					isbnBook = isbn.getText().trim();
					idMember = memberId.getText().trim();
				}
				ci.checkout(isbnBook, idMember);
				okClicked = true;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(dialogStage);
				alert.setTitle("Success");
				alert.setContentText("Checkout Done successfuly");

				alert.showAndWait();
				
				dialogStage.close();
			}

		} catch (LibrarySystemException e) {
			Utils.makeAlert(e.getMessage(), "Checkout", AlertType.ERROR).show();
			clearField();
		}

	}

	@FXML
	void findBookAction(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Start.class.getResource("AllBooks" + ".fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Select Book");
			stage.initModality(Modality.WINDOW_MODAL);

			stage.initOwner(Start.getPrimaryStage());
			Scene newScene = new Scene(page);
			stage.setScene(newScene);

			AllBooksController con = loader.getController();
			con.setDialogStage(stage);

			stage.showAndWait();
			if (AllBooksController.returnBook != null) {
				book = AllBooksController.returnBook;
				bookNameFld.setText(AllBooksController.returnBook.getTitle());
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
			AnchorPane page = (AnchorPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Select Member");
			stage.initModality(Modality.WINDOW_MODAL);

			stage.initOwner(Start.getPrimaryStage());
			Scene newScene = new Scene(page);
			stage.setScene(newScene);

			AllMembersController m = loader.getController();
			m.setDialogStage(stage);

			stage.showAndWait();
			if (m.getLibraryMember() != null) {
				memberNameFld.setText(m.getLibraryMember().getFirstName() + " " + m.getLibraryMember().getLastName());
				member = m.getLibraryMember();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void clearField() {
		bookNameFld.setText("");
		memberNameFld.setText("");
		isbn.setText("");
		memberId.setText("");
		book = null;
		member = null;
	}

	private int isInputValid() {
		String errorMessage = "";
		String errorMessage2 = "";

		if (bookNameFld.getText() == null || bookNameFld.getText().length() == 0) {
			errorMessage2 += "Please select a book!\n";
		}
		if (memberNameFld.getText() == null || memberNameFld.getText().length() == 0) {
			errorMessage2 += "Please select a Member!\n";
		}
		if (isbn.getText() == null || isbn.getText().length() == 0) {
			errorMessage += "No ISBN!\n";
		}

		if (memberId.getText() == null || memberId.getText().length() == 0) {
			errorMessage += "No member ID inserted!\n";
		}
		int val = 0;
		if (errorMessage.length() == 0 && errorMessage2.length() > 0) {
			val = 2;
			return 2;
		} else if (errorMessage.length() > 0 && errorMessage2.length() == 0) {
			val = 1;
			return 1;
		} else {
			// Show the error message.
			String finalMsg = "";
			if(val == 1) {
				finalMsg = errorMessage;
			}
			else if(val == 2) {
				finalMsg = errorMessage2;
			}
			else {
				finalMsg = "You should use one checkout method and complete Fields!\n";
			}
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setContentText(finalMsg+errorMessage+errorMessage2);

			alert.showAndWait();
			return 0;
		}
	}

	@FXML
	public void initialize() {

		bookNameFld.setEditable(false);
		memberNameFld.setEditable(false);
		clearField();
//    	Utils.numberOnly(checkoutDate);

	}

	public boolean isOkClicked() {
		return okClicked;
	}

}
