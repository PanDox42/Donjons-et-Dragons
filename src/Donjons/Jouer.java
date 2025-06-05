package Donjons;

import Addon.Scan;
import Entites.Personnages.MaitreJeu;

import static java.lang.Integer.parseInt;

public class Jouer {
    public static void jouer() {
        Donjon donjon = null;
        while (true) {
            try {
                System.out.println("Maître du Jeu - Souhaitez-vous créer le donjon manuellement ou choisir celui par défault ?");
                System.out.println("1 - Manuellement");
                System.out.println("2 - Par Default");

                String saisie = Scan.ScanLine().trim();
                int choix = Integer.parseInt(saisie);

                if (choix == 1) {
                    donjon = preparerDonjonManuellement();
                } else if (choix == 2) {
                    donjon = preparerDonjonDefault();
                } else {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre (1 ou 2).");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        donjon.afficherCarte();
        donjon.placerJoueursAvecConfirmation();
        donjon.modifierContexte(donjon.raconterTourMdj());
    }

    private static Donjon preparerDonjonManuellement() {
        MaitreJeu mdj = new MaitreJeu();
        Donjon donjon = mdj.creerDonjon();
        donjon.placerMonstresAvecConfirmation();
        donjon.placerObjetsAvecConfirmation();
        donjon.placerObstaclesAvecConfirmation();
        return donjon;
    }

    private static Donjon preparerDonjonDefault() {
        MaitreJeu mdj = new MaitreJeu();
        Donjon donjon = mdj.creerDonjonParDefaut();
        return donjon;
    }
}
