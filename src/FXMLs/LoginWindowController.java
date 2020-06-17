package FXMLs;

import classes.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginWindowController implements Initializable{
	
	@FXML
	private TextField loginField;
	
	@FXML
	private PasswordField passwdField;
	
	@FXML
    private Button loginBtn;
	
	@FXML
	private AnchorPane logPane;

	@FXML
	private void logIn(ActionEvent act) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AppWindow.fxml"));
		
		Stage stage = (Stage)((Node)act.getSource()).getScene().getWindow();
		stage.setScene(
			new Scene(
				loader.load()
			)
		);
		
		AppWindowController controller = loader.<AppWindowController>getController(); 
		controller.init();
		controller.usernameLbl.setText(loginField.getText());
		controller.entityLbl.setText("do zrobienia");
		controller.updateProjectBox();
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//updateCBox();
		
	}
}
