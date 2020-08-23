package testobjects;

public class TestUser {
	
	private String email = "wendellborton@getnada.com";
	private String pword = "BJSSTest";
	private boolean loggedIn = false;
	
	
	public boolean getLogin() {
		return loggedIn;
	}
	
	public void setLogin(boolean newLoggedIn) {
		loggedIn = newLoggedIn;
	}
	
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return pword;
	}

	public String getPword() {
		return pword;
	}

}
