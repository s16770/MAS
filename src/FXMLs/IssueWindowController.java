package FXMLs;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import classes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class IssueWindowController implements Initializable{
	
	@FXML
    private Button logoutBtn, issueListBtn, watchBtn;
	@FXML
	private AnchorPane issuePane;
	@FXML
	private TableView<String> issueInfoTable;
	@FXML
	private TextArea commentArea;
	@FXML
	private TableView<Project.Issue.Comment> commentTable;
	@FXML
	private Button publishBtn;
	@FXML
	private RadioButton privateBtn;
	@FXML
	private Button addFileBtn;
	@FXML
	private ListView<File> fileList;
	@FXML
	private TableView<Project.Issue.Modification> modificationTable;
	@FXML
	private TableView<UserIssue> watchingTable;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
