package contact;

/**
 * 
 * @author Javier Bryan 620119423
 * @author Tashae Bowes 620116906
 * @version 1.0
 *
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AddressBook implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Contact> contacts;
	
	/**
	 * null constructor for AddressBook 
	 */
	public AddressBook() {
		contacts = new ArrayList<Contact>();
	}
	
	/**
	 * adds the  contact parameter to the list
	 * @param c0 the contact to be added
	 */
	public void add(Contact c0) {
		Contact.setCounter(contacts.size());
		contacts.add(c0);
	}
	
	/**
	 * returns the list of contacts;
	 * @return the list of contacts
	 */
	public ArrayList<Contact> getContacts() {
		return contacts;
	}
	
	/**
	 * adds a new contact to the list
	 * @param firstName contact firstname
	 * @param lastName contact lastname
	 * @param gender contact gender
	 * @param alias contact alias
	 * @param address contact address
	 * @param dob contact date of birth
	 */
	public void add(String firstName, String lastName, Gender gender, String alias, String address, int dob) {
		sort();
		if (contacts.isEmpty()) 
			Contact.setCounter(0);

		else
			Contact.setCounter(contacts.get(contacts.size()-1).getEntryNo());
		
		Contact c0 = new Contact(firstName, lastName, gender, dob);
		c0.setAlias(alias);
		c0.setAddress(address);
		contacts.add(c0);
	}
	
	/**
	 * searches the address book for a contact with entry number passed as the key
	 * @param key entry number
	 * @return the position of the contact in the sorted list
	 */
	public int search(int key) {
		ArrayList<Integer> l0 = new ArrayList<Integer>();
		Collections.sort(contacts);
		
 		for (int i=0; i<contacts.size(); i++) {
			l0.add(contacts.get(i).getEntryNo());
		}
 		
 		return Collections.binarySearch(l0, key);
	}
	
	/**
	 * searches the address book for a contact with email passed as the key
	 * @param key email address
	 * @return the position of the contact in the sorted list
	 */
	public int search(String key) {
		String l0[];
		int index=-1;
		
		for (int i=0; i<contacts.size();i++) {
			l0 = contacts.get(i).getEmailList();
			Arrays.sort(l0);
			index = Arrays.binarySearch(l0, key);
			if (index>=0) {
				return index;
			}
		}
		return index;
	}
	
	/**
	 * provides a string representation of the contact with the passed parameter
	 * @param entryNo the entry number for the contact
	 * @return string form of the contact
	 */
	public String displayContact(int entryNo) {
		int index = search(entryNo);
		if (index<0)
			return "No Such Contact Exist";
		return contacts.get(index).toDisplay();
	}
	
	/**
	 * provides a string representation of the contact with the passed parameter
	 * @param email the entry number for the contact
	 * @return string form of the contact
	 */
	public String displayContact(String email) {
		int index = search(email);
		if (index<0)
			return "No Such Contact Exist";
		return contacts.get(index).toDisplay();
	}
	
	/**
	 * sorts the addressbook based on their entry number in ascending order
	 */
	public void sort()
	{
		Collections.sort(contacts);
	}
	
	/**
	 * sorts the addressbook based on their names in ascending order
	 */
	public void sortByName() {
		Collections.sort(contacts, contacts.get(0));
	}
	
	/**
	 * allow a specific attribute of a contact to be modified
	 * @param key the entry number of the contact to modify
	 * @param attribute the attribute one wishes to change
	 * @param newValue the new value to be set
	 */
	public void update(int key, String attribute, String newValue) {
		int index = search(key);
		
		if (index>=0) {
			switch(attribute.trim().toLowerCase()) {
				case "lastname":
					contacts.get(index).changeLastName(newValue);
					break;
				case "alias":
					contacts.get(index).setAlias(newValue);
					break;
				case "address":
					contacts.get(index).setAddress(newValue);
					break;
				case "addemail":
					Arrays.sort(contacts.get(index).getEmailList());
					if (Arrays.binarySearch(contacts.get(index).getEmailList(), newValue)<0);
						contacts.get(index).addEmail(newValue);
					break;
				case "deleteemail":
					contacts.get(index).deleteEmail(newValue);
					break;
				default:
					System.out.println("Action not Supported");
					break;
			}
		}
	}
	
	/**
	 * allow a specific attribute of a contact to be modified
	 * @param key the entry number of the contact to modify
	 * @param attribute the attribute one wishes to change
	 * @param newValue the new value to be set
	 */
	public void update(int key, String attribute, long newValue) {
		int index = search(key);
		
		if (index>=0) {
			switch(attribute.trim().toLowerCase()) {
				case "addnumber":
					Arrays.sort(contacts.get(index).getPhoneList());
					if (Arrays.binarySearch(contacts.get(index).getPhoneList(), newValue)<0);
						contacts.get(index).addPhone('M', newValue);
					break;
				case "deletenumber":
					contacts.get(index).deletePhone(newValue);
					break;
				default:
					System.out.println("Action not Supported");
					break;
			}
		}
	}
	/**
	 * 
	 * allow a specific attribute of a contact to be modified
	 * @param key the entry number of the contact to modify
	 * @param attribute the attribute one wishes to change
	 * @param newValue the new value to be set
	 * @param type the type of number to be added (Only in the case of updating numbers)
	 */
	public void update(int key, String attribute, long newValue, char type) {
		int index = search(key);
		
		if (index>=0) {
			switch(attribute.trim().toLowerCase()) {
				case "addnumber":
					Arrays.sort(contacts.get(index).getPhoneList());
					if (Arrays.binarySearch(contacts.get(index).getPhoneList(), newValue)<0);
						contacts.get(index).addPhone(type, newValue);
					break;
				case "deletenumber":
					contacts.get(index).deletePhone(newValue);
					break;
				default:
					System.out.println("Action not Supported");
					break;
			}
		}
	}
		
	/**
	 * deleted the contact with the entry number passed
	 * @param key the entry number of the contact to be deleted
	 */
	public void delete(int key) {
		ArrayList<Integer> l0 = new ArrayList<Integer>();
		int index;
		
		sort();
		
 		for (int i=0; i<contacts.size(); i++) {
			l0.add(contacts.get(i).getEntryNo());
		}
 		index = Collections.binarySearch(l0, key);
 		if (index>=0) {
 			contacts.remove(index);
 		}
	}
	
	/**
	 * deletes the contact with the email passed as key if found 
	 * @param key the email of the contact
	 */
	public void delete (String key) {
		String l0[];
		int index;
		
		for (int i=0; i<contacts.size();i++) {
			l0 = contacts.get(i).getEmailList();
			Arrays.sort(l0);
			index = Arrays.binarySearch(l0, key);
			if (index>=0) {
				contacts.remove(index);
			}
		}
	}
	/**
	 * String representation of a the book
	 */
	public String toString() {
		String string="";
		for (Contact contact :contacts) {
			string += contact.toDisplay()+"\n\n";
		}
		return string;
	}
	
	/**
	 * 
	 * @param f
	 */
	public void addProfile(Profile f) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Profile getProfile() {
		return new Profile();
	}
}