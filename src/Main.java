import Des.De;
import Donjons.Donjon;
import Entites.Monstres.Monstre;
import Entites.Personnages.Classes.*;
import Entites.Personnages.Personnage;
import Entites.Personnages.Races.*;
import Objets.Arme.Arme;
import Objets.Arme.ArmeCourante;
import Objets.Armure.ArmureLourde;
import Objets.Objet;
import Scanner.Scan;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons\n");
/*
        // QUE DES TESTS

        Race r = new Elfe(); // La dcp il aura 16pts de vie psk c'est un elfe
        Classe c = new Clerc();
        Personnage bob = new Personnage("bo", r, c);
        Monstre kingkong = new Monstre("King Kong");
        Objet buche = new ArmeCourante("Bûche", 1, new De(1,6));

        Donjon d = new Donjon(26,26);
        d.creationObstacle();
        d.placerPersonnage(bob);
        d.placerMonstre(kingkong);
        d.placerEquipement(buche);
        d.afficherCarte();

        bob.afficherSituation();
*/
        // FIN TESTS

        // VRAI JEU
        int m_nbJoueur = 0;
        while(true) {
            try {
                System.out.println("A combien de joueur voulez-vous jouer ?");
                m_nbJoueur = parseInt(Scan.ScanLine());
                if(m_nbJoueur<1) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (Exception e) {
                System.out.println("Le format n'est pas valide");
            }
        }

        ArrayList<Personnage> m_personnages = new ArrayList<>();
        int classe = 0;
        int race = 0;
        Race raceClasse = new Elfe();
        Classe classeClasse = new Roublard();
        String nom = "";

        for(int i = 0; i<m_nbJoueur; i++) {
            while(true) {
                try {
                    System.out.println("Quel sera le nom du joueur "+(i+1)+" ?");
                    nom = Scan.ScanLine();

                    System.out.println("Quel sera la race du joueur " + (i + 1) + " parmi cela ?");
                    System.out.println("Entrez 0 pour Elfe\nEntrez 1 pour Halfelin\nEntrez 2 pour Humain\nEntrez 3 pour Nain");
                    race = parseInt(Scan.ScanLine());
                    if (race > 3 || race < 0) {
                        throw new IllegalArgumentException("Veuillez entrer un nombre entier entre 0 et 3 inclus");
                    }

                    System.out.println("Quel sera la classe du joueur " + (i + 1) + " parmi cela ?");
                    System.out.println("Entrez 0 pour Clerc\nEntrez 1 pour Guerrier\nEntrez 2 pour Magicien\nEntrez 3 pour Roublard");
                    classe = parseInt(Scan.ScanLine());
                    if (classe > 3 || classe < 0) {
                        throw new IllegalArgumentException("Veuillez entrer un nombre entier entre 0 et 3 inclus");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Erreur: " + e.getMessage());
                }
            }

            switch(race) {
                case 0:
                    raceClasse = new Elfe();
                case 1:
                    raceClasse = new Halfelin();
                case 2:
                    raceClasse = new Humain();
                case 3:
                    raceClasse = new Nain();
            }

            switch(classe) {
                case 0:
                    classeClasse = new Clerc();
                case 1:
                    classeClasse = new Guerrier();
                case 2:
                    classeClasse = new Magicien();
                case 3:
                    classeClasse = new Roublard();
            }

            m_personnages.add(new Personnage(nom, raceClasse, classeClasse));
        }
    }
}
