package modèlePourEtudiants;

public class Client {

	private String nom;
	private String prénom;
	private String téléphone;
	private String email;
	private String adresse1;
	private String adresse2;
	private String complément;
	private String codePostal;
	private String ville;

	//constructeur du client
	public Client(String nom, String prénom, String téléphone, String email, String adresse1, String adresse2,
			String complément, String codePostal, String ville) {
		this.nom = nom;
		this.prénom = prénom;
		this.téléphone = téléphone;
		this.email = email;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.complément = complément;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	//vérifie si les champs sont bien rempli (exception avec certains champs qui sont "non renseignés" si l'utilisateur ne l'a pas rempli)
	public boolean verificationChamps() {

		if (this.nom.equals("")) {
			return false;
		}
		if (this.prénom.equals(""))
			return false;
		
		if (this.téléphone.equals(""))
			this.téléphone = "Non renseigné";
		if (this.email == " ")
			return false;
		
		if (this.adresse1.equals(""))
			return false;
		
		if (this.adresse2.equals(""))
			this.adresse2 = "Non renseigné";
		
		if (this.complément.equals(""))
			this.complément = "Non renseigné";
		
		if (this.codePostal.equals(""))
			return false;
		
		if (this.ville.equals(""))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "\n    Nom 		: " + nom + "\n    Prénom 		: " + prénom + "\n    Téléphone 		: " + téléphone
				+ "\n    Email 		: " + email + "\n    Adresse 1 		: " + adresse1 + "\n    Adresse 2 		: "
				+ adresse2 + "\n    Complément 	: " + complément + "\n    CodePostal 		: " + codePostal
				+ "\n    Ville		: " + ville;
	}

}
