import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		File dbFile = new File("db_file");
		int selection = 0;
		
		while(selection != 4) {
			
			System.out.println("To save exstension press - 1\n"
					+ "To read exstension press - 2\n"
					+ "To show extension and critical issues press - 3\n"
					+ "To exit press - 4");
			
			Scanner sc = new Scanner(System.in);
			selection = Integer.parseInt(sc.next());
	
			if(selection == 1) {
		
				enterData();
				ObjectOutputStream outStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dbFile)));
				Project.saveExtent(outStream);
				outStream.close();
		
			}else if(selection == 2) {

				ObjectInputStream inStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dbFile)));
				Project.readExtent(inStream);
				inStream.close();
				
			}else if(selection == 3) {
				
				Project.showProjects();
				Project.showCriticalIssues();
			}
		}
	}
	
	public static void enterData() {
		
		Company com = new Company("firmaTestowa");
		Company com2 = new Company("fTest2");
		Company com3 = new Company("fTest3");
		
		User user1 = new User("Jan", "Kowalski", "jankow", "qaz123", com);
		User user2 = new User("Tomasz", "Sikorski", "tomsik", "2wsxzaq1", com2);
		
		Client cl = new Client("KlientTestowy");
		Client cl2 = new Client("cTest2");
		Client cl3 = new Client("cTest3");
		
		Issue iss1 = new Issue(111, "awaria firewalla", "Przeciazony data plane", "breakdown", 4, user1);
		Issue iss2 = new Issue(122, "konsultacja dlp", "Metoda wdra¿ania dlp", "consultation", 2, user1);
		Issue iss3 = new Issue(133, "zmiana hasla", "Proœba o zmiane hasla uzytkownika jankow", "users", 3, user2);
		
		iss1.writeComment("This is a comment that has been written for testing purposes", false, user1);
		iss2.writeComment("Comments are like box of chocolates", false, user2);
		
		Project p1 = new Project(1, "projektTestowy", "Sprawdzanie dzia³ania ekstensji");
		Project p2 = new Project(2, "pTestowy2", "testestestestest");
		Project p3 = new Project(2, "pTestowy3", "testestestestest");
		
		p1.addIssue(iss2);
		p1.addIssue(iss3);
		p3.addIssue(iss1);
		
		com.addProjectQualif(p1);
		cl.addProjectQualif(p1);
		
		
		
		System.out.println(iss1.getEngineer());
	}

}
