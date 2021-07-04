package contact;

import java.io.Serializable;

/**
 * 
 * @author Javier Bryan 620119423
 * @version 1.0
 *
 */
public class Phone implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long phoneNumber;
	private char phoneType;
	
	/**
	 * Constructs a phone number with the number provided
	 * and of the type provided 
	 * @param number the phone number 
	 * @param type the type of phone number 
	 * 'H' for Home
	 * 'W' for Work
	 * 'M' for Mobile 
	 */
	public Phone(long number, char type) {
		phoneNumber=number;
		phoneType=type;
	}
	
	/**
	 * Returns the area code of the phone number
	 * @return the first three digits of the 10 digit 
	 * phone number 
	 */
	public int getAreaCode() {
		return (int)(phoneNumber/10000000);
	}
	
	/**
	 * Returns the type of phone number 
	 * @return 
	 * 'H' for Home
	 * 'W' for Work
	 * 'M' for Mobile 
	 */
	public char getType() {
		return phoneType;
	}
	
	/**
	 * Returns the phone number
	 * @return a 10 digit phone number as a long 
	 */
	public long getNumber() {
		return phoneNumber;
	}
	
	/**
	 * Returns a string formatted in the similar 
	 * to the example “(876) 555-1234”.
	 */
	public String toString() {
		return "(" + getAreaCode() + ")" + " "+ (Long.toString(phoneNumber).substring(3,6)) + "-" +
				(Long.toString(phoneNumber).substring(6));
	}
}
