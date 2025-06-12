package entites.personnages;

import addon.De;
import addon.Scan;
import donjons.Coordonnee;
import donjons.Obstacle;
import donjons.Donjon;
import donjons.PreparerTour;
import entites.caracteristiques.CaracteristiqueMonstre;
import entites.Entite;
import entites.personnages.monstres.Attaque;
import entites.personnages.monstres.Monstre;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MaitreJeu{

    public MaitreJeu(){};

    public String raconterTour(){
        System.out.println("Maitre du jeu - Écrivez le contexte :");
        return Scan.ScanLine();
    }

    public Donjon creerDonjon() {
        int x;
        int y;
        // Afficher message de création du donjon pour le maitre du jeu
        System.out.println("Veuillez indiquer la dimension du donjon entre 15 et 25\nExemple : vous voulez créer un donjon de la taille 25x25 alors rentrer : 25:25");
        while(true) {
            try {
                String dimensions = Scan.ScanLine();
                x = parseInt(dimensions.split(":")[0]);
                y = parseInt(dimensions.split(":")[1]);
                if(x < 15 || y < 15 || x > 25 || y> 25) {
                    throw new IllegalAccessException("La taille du donjon doit être compris entre 15 et 25");
                }
                break;
            }
            catch(IllegalAccessException iae) {
                System.out.println(iae.getMessage());
            }
            catch(Exception e) {
                System.out.println("Le format n'est pas le bon, merci de mettre x:x");
            }
        }

        // Appeler creator de donjon
        Donjon donjon = new Donjon(x,y, this);

        donjon.afficherCarte();
        return donjon;
    }

    public Donjon creerDonjonParDefaut() {
        try {
            Donjon donjon = new Donjon(25, 25, this);
            donjon.placerObstacle(5, 9, new Obstacle());
            donjon.placerObstacle(6, 9, new Obstacle());
            donjon.placerObstacle(7, 9, new Obstacle());
            donjon.placerObstacle(7, 10, new Obstacle());
            donjon.placerObstacle(7, 11, new Obstacle());
            donjon.placerObstacle(15, 19, new Obstacle());
            donjon.placerObstacle(16, 19, new Obstacle());
            donjon.placerObstacle(17, 19, new Obstacle());
            donjon.placerObstacle(17, 20, new Obstacle());
            donjon.placerObstacle(17, 21, new Obstacle());
            donjon.placerObstacle(9, 8, new Obstacle());
            donjon.placerObstacle(8, 8, new Obstacle());
            donjon.placerObstacle(7, 8, new Obstacle());
            donjon.placerObjet(6, 15, PreparerTour.creerObjetDepuisChoix(0,1));
            donjon.placerObjet(8, 0, PreparerTour.creerObjetDepuisChoix(0,1));
            donjon.placerObjet(3, 5, PreparerTour.creerObjetDepuisChoix(0,3));
            donjon.placerObjet(20, 6, PreparerTour.creerObjetDepuisChoix(1,2));
            donjon.placerObjet(16, 15, PreparerTour.creerObjetDepuisChoix(1,1));
            donjon.placerObjet(9, 3, PreparerTour.creerObjetDepuisChoix(1,4));
            donjon.placerMonstre(6,15,PreparerTour.creerMonstreDepuisValeurs(0,"Dragon", "Dragou", new Attaque(2, new De(1, 6)), new CaracteristiqueMonstre(15, 4, 4, 12, 10, 15)));
            donjon.placerMonstre(4,5,PreparerTour.creerMonstreDepuisValeurs(1,"Berserk", "B-Rex", new Attaque(1, new De(1, 4)), new CaracteristiqueMonstre(8, 6, 2, 8, 5, 3)));
            donjon.placerMonstre(14,15,PreparerTour.creerMonstreDepuisValeurs(2,"Mutant", "Jean-Pierre", new Attaque(1, new De(2, 3)), new CaracteristiqueMonstre(10, 5, 4, 15, 6, 12)));
            donjon.afficherCarte();
            donjon.modifierContexte(donjon.raconterTourMdj());
            return donjon;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deplacerEntite(Donjon donjon) {
        System.out.println("Quelle entité voulez-vous déplacer ?");
        System.out.println("1 - Personnage");
        System.out.println("2 - Monstre");

        try {
            int choixType = Integer.parseInt(Scan.ScanLine().trim());

            if (choixType == 1) {
                ArrayList<Personnage> joueurs = donjon.getJoueur();

                for (int i = 0; i < joueurs.size(); i++) {
                    System.out.print("[" + i + "] ");
                    joueurs.get(i).afficherSituation();
                    System.out.println();
                }

                System.out.println("Entrez le numéro du personnage à déplacer :");
                int num = Integer.parseInt(Scan.ScanLine());

                if (num >= 0 && num < joueurs.size()) {
                    Entite entite = joueurs.get(num);
                    System.out.println("Maître du Jeu - Entrez les coordonnées cibles pour " + entite.getNom() + " (ex : A:3) :");
                    int[] XY = Donjon.convertirCoordonnnee(Scan.ScanLine());
                    donjon.deplacerEntite(new Coordonnee(XY[0], XY[1]), entite);
                } else {
                    System.out.println("Numéro de personnage invalide.");
                }

            } else if (choixType == 2) {
                ArrayList<Monstre> monstres = donjon.getMonstres();

                for (int i = 0; i < monstres.size(); i++) {
                    System.out.print("[" + i + "] ");
                    monstres.get(i).afficherSituation();
                    System.out.println();
                }

                System.out.println("Entrez le numéro du monstre à déplacer :");
                int num = Integer.parseInt(Scan.ScanLine());

                if (num >= 0 && num < monstres.size()) {
                    Entite entite = monstres.get(num);
                    System.out.println("Maître du Jeu - Entrez les coordonnées cibles pour " + entite.getNom() + " (ex : A:3) :");
                    int[] XY = Donjon.convertirCoordonnnee(Scan.ScanLine());
                    donjon.deplacerEntite(new Coordonnee(XY[0], XY[1]), entite);
                } else {
                    System.out.println("Numéro de monstre invalide.");
                }

            } else {
                System.out.println("Choix invalide. Veuillez saisir 1 ou 2.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }


    public void infligerDegatsEntite(Donjon donjon) {
        System.out.println("Sur quel type d'entité voulez-vous infliger des dégâts ?");
        System.out.println("1 - Personnage");
        System.out.println("2 - Monstre");

        try {
            int choixType = Integer.parseInt(Scan.ScanLine().trim());

            Entite entite = null;

            if (choixType == 1) {
                ArrayList<Personnage> joueurs = donjon.getJoueur();
                for (int i = 0; i < joueurs.size(); i++) {
                    System.out.print("[" + i + "] ");
                    joueurs.get(i).afficherSituation();
                    System.out.println();
                }

                System.out.println("Entrez le numéro du personnage ciblé :");
                int num = Integer.parseInt(Scan.ScanLine());

                if (num >= 0 && num < joueurs.size()) {
                    entite = joueurs.get(num);
                } else {
                    System.out.println("Numéro de personnage invalide.");
                    return;
                }

            } else if (choixType == 2) {
                ArrayList<Monstre> monstres = donjon.getMonstres();
                for (int i = 0; i < monstres.size(); i++) {
                    System.out.print("[" + i + "] ");
                    monstres.get(i).afficherSituation();
                    System.out.println();
                }

                System.out.println("Entrez le numéro du monstre ciblé :");
                int num = Integer.parseInt(Scan.ScanLine());

                if (num >= 0 && num < monstres.size()) {
                    entite = monstres.get(num);
                } else {
                    System.out.println("Numéro de monstre invalide.");
                    return;
                }

            } else {
                System.out.println("Choix invalide. Veuillez saisir 1 ou 2.");
                return;
            }

            System.out.println("Maître du Jeu - Indiquez le dé à lancer pour les dégâts (ex : 1d6, 2d8...) :");
            De degats = De.convertirStringDe(Scan.ScanLine());

            int totalDegats = degats.lancer();
            donjon.faireDegats(entite, totalDegats);
            System.out.println(entite.getNom() + " a subi " + totalDegats + " point(s) de dégât.");

        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }


    public void ajouterObstacle(Donjon donjon) {
        System.out.println("Maître du Jeu - Veuillez indiquer les coordonnées pour rajouter un obstacle :");
        int[] XY = Donjon.convertirCoordonnnee(Scan.ScanLine());

        donjon.placerObstacle(XY[0], XY[1], new Obstacle());
    }
}
