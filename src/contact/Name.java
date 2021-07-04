package contact;

import java.io.Serializable;

/**
 * 
 * @author Javier Bryan 620119423
 * @version 1.0
 *
 */
public class Name implements Serializable{

	private static final long serialVersionUID = 1L;
	private String firstName, lastName;
	
	/**
	 * Constructs a name with the Strings first and last 
	 * for the first name and last name respectively
	 * @param first the String to used as the first name
	 * @param last the String to be used as the last name
	 */
	public Name(String first, String last) {
		this.firstName=first;
		this.lastName=last;
	}
	
	/**
	 * Returns the first name
	 * @return  the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Returns the first name
	 * @return the first name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Change the last name to the String parameter 
	 * provided as newName 
	 * @param newName the new String to be set as the 
	 * last name
	 */
	public void changeLastName(String newName) {
		lastName=newName;
	}
}
