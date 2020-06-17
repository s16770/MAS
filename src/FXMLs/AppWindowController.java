package FXMLs;

import classes.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppWindowController implements Initializable{
	
	
	@FXML
    private Button logoutBtn, addIssueBtn;
    @FXML 
    public Label usernameLbl, entityLbl;
    @FXML
    private ToggleButton showClosedBtn;  
    @FXML
    private ChoiceBox<Project> projectCBox;
    @FXML
    private TableView<Project.Issue> yourIssuesTable;
    @FXML
    private TableView<Project.Issue> watchedIssuesTable;
	
    @FXML
	private void logOut(ActionEvent act) throws IOException{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
		
		Stage stage = (Stage)((Node)act.getSource()).getScene().getWindow();
		stage.setScene(
			new Scene(
				loader.load()
			)
		);
		stage.show();
		
	}
	
	@FXML
	private void viewIssue(ActionEvent act) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("IssueWindow.fxml"));
		
		Stage stage = (Stage)((Node)act.getSource()).getScene().getWindow();
		stage.setScene(
			new Scene(
				loader.load()
			)
		);
		IssueWindowController controller = loader.<IssueWindowController>getController(); 
		//controller.init();
		stage.show();
	}
	
	@FXML
	private void addNewIssue(ActionEvent act) throws IOException{
		
	    FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddIssueWindow.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load(), 932, 614);
        Stage stage = new Stage();
        stage.setTitle("Add new issue");
        stage.setScene(scene);
    	AddIssueWindowController controller = fxmlLoader.<AddIssueWindowController>getController(); 
		//controller.init();
    	controller.updatePriorityBox();
    	controller.updateProjectBox();
        stage.show();
       
	}
	
	public void updateProjectBox() {

		ObservableList<Project> projectList = FXCollections.observableArrayList(Project.getExtent());
		projectCBox.getItems().addAll(projectList);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	public void init() {
		
	}
}
