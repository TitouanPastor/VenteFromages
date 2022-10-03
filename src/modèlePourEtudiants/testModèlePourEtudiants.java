package modèlePourEtudiants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class testModèlePourEtudiants {

	@Test
	public void testFiltreTypeFromage() {
		
		//Création des fromages dans la liste
		Articles mesArticles = GenerationFromages.générationBaseFromages();
		
		//On créer trois listes de chaque type de fromage
		Fromage[] listChevre = mesArticles.fromagesAuLaitDe(TypeLait.CHEVRE);
		Fromage[] listbrebis = mesArticles.fromagesAuLaitDe(TypeLait.BREBIS);
		Fromage[] listVache = mesArticles.fromagesAuLaitDe(TypeLait.VACHE);
		
		//On regarde pour chaque liste si elle ne contiennent que le type de fromage que l'on veut
		for (Fromage f : listChevre) {
			assertEquals(f.getTypeFromage(), TypeLait.CHEVRE);
		}
		for (Fromage f : listbrebis) {
			assertEquals(f.getTypeFromage(), TypeLait.BREBIS);
		}
		for (Fromage f : listVache) {
			assertEquals(f.getTypeFromage(), TypeLait.VACHE);
		}
	}

	@Test
	public void testRecupArticlePourFromage() {

		//On créer un fromage
		Fromage f = new Fromage("Miam");
		
		//On lui ajoute deux articles
		f.addArticle("0", 2);
		f.addArticle("1", 8);
		
		//On verifie que le fromage contient bien les deux articles
		assertEquals(f.getArticles().toString(), "[Miam, 0, Prix TTC : 2.0 €, Miam, 1, Prix TTC : 8.0 €]");

	}

	@Test
	public void testGestionPanier() {
		Articles mesArticles = GenerationFromages.générationBaseFromages();
		
		//On créer la listes des fromages pour récuperer un fromage de la liste
		Fromage f = mesArticles.getLesFromages()[0];

		//On créer un panier vide
		Panier panier = new Panier();
		
		//On récupère un article du fromage
		Article a1 = f.getArticles().get(0);
		
		//On l'ajoute au panier
		panier.addArticleToPanier(mesArticles, a1, 5);
		//On verifie que l'article est bien dans le panier
		assertTrue(panier.getArticleDuPanier(0).equals(a1));

		//On ajoute deux autres article a1
		panier.addArticleToPanier(mesArticles, a1, 2);
		//On verifie que la quantité est bien ajouté
		assertTrue(panier.getArticleDuPanier(0).getQuantitéEnStock() == 7);

		//On réinitialise le panier
		panier.réinitialisationPanier(mesArticles);
		//On vérifie que le prix de livraison et le sous total soient rafraichis, donc qu'il n'y a plus d'articles dans le panier
		assertEquals(panier.getPrixLivraison(), 0, 2);
		assertEquals(panier.getPrixSousTotal(), 0, 2);
	}

	@Test
	public void testCalculsFacture() {

		Articles mesArticles = GenerationFromages.générationBaseFromages();

		//On créer un panier vide
		Panier panier = new Panier();
		
		//On créer un article
		Article a1 = new Article(new Fromage("miam"), "0", 5);
		//On l'ajoute au panier
		panier.addArticleToPanier(mesArticles, a1, 5);
		//On verifie que le prix du sous total soit bien 5*5
		assertTrue(panier.getPrixSousTotal() == 25F);
		
		//On set le prix de livraison a 5 euros
		panier.setPrixLivraison(5F);
		
		//On vérifie bien que les prix sont rafraichis avec les nouvelles valeurs
		assertTrue(panier.getPrixLivraison() == 5F);
		assertTrue(panier.getTotalTTC() == 30F);

	}
	
	@Test
	public void testMajStocks() {
		
		Articles mesArticles = GenerationFromages.générationBaseFromages();
		Panier panier = new Panier();
		
		//On récupère un article d'un fromage (ici fromage au rang 0 et article du fromage au rang 0)
		Article a1 = mesArticles.getLesFromages()[0].getArticles().get(0);
		int quantitéAvant = a1.getQuantitéEnStock();
		
		//On ajoute l'article au panier
		panier.addArticleToPanier(mesArticles, a1, 2);
		//On vérifie que la quantité de l'aricle en stock a diminuer de 2 
		assertEquals(a1.getQuantitéEnStock(), quantitéAvant - 2);
		
	}
}
