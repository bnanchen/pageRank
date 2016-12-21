package assignment;

import java.util.Arrays;
import java.util.Random;

public class PageRankTask4 {

	/*
	Réponses à la question de la tâche 4.d :
		- Quelle méthode nécessite le moins d'itérations pour obtenir
		un PageRank précis ? 
		- Réponse : computePageRank
		
		- Pourquoi, selon vous ?
		- Réponse : car estimatePageRank dépend de la grandeur de la matrice de transition et du nombre de pas,
		alors que le temps de calcul de computePageRank dépend du nombre de pas. 
		
	*/

	/* Utilisez cet objet pour générer des nombres aléatoires*/
	public static Random random = new Random(2013);    
	
	public static void main(String[] argv) {   
		/*Réseau de pages exemple*/
		int [][] net = {
				{ 1, 2 },    //page 0
				{ 2, 2, 4},  //page 1
				{ 4 },       //page 2
				{ 0, 0},     //page 3
				{ 1, 2 , 4}  //page 4

				};
		
		System.out.println("Estimation du PageRank (random walk - 25 itérations - damping 0.9) : ");
		int[] path = PageRankTask1.randomSurfer(net, 25);
		System.out.println(Arrays.toString(PageRankTask3.computePageRank(path, net.length)));

		System.out.println("Estimation du PageRank (méthode matricielle) - 25 itérations - damping 0.9 : ");
		System.out.println(Arrays.toString(estimatePageRank(net, 25, 0.9)));	
	}


	public static double[] estimatePageRank(int[][] net, int steps, double dampingFactor) {//produit une estimation du PageRank pour chaque pages.
		/* Méthode à coder */
		double [][] transitionM = computeTransitionsMatrix(net, dampingFactor);
		double [] estimation = pageRankIterations(transitionM, steps);
		
		return estimation;
	}

	public static double[][] computeTransitionsMatrix(int[][] net, double dampingFactor) {//retourne la matrice de transition
		/* Méthode à coder */
		double [][] matrix = new double[net.length][net.length];
		for (int i = 0; i < net.length; i++) {//crée la matrice de transition qui sera retournée.
			double [] ligne = new double [net.length];
			double Li = 0;
			for(int j = 0; j < net[i].length; j++){//on incrémente ligne pour chaque liens avec une page et création de la ième ligne de la matrice. 
				ligne[net[i][j]]++;
				}
			for (int b = 0; b < ligne.length; b++) {//obtenir Li qui additione le nombre de liens avec toutes les autres pages.
				Li = Li + ligne[b];
			}
			for (int h = 0; h < ligne.length; h++) {//effectue le calcule demandé.
				ligne[h] = (ligne[h] / Li) * 0.9;
				ligne[h] = ligne[h] + ((1 - 0.9) / net.length);//deux lignes parce qu'il faut respecter l'ordre des opérations autrement on aurait beaucoup de parenthèses.
			}   
			matrix[i] = ligne;
		}
		return matrix;
	}
	

	public static double[] pageRankIterations(double[][] transitions, int steps) {//retourne un tableau avec les PageRank estimé pour chaque pages.
		/* Méthode à coder */
		double [] tab = new double [transitions.length];
		tab[0] = 1.0;
		for(int a = 0; a < steps; a++) {//multiplication de matrices pour un certain nombre d'itérations (steps).
			double [] intermediaire = new double [transitions.length];//on crée un intermédiaire car on peut le vider à chaque i (ligne de matrice).
			for (int j = 0; j < transitions.length; j++){//déplacement par rapport aux colonnes de la matrice.
				for (int i = 0; i < transitions.length; i++) {//déplacement par rapport aux lignes de la matrice.
					intermediaire[j] = intermediaire[j] + (tab[i] * transitions[i][j]);//multiplication entre matrice (transitions) et vecteur (tab)
					}
				}
			for(int k = 0; k < tab.length; k++) {//rempli les solutions de la multiplication dans le tab.
				tab[k] = intermediaire[k];
			}
			
		}
		
		return tab;
	}

}
