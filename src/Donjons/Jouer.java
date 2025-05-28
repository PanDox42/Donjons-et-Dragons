package Donjons;

import Addon.Scan;
import Entites.Personnages.MaitreJeu;
import Objets.Objet;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Jouer {
    public static void jouer() {
        MaitreJeu mdj = new MaitreJeu();
        Donjon donjon = mdj.creerDonjon();
        PreparerTour.creerObstacle(donjon);
        donjon.placerObjet(PreparerTour.creerObjet());
    }
}
