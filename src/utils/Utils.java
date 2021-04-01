package utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class Utils {

	public static void numberOnly(TextField txt) {
		txt.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            txt.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
}
