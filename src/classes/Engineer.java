package classes;
import java.util.HashSet;

public class Engineer extends Person{
	
	protected HashSet<Project.Issue> assignedIssues = new HashSet<>();
	
	public Engineer(String fName, String sName, String uName, String passwd, Entity ent) {
		super(fName, sName, uName, passwd, ent);
	}
	
	public void assignIssue(Project.Issue iss) {
		if(!this.assignedIssues.contains(iss)) {
			this.assignedIssues.add(iss);
			iss.assignEngineer(this);
		}
	}	
}
