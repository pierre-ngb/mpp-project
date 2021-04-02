package ui;

import java.io.IOException;

import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import utils.Utils;

public class MenuController {

	Auth authorization = SystemController.currentAuth;

	@FXML
	void checkoutBookAction(ActionEvent event) {
		if (authorization == Auth.BOTH || authorization == Auth.LIBRARIAN) {

			try {
				Start.setRoot("CheckOut");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			Utils.makeAlert("You don't have privileges to access to this module", "Error", AlertType.ERROR).show();
			return;
		}

	}

	@FXML
	void manageBookAction(ActionEvent event) {

		if (authorization == Auth.BOTH || authorization == Auth.ADMIN) {

			try {
				Start.setRoot("LibrarianView");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Utils.makeAlert("You don't have privileges to access to this module", "Error", AlertType.ERROR).show();
			return;
		}

	}

	@FXML
	void manageMemberAction(ActionEvent event) {

		if (authorization == Auth.BOTH || authorization == Auth.ADMIN) {

			try {
				Start.setRoot("ManageMembers");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Utils.makeAlert("You don't have privileges to access to this module", "Error", AlertType.ERROR).show();
			return;
		}
	}

	@FXML
	void manageUserAction(ActionEvent event) {

		if (authorization == Auth.BOTH) {

			try {
				Start.setRoot("ManageUsers");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Utils.makeAlert("You don't have privileges to access to this module", "Error", AlertType.ERROR).show();
			return;
		}

	}
}