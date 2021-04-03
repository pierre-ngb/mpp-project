package ui;

import java.io.IOException;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.SystemController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Utils;

public class CheckoutRecordController {

	@FXML
	TableView<CheckoutRecordEntry> records;
	@FXML
	TableColumn<CheckoutRecordEntry, String> book;
	@FXML
	TableColumn<CheckoutRecordEntry, String> copyNum;
	@FXML
	TableColumn<CheckoutRecordEntry, String> checkoutDate;
	@FXML
	TableColumn<CheckoutRecordEntry, String> dueDate;
	@FXML
	Button checkoutHandle;

	@FXML
	public void initialize() {
		initView();
	}

	private void initView() {
		records.getItems().clear();
		book.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getBook().getBook().getTitle()));
		checkoutDate.setCellValueFactory(
				cellData -> new SimpleStringProperty(Utils.format(cellData.getValue().getCheckoutDate())));
		dueDate.setCellValueFactory(
				cellData -> new SimpleStringProperty(Utils.format(cellData.getValue().getDueDate())));
		copyNum.setCellValueFactory(
				cellData -> new SimpleStringProperty(""+cellData.getValue().getBook().getCopyNum()));

		SystemController ci = new SystemController();
		ci.allMembers().forEach(t -> {
			records.getItems().addAll(t.getRecord().getRecords());
		});
	}
	
	@FXML
	public void btnCheckoutHandle() {
		boolean ok = createCheckout();
		if(ok) {
	    	initView();
		}
	}

	private boolean createCheckout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Start.class.getResource("CheckOut.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage stage = new Stage();
			stage.setTitle("New Record Entry");
			stage.initModality(Modality.WINDOW_MODAL);

			stage.initOwner(Start.getPrimaryStage());
			Scene newScene = new Scene(page);
			stage.setScene(newScene);

			CheckOutController con = loader.getController();
			con.setDialogStage(stage);

			stage.showAndWait();
			return con.isOkClicked();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
