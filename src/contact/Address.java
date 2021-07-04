package contact;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 
 * @author Javier Bryan 620119423
 * @version 1.0
 *
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	private String country;
	private ArrayList<String> addr= new ArrayList<String>();
	
	/**
	 * Constructs an address with the String parameter, 
	 * address, that is provided 
	 * @param address a String containing the parts of the 
	 * address to be constructed 
	 */
	public Address(String address) {
		if(address.length()>0) {
			String[] array= address.split(";");
			for (int i=0;i<array.length;i++) {
				if (array[i].trim().length()>0){
					addr.add(array[i].trim());
				}
			}
			country = addr.get(addr.size()-1);	
		}
	}
	
	/**
	 * Returns the name of the country
	 * @return returns the name of the country as a String 
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Returns an array of the lines in an address
	 * not including blank lines.
	 * @return array of the lines in an address
	 */
	public String[] getAddress() {
		String[] array= new String[addr.size()];
		
		for (int i=0; i<addr.size();i++) {
			array[i]=addr.get(i);
		}
		return array;
	}
	
	/**
	 * Returns a string with the address lines separated by newlines
	 */
	public String toString() {
		String string= new String();
		
		for (int i=0; i<addr.size();i++) {
			string+=(addr.get(i)+"\n");
		}	
		return string;
	}
}
