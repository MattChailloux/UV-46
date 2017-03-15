package Info;

public class Abonne {
	private String patronyme;
	private String adresse;
	private String[] email;
	private String telephone;
	private String date;
	private Ami[] amis;
	
	public Abonne()
	{
		patronyme = new String("");
		adresse = new String("");
		email = new String[0];
		amis = new Ami[0];
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getEmail() {
		String rep = "";
		for(int i = 0;i<email.length;i++)
			rep += email[i]+ "  ";
			
		return rep;
	}

	public void setEmail(String[] email) {
		this.email = email;
	}

	public String getPatronyme() {
		return patronyme;
	}

	public void setPatronyme(String patronyme) {
		this.patronyme = patronyme;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getAmis()
	{
		String rep = "";
		for(int i = 0;i<amis.length;i++)
			rep += "["+amis[i].getTelephone()+","+amis[i].getAdresse()+"]";
		return rep;
	}
	
	public void setAmis(String[] friends)
	{
		String[] parts= new String[0];
		this.amis = new Ami[friends.length];
		
		for(int i = 0;i<friends.length;i++)
		{
			parts = friends[i].split(",");
			this.amis[i] = new Ami(parts[0], parts[1]);
		}
	}

}
