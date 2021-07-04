package contact;

public class LogBook {
	private String username = "admin";
	private String password = "admin"; 
	private int attempts;
	
	public int getAttemptsRemaining() {
		return 3-attempts;
	}
	
	public void changeUsername(String newUsername) {
		this.username = newUsername;
	}
	
	public void changePassword(String newPassword) {
		this.password= newPassword;
	}
	
	public int login(String username, String password) {
		if (this.username.toLowerCase().trim().equals(username.toLowerCase().trim()) && 
				this.password.toLowerCase().trim().equals(password.toLowerCase().trim()) &&
				attempts<=2) {
			attempts =0;
			return 0;
		}
		else if (attempts<=2){
			attempts++;
			return 1;
		}
		else 
			return -1;
	}
}
