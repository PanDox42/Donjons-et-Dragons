package Donjons;

import Entites.Personnages.MaitreJeu;

import static java.lang.Integer.parseInt;

public class Jouer {
    public static void jouer() {
        MaitreJeu mdj = new MaitreJeu();
        Donjon donjon = mdj.creerDonjon();
        donjon.placerObstaclesAvecConfirmation();
        donjon.placerObjetsAvecConfirmation();
        donjon.placerMonstresAvecConfirmation();
        donjon.placerJoueursAvecConfirmation();
    }
}
