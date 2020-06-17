package classes;
import java.io.Serializable;
import java.time.LocalDate;

public class UserIssue implements Serializable{
	
	public Project.Issue watchedIssues;
	public User usersWatching;
	
	private LocalDate watchedsince;
	
	public UserIssue(User user, Project.Issue issue) {
		this.watchedsince = LocalDate.now();
		this.watchedIssues = issue;
		this.usersWatching = user;
		usersWatching.addUserIssue(this);
		watchedIssues.addUserIssue(this);
		
	}
	
	public LocalDate getWatchedSince() {
		return this.watchedsince;
	}
	
	public String toString() {
		return "Zgloszenie: " + watchedIssues.id + " jest obserwowane przez " + usersWatching.uName + " od " + getWatchedSince();
	}
}
