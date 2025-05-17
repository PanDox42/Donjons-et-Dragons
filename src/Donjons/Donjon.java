package Donjons;

import Entites.Monstres.Monstre;
import Entites.Personnages.MaitreJeu;
import Entites.Personnages.Personnage;
import Objets.Objet;
import Scanner.Scan;
import org.w3c.dom.ranges.RangeException;

import java.util.*;

public class Donjon {
    private int m_x;
    private int m_y;
    private String[][] m_donjon;
    private Objet[][] m_donjon_objets;
    private ArrayList<String> m_alphabet = new ArrayList<>(Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    ));

    public Donjon(int x, int y)
    {
        m_y = y;
        m_x = x;
        m_donjon =  new String[m_x][m_y];
        for(int i = 0; i<m_donjon.length; i++) {
            for(int j = 0; j<m_donjon[i].length; j++) {
                m_donjon[i][j] = " . ";
            }
        }
    }

    public void creationObstacle() {
        afficherCarte();

        System.out.println("Veuillez indiquer les obstacles à créer dans le donjon : \n(exemple : pour mettre un obstacle à l'endroit A:5 vous devez indiquer A:5)\n");

        while (true) {
            try {
                String[] obstacleSplit = Scan.ScanLine().split(":");
                if (obstacleSplit.length != 2) {
                    throw new IllegalArgumentException("Format incorrect. Utilisez le format x:x.");
                }

                int x = -1;
                for (int i = 0; i < m_alphabet.size(); i++) {
                    if (m_alphabet.get(i).equals(obstacleSplit[0])) {
                        x = i;
                    }
                }

                if (x == -1) {
                    throw new IllegalArgumentException("Colonne invalide.");
                }

                int y = Integer.parseInt(obstacleSplit[1]);

                if (x < 0 || x >= m_donjon.length || y < 0 || y >= m_donjon[x].length) {
                    throw new IllegalArgumentException("Coordonnées hors limites.");
                }

                if(m_donjon[x][y]!=" . ") {
                    throw new IllegalArgumentException("Endroit non disponible, il y a déjà -> "+ m_donjon[x][y]);
                }

                m_donjon[x][y] = "[ ]";
                System.out.println("Obstacle placé avec succès à " + obstacleSplit[0] + ":" + y);
                break;

            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    public void placerPersonnage(Personnage personnage) {
        afficherCarte();

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

                if (x == -1) {
                    throw new IllegalArgumentException("Colonne invalide.");
                }

                int y = Integer.parseInt(obstacleSplit[1]);

                if (x < 0 || x >= m_donjon.length || y < 0 || y >= m_donjon[x].length) {
                    throw new IllegalArgumentException("Coordonnées hors limites.");
                }

                if(m_donjon[x][y]!=" . ") {
                    throw new IllegalArgumentException("Endroit non disponible, il y a déjà -> "+ m_donjon[x][y]);
                }

                if(personnage.getNom().length() < 3) {
                    m_donjon[x][y] = personnage.getNom().toUpperCase();
                }
                else {
                    m_donjon[x][y] = personnage.getNom().substring(0,3).toUpperCase();
                }

                System.out.println("Joueur placé avec succès à " + obstacleSplit[0] + ":" + y);
                break;

            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    public void placerMonstre(Monstre monstre) {
        afficherCarte();

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

                if (x == -1) {
                    throw new IllegalArgumentException("Colonne invalide.");
                }

                int y = Integer.parseInt(obstacleSplit[1]);

                if (x < 0 || x >= m_donjon.length || y < 0 || y >= m_donjon[x].length) {
                    throw new IllegalArgumentException("Coordonnées hors limites.");
                }

                if(m_donjon[x][y]!=" . ") {
                    throw new IllegalArgumentException("Endroit non disponible, il y a déjà -> "+ m_donjon[x][y]);
                }

                if(monstre.getNom().length() < 3) {
                    m_donjon[x][y] = monstre.getNom().toUpperCase();
                }
                else {
                    m_donjon[x][y] = monstre.getNom().substring(0,3).toUpperCase();
                }

                System.out.println("Monstre placé avec succès à " + obstacleSplit[0] + ":" + y);
                break;

            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    public void placerEquipement(Objet objet) {
        afficherCarte();

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

                if (x < 0 || x >= m_donjon.length || y < 0 || y >= m_donjon[x].length) {
                    throw new IllegalArgumentException("Coordonnées hors limites.");
                }

                if(m_donjon[x][y]!=" . ") {
                    throw new IllegalArgumentException("Endroit non disponible, il y a déjà -> "+ m_donjon[x][y]);
                }

                m_donjon[x][y] = " * ";
                m_donjon_objets[x][y] = objet;

                System.out.println("Equipement placé avec succès à " + obstacleSplit[0] + ":" + y);
                break;

            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    public void afficherCarte(){
        System.out.print("\n      ");
        for(int i = 0; i < m_x; i++) {
            System.out.print(m_alphabet.get(i)+"  ");
        }
        System.out.println();

        System.out.print("   *--");
        for(int i = 0; i < m_x; i++) {
            System.out.print("---");
        }
        System.out.println("*");

        for(int i = 1; i < m_y; i++) {
            if(i<=9) {
                System.out.print(i+"  | ");
            }
            else {
                System.out.print(i+" | ");
            }
            for(int j = 0; j < m_x; j++) {
                System.out.print(m_donjon[j][i]);
            }
            System.out.println(" |");
        }

        System.out.print("   *--");
        for(int i = 0; i < m_x; i++) {
            System.out.print("---");
        }
        System.out.println("*\n* Equipement   |   [ ] Obstacle  |\n");
    }
}
