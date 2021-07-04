package contact;

import java.io.Serializable;

/**
 * 
 * @author Javier Bryan 620119423
 * @version 1.0
 *
 */
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	protected Name personName;
	private Gender personGender;
	private long personDOB;
	
	/**
	 * Constructs a person using the first name, last name
	 * gender and the date of birth provided as parameters 
	 * @param firstName the first name of the person
	 * @param lastName the last name of the person
	 * @param gender the gender of the person 
	 * @param dob the date of birth of the person in the 
	 * format yyyymmdd
	 */
	public Person(String firstName,String lastName, Gender gender, long dob) {
		personName = new Name(firstName, lastName);
		personDOB=dob;
		personGender= gender;
	}
	
	/**
	 * Returns the name of the person
	 * @return the name of the person in the format, 
	 * first name then last name.
	 * For example, "John Brown" 
	 */
	public String getName() {
		return personName.getFirstName()+" "+personName.getLastName();
	}
	
	/**
	 * Change the last name to the String parameter 
	 * provided as newName 
	 * @param newName the new String to be set as the 
	 * last name
	 */
	public void changeLastName(String newName) {
		personName.changeLastName(newName);
	}
	
	/**
	 * Returns the gender of the person 
	 * @return a String representation of the gender. 
	 * For example, "Male"
	 */
	public Gender getGender() {
		return personGender;
	} 
	
	/**
	 * Returns the date of birth of the person
	 * @return a long value of the date of birth in the
	 * format yyyymmdd
	 */
	public long getDOB() {
		return personDOB;
	}
	
	public String toString() {
		return this.getName();
	}
}
