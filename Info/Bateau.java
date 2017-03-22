package Info;

public class Bateau {
	
	String immat;
	String nom;
	String modele;
	String type;
	String statio;
	String etat;
	
	public Bateau()
	{
		this.immat = "";
		this.nom = "";
		this.modele = "";
		this.type = "";
		this.statio = "";
		this.etat = "";
	}

	public String getImmat() {
		return immat;
	}

	public void setImmat(String immat) {
		this.immat = immat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatio() {
		return statio;
	}

	public void setStatio(String statio) {
		this.statio = statio;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

}
