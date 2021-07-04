package contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author Javier Bryan 620119423
 * @author Tashae Bowes 620116906
 * @version 1.0
 *
 */

public class GUI extends UserInterface {
	private static AddressBook book;
	
	private static JFrame mainFrame = new JFrame("Address Book");
	
	//MenuBar and Menus
	private static JMenuBar menuBar = new JMenuBar();
	private static JMenu menuFile = new JMenu("File");
	private static JMenuItem menuItemOpen = new JMenuItem("Open");
	private static JMenuItem menuItemNew = new JMenuItem("New");
	private static JMenuItem menuItemSave = new JMenuItem("Save");
	private static JMenuItem menuItemExit = new JMenuItem("Exit");
	private static JMenu menuSearch = new JMenu("Search");
	private static JMenuItem menuItemSearchByEntry = new JMenuItem("Search By Entry Number");
	private static JMenuItem menuItemSearchByEmail = new JMenuItem("Search By Email Address");
	
	//ComboBoxes
	private static DefaultComboBoxModel<String> updateModel = new DefaultComboBoxModel<>();
	private static JComboBox<String> updateComboBox = new JComboBox<String>(updateModel);
	
	// Panels
	private static JPanel listAndUpdateAndSortPanel = new JPanel();
	private static JPanel updateAndSortPanel = new JPanel();
	private static JPanel crudPanel = new JPanel();
	private static JPanel mainPanel = new JPanel();
	private static JPanel leftPanel = new JPanel();
	
	//Labels
	private static JLabel contactLabel = new JLabel("Nothing Selected");
	private static JLabel updateLabel = new JLabel("Update");
	
	//Buttons
	private static JButton sortByEntryNoButton = new JButton("Sort By Entry Number");
	private static JButton sortByNameButton = new JButton("Sort By Name");
	private static JButton btnNew = new JButton("Add");
	private static JButton btnUpdate = new JButton("Update");
	private static JButton btnDelete = new JButton("Delete");
	
	//Text Areas
	JTextArea multipleEmails = new JTextArea("Delete this and enter multiple emails in separate lines");
	
	//Structures for the contact list
	private static DefaultListModel<Contact> contactModel = new DefaultListModel<Contact>();
	private static JList<Contact> contactList = new JList<Contact>(contactModel);
	private static JScrollPane contactScrollPane = new JScrollPane(contactList);
	
	int date=0;
	
