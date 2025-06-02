package Donjons;

import Entites.Entite;
import Entites.Personnages.MaitreJeu;
import Entites.Personnages.Monstre.Monstre;
import Entites.Personnages.Personnage;
import Addon.Scan;
import Objets.Objet;

import java.util.*;

public class Donjon {
    MaitreJeu m_mdj = null;
    Coordonnee m_coordonnee = null;
    private Contenu[][] m_donjon_contenu;
    private ArrayList<String> m_alphabet = new ArrayList<>(Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    ));

    public Donjon(int x, int y, MaitreJeu mdj)
    {
        m_coordonnee = new Coordonnee(x, y);
        m_donjon_contenu = new Contenu[m_coordonnee.getX()][m_coordonnee.getY()];
        m_mdj = mdj;
    }

    public Coordonnee getCoordonnee() {
        return m_coordonnee;
    }

    public void placerObstacle(int x, int y, Obstacle obstacle) {
        verifierdeplacerEntiteValide(new Coordonnee(x, y));

        obstacle.m_coordonnee = new Coordonnee(x, y);

        modifierDonjon(obstacle, obstacle.m_coordonnee);
    }

    public void placerObstaclesAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                Obstacle obstacle = new Obstacle();
                System.out.println("Veuillez indiquer les obstacles à créer dans le donjon : \n(exemple : pour mettre un obstacle à l'endroit A:5 vous devez indiquer A:5)\n");
                int[] XY = convertirCoordonnnee(Scan.ScanLine());
                placerObstacle(XY[0], XY[1], obstacle);

                afficherCarte();

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
                System.out.println(e.getMessage());
            }
        }
    }

    public void placerJoueur(int x, int y, Personnage joueur) {
        verifierdeplacerEntiteValide(new Coordonnee(x, y));

        joueur.m_coordonnee = new Coordonnee(x, y);

        modifierDonjon(joueur, joueur.m_coordonnee);
    }

    public void placerJoueursAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                Personnage joueur = PreparerTour.demanderJoueur();

                while(true) {
                    try {
                        System.out.println("Veuillez indiquer les coordonnées du joueur à placer dans le donjon : \n(exemple : pour placer le joueur à l'endroit A:5 vous devez indiquer A:5)\n");
                        int[] XY = convertirCoordonnnee(Scan.ScanLine());
                        placerJoueur(XY[0], XY[1], joueur); // Appel de votre fonction existante
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                afficherCarte();

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
                System.out.println(e.getMessage());
            }
        }
    }


    public void placerMonstre(int x, int y, Monstre monstre) {
        verifierdeplacerEntiteValide(new Coordonnee(x, y));

        monstre.m_coordonnee = new Coordonnee(x, y);

        modifierDonjon(monstre, monstre.m_coordonnee);
    }

    public void placerMonstresAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                Monstre monstre = PreparerTour.creerMonstre();

                while(true) {
                    try {
                        System.out.println("Veuillez indiquer les coordonnées du Monstre à placer dans le donjon : \n(exemple : pour placer le joueur à l'endroit A:5 vous devez indiquer A:5)\n");
                        int[] XY = convertirCoordonnnee(Scan.ScanLine());

                        placerMonstre(XY[0], XY[1], monstre);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                afficherCarte();

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
                System.out.println(e.getMessage());
            }
        }
    }


    public void placerObjet(int x, int y, Objet objet) {
        verifierdeplacerEntiteValide(new Coordonnee(x, y));

        objet.m_coordonnee = new Coordonnee(x, y);
        modifierDonjon(objet, objet.m_coordonnee);
    }

    public void placerObjetsAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                Objet objet = PreparerTour.creerObjet();
                while(true) {
                    try {
                        System.out.println("Veuillez indiquer les coordonnées du Monstre à placer dans le donjon : \n(exemple : pour placer le joueur à l'endroit A:5 vous devez indiquer A:5)\n");
                        int[] XY = convertirCoordonnnee(Scan.ScanLine());

                        placerObjet(XY[0], XY[1], objet);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                afficherCarte();

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
                System.out.println(e.getMessage());
            }
        }
    }


    public void afficherCarte(){
        System.out.print("\n      ");
        for(int i = 0; i < m_coordonnee.getX(); i++) {
            System.out.print(m_alphabet.get(i)+"  ");
        }
        System.out.println();

        System.out.print("   *--");
        for(int i = 0; i < m_coordonnee.getX(); i++) {
            System.out.print("---");
        }
        System.out.println("*");

        for(int i = 0; i < m_coordonnee.getY(); i++) {
            if(i<=9) {
                System.out.print(i+"  | ");
            }
            else {
                System.out.print(i+" | ");
            }
            for(int j = 0; j < m_coordonnee.getX(); j++) {
                if (m_donjon_contenu[j][i] != null) {
                    System.out.print(m_donjon_contenu[j][i].getSymbole());
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println(" |");
        }

        System.out.print("   *--");
        for(int i = 0; i < m_coordonnee.getX(); i++) {
            System.out.print("---");
        }
        System.out.println("*\n* Equipement   |   [ ] Obstacle  |\n");
    }

    public void deplacerEntite(Entite entite) {
        while(true) {
            int[] XY = convertirCoordonnnee(Scan.ScanLine());
            Coordonnee coordonnee = new Coordonnee(XY[0], XY[1]);
            if(verifierdeplacerEntiteValide(coordonnee)) {
                entite.m_coordonnee = coordonnee;
                afficherCarte();
                break;
            }
        }
    }

    public boolean verifierdeplacerEntiteValide(Coordonnee coordonnee) {
        if(!Objects.equals(m_donjon_contenu[coordonnee.getX()][coordonnee.getY()], null)) {
            throw new IllegalArgumentException("Endroit non disponible, il y a déjà -> " + m_donjon_contenu[coordonnee.getX()][coordonnee.getY()].getSymbole());
        }
        else if(coordonnee.getX()<0 || coordonnee.getY()<0 || coordonnee.getX()>m_coordonnee.getX() || coordonnee.getY()>m_coordonnee.getY()) {
            throw new IllegalArgumentException("L'emplacement est en dehors du donjon");
        }
        else {
            return true;
        }
    }

    public int[] convertirCoordonnnee(String coordonnee) {
        int[] result = new int[2];
        String[] coordonneeSplit = coordonnee.split(":");
        if (coordonneeSplit.length != 2) {
            throw new IllegalArgumentException("Format incorrect. Utilisez le format x:x.");
        }
        int x = -1;
        for(int i = 0; i < m_alphabet.size(); i++) {
            if(m_alphabet.get(i).equals(coordonneeSplit[0])) {
                x = i;
            }
        }
        if (x == -1) {
            throw new IllegalArgumentException("Colonne invalide.");
        }
        int y = Integer.parseInt(coordonneeSplit[1]);

        result[0] = x;
        result[1] = y;

        return result;
    }

    public void modifierDonjon(Contenu contenu, Coordonnee coordonee) {
        m_donjon_contenu[coordonee.getX()][coordonee.getY()] = contenu;
    }
}
