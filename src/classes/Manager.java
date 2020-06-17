package classes;

public class Manager extends Engineer{
	
	public Manager(String fName, String sName, String uName, String passwd, Entity ent) {
		super(fName, sName, uName, passwd, ent);
	}
	
	//przeciazenie
	public void assignIssue(Project.Issue iss, Engineer eng) {
		if(!eng.assignedIssues.contains(iss)) {
			eng.assignedIssues.add(iss);
			iss.assignEngineer(eng);
		}
	}
	
}
