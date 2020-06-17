package classes;
import java.io.Serializable;

public abstract class Person implements Serializable{
	
	private String fName, sName, passwd;
	public String uName;
	public Entity ent;
	
	public Person(String fName, String sName, String uName, String passwd, Entity ent) {
		this.fName = fName;
		this.sName = sName;
		this.uName = uName;
		this.passwd = passwd;
		this.ent = ent;
	}

	public String getEmail() {
		return uName + "@" + ent.name + ".pl";
	}
	
	public void addEntity(Entity e) {
		if(this.ent != e) {
			this.ent = e;
			e.addWorker(this);
		}
	}
	
	public String toString() {
		return "Username: " + uName + "\n" + "Email: " + getEmail();
	}
}
