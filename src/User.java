import java.util.HashSet;
import java.util.Set;

public class User extends Person{
	
	private UserIssue watchedIssues;
	
	public User(String fName, String sName, String uName, String passwd, Entity ent) {
		super(fName, sName, uName, passwd, ent);
	}
	
	public void watchIssue(Issue iss) {
		if(!watchedIssues.watchedIssues.contains(iss)) {
			watchedIssues.watchedIssues.add(iss);
			iss.addUser(this);
		}
	}
	
	public void stopWatchIssue(Issue iss) {
		watchedIssues.watchedIssues.remove(iss);
	}
}
