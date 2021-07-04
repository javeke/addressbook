package contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
 
/**
 * 
 * @author Javier Bryan 620119423
 * @author Tashae Bowes 620116906
 * @version 1.0
 *
 */
public class Contact extends Person implements Comparable<Contact>, Comparator<Contact>, Serializable{

	private static final long serialVersionUID = 1L;
	private static int counter=0;
	private int entryNumber;
	private String contactAlias;
	private Address contactAddress;
	private ArrayList<String> contactEmailAddresses = new ArrayList<String>();
	private ArrayList<Phone> contactPhoneNumbers = new ArrayList<Phone>();
	/**
	 * Constructs a contact with the parameters provided
	 * @param firstName the first name of the contact
	 * @param lastName the last name of the contact 
	 * @param gender the gender of the contact as a String
	 * "Male" or "Female"
	 * @param dob the date of birth of the contact in the 
	 * format yyyymmdd
	 */
	
	public Contact(String firstName, String lastName, Gender gender, long dob) {
		super(firstName, lastName, gender, dob);
		entryNumber=++counter;		
	}
	
	/**
	 * Returns the entry number for the contact
	 * @return the entry number as an int 
	 */
	public int getEntryNo(){
		return entryNumber;
	}
	
	/**
	 * Sets the number of contacts that exists
	 * @param count number of contact already created
	 */
	public static void setCounter(int count) {
		counter = count;
	}
	
	/**
	 * Returns the age of the contact
	 * @return the age of the contact
	 */
	public int getAge() {
		Calendar cal = Calendar.getInstance();
		int age;
		int dob= (int)getDOB();
		int birthDay=Integer.parseInt((Integer.valueOf(dob).toString().substring(6,8)));
		int currentDay=cal.get(Calendar.DAY_OF_MONTH);
		int birthMonth=Integer.parseInt((Integer.valueOf(dob).toString().substring(4,6)));
		int currentMonth=cal.get(Calendar.MONTH)+1;
		
		if(birthMonth>currentMonth || ((birthMonth==currentMonth) && (birthDay > currentDay)))
			age = cal.get(Calendar.YEAR)-Integer.parseInt((Integer.valueOf(dob).toString().substring(0,4)))-1;
		else 
			age = cal.get(Calendar.YEAR)-Integer.parseInt((Integer.valueOf(dob).toString().substring(0,4)));
		
		return age;
	}
	
	/**
	 * Returns the contacts name in the format last name,
	 * first name. For example, "Brown, John"
	 */
	public String getName() {
		return personName.getLastName()+", "+personName.getFirstName();
	}
	
	/**
	 * Update last name of contact
	 * @param name the new last name of the contact
	 */
	public void updateName(String name) {
		personName.changeLastName(name);
	}
	
	/**
	 * Returns the alias of the contact
	 * @return the alias of the contact as a String
	 */
	public String getAlias() {
		return contactAlias;
	}
	
	/**
	 * Sets the alias of the contact
	 * to the String that is passed 
	 * @param alias a String that will become the alias 
	 * of the contact
	 */
	public void setAlias(String alias) {
		//if (!(aliasList.contains(alias))) {
			//aliasList.add(alias);
		contactAlias = alias;
		//}
	}
	
	/**
	 * Returns an array containing the lines in the 
	 * address not including blank lines. 
	 * @return a String array of the lines in the address
	 */
	public String[] getAddress() {
		return contactAddress.getAddress();
	}
	
	/**
	 * Takes a given String and create and sets a the 
	 * address of the contact
	 * @param address a String with the parts of the 
	 * address separated by semicolons
	 */
	public void setAddress(String address) {
		contactAddress = new Address(address);
	}
	
	/**
	 * Adds an email address to a set of email address 
	 * for the contact. Each contact can have an infinite 
	 * number of email addresses
	 * @param email a String of the email address to be 
	 * added
	 */
	public void addEmail(String email) {
		contactEmailAddresses.add(email.toLowerCase());
	}
	
	/**
	 * Deletes the email address given as a parameter 
	 * from the set of email addresses for the contact, if 
	 * found and if not found then the list remains 
	 * unchanged
	 * @param email the email address to be removed
	 */
	public void deleteEmail(String email) {
		contactEmailAddresses.remove(email.toLowerCase());
	}
	
	/**
	 * Returns an array of the email addresses
	 * for the contact
	 * @return an array of the email addresses 
	 */
	public String[] getEmailList(){
		String[] array=new String[contactEmailAddresses.size()];
		for (int i=0;i<contactEmailAddresses.size();i++) {
			array[i]=contactEmailAddresses.get(i);
		}
		return array;
	}
	
	/**
	 * Adds a phone number to the list of phone numbers 
	 * that the contact has.  A contact can have up to 
	 * 5 phone numbers.
	 * @param type the type of phone number 
	 * 'H' for Home
	 * 'W' for Work
	 * 'M' for Mobile 
	 * @param number the phone number where the first 
	 * three digits are the area code. For example,
	 *  8767024455.
	 */
	public void addPhone(char type, long number) {
		if (contactPhoneNumbers.size()<5){
			contactPhoneNumbers.add(new Phone(number, type));
		}
	}
	
	/**
	 * Deletes the phone number given as a parameter 
	 * from the list of phone numbers, if found and if not 
	 * the list remains unchanged
	 * @param number the number to be removed 
	 */
	public void deletePhone(long number) {	
		for (int i=0;i<contactPhoneNumbers.size();i++) {
			if (contactPhoneNumbers.get(i).getNumber()==number) {
				contactPhoneNumbers.remove(i);
				break;
			}
		}
	}
	
	/**
	 * Return an array of strings representing the 
	 * contact’s phone numbers. Each phone number is 
	 * formatted as specified by the toString method
	 * of the Phone class
	 * @return an array of the phone number
	 */
	public String[] getPhoneList(){
		ArrayList<String> list = new ArrayList<String>();
		
		for (Phone phone:contactPhoneNumbers) {
			list.add(phone.toString());
		}
		String[] array = new String[list.size()];
		for (int i=0; i<list.size();i++) {
			array[i]=list.get(i);
		}
		return array;
	}

	@Override
	public int compareTo(Contact o) {
		if (this.getEntryNo()>o.getEntryNo())
			return 1;
		else if (this.getEntryNo()==o.getEntryNo())
			return 0;
		else 
			return -1;
	}
	
	public String toString() {
		return getName()+"\n";
	}
	
	public String toDisplay() {
		String mails=" ";
		String numbers =" ";
		String gender = getGender().toString().toLowerCase();
		gender = gender.substring(0,1).toUpperCase() + gender.substring(1);

		for(int i=0;i<contactEmailAddresses.size();i++) {
			mails+="\n "+contactEmailAddresses.get(i);
		}
		for(int i=0;i<contactPhoneNumbers.size();i++) {
			numbers+="\n "+contactPhoneNumbers.get(i);
		}
		return "Entry Number: "+ getEntryNo()+ "\nName: "+getName()+"\nGender: "+gender+ 
				"\nEmail Addresses: "+mails + "\nContact Number: "+numbers+"\n";
	}

	@Override
	public int compare(Contact o1, Contact o2) {
		if ((o1.getName().compareTo(o2.getName()))>0)
			return 1;
		else if ((o1.getName().compareTo(o2.getName()))==0)
			return 0;
		else 
			return -1;
	}
}

