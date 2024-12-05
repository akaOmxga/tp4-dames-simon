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
        System.out.println("NB les cases sont numérotées de 1 à 8");
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
        String[] parts = coup.split(" ");
        int x1 = Integer.parseInt(parts[0]);
        int y1 = Integer.parseInt(parts[1]);
        int x2 = Integer.parseInt(parts[2]);
        int y2 = Integer.parseInt(parts[3]);
        char[][] pions = plateau.getPions();
        pions[x1-1][y1-1] = '.';
        pions[x2-1][y2-1] = joueur;
    }

    private boolean validerCoup(char joueur, String coup) {
        String e = "\\d+ \\d+ \\d+ \\d+";
        if (!coup.matches(e)){return false;} // le coup donné ne matche pas l'expression régulière d'un coup
        String[] parts = coup.split(" ");
        int x1 = Integer.parseInt(parts[0]);
        int y1 = Integer.parseInt(parts[1]);
        int x2 = Integer.parseInt(parts[2]);
        int y2 = Integer.parseInt(parts[3]);
        char[][] pions = plateau.getPions();
        if (x1-1<0 || x1-1 > 7 || x2-1<0 || x2-1 > 7 || y1-1<0 || y1-1 > 7 || y2-1<0 || y2-1 > 7)
            {return false;} // les cases fournies sont hors du plateau
        if (pions[x1-1][y1-1] != joueur)
            {return false;} // la case initiale n'est pas celle d'un pion du joueur joueur
        if (pions[x2-1][y2-1] == joueur)
            {return false;} // la case finale est celle d'un pion du joueur joueur
        return true;
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
