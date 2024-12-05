/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp4.dame;

/**
 *
 * @author victor
 */
import java.util.Scanner;

class JeuDame {
    private Plateau plateau = new Plateau(); // Plateau de 8x8
    private Scanner scanner = new Scanner(System.in);

    public void demarrerPartie() {
        System.out.println("La Partie va commencer, bonne chance à tous, que le meilleur gagne !");
        System.out.println("Entrez votre coup de la façon suivante : 2 3 3 4 pour déplacer de [2,3] à [3,4]):");
        
        plateau.afficherPlateau();

        while (!jeuFini()) {
            // Tour du joueur Blanc
            System.out.println("C'est le tour des Blancs (B). Entrez votre coup : ");
            String coupB = scanner.nextLine();
            while (!validerCoup('B',coupB)){
                System.out.println("Votre coup n'est pas valide, merci d'en fournir un nouveau : ");
                coupB = scanner.nextLine();
            };
            // Le coup est valide, actualisation du plateau en fonction du coup
            jouerTour('B',coupB);
            
            // Affichage du plateau et fin du tour
            plateau.afficherPlateau();
            
            // Tour du joueur Noir
            System.out.println("C'est le tour des Noirs (N). Entrez votre coup : ");
            String coupN = scanner.nextLine();
            while (!validerCoup('N',coupN)){
                System.out.println("Votre coup n'est pas valide, merci d'en fournir un nouveau : ");
                coupN = scanner.nextLine();
            };
            // Le coup est valide, actualisation du plateau en fonction du coup
            jouerTour('N',coupN); 
            
            // Affichage du plateau et fin du tour
            plateau.afficherPlateau();
        }

        System.out.println("Fin de la partie !");
    }

    private void jouerTour(char joueur, String coup){
         // TODO
    }

    private boolean validerCoup(char joueur, String coup) {
       // TODO
       return false;
    }
    
    public Boolean jeuFini() {
        boolean noirPresent = false; // Indique s'il reste des pions noirs sur le plateau
        boolean blancPresent = false; // Indique s'il reste des pions blancs sur le plateau
        char[][] pions = plateau.getPions();
        // Parcourir le plateau
        for (int i = 0; i < pions.length; i++) {
            for (int j = 0; j < pions[i].length; j++) {
                if (pions[i][j] == 'N') {
                    noirPresent = true; // Pion noir trouvé
                } else if (pions[i][j] == 'B') {
                    blancPresent = true; // Pion blanc trouvé
                }

                // Si les deux types de pions sont trouvés, pas besoin de continuer
                if (noirPresent && blancPresent) {
                    return false; // Le jeu n'est pas terminé
                }
            }
        }

        // Si l'un des deux types de pions n'existe plus, le jeu est terminé
        return !noirPresent || !blancPresent;
    }

}
