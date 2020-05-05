import java.util.ArrayList;

public class Company extends Entity{
	
	private ArrayList<Engineer> workers = new ArrayList<>();
	
	public Company(String name) {
		super(name);
	}
}
