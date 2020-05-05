import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable{
	
	public int id;
	private String name;
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
	
	public void addIssue(Issue is) {
		issues.add(is);
	}
	
	public void addEntity(Entity newEntity) {
		
		if(newEntity.getClass() == Company.class) {
			entities[0] = newEntity;
		}else {
			entities[1] = newEntity;
		}
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
	
	public String toString() {
		String allIssues = "";
		for(Issue i : issues) {
			allIssues += i;
		}
		return this.name + "\n" + "Contractor: " + entities[company] + "\n" + "Client: " + entities[client] + "\n" + allIssues + "\n";
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
}
