package classes;
import java.util.HashSet;
import java.util.Set;

public class User extends Person{
	
	public Set<UserIssue> watchedIssues;
	
	public User(String fName, String sName, String uName, String passwd, Entity ent) {
		super(fName, sName, uName, passwd, ent);
		this.watchedIssues = new HashSet<>();
	}
	
	public void addUserIssue(UserIssue usis) {
		if(!watchedIssues.contains(usis)) {
			watchedIssues.add(usis);
			usis.usersWatching = this;
		}
	}
	
	public void stopWatchIssue(UserIssue usis) {
		if(watchedIssues.contains(usis)) {
			watchedIssues.remove(usis);
			usis.usersWatching = null;
		}
	}
}
