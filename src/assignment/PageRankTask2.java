package assignment;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class PageRankTask2 {
private static Scanner input = new Scanner(System.in);
	/* Utilisez cet objet pour générer des nombres aléatoires*/
	public static Random random = new Random(2013);    

	public static void main(String[] argv) {   
		/*Réseau de pages exemple*/
		int [][] net = {
			{ 1, 2 },    //page 0
			{ 2, 4 },  //page 1 
			{ 4 },       //page 2
			{ 0, 0 },     //page 3 
			{ 1, 2, 4 }  //page 4
		};
		
		int[] path = randomSurfer(net);
		System.out.println("Séquence des pages suivies : " + Arrays.toString(path));
	}

	public static int[] randomSurfer(int[][] net) { //fonction qui demande le nombre d'itérations, un facteur de damping et qui retourne le path.	
		/* Copiez/collez et adaptez votre solution à la tâche 1 */
		int iteration = -1;
		double damping = -1.0;
		while (iteration <= 0) { //permet de gerer les exceptions, requestionne tant que iteration <= 0. 
		System.out.println("Entrez le nombre de pas :");
		iteration = input.nextInt();
		if (iteration <= 0) {
			System.out.println("Le nombre d'itérations doit etre positif.");
		}
		}
		while (damping < 0.0 || damping > 1.0) { // permet de gerer les exceptions, requestionne tant que damping n'est pas entre 0 et 1.
		System.out.println("Entrez le facteur de damping :");
		damping = input.nextDouble();
		if (damping < 0.0 || damping > 1.0) {
			System.out.println("Le facteur damping doit etre compris entre 0.0 et 1.0");
		}
		}
		int tab [] = new int [iteration];
		tab [0] = 0;
		for ( int i = 1; i < iteration; i++) { //rempli le path en utilisant getNextPage (au pire voir expli. task 1)
			tab [i] = getNextPage(net, tab[i-1], damping);	
		}
		return tab;
	}

	public static int getNextPage(int[][] net, int currentPage, double damping) { //retourne la prochaine page que l'utilisateur visitera, ici en plus en prenant compte du damping rentré.
		/* Copiez/collez et adaptez votre solution à la tâche 1 */
		int resultat = 0;
		double aleatoire2 = random.nextDouble();
		while (net[currentPage].length == 0) {
			currentPage = random.nextInt(net.length);
		}
		if (aleatoire2 >= damping) {//prend le damping entré par l'utilisateur comme limite. L'aléatoire2 décide si on est à droite ou à gauche de la limite,du damping.
			int aleatoire = random.nextInt(net.length);//retourne une page au hasard
			resultat = aleatoire;
		} else {//retourne une page en fonction des liens entre les pages. 
		int tab [] = net [currentPage];
		if (tab.length == 0) {//pour gérer le premier passage ou tab sera vide.
			int aleatoire = random.nextInt(net.length);
			resultat = tab[aleatoire];
		} else {//retourne une page en fonction des liens entre les pages.
		int aleatoire = random.nextInt(tab.length);
		resultat = tab[aleatoire];
		}
		}
		return resultat;
	}

}
