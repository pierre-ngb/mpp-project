package ui;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    
    public static Stage getPrimaryStage() {
    	return primaryStage;
    }

    @Override

    public void start(Stage primaryStage) throws IOException {

     primStage = primaryStage;

     primaryStage.setTitle("Library Management System");
     parent = loadFXML("login");
        scene = new Scene(parent, 600, 400);

        primaryStage.setScene(scene);

        primaryStage.show();

    }
}

