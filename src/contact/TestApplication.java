package contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class TestApplication {
	private ArrayList<String> usernames = new ArrayList<String>();
	private ArrayList<String> passwords = new ArrayList<String>();
	BufferedReader file;
	
	public TestApplication(){
		try {
			file = new BufferedReader(new FileReader("resources/textfile"));
			String line;
			String tokens[];
			while ((line=file.readLine())!=null) {
				tokens = line.split(":");
				usernames.add(tokens[0].toLowerCase());
				passwords.add(tokens[1]);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		Scanner cout = new Scanner(System.in);
					
		System.out.println("Enter username: ");
		
		System.out.println("Enter password: ");
		
		cout.close();
	}
	public static void main(String[] args) {
	}
}