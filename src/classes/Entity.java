package classes;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Entity implements Serializable{
	
	String name;
	private Set<Person> workers = new HashSet<>();
	private Map<Integer, Project> projectsQualif = new HashMap<>();
	
	public Entity(String name) {
		this.name = name;
	}
	
	public void addProjectQualif(Project newProject) {
		
		if(!projectsQualif.containsKey(newProject.id)) {
			projectsQualif.put(newProject.id, newProject);
			newProject.addEntity(this);
		}
	}
	
	public Project findProjectQualif(int id) throws Exception {
		
		if(!projectsQualif.containsKey(id)) {
			throw new Exception("Unable to find a project with id: " + id);
		}
		return projectsQualif.get(id);
	}
	
	public void addWorker(Person person) {
		if(!workers.contains(person)) {
			this.workers.add(person);
			person.ent = this;
		}
	}
	
	public String toString() {
		return this.name;
	}
	
}