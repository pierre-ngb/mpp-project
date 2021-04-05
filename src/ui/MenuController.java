package ui;

import java.io.IOException;

import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController {

	Auth authorization = SystemController.currentAuth;

	@FXML
	private Button member;
	@FXML
	private Button users;
	@FXML
	private Button books;
	@FXML
	private Button checkout;
	@FXML
	private Button checkoutrec;

	@FXML
	public void initialize() {
		switch (authorization) {
		case LIBRARIAN:
			member.setVisible(false);
			books.setVisible(false);
			users.setVisible(false);
			checkoutrec.setVisible(false);
			break;
		case ADMIN:
			users.setVisible(false);
			checkout.setVisible(false);
			checkoutrec.setVisible(false);
			break;
		default:
			break;
		}
	}

	@FXML
	void checkoutBookAction(ActionEvent event) {
		try {
			Start.setRoot("CheckoutRecord");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void manageBookAction(ActionEvent event) {

		try {
			Start.setRoot("LibrarianView");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void manageMemberAction(ActionEvent event) {

		try {
			Start.setRoot("ManageMembers");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
    void backBtnAction(ActionEvent event) {

 

        try {
            Start.setRoot("login");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	@FXML
	void manageUserAction(ActionEvent event) {

		try {
			Start.setRoot("ManageUsers");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void seeRecordsAction() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Start.class.getResource("OverdueBooks.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("Overdue Book copies");
			stage.initModality(Modality.WINDOW_MODAL);

			stage.initOwner(Start.getPrimaryStage());
			Scene newScene = new Scene(page);
			stage.setScene(newScene);
			OverdueBooksController con = loader.getController();
//			con.setIsbn(isbn);
			con.setDialogStage(stage);

			stage.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}