package assignment;

import java.util.Random;

public class PageRankTask3 {

	/* Utilisez cet objet pour générer des nombres aléatoires*/
	public static Random random = new Random(2013);  
	
	/* Vraies valeur de pagerank pour le réseau "net" */
	public static final double[] realPageranks = new double[]{
		0.03800000000000003, 0.17960674190896989, 
		0.2873707867385369, 0.02000000000000001, 
		0.47502247135249404};
	
	/* Please modify this network with your solution of task 2.c */
	public static int [][] netpage0 = {
		{ 1, 2 },    //page 0
		{ 2, 2, 4, 3, 3, 3, 3, 3, 3, 3},  //page 1
		{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},       //page 2
		{ 0, 0},     //page 3
		{ 1, 2 , 4, 3, 3, 3, 3, 3, 3}  //page 4
	};

	public static void main(String[] argv) {   
		/*Réseau de pages exemple*/
		int [][] net = {
				{ 1, 2 },    //page 0
				{ 2, 2, 4},  //page 1
				{ 4 },       //page 2
				{ 0, 0},     //page 3
				{ 1, 2 , 4}  //page 4
			};

		int[] path = randomSurfer(net, 20);
		double[] ranks = computePageRank(path, net.length);
		
		for (int p = 0; p < ranks.length; p++) {
			System.out.print("La page " + p + " a été visitée " + countVisit(path, p) + " fois. ");
			System.out.println("Son PageRank estimé est de " + ranks[p]);
		}
		System.out.println("Il faut " + getConvSteps(net) + " itérations pour avoir un résultat précis");
	}

	public static int[] randomSurfer(int[][] net, int steps) {  //le même que la tâche 1 donc voir commentaire tâche 1.
		/* Copiez/collez votre solution à la tâche 1 */
		int tab [] = new int [steps];
		tab [0] = 0;
		for ( int i = 1; i < steps; i++) {
			tab [i] = getNextPage(net, tab[i-1]);	
		}
		return tab;
	}
	
	public static int getNextPage(int[][] net, int currentPage) { //le même que la tâche 1 donc voir commentaire tâche 1
		/* Copiez/collez votre solution à la tâche 1 */
		int damp = random.nextInt(9);
		int resultat = 0;
		while (net[currentPage].length == 0) {
			currentPage = random.nextInt(net.length);
		}
		if (damp == 0) {
			int aleatoire = random.nextInt(net.length);
			resultat = aleatoire;
		} else {
		int tab [] = net [currentPage];
		if (tab.length == 0) {
			int aleatoire = random.nextInt(net.length);
			resultat = tab[aleatoire];
		} else {
		int aleatoire = random.nextInt(tab.length);
		resultat = tab[aleatoire];
		}
		}
		return resultat;
	}
	
	public static double[] computePageRank(int[] path, int pageCount) {//calcule le PageRank estimé pour chaque pages.
		/* Méthode à coder */
		double tab [] = new double [pageCount];//tableau avec le nombre de pages qui sera retourné.  
		for (int i = 0; i < tab.length; i++) {//rempli le tableau avec chaque PageRank estimé.
			double pageRank = 0.0;
			int nbVisite = countVisit(path, i);
			pageRank = (double)nbVisite / path.length;
			tab [i] = pageRank;
		}
		return tab;
	}
	
	public static double getMaxDiff(final double[] rank1, final double[] rank2) {//retourne la plus grande différence entre chaque paire de valeurs.
		/* Méthode à coder */
		double difference = 0.0;
		for ( int i = 0; i < rank1.length; i++) {
			double ecart = rank1[i] - rank2[i];//fait la différence entre chaque valeur
			ecart = Math.abs(ecart);
			if (ecart > difference) {//si la différence est plus grande que l'ancienne alors on remplace dans difference.
				difference = ecart;
			}
		}
		return difference;
	}

	public static int getConvSteps(final int[][] net) {// retourne le nombre minimal d'itérations pour que la précision du PageRank estimé soit en dessous de 0.001.
		/* Méthode à coder */
		boolean plusPetit = true;//on le met pour sortir de la condition d'arrêt (do).
		int compteur = 1;
		double [] pageR = new double [net.length];
		do {//on sort de cette boucle quand le nombre d'itérations est plus petit que 0.001.
			int chemin [] = randomSurfer(net, compteur);
			pageR = computePageRank(chemin, net.length);
			compteur++;
			plusPetit = false;//on met ca car autrement après un passage dans le do et donc dans le if, alors il sera true et ne pourra jamais plus devenir false.
			for (int i = 0; i < net.length; i++) { //boucle pour la condition d'arrêt
				
				if (pageR[i] - realPageranks[i] >= 0.001) { //condition d'arrêt
					plusPetit = true;//tant que la différence entre deux ième valeurs est plus grande que 0.001, on reste dans la boucle do. 
				}
				
			}
		} while (plusPetit);
		return compteur;
	}
	
	public static int countVisit(int[] path, int page) {// retourne le nombre de fois où la page en question a été visitée.
		/* Méthode à coder */
		int resultat = 0;
		if (path.length == 0) {//pour gérer lorsque le chemin (path) est vide.
			resultat = 0;
		} else {//incrémente (un compteur) le résultat pour correspondre au nombre de fois où la page a été visitée
			for (int i = 0; i < path.length; i++) {
				if (path [i] == page) {
					resultat++;
				}
			}
		}
		return resultat;
	}

}
