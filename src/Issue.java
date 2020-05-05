import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Issue implements Serializable{

	 private int id;
	 private String title;
	 private String description;
	 private Category category;
	 public int priority;
	 private Status status;
	 private ArrayList<File> attachments = new ArrayList<>();
	 private static int maxFileSize = 2;
	 public Engineer engineer; 
	 private User user; 
	 public UserIssue watchingUser;
	 private Set<Issue> relatedIssues = new HashSet<>(); 
	 private List<Modification> modHistory = new ArrayList<>(); 
	 private List<Comment> comments = new ArrayList<>();
	 public enum Category {installation,  breakdown, users, modification, consultation};
	 public enum Status {fresh, assigned, in_Progress, suspended, closed};
	 
	 public Issue(int id, String title, String desc,String cat, int pri, User user) {
		this.id = id;
		this.title = title;
		this.description = desc;
		this.status = Issue.Status.fresh;
		this.category = Issue.Category.valueOf(cat);
		this.priority = pri;
		this.user = user;
	 }
	 
	 public Comment writeComment(String content, boolean priv, Person person) {
		 Comment comm = new Comment(content, priv, person);
		 comments.add(comm);
		 return comm;
	 }
	 
	 public Modification addModification(Person person, String oldV, String newV) {
		 Modification mod = new Modification(person, oldV, newV);
		 modHistory.add(mod);
		 return mod;
	 }
	 
	 public void assignEngineer(Engineer eng) {
		 if(this.engineer != eng) {
			 this.engineer = eng;
			 eng.assignIssue(this);
		 }
	 }
	 
	 public String getEngineer() {
		 if(this.engineer == null) {
			 return "This issue is not assigned to anyone yet";
		 }else {
			 return this.engineer.toString();
		 }
	 }
	 
	 public void addUser(User user) {
			if(!watchingUser.usersWatching.contains(user)) {
				watchingUser.usersWatching.add(user);
				user.watchIssue(this);
			}
	 }
	 
	 public String toString() {
		String allComments = "";
			for(Comment c : comments) {
				allComments += c;
			}
		return "#" + id + "\nTitle: " + title + "\nStatus: " + status + "\n" + user + "\n" + allComments + "\n";
	 }
	 
	 public class Comment implements Serializable{
			
			private String content;
			private boolean privComment; 
			private Person person;
			
			public Comment(String content, boolean priv, Person person) {
				this.content = content;
				this.privComment = priv;
				this.person = person;
			}
			
			public String toString() {
				return this.person.uName + " commented: " + this.content;
			}
	 }
	 
	 public class Modification implements Serializable{
			
			LocalDate date;
			Person person;
			String oldValue;
			String newValue;
			
			public Modification(Person person, String oldV, String newV) {
				this.date = LocalDate.now();
				this.person = person;
				this.oldValue = oldV;
				this.newValue = newV;
			}
	 }
}
