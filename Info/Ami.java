package Info;

public class Ami {
	private String adresse;
	private String telephone;
	
	public Ami(String adr, String tel)
	{
		this.adresse = adr;
		this.telephone = tel;
	}
	
	public Ami()
	{
		this.adresse = "";
		this.telephone = "";
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
