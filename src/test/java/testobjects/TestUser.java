package testobjects;

public class TestUser {
	
	private String email = "tanglewoodnumbers2008@getnada.com";
	private String pword = "StarliteWalker#94";
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
