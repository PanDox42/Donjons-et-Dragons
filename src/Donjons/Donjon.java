package Donjons;

import Entites.Personnages.Monstre.Monstre;
import Entites.Personnages.Personnage;
import Addon.Scan;
import Objets.Objet;

import java.util.*;

public class Donjon {
    Coordonnee coordonnee = null;
    private Contenu[][] m_donjon_contenu;
    private ArrayList<String> m_alphabet = new ArrayList<>(Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    ));

    public Donjon(int x, int y)
    {
        coordonnee = new Coordonnee(x, y);
        m_donjon_contenu = new Contenu[coordonnee.getX()][coordonnee.getY()];
    }

    public void placerObstacle() {
        afficherCarte();
        Obstacle obstacle = new Obstacle();

        System.out.println("Veuillez indiquer les obstacles à créer dans le donjon : \n(exemple : pour mettre un obstacle à l'endroit A:5 vous devez indiquer A:5)\n");

        while (true) {
            try {
                String[] obstacleSplit = Scan.ScanLine().split(":");
                if (obstacleSplit.length != 2) {
                    throw new IllegalArgumentException("Format incorrect. Utilisez le format x:x");
                }

                int x = -1;
                for (int i = 0; i < m_alphabet.size(); i++) {
                    if (m_alphabet.get(i).equals(obstacleSplit[0])) {
                        x = i;
                    }
                }

                int y = Integer.parseInt(obstacleSplit[1]);

                try {
                    if (x < 0 || x >= m_donjon_contenu.length || y < 0 || y >= m_donjon_contenu[x].length) {
                        throw new IllegalArgumentException("Coordonnées hors limites.");
                    }
                    if(m_donjon_contenu[x][y].getSymbole()!=" . ") {
                        throw new IllegalArgumentException("Endroit non disponible, il y a déjà -> "+ m_donjon_contenu[x][y]);
                    }
                    else {
                        obstacle.m_coordonnee = new Coordonnee(x, y);
                        m_donjon_contenu[x][y] = obstacle;
                    }
                }
                catch(Exception e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("Obstacle placé avec succès à " + obstacleSplit[0] + ":" + y);
                break;

            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    public void placerObstaclesAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                placerObstacle(); // Appel de la méthode existante

                System.out.println("Voulez-vous ajouter un autre obstacle ? (oui/non)");

                String reponse = Scan.ScanLine().trim().toLowerCase();

                while (!reponse.equals("oui") && !reponse.equals("non")) {
                    System.out.println("Réponse invalide. Veuillez répondre par 'oui' ou 'non'.");
                    reponse = Scan.ScanLine().trim().toLowerCase();
                }

                if (reponse.equals("non")) {
                    continuer = false;
                }

            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        }

        System.out.println("Fin de la création des obstacles.");
    }

    public void placerJoueur() {
        afficherCarte();

        Personnage joueur = PreparerTour.creerJoueur();

        System.out.println("Veuillez indiquer les coordonnées du joueur à placer dans le donjon : \n(exemple : pour placer le joueur à l'endroit A:5 vous devez indiquer A:5)\n");

        while (true) {
            try {
                String[] obstacleSplit = Scan.ScanLine().split(":");
                if (obstacleSplit.length != 2) {
                    throw new IllegalArgumentException("Format incorrect. Utilisez le format x:x.");
                }

                int x = -1;
                for(int i = 0; i < m_alphabet.size(); i++) {
                    if(m_alphabet.get(i).equals(obstacleSplit[0])) {
                        x = i;
                    }
                }

                int y = Integer.parseInt(obstacleSplit[1]);

                String afficherDonjon = "";
                if(joueur.getNom().length() == 1) {
                    afficherDonjon = " "+joueur.getNom().toUpperCase()+" ";
                }
                else if(joueur.getNom().length() == 2) {
                    afficherDonjon = joueur.getNom().toUpperCase()+" ";
                }
                else {
                    afficherDonjon = joueur.getNom().substring(0,3).toUpperCase();
                }

                if (x < 0 || x >= m_donjon_contenu.length || y < 0 || y >= m_donjon_contenu[x].length) {
                    throw new IllegalArgumentException("Coordonnées hors limites.");
                }
                else if (m_donjon_contenu[x][y].getSymbole() != " . ") {
                    throw new IllegalArgumentException("Endroit non disponible, il y a déjà -> " + m_donjon_contenu[x][y]);
                }
                else {
                    joueur.m_coordonnee = new Coordonnee(x, y);
                    m_donjon_contenu[x][y] = joueur;
                }

                System.out.println("Joueur placé avec succès à " + obstacleSplit[0] + ":" + y);
                break;

            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    public void placerJoueursAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                placerJoueur(); // Appel de votre fonction existante

                System.out.println("Voulez-vous ajouter un autre joueur ? (oui/non)");

                String reponse = Scan.ScanLine().trim().toLowerCase();

                while (!reponse.equals("oui") && !reponse.equals("non")) {
                    System.out.println("Réponse invalide. Veuillez répondre par 'oui' ou 'non'.");
                    reponse = Scan.ScanLine().trim().toLowerCase();
                }

                if (reponse.equals("non")) {
                    continuer = false;
                }

            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        }

        System.out.println("Fin du placement des joueurs.");
    }


    public void placerMonstre() {
        afficherCarte();

        Monstre monstre = PreparerTour.creerMonstre();

        System.out.println("Veuillez indiquer les coordonnées du Monstre à placer dans le donjon : \n(exemple : pour placer le joueur à l'endroit A:5 vous devez indiquer A:5)\n");

        while (true) {
            try {
                String[] obstacleSplit = Scan.ScanLine().split(":");
                if (obstacleSplit.length != 2) {
                    throw new IllegalArgumentException("Format incorrect. Utilisez le format x:x.");
                }

                int x = -1;
                for(int i = 0; i < m_alphabet.size(); i++) {
                    if(m_alphabet.get(i).equals(obstacleSplit[0])) {
                        x = i;
                    }
                }

                int y = Integer.parseInt(obstacleSplit[1]);

                String afficherDonjon = "";
                if(monstre.getNom().length() == 1) {
                    afficherDonjon = " "+monstre.getNom().toUpperCase()+" ";
                }
                else if(monstre.getNom().length() == 2) {
                    afficherDonjon = monstre.getNom().toUpperCase()+" ";
                }
                else {
                    afficherDonjon = monstre.getNom().substring(0,3).toUpperCase();
                }

                if (x < 0 || x >= m_donjon_contenu.length || y < 0 || y >= m_donjon_contenu[x].length) {
                    throw new IllegalArgumentException("Coordonnées hors limites.");
                }
                else if (m_donjon_contenu[x][y].getSymbole() != " . ") {
                    throw new IllegalArgumentException("Endroit non disponible, il y a déjà -> " + m_donjon_contenu[x][y]);
                }
                else {
                    monstre.m_coordonnee = new Coordonnee(x, y);
                    m_donjon_contenu[x][y] = monstre;
                }

                System.out.println("Monstre placé avec succès à " + obstacleSplit[0] + ":" + y);
                break;

            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    public void placerMonstresAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                placerMonstre(); // Appel de la méthode existante

                System.out.println("Voulez-vous ajouter un autre monstre ? (oui/non)");

                String reponse = Scan.ScanLine().trim().toLowerCase();

                while (!reponse.equals("oui") && !reponse.equals("non")) {
                    System.out.println("Réponse invalide. Veuillez répondre par 'oui' ou 'non'.");
                    reponse = Scan.ScanLine().trim().toLowerCase();
                }

                if (reponse.equals("non")) {
                    continuer = false;
                }

            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        }

        System.out.println("Fin du placement des monstres.");
    }


    public void placerObjet() {
        afficherCarte();
        Objet objet = PreparerTour.creerObjet();
        System.out.println("Veuillez indiquer les coordonnées de l'équipement à placer dans le donjon : \n(exemple : pour placer le joueur à l'endroit A:5 vous devez indiquer A:5)\n");
        while (true) {
            try {
                String[] obstacleSplit = Scan.ScanLine().split(":");
                if (obstacleSplit.length != 2) {
                    throw new IllegalArgumentException("Format incorrect. Utilisez le format x:x.");
                }

                int x = -1;
                for(int i = 0; i < m_alphabet.size(); i++) {
                    if(m_alphabet.get(i).equals(obstacleSplit[0])) {
                        x = i;
                    }
                }

                if (x == -1) {
                    throw new IllegalArgumentException("Colonne invalide.");
                }

                int y = Integer.parseInt(obstacleSplit[1]);

                if (x < 0 || x >= m_donjon_contenu.length || y < 0 || y >= m_donjon_contenu[x].length) {
                    throw new IllegalArgumentException("Coordonnées hors limites.");
                }
                else if (m_donjon_contenu[x][y].getSymbole() != " . ") {
                    throw new IllegalArgumentException("Endroit non disponible, il y a déjà -> " + m_donjon_contenu[x][y]);
                }
                else {
                    objet.m_coordonnee = new Coordonnee(x, y);
                    m_donjon_contenu[x][y] = objet;
                }

                System.out.println("Equipement placé avec succès à " + obstacleSplit[0] + ":" + y);
                break;

            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    public void placerObjetsAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                placerObjet(); // Appel de ta méthode de placement d'objet

                System.out.println("Voulez-vous ajouter un autre équipement ? (oui/non)");

                String reponse = Scan.ScanLine().trim().toLowerCase();

                while (!reponse.equals("oui") && !reponse.equals("non")) {
                    System.out.println("Réponse invalide. Veuillez répondre par 'oui' ou 'non'.");
                    reponse = Scan.ScanLine().trim().toLowerCase();
                }

                if (reponse.equals("non")) {
                    continuer = false;
                }

            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        }

        System.out.println("Fin du placement des équipements.");
    }


    public void afficherCarte(){
        System.out.print("\n      ");
        for(int i = 0; i < coordonnee.getX(); i++) {
            System.out.print(m_alphabet.get(i)+"  ");
        }
        System.out.println();

        System.out.print("   *--");
        for(int i = 0; i < coordonnee.getX(); i++) {
            System.out.print("---");
        }
        System.out.println("*");

        for(int i = 0; i < coordonnee.getY(); i++) {
            if(i<=9) {
                System.out.print(i+"  | ");
            }
            else {
                System.out.print(i+" | ");
            }
            for(int j = 0; j < coordonnee.getX(); j++) {
                if (m_donjon_contenu[j][i] != null) {
                    System.out.print(m_donjon_contenu[j][i].getSymbole());
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println(" |");
        }

        System.out.print("   *--");
        for(int i = 0; i < coordonnee.getX(); i++) {
            System.out.print("---");
        }
        System.out.println("*\n* Equipement   |   [ ] Obstacle  |\n");
    }
}
