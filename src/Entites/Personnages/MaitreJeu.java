package Entites.Personnages;

import Addon.De;
import Donjons.Donjon;
import Addon.Scan;
import Donjons.Jouer;
import Donjons.Obstacle;
import Donjons.PreparerTour;
import Entites.Personnages.Monstre.Monstre;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MaitreJeu{

    public MaitreJeu(){};

    public void raconterTour(){
        System.out.println("Maitre du jeu - Écrivez le contexte :\n"+Scan.ScanLine());
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
            donjon.placerObjet(6, 15, PreparerTour.creerObjetDepuisChoix(0,0));
            donjon.placerObjet(8, 0, PreparerTour.creerObjetDepuisChoix(0,1));
            donjon.placerObjet(3, 5, PreparerTour.creerObjetDepuisChoix(0,3));
            donjon.placerObjet(20, 6, PreparerTour.creerObjetDepuisChoix(1,2));
            donjon.placerObjet(16, 15, PreparerTour.creerObjetDepuisChoix(1,1));
            donjon.placerObjet(9, 3, PreparerTour.creerObjetDepuisChoix(1,5));

            return donjon;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
