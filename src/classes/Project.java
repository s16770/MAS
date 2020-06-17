package classes;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Project implements Serializable{
	
	public int id;
	public String name;
	private String description;
	private Entity [] entities = new Entity[2]; 
	private ArrayList <Issue> issues = new ArrayList<>(); 
	private static ArrayList<Project> extent = new ArrayList<>();
	private static int company = 0, client = 1;
	
	public Project(int id, String name, String description) {
		addProjectToExtent(this);
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public void addEntity(Entity newEntity) {
		
		if(entities[0] != newEntity && entities[1] != newEntity) {
			if(newEntity.getClass() == Company.class) {
				entities[0] = newEntity;
			}else {
				entities[1] = newEntity;
			}
			newEntity.addProjectQualif(this);
		}
	}
	
	public Issue addIssueToProject(int id, String title, String desc,String cat, int pri, User user) {
		 Issue iss = new Issue(id, title, desc, cat, pri, user);
		 issues.add(iss);
		 iss.project = this;
		 return iss;
	}
	
	public Issue addIssueToProject(int id, String title, String desc,String cat, int pri, Manager man) {
		 Issue iss = new Issue(id, title, desc, cat, pri, man);
		 issues.add(iss);
		 iss.project = this;
		 return iss;
	}
	
	public Issue getIssue(int id) {
		for(Issue i : issues) {
			if(i.id == id) {
				return i;
			}
		}
		return null;
	}
	
	 //metoda klasowa
	 public static void showCriticalIssues() {
		 System.out.println("Critical issues: \n");
		 for(Project p : extent) {
			 for (Issue i : p.issues) {
				 if(i.priority >= 4) {
					 System.out.println(i);
				 }
			 }
		 }
	 }
	
	/*public String toString() {
		String allIssues = "";
		for(Issue i : issues) {
			allIssues += i;
		}
		return this.name + "\n" + "Contractor: " + entities[company] + "\n" + "Client: " + entities[client] + "\n" + allIssues + "\n";
	}*/
	 
	 public String toString() {
		 return this.name;
	 }
	
	public static List<Project> getExtent() {
		return extent;
	}
	
	public static void addProjectToExtent(Project p) {
		extent.add(p);
	}
	
	public static void removeProjectFromExtent(Project p) {
		extent.remove(p);
	}
	
	public static void showProjects() {
		System.out.println("Projects: " + "\n");
		for(Project p : extent){
			System.out.println(p);
		}
	}
	
	public static void readExtent(ObjectInputStream stream) throws ClassNotFoundException, IOException {
		extent = (ArrayList<Project>) stream.readObject();
	}
	
	public static void saveExtent(ObjectOutputStream stream) throws IOException {
		stream.writeObject(extent);
	}
	
	public class Issue implements Serializable{

		 public int id;
		 public Project project;
		 private String title;
		 private String description;
		 private Category category;
		 public int priority;
		 private Status status;
		 private ArrayList<File> attachments = new ArrayList<>();
		 private final static int maxFileSize = 2;
		 public Engineer engineer; 
		 private Person issueCreator; 
		 public Set<UserIssue> watchingUser; 
		 private Set<Issue> relatedIssues = new HashSet<>(); 
		 public List<Modification> modHistory = new ArrayList<>(); 
		 public List<Comment> comments = new ArrayList<>();

		 
		 public Issue(int id, String title, String desc,String cat, int pri, User user) {
			 	this.id = id;
			 	this.title = title;
			 	this.description = desc;
			 	this.status = Status.fresh;
			 	this.category = Category.valueOf(cat);
			 	this.priority = pri;
			 	this.issueCreator = user;
			 	this.watchingUser = new HashSet<>();
		 }
		 
		 public Issue(int id, String title, String desc,String cat, int pri, Manager man) {
				this.id = id;
				this.title = title;
				this.description = desc;
				this.status = Status.fresh;
				this.category = Category.valueOf(cat);
				this.priority = pri;
				this.issueCreator = man;
				this.watchingUser = new HashSet<>();
		 }
		 
		 public Comment writeComment(String content, boolean priv, Person person) {
			 Comment comm = new Comment(content, priv, person);
			 comments.add(comm);
			 comm.issue = this;
			 return comm;
		 }
		 
		 public Modification addModification(Person person, String oldV, String newV) {
			 Modification mod = new Modification(person, oldV, newV);
			 modHistory.add(mod);
			 mod.issue = this;
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
		 
		 public void addUserIssue(UserIssue usis) {
			 if(!watchingUser.contains(usis)) {
					watchingUser.add(usis);
					usis.watchedIssues = this;
				}
		 }
		 
		 public String toString() {
			String allComments = "";
				for(Comment c : comments) {
					allComments += c;
				}
			String allWatchingUsers = "";
				for(UserIssue u : watchingUser) {
					allWatchingUsers += u.usersWatching.uName + " ";
				}
			if(!watchingUser.isEmpty()) {
				return "#" + id + "\nTitle: " + title + "\nStatus: " + status + "\n" + issueCreator + "\n" + allComments + "\n" + "obserwuje: " + allWatchingUsers + "\n";
			}else {
				return "#" + id + "\nTitle: " + title + "\nStatus: " + status + "\n" + issueCreator + "\n" + allComments + "\n";
			}
			
		 }
		 
		 public class Comment implements Serializable{
				
				private String content;
				private boolean privComment; 
				private Person person;
				private Issue issue;
				
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
				private Issue issue;
				
				public Modification(Person person, String oldV, String newV) {
					this.date = LocalDate.now();
					this.person = person;
					this.oldValue = oldV;
					this.newValue = newV;
				}
		 }
	}

}
