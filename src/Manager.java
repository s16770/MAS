
public class Manager extends Engineer{
	
	public Manager(String fName, String sName, String uName, String passwd, Entity ent) {
		super(fName, sName, uName, passwd, ent);
	}
	
	//przeciazenie
	public void assignIssue(Issue iss, Engineer eng) {
		eng.assignedIssues.add(iss);
		iss.engineer = eng;
	}
}
