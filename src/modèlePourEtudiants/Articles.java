package modèlePourEtudiants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class Articles {

	private List<Fromage> lesFromages;

	public Articles() {
		this.lesFromages = new LinkedList<Fromage>();
	}

	public void addFromages(List<Fromage> fromages) {
		this.lesFromages.addAll(fromages);
	}

	public String toStringFromagesEtArticles() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.lesFromages) {
			enForme.append(f.toString() + '\n');
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toString() + '\n');
				}
			}
		}
		return enForme.toString();
	}

	public String toStringArticlesEtStock() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.lesFromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					enForme.append(article.toStringAvecStock() + '\n');
				}
			}
		}
		return enForme.toString();
	}

	public void regénérationDuStock() {
		for (Fromage f : this.lesFromages) {
			if (f.nombreArticles() > 0) {
				for (Article article : f.getArticles()) {
					article.setQuantitéEnStock((int) Math.round(Math.random() * 100));
				}
			}
		}
	}

	public String vérificationSaisie() {
		StringBuffer enForme = new StringBuffer();
		for (Fromage f : this.lesFromages) {
			if (f.nombreArticles() == 0) {
				enForme.append("Pas d'articles pour " + f.toString() + '\n');
			}
		}
		return enForme.toString();
	}

	public Fromage[] fromagesAuLaitDe(TypeLait lait) {
		List<Fromage> listFromage = new ArrayList<Fromage>();
		for (Fromage f : this.lesFromages) {
			if (f.getTypeFromage() == lait) {
				listFromage.add(f);
			}
		}
		return listFromage.stream().filter(Objects::nonNull).toArray(Fromage[]::new);
	}

	public Fromage[] getLesFromages() {
		Fromage[] fromages = new Fromage[this.lesFromages.size()];
		return this.lesFromages.toArray(fromages);
	}

	public Article getArticle(String désignation, String clé) {
		for (Fromage f : this.lesFromages) {
			if (f.getDésignation() == désignation) {
				for (Article a : f.getArticles()) {
					if (a.getClé() == clé) {
						return a;
					}
				}
			}
		}
		return null;
	}

	public void setQuantitéArticle(String désignation, String clé, int quantité) {
		for (Fromage f : this.lesFromages) {
			if (f.getDésignation() == désignation) {
				for (Article a : f.getArticles()) {
					if (a.getClé() == clé) {
						a.préempterQuantité(quantité);
					}
				}
			}
		}
	}

	
	//renvoie la liste des fromages avec seulement leur désignation (sans le toString de la classe fille du fromage)
	public static Object[] getListFromageAvecDesignation(Fromage[] fromages) {

		List<String> liste = new LinkedList<String>();
		for (Fromage f : fromages) {
			liste.add(f.getDésignation());
		}

		// trier la liste
		List<String> sortedList = liste.stream().sorted().collect(Collectors.toList());
		return sortedList.toArray();

	}

	//Renvoie le fromage avec la désignation demandé
	public Fromage getFromageAvecDesignation(String designation) {

		for (Fromage f : this.lesFromages) {
			if (f.getDésignation() == designation) {
				return f;
			}
		}
		return null;
	}

}
