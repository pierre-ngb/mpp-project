package ui;


import business.Book;
import business.LibraryMember;
import business.SystemController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AllMembersController {
	
	
	@FXML
	private TableView< LibraryMember > allMembers;
	@FXML
	private TableColumn<LibraryMember, String> firstName;
	@FXML
	private TableColumn<LibraryMember, String> lastName;
	@FXML
	private TableColumn<LibraryMember, String> phone;
	@FXML
	private TableColumn<LibraryMember, String> adress;
	
	LibraryMember returnMember;
	
	
	public LibraryMember getLibraryMember() {
		return returnMember;
	}
	
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
		allMembers.getItems().clear();
		
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		phone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		adress.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getAddress().toString()));
		

		
		SystemController ci = new SystemController();
		ci.allMembers().forEach(t->{
			allMembers.getItems().add(t);
    	});
		
		allMembers.setRowFactory(rf -> {
			TableRow<LibraryMember> row = new TableRow<LibraryMember>();
			row.setOnMouseClicked(evt -> {
				if(evt.getClickCount() == 2 && (!row.isEmpty())) {
					returnMember = row.getItem();
					System.out.println(returnMember);
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


