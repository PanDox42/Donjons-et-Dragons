package Sorts;

import Addon.Scan;
import Donjons.Coordonnee;
import Entites.Entite;

import java.util.*;
import Donjons.*;
import Entites.Personnages.Personnage;

public class BoogieWoogie implements Sort {
    public static void lancer(Personnage lanceur, Donjon donjon) {
        List<Entite> entites = new ArrayList<>();
        entites.addAll(donjon.getJoueur());
        entites.addAll(donjon.getMonstres());

        System.out.println("Choisissez la première entité à échanger :");
        for (int i = 0; i < entites.size(); i++) {
            System.out.println("[" + i + "] " + entites.get(i).getNom() + " - Position: " + entites.get(i).getCoordonnee());
        }

        try {
            int choix1 = Integer.parseInt(Scan.ScanLine());
            Entite e1 = entites.get(choix1);

            System.out.println("Choisissez la seconde entité à échanger :");
            int choix2 = Integer.parseInt(Scan.ScanLine());
            Entite e2 = entites.get(choix2);

            Coordonnee tmp = e1.getCoordonnee();
            donjon.deplacerEntite(e2.getCoordonnee(), e1);
            donjon.deplacerEntite(tmp, e2);

            System.out.println("Boogie Woogie ! " + e1.getNom() + " et " + e2.getNom() + " ont échangé leurs places.");
        } catch (Exception e) {
            System.out.println("Erreur lors du lancement de Boogie Woogie.");
        }
    }
}