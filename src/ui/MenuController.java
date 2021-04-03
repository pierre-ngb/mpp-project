package ui;

import java.io.IOException;

import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
	public void initialize() {
		switch (authorization) {
		case LIBRARIAN:
			member.setVisible(false);
			books.setVisible(false);
			users.setVisible(false);
			break;
		case ADMIN:
			users.setVisible(false);
			checkout.setVisible(false);
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
	void manageUserAction(ActionEvent event) {

		try {
			Start.setRoot("ManageUsers");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}