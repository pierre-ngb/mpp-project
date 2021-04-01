package ui;
import business.ControllerInterface;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class LibrarianViewController {

    @FXML
    private TableView<?> tableView;

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
    private Button newBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button deletBtn;

    @FXML
    private Button checkBtn;

    @Override
    protected void finalize() throws Throwable {
    	// TODO Auto-generated method stub
    	super.finalize();
    	
    	
//    	TableColumn<Book, String> column1 = new TableColumn<>("First Name");
//        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//
//
//        TableColumn<Book, String> column2 = new TableColumn<>("Last Name");
//        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//
//
//        tableView.getColumns().add(column1);
//        tableView.getColumns().add(column2);
//
//        tableView.getItems().add(new Book("John", "Doe"));
//        tableView.getItems().add(new Book("Jane", "Deer"));
//
//        VBox vbox = new VBox(tableView);
//		ControllerInterface c = new SystemController();
//        System.out.println(c.allBooks()
//);
    }
    
    @FXML
    void checkbtnAction(ActionEvent event) {

    }

    @FXML
    void deletebtnAction(ActionEvent event) {

    }

    @FXML
    void editbtnAction(ActionEvent event) {

    }

    @FXML
    void newBtnAction(ActionEvent event) {

    }
    }