package Info;

import java.util.Observable;

public class Client extends Observable {
	
	public Abonne abonne;
	private String accountName = "";
    private String password = "";
    private String boatName = "Foobar";
	//public Bateau bateau;
	
	public Client()
	{
		this.abonne = new Abonne();
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getBoatName() {
		return boatName;
	}
	
	public void setBoatName(String boatName) {
		this.boatName = boatName;
		this.setChanged();
		this.notifyObservers();
		
	}

}
