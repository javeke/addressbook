package contact;

import java.io.File;
import java.util.Scanner;

/**
 * 
 * @author Javier Bryan 620119423
 * @author Tashae Bowes 620116906
 * @version 1.0
 *
 */

public class TextUI extends UserInterface {
	
	private static AddressBook book;
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * displays a menu to the user
	 * @return the value of the users selection
	 */
	
	public static int display() {
		int choice=0;
		
		System.out.println("Choose an option (0-5)");
		System.out.println("1 - Create new contact");
		System.out.println("2 - Search contacts");
		System.out.println("3 - Update a contact");
		System.out.println("4 - Display contacts sorted by entry number");
		System.out.println("5 - Display contacts sorted by name");
		System.out.println("6 - Delete contact");
		System.out.println("0 - Exit");
		
		while (!sc.hasNextInt()) {
			System.out.println("Enter an integer value");
			sc.nextLine();
		}
		choice = sc.nextInt();
		sc.nextLine();
		
		return choice;
	}
	
	
	/**
	 * create and add a contact to the address book
	 */
	public static void createContact() {	
		String fname, lname, gender, address, alias;
		int dob;
		
		System.out.println("Enter first name: ");
		fname=sc.nextLine();
		
		System.out.println("Enter last name: ");
		lname=sc.nextLine();
		
		System.out.println("Enter gender: ");
		gender=sc.nextLine();
		
		System.out.println("Enter semi-colon separated address: ");
		address=sc.nextLine();
		
		System.out.println("Enter alias: ");
		alias=sc.nextLine();
		
		System.out.println("Enter date of birth(yyyymmdd): ");
		
		while (!sc.hasNextInt()) {
			System.out.println("Enter an integer value");
			sc.nextLine();
		}
		dob=sc.nextInt();
		sc.nextLine();
		
		book.add(fname, lname, Gender.valueOf(gender.trim().toUpperCase()), alias, address, dob);
	}
	
	/**
	 * Allows to search the address book for a specific contact
	 */
	public static void searchContact() {
		int option = 0;
		
		System.out.println("0 - Back");
		System.out.println("1 - Search by entry number");
		System.out.println("2 - Search by email");
		
		while (!sc.hasNextInt()) {
			System.out.println("Enter an integer value");
			sc.nextLine();
		}
		
		option = sc.nextInt();
		sc.nextLine();
		
		switch(option) {
			case 0:
				return;
			case 1:
				System.out.println("Enter entry number");
				while (!sc.hasNextInt()) {
					System.out.println("Enter an integer value");
					sc.nextLine();
				}
				System.out.println(book.displayContact(sc.nextInt()));
				sc.nextLine();
				break;
			case 2:
				System.out.println("Enter email");
				System.out.println(book.displayContact(sc.nextLine().trim()));
				break;	
			default:
				System.out.println("Action not Supported");
				break;
		}
	}
	
	/**
	 * Allows to modify a contact's information
	 */
	public static void updateContact() {
		String newValue = "";
		int answer = 0;
		int entryNo;
		
		System.out.println("Enter entry number for contact or enter 0 to go back");
		while (!sc.hasNextInt()) {
			System.out.println("Enter an integer value");
			sc.nextLine();
		}
		entryNo = sc.nextInt();
		
		if (entryNo == 0) {
			return;
		}
		  
		while (true) {
			System.out.println("Select property to modify");
			System.out.println("0 - Back");
			System.out.println("1 - Last name");
			System.out.println("2 - Alias");
			System.out.println("3 - Address");
			System.out.println("4 - Add email");
			System.out.println("5 - Delete email");
			System.out.println("6 - Add phone number");
			System.out.println("7 - Delete phone number");
			
			while (!sc.hasNextInt()) {
				System.out.println("Enter an integer value");
				sc.nextLine();
			}
			answer= sc.nextInt();
			sc.nextLine();
			
			switch (answer) {
				case 0:
					return;
				case 1:
					System.out.println("Enter new last name");
					newValue = sc.nextLine();
					book.update(entryNo,"lastname",newValue.trim());
					break;
				case 2:
					System.out.println("Enter new alias");
					newValue = sc.nextLine();
					book.update(entryNo,"alias",newValue.trim());
					break;
				case 3:
					System.out.println("Enter new address (Separated with semi-colons)");
					newValue = sc.nextLine();
					book.update(entryNo,"address",newValue.trim());
					break;
				case 4:
					System.out.println("Enter new email");
					newValue = sc.nextLine();
					book.update(entryNo,"addemail",newValue.trim());
					break;
				case 5:
					System.out.println("Enter email to delete");
					newValue = sc.nextLine();
					book.update(entryNo,"deleteemail",newValue.trim().toLowerCase());
					break;
				case 6:
					System.out.println("Enter new phone number");
					while (!sc.hasNextLong()) {
						System.out.println("Enter an integer value");
						sc.nextLine();
					}
					long newVal = sc.nextLong();
					sc.nextLine();
					book.update(entryNo,"addnumber",newVal);
					break;
				case 7:
					System.out.println("Enter number to delete");
					while (!sc.hasNextLong()) {
						System.out.println("Enter an integer value");
						sc.nextLine();
					}
					newVal = sc.nextLong();
					sc.nextLine();
					book.update(entryNo,"deletenumber",newVal);
					break;
				default:
					System.out.println("Action not Supported");
					break;
			}
		}
	}
	/**
	 * Displays a sorted a address book
	 */
	public static void displayContacts()
	{
		book.sort();
		System.out.println(book);
	}
	
