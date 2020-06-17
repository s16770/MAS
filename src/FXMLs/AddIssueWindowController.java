package FXMLs;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddIssueWindowController implements Initializable{
	
	@FXML
	private AnchorPane newIssuePane;
	@FXML
	private ChoiceBox<Project> projectCBox;
	@FXML
	private TextField titleField;
	@FXML
	private TextArea descriptionField;
	@FXML
	private RadioButton installationBtn;
	@FXML
	private RadioButton breakdownBtn;
	@FXML
	private RadioButton usersBtn;
	@FXML
	private RadioButton modificationBtn;
	@FXML
	private RadioButton consultationBtn;
	@FXML
	private ChoiceBox<String> priorityCBox;
	@FXML
	private ListView<File> fileList;
	@FXML
	private Button createIssueBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	public void updatePriorityBox() {
		
		ObservableList<String> priorityList = FXCollections.observableArrayList("Low", "Medium", "High", "Critical");
		priorityCBox.getItems().addAll(priorityList);
	}
	
	public void updateProjectBox() {

		ObservableList<Project> projectList = FXCollections.observableArrayList(Project.getExtent());
		projectCBox.getItems().addAll(projectList);
	}

	
	
}
