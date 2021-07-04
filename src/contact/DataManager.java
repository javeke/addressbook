package contact;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataManager {
	private static String filename =null;
	private static File file;
	
	/**
	 * gets the File currently in used
	 * @return the file
	 */
	public static File getFile() {
		return file;
	}
	
	/** 
	 * sets the data manager to used the file that is passed
	 * @param file the file to be used
	 */
	public static void setFile(File file) {
		DataManager.file =file;
	}
	
	/**
	 * name of the file currently in use
	 * @return the name of the file
	 */
	public static String getFilename() {
		return filename;
	}
	
	/**
	 * sets the name of the file to be used
	 * @param name the name of the file that will be used
	 */
	public static void setFilename(String name) {
		filename =name;
	}
	
	/**
	 * retrieves data from the current file location for the address book
	 * this file location is null at start
	 * @return the address book from the file
	 */
	public static AddressBook open() {
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
			AddressBook book= (AddressBook)stream.readObject();
			stream.close();
			return book;
		}
		catch(ClassNotFoundException | NullPointerException| IOException e) {
			return new AddressBook();
		}		
	}
	
	/**
	 * retrieves address book data from the file with the parameter filename 
	 * @param filename name of file
	 * @return the address book data
	 */
	public static AddressBook open(String filename) {
		try {
			DataManager.filename=filename;
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename));
			AddressBook book= (AddressBook)stream.readObject();
			stream.close();
			return book;
		}
		catch(ClassNotFoundException | IOException e) {
			return new AddressBook();
		}		
	}
	
	/**
	 * retrieves address book data from the file passed as parameter 
	 * @param file the File object
	 * @return the address book data
	 */
	public static AddressBook open(File file) {
		try {
			DataManager.file=file;
			DataManager.filename=file.getName();
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
			AddressBook book= (AddressBook)stream.readObject();
			stream.close();
			return book;
		}
		catch(ClassNotFoundException | IOException e) {
			return new AddressBook();
		}		
	}
	
	/**
	 * saves the address book to the file currently being used
	 * @param book the address book to be saved
	 * @return the state of the operation 
	 * 0 for successful
	 * 1 for remaining attempts
	 * -1 for locked out
	 */
	public static int save(AddressBook book) {
		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
			stream.writeObject(book);
			stream.close();
			return 0;
		} 
		catch (IOException e) {
			return -1;
		}
	}
}