	/**
	 * Displays a sorted address book based on their name
	 */
	public static void displayContactsByName() {
		book.sortByName();
		System.out.println(book);
	}
	
	/**
	 * Removes a contact
	 */
	public static void deleteContact() {
		int entryNo =0;
		int opt=0;
		String email="";
		
		System.out.println("0 - Back");
		System.out.println("1 - Delete by entry number");
		System.out.println("2 - Delete by email address");
		
		
		while (!sc.hasNextInt()) {
			System.out.println("Enter an integer value");
			sc.nextLine();
		}
		opt = sc.nextInt();
		sc.nextLine();
		
		switch(opt) {
			case 0:
				return;
			case 1:
				System.out.println("Enter entry number");
				while (!sc.hasNextInt()) {
					System.out.println("Enter an integer value");
					sc.nextLine();
				}
				entryNo=sc.nextInt();
				sc.nextLine();
				book.delete(entryNo);
				break;
			case 2:
				System.out.println("Enter email");
				email=sc.nextLine();
				book.delete(email);
				break;
			default:
				System.out.println("Action not Supported");
				break;
		}	
	}
	
	/**
	 * Allows the user to exit and save information
	 */
	public static void quit() {
		int choice =0;
		
		System.out.println("Save information to a file?");
		System.out.println("0 - Back");
		System.out.println("1 - Save");
		System.out.println("2 - Exit without saving");
		
		while (!sc.hasNextInt()) {
			System.out.println("Enter an integer value");
			sc.nextLine();
		}
		choice = sc.nextInt();
		sc.nextLine();
		
		switch(choice) {
			case 2:
				sc.close();
				System.exit(0);
			case 1:
				System.out.print("Save as: ");
				DataManager.setFilename(sc.nextLine().trim());
				DataManager.setFile(new File(DataManager.getFilename()));
				int status = DataManager.save(book);
				if (status==0) {
					System.out.println("Saved");
					sc.close();
					System.exit(0);
				}
				else {
					System.out.println("Error: File Not Saved");
					sc.close();
					System.exit(0);
				}
			case 0:
				return;
		}
	}
	
	/**
	 * Provides security via a login screen
	 */
	public static void login()
	{
		int val =0;
		LogBook log = new LogBook();
		String username="",password="";

		while(true) {
			System.out.println("Enter username");
			username = sc.nextLine();
			
			System.out.println("Enter password");
			password = sc.nextLine();
			
			val = log.login(username, password);
			int attempts = log.getAttemptsRemaining();
			
			if(val >0 && attempts>0) {
				System.out.println("Username or password incorrect");
				System.out.println(attempts+" attemps remaining");
			}
			else if(val==0) {
				System.out.println("\n");
				return;
			}
			else {
				System.out.println("Locked Out");
				System.out.println("Restart and Try Again");
				System.out.println("Hit enter to exit");
				sc.nextLine();
				sc.close();
				System.exit(0);
			}
		}
	}
	
	
	/**
	 * Opens an address book
	 */
	public static void open() {
		System.out.println("Enter filename if one exists or press enter if none exists");
		String filename = sc.nextLine();
		
		if (!(filename==null || filename=="")) {
			File file = new File(filename);
			if(file.exists()) {
				DataManager.setFilename(filename);
				DataManager.setFile(file);
				book= DataManager.open();
				return;
			}
		}
		book = DataManager.open();
		return;
	}
	
	public static void main(String args[]) {
		book = new AddressBook();
		
		int choice=0;
		
		login();	
		
		open();
		
		while(true) {
			
			choice = display();		
			
			switch(choice) {
				case 0:		
					quit();
					break;
				case 1:
					createContact();
					break;
				case 2:
					searchContact();
					break;
				case 3:
					updateContact();
					break;
				case 4:
					displayContacts();
					break;
				case 5: 
					displayContactsByName();
					break;
				case 6:
					deleteContact();
					break;
				default:
					System.out.println("Action not supported");
					break;				
			}
		}
	}
}

