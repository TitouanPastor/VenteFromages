package modèlePourEtudiants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Récapitulatif {
	
	private Client client;
	private Panier panier;


	//Constructeur du récapitulatif
	public Récapitulatif(Client client, Panier panier) {
		this.client = client;
		this.panier = panier;
	}


	//Permet d'afficher sur l'interface graphique le récapitulatif complet avec les infos clients, le contenu du panier...
	public String toString() {
		return "Fromagerie Joyeux Fromage fromagerie locale du Tarn\n\n" +
				"Toulouse le " +LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm")) + "\n\n" +
				"~~~~~~~~~~~~~~~~~~INFORMATIONS CLIENT~~~~~~~~~~~~~~~~~~\n\n" +
				this.client + "\n\n" +
				"~~~~~~~~~~~~~~~~~~~~VOTRE COMMANDE~~~~~~~~~~~~~~~~~~~\n\n" +
				this.panier.toStringRecap() + "\n\n" +
				"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n" +
				"Merci pour votre commande, à bientôt chez Joyeux Fromage, la fromagerie du Tarn.\n";
	}
	
}
