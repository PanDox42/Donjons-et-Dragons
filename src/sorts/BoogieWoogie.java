package sorts;

import addon.Scan;
import donjons.Coordonnee;
import entites.Entite;

import java.util.*;
import donjons.*;
import entites.personnages.Personnage;

public class BoogieWoogie implements Sort {
    public static void lancer(Personnage lanceur, Donjon donjon) {
        List<Entite> entites = new ArrayList<>();
        entites.addAll(donjon.getJoueur());
        entites.addAll(donjon.getMonstres());

        System.out.println("Choisissez la première entité à échanger :");
        for (int i = 0; i < entites.size(); i++) {
            Coordonnee c = entites.get(i).getCoordonnee();
            System.out.println("[" + i + "] " + entites.get(i).getNom() + " - Position: " + c.getX() + ":" + c.getY());
        }

        try {
            int choix1 = Integer.parseInt(Scan.ScanLine());
            Entite e1 = entites.get(choix1);

            System.out.println("Choisissez la seconde entité à échanger :");
            int choix2 = Integer.parseInt(Scan.ScanLine());
            Entite e2 = entites.get(choix2);

            Coordonnee pos1 = e1.getCoordonnee();
            Coordonnee pos2 = e2.getCoordonnee();

            // Retirer temporairement les deux entités de la carte pour éviter les conflits
            donjon.retirerEntite(pos1);
            donjon.retirerEntite(pos2);

            // Échanger les positions
            e1.setCoordonnee(pos2);
            e2.setCoordonnee(pos1);

            // Réinsérer aux nouvelles positions
            donjon.placerEntite(pos2, e1);
            donjon.placerEntite(pos1, e2);

            System.out.println("Boogie Woogie ! " + e1.getNom() + " et " + e2.getNom() + " ont échangé leurs places.");
        } catch (Exception e) {
            System.out.println("Erreur lors du lancement de Boogie Woogie.");
        }
    }

}