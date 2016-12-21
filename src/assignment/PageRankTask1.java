package assignment;

import java.util.Random;

public class PageRankTask1 {

	/* Utilisez cet objet pour générer des nombres aléatoires*/
	public static Random random = new Random(2013);  //on a enlevé le seed 2013 dans Random() pour obtenir un pseudo aléatoire différent à chaque essai   

	public static void main(String[] argv) {   
		/*Réseau de pages exemple*/
		int [][] net = {
			{ 1, 2, 5},    //page 0 ajouter un 5
			{ 2, 2, 4},  //page 1
			{ 4 },       //page 2 
			{ 0, 0},     //page 3
			{ 1, 2 , 4, 5},  //page 4 ajouter un 5
			{ }           //page 5
		};
		
		int[] path = randomSurfer(net, 10); 
		
		for (int i = 0; i < path.length; i++) {
			System.out.println("L'utilisateur visite la page " + path[i]);
		}  
		System.out.println("Visualisation graphique des visites : ");
		for (int i = 0; i < path.length; i++) {
			System.out.println(visualizeVisit(path[i], net.length));
		} 
	}

	public static int[] randomSurfer(int[][] net, int steps) { //ça retourne le path (le chemin que l'utilisateur fait dans le net)
		/* Méthode à coder */
		int tab [] = new int [steps]; //le tab est configurer par rapport au nombre de pas (steps)
		tab [0] = 0;
		for ( int i = 1; i < steps; i++) {
			tab [i] = getNextPage(net, tab[i-1]); //utilisation de la fonction getNextPage() un nombre ième de fois pour remplir le tab	
		}
		return tab;
	}
	
	public static int getNextPage(int[][] net, int currentPage) { //retourne la prochaine page que l'utilisateur visitera.
		// retourne 0 en cas d'erreurs (voir plus loin)
		/* Méthode à coder
		 * Utilisez random.nextDouble() pour générer un réel aléatoire
		 * et random.nextInt(int n) pour générer un entier aléatoire.
		 */
		int damp = random.nextInt(9);//tire un nombre au bol pour le damping
		int resultat = 0;
		while (net[currentPage].length == 0) { //si on a une page qui n'a pas de lien---> on choisit une page au hasard
			currentPage = random.nextInt(net.length); 
		}
		if (damp == 0) { //si le damp = 0 alors la fonction retourne une page aléatoire (pour respecter le damping 0.9
			int aleatoire = random.nextInt(net.length);
			resultat = aleatoire;
		} else {
		int tab [] = net [currentPage];
		if (tab.length == 0) { //pour le premier passage de la fonction quand le tab est vide.
			int aleatoire = random.nextInt(net.length);
			resultat = tab[aleatoire];
		} else {
		int aleatoire = random.nextInt(tab.length);
		resultat = tab[aleatoire];
		}
		}
		return resultat;
		
		}
	
	

	public static String visualizeVisit(int page, int totalPageNum) { //affiche le schéma (--x-)
		/* Méthode à coder */
		String resultat = "";
		for ( int i = 0; i < totalPageNum; i++) { //une boucle pour une page visitée (un schéma = une ligne)
			if ( i != page ) { //quand i = la page visitée alors il met un x autrement -
				resultat = resultat + "-";
			} else {
				resultat = resultat + "x";
			}
		}
		return resultat;
	}
}
