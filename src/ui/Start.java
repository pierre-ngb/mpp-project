package ui;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import business.ControllerInterface;
import business.SystemController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Start extends Application {
	
	 private static Scene scene;
	    private static Stage primaryStage;
	    private Parent parent;
	    
	    
	public static void main(String[] args) {
		launch(args);
	}
	private static Stage primStage = null;
	public static Stage primStage() {
		return primStage;
	}
	
	public static class Colors {
		static Color green = Color.web("#034220");
		static Color red = Color.FIREBRICK;
	}
	
	private static Stage[] allWindows = { 
		LoginWindow.INSTANCE,
		AllMembersWindow.INSTANCE,	
		AllBooksWindow.INSTANCE
	};
	
	public static void hideAllWindows() {
		primStage.hide();
		for(Stage st: allWindows) {
			st.hide();
		}
	}
	
	
	
    private static Parent loadFXML(String fxml) throws IOException {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(Start.class.getResource(fxml + ".fxml"));
//    	"ui/"+
    	return fxmlLoader.load();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    @Override

    public void start(Stage primaryStage) throws IOException {

     primStage = primaryStage;

     primaryStage.setTitle("Library Management System");
     parent = loadFXML("login");

//        parent = loadFXML("studentDashboard");

        //clean error authentication

//        parent.getChildrenUnmodifiable().get(6).setVisible(false);
        scene = new Scene(parent, 640, 480);

        primaryStage.setScene(scene);

        primaryStage.show();

    }
}

//parent = loadFXML("Books");
////
////parent.getChildrenUnmodifiable().get(6).setVisible(false);
//
//scene = new Scene(topContainer, 420, 375);
//primaryStage.setScene(scene);
//primaryStage.show();

//next
//scene = new Scene(parent, 420, 375);
//primaryStage.setScene(scene);
//primaryStage.show();