import java.util.HashSet;

public class Engineer extends Person{
	
	protected HashSet<Issue> assignedIssues = new HashSet<>();
	
	public Engineer(String fName, String sName, String uName, String passwd, Entity ent) {
		super(fName, sName, uName, passwd, ent);
	}
	
	public void assignIssue(Issue iss) {
		if(!assignedIssues.contains(iss)) {
			assignedIssues.add(iss);
			iss.assignEngineer(this);
		}
	}	
}
