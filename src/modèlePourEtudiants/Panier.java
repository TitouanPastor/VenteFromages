package modèlePourEtudiants;

import java.util.LinkedList;
import java.util.List;

public class Panier {

	private List<Article> articlesPanier;
	private float prixLivraison;
	private float sousTotal;
	private float totalTTC;
	
	//Constructeur du panier
	public Panier() {
		this.articlesPanier = new LinkedList<Article>();
		this.prixLivraison = 0;
		this.sousTotal = 0;
		this.totalTTC = 0;
	}
	
	public void setPrixLivraison(float prix) {
		this.prixLivraison = prix;
	}
	
	public float getPrixLivraison() {
		return this.prixLivraison;
	}
	

	public float getTotalTTC() {
		return this.calculTotalTTC();
	}
	
	private float calculTotalTTC() { // (setter)
		return this.totalTTC = this.getPrixLivraison() + this.sousTotal;
	}

	public void setPrixSousTotal(float prix) {
		this.sousTotal = prix;
	}
	
	public float getPrixSousTotal() {
		return this.sousTotal;
	}
	
	public Article getArticleDuPanier(int i) {
		return this.articlesPanier.get(i);
	}
	
	//Ajoute l'article au panier en rafraichissant les stocks
	public void addArticleToPanier(Articles mesArticles,Article article, int quantité) {
		boolean trouvé = false;
		for (Article a : this.articlesPanier) {
			if (article.equals(a)) {
				a.rendreQuantité(quantité);
				trouvé = true;
			}
		}
		
		// Si il n'y a pas deja l'article dans le panier
		if (trouvé == false) {
			Article alocal = new Article(article.getFromage(), article.getClé(), article.getPrixTTC());
			alocal.setQuantitéEnStock(quantité);
			this.articlesPanier.add(alocal);
		}
		
		//On rafraichi la quantité de l'article dans les stocks
		int nouvelleQuantité = article.getQuantitéEnStock() - quantité;
		article.setQuantitéEnStock(nouvelleQuantité);
		this.setPrixSousTotal(this.sousTotal + quantité * article.getPrixTTC());
	}
	
	//Réinitialise le panier en rafraichissant les stocks (panier et mesArticles)
	public void réinitialisationPanier(Articles mesarticles) {
		for (Article a : this.articlesPanier) {
			mesarticles.getArticle(a.getFromage().getDésignation(), a.getClé()).rendreQuantité(a.getQuantitéEnStock());
		}
		this.articlesPanier = new LinkedList<Article>();
		this.setPrixSousTotal(0);
		this.setPrixLivraison(0);
	}
	
	
	
	@Override
	public String toString() {
		String str = "";
		float sousTotal = 0;
		for (Article a : this.articlesPanier) {
			sousTotal += a.getPrixTTC()* a.getQuantitéEnStock();
			str += String.format("¤ %20s \n	Quantité : %3s \n	Prix : %.2f € \n\n", a.toString(), a.getQuantitéEnStock(), a.getQuantitéEnStock()*a.getPrixTTC() );
		}
		str += String.format("\nSous total : %.2f€", sousTotal);
		return str;
	}
	
	//Autre toString utilisé dans le récapitulatif (on rajoute le prix de la livraison et le totalTTC)
	public String toStringRecap() {
		String str = "";
		this.sousTotal = 0;
		for (Article a : this.articlesPanier) {
			sousTotal += a.getPrixTTC();
			str += String.format("¤ %20s \n	Quantité : %3s \n	Prix : %.2f € \n\n", a.toString(), a.getQuantitéEnStock(), a.getQuantitéEnStock()*a.getPrixTTC() );
		}
		this.calculTotalTTC();
		str += String.format("\nSous total	: %.2f€ "
						   + "\nPrix livraison	: %.2f€ "
						   + "\nPrix TOTAL	: %.2f€ ", this.sousTotal, this.getPrixLivraison(), getTotalTTC());
		return str;
	}
	
}