	ActionListener sortByEntryNo = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				book.sort();
				contactModel.clear();
				addList();
		}
	};
	
	ActionListener sortByName = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				book.sortByName();
				contactModel.clear();
				addList();
		}
	};
	
	ActionListener create = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String firstName = JOptionPane.showInputDialog(null, "Enter contact first name", "First Name", JOptionPane.INFORMATION_MESSAGE);
			
			if(firstName == null) {
				return;
			}
			
			String lastName = JOptionPane.showInputDialog(null, "Enter contact last name", "Last Name", JOptionPane.INFORMATION_MESSAGE);
			
			if(lastName == null) {
				return;
			}
			
			boolean invalidDate = true;
			
			while (invalidDate) {
				try {
					String birthDate = JOptionPane.showInputDialog(null, "Date of Birth(yyyymmdd): ", 
							"Date of Birth", JOptionPane.INFORMATION_MESSAGE);
					if(birthDate == null) {
						return;
					}
				
					date = Integer.parseInt(birthDate);
					invalidDate=false;
				}
				catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid Date", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			String alias = JOptionPane.showInputDialog(null, "Enter alias ", "Alias", JOptionPane.INFORMATION_MESSAGE);
			
			if(alias == null) {
				return;
			}
			String address = JOptionPane.showInputDialog(null, "Enter address", "Address", JOptionPane.INFORMATION_MESSAGE);
			

			if(address == null) {
				return;
			}

			JFrame genderFrame = new JFrame("Gender");
			ButtonGroup group = new ButtonGroup();
			JRadioButton btnMale = new JRadioButton("Male",true);
			JRadioButton btnFemale = new JRadioButton("Female",false);
			JButton btnGenderSelect = new JButton("Ok");
			
			group.add(btnMale);
			group.add(btnFemale);
			
			genderFrame.setBounds(mainFrame.getX()+(mainFrame.getWidth()/2),mainFrame.getY()+(mainFrame.getHeight()/2), 0, 0);
			
			Box genderBox = Box.createVerticalBox();
			
			btnMale.setAlignmentX(Box.CENTER_ALIGNMENT);
			genderBox.add(btnMale);
			genderBox.add(Box.createRigidArea(new Dimension(0,10)));
			
			btnFemale.setAlignmentX(Box.CENTER_ALIGNMENT);
			genderBox.add(btnFemale);
			genderBox.add(Box.createRigidArea(new Dimension(0,10)));
			
			btnGenderSelect.setAlignmentX(Box.CENTER_ALIGNMENT);
			genderBox.add(btnGenderSelect);
			
			genderFrame.add(genderBox);
			
			genderFrame.pack();
			genderFrame.setVisible(true);
			genderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			btnGenderSelect.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (btnMale.isSelected()) {
						book.add(firstName, lastName,Gender.MALE,alias, address,date);
						contactModel.clear();
						addList();
						genderFrame.dispose();
						JOptionPane.showMessageDialog(null, "Contact Added Successfully", "Added", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						book.add(firstName, lastName,Gender.FEMALE,alias, address,date);
						contactModel.clear();
						addList();
						genderFrame.dispose();
						JOptionPane.showMessageDialog(null, "Contact Added Successfully", "Added", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}
	};
	
	ActionListener newAddressBook = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("New");
				
				if (fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					DataManager.setFilename(file.getName());
					DataManager.setFile(file);
					file.createNewFile();
					contactModel.clear();
					book = DataManager.open(file);
					addList();
				}
			}catch(Exception exception)	{
				exception.getMessage();
			}
		}
	};
	
	ActionListener update = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				if(updateComboBox.getSelectedIndex()==-1 || contactList.getSelectedIndex()==-1){
					JOptionPane.showMessageDialog(null, "Nothing Selected", "Select Contact", JOptionPane.ERROR_MESSAGE);
				}
				else if(updateComboBox.getSelectedIndex()==0) {
					String newValue = JOptionPane.showInputDialog(null,"Enter new last name",JOptionPane.INFORMATION_MESSAGE);
					book.update(contactList.getSelectedValue().getEntryNo(),"lastname",newValue.trim());
					contactModel.clear();
					addList();
				}
				else if(updateComboBox.getSelectedIndex()==1) {
					String newValue = JOptionPane.showInputDialog(null,"Enter new alias",JOptionPane.INFORMATION_MESSAGE);
					book.update(contactList.getSelectedValue().getEntryNo(),"alias",newValue.trim());
					contactModel.clear();
					addList();
				}
				else if(updateComboBox.getSelectedIndex()==2) {
					String newValue = JOptionPane.showInputDialog(null,"Enter new address",JOptionPane.INFORMATION_MESSAGE);
					book.update(contactList.getSelectedValue().getEntryNo(),"address",newValue.trim());
					contactModel.clear();
					addList();
				}
				else if(updateComboBox.getSelectedIndex()==3) {
					String newValue = JOptionPane.showInputDialog(null,"Enter email address",JOptionPane.INFORMATION_MESSAGE);
					if(newValue == null) {
						return;
					}
					book.update(contactList.getSelectedValue().getEntryNo(),"addemail",newValue.trim());
					contactModel.clear();
					addList();
				}
				else if(updateComboBox.getSelectedIndex()==4) {
					JFrame mailFrame = new JFrame("Add Emails");
					JButton btnAddEmails = new JButton("Add");
							
					
					mailFrame.setBounds(300,300,300, 300);
					
					Box mailBox = Box.createVerticalBox();
					
					
					multipleEmails.setBounds(200,200,150,150);
					multipleEmails.setAlignmentX(Box.CENTER_ALIGNMENT);
					mailBox.add(multipleEmails);
					mailBox.add(Box.createRigidArea(new Dimension(0,10)));
					btnAddEmails.setAlignmentX(Box.CENTER_ALIGNMENT);
					mailBox.add(btnAddEmails);
					
					mailFrame.add(mailBox);

					mailFrame.setVisible(true);
					mailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					ActionListener addMails = new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String mail = 	multipleEmails.getText();
							String[] mails = mail.split("\n");
							
							for (String m : mails) {
								if(m == null) {
									return;
								}
								book.update(contactList.getSelectedValue().getEntryNo(),"addemail",m.trim());
							}
							contactModel.clear();
							addList();
						}
					};
					
					btnAddEmails.addActionListener(addMails);

				}
				else if(updateComboBox.getSelectedIndex()==5) {
					String newValue = JOptionPane.showInputDialog(null,"Enter email to delete",JOptionPane.INFORMATION_MESSAGE);
					book.update(contactList.getSelectedValue().getEntryNo(),"deleteemail",newValue.trim().toLowerCase());
					contactModel.clear();
					addList();
				}
				else if(updateComboBox.getSelectedIndex()==6) {
					String newValue = JOptionPane.showInputDialog(null,"Enter phone number to add",JOptionPane.INFORMATION_MESSAGE);
					book.update(contactList.getSelectedValue().getEntryNo(),"addnumber",Long.parseLong(newValue.trim()));
					contactModel.clear();
					addList();
				}
				else {
					String newValue = JOptionPane.showInputDialog(null,"Enter phone number to delete",JOptionPane.INFORMATION_MESSAGE);
					book.update(contactList.getSelectedValue().getEntryNo(),"deletenumber",Long.parseLong(newValue.trim()));
					contactModel.clear();
					addList();
				}
		
			}
			catch(Exception exception) {
				
			}
		}
	};
	
	ActionListener search = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==menuItemSearchByEntry) {
				int entryNo =Integer.parseInt(JOptionPane.showInputDialog(null, "Enter entry number", "Search",
						JOptionPane.INFORMATION_MESSAGE));
				JOptionPane.showMessageDialog(null, book.displayContact(entryNo), "Search", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				String email = JOptionPane.showInputDialog(null, "Enter email address", "Search",JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, book.displayContact(email.trim().toLowerCase()), "Search", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	};
	
	ActionListener delete = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Contact c : contactList.getSelectedValuesList()) {
				book.delete(c.getEntryNo());
			}
			contactModel.clear();
			addList();
		}
	};
	
	ActionListener open = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open");
			fileChooser.showOpenDialog(null);
			try {
				if (fileChooser.getSelectedFile().canRead()) {
					contactModel.clear();
					book = DataManager.open(fileChooser.getSelectedFile());
					addList();
				}
			}
			catch(Exception exception) {
			}
			
		}
	};
	
	ActionListener save = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(DataManager.getFilename() ==null) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Save as");
				
				if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					DataManager.setFile(file);
					int status = DataManager.save(book);
					if (status==0) {
						JOptionPane.showMessageDialog(null,"Saved","Saved",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null,"Error: File Not Saved","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				return;
			}
			
			int status = DataManager.save(book);
			if (status==0) {
				JOptionPane.showMessageDialog(null,"Saved","Saved",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,"Error: File Not Saved","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	
	ActionListener quit = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int state =JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Confirm Exit",JOptionPane.YES_NO_CANCEL_OPTION);
			
			if (state==JOptionPane.YES_OPTION) {
				mainFrame.dispose();
				System.exit(0);
			}
		}
		
	};
		
	ListSelectionListener itemSelected = new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			try {
				String newValue = contactList.getSelectedValue().toDisplay();
				newValue = "<html>"+ newValue.replaceAll("\n", "<br>");
				contactLabel.setText(newValue);
			}
			catch(NullPointerException exception){
				contactLabel.setText("Nothing Selected");
			}
		}
	};
	
	public static void addList() {
		for (int i=0; i<book.getContacts().size();i++) {
			contactModel.addElement(book.getContacts().get(i));
		}
	}
	
	/**
	 * Sets up the application frame
	 */
	public GUI() {
		mainFrame.setTitle("Address Book");
		mainFrame.setBounds(400,140,400, 400); 
		
		menuItemSave.addActionListener(save);
		menuItemExit.addActionListener(quit);
		menuItemSearchByEntry.addActionListener(search);
		menuItemSearchByEmail.addActionListener(search);
		menuItemOpen.addActionListener(open);
		menuItemNew.addActionListener(newAddressBook);
			
		mainFrame.setJMenuBar(menuBar);
		menuBar.add(menuFile);
		menuFile.add(menuItemNew);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemSave);		
		menuFile.add(menuItemExit);	
		menuBar.add(menuSearch);
		menuSearch.add(menuItemSearchByEntry);
		menuSearch.add(menuItemSearchByEmail);
		
		listAndUpdateAndSortPanel.setLayout(new GridLayout(1,2));
	
		addList();
		
		contactLabel.setOpaque(true);
		contactLabel.setBackground(Color.GRAY);
		contactLabel.setForeground(Color.CYAN);
		contactLabel.setFont(new Font("Arial", Font.ITALIC, 12));
		
		sortByEntryNoButton.addActionListener(sortByEntryNo);
		sortByNameButton.addActionListener(sortByName);
		
		updateAndSortPanel.setLayout(new BoxLayout(updateAndSortPanel,BoxLayout.Y_AXIS));
		
		updateLabel.setForeground(Color.cyan);
		updateLabel.setAlignmentX(Box.CENTER_ALIGNMENT);
		updateComboBox.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		updateModel.addElement("Change Last Name");
		updateModel.addElement("Change Alias");
		updateModel.addElement("Change Address");
		updateModel.addElement("Add Email Address");
		updateModel.addElement("Add Multiple Email Address");
		updateModel.addElement("Delete Email Address");
		updateModel.addElement("Add Phone Number");
		updateModel.addElement("Delete Phone Number");
		
		updateAndSortPanel.add(updateLabel);
		updateAndSortPanel.add(Box.createRigidArea(new Dimension(0, 1)));
		updateAndSortPanel.add(updateComboBox);
		
		updateAndSortPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		sortByEntryNoButton.setAlignmentX(Box.CENTER_ALIGNMENT);
		sortByNameButton.setAlignmentX(Box.CENTER_ALIGNMENT);
		updateAndSortPanel.add(sortByEntryNoButton);
		updateAndSortPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		updateAndSortPanel.add(sortByNameButton);
		updateAndSortPanel.setBackground(Color.gray);
		
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(contactLabel, BorderLayout.NORTH);
		leftPanel.add(updateAndSortPanel,BorderLayout.SOUTH);
		leftPanel.setBackground(Color.gray);
		
		contactList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		contactList.getSelectionModel().addListSelectionListener(itemSelected);
		listAndUpdateAndSortPanel.add(contactScrollPane);
		listAndUpdateAndSortPanel.add(leftPanel);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		crudPanel.setLayout(new BoxLayout(crudPanel, BoxLayout.X_AXIS));
		
		btnNew.addActionListener(create);
		
		crudPanel.add(btnNew);
		crudPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		btnUpdate.addActionListener(update);
		crudPanel.add(btnUpdate);
		crudPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		btnDelete.addActionListener(delete);
		crudPanel.add(btnDelete);
		crudPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		
		crudPanel.setBackground(Color.LIGHT_GRAY);
		
		mainPanel.add(listAndUpdateAndSortPanel);
		mainPanel.add(crudPanel);
		mainFrame.add(mainPanel);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	
	/**
	 * Used to run the application
	 * @param args command line arguments list
	 */
	public static void main(String args[]) {
		book = DataManager.open();
		new LoginGUI();

	}
}
