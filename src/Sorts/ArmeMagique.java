package Sorts;

import Addon.Scan;
import Donjons.Donjon;
import Entites.Personnages.Personnage;
import Objets.Arme.Arme;

import java.util.*;

public class ArmeMagique implements Sort {
    public static void lancer(Personnage lanceur, Donjon donjon) {
        List<Personnage> personnages = donjon.getJoueur();
        System.out.println("Choisissez un personnage détenant l'arme à améliorer :");
        for (int i = 0; i < personnages.size(); i++) {
            System.out.println("[" + i + "] " + personnages.get(i).getNom());
        }

        try {
            int choixPerso = Integer.parseInt(Scan.ScanLine());
            Personnage cible = personnages.get(choixPerso);
            List<Arme> armes = cible.getArme();

            if (armes.isEmpty()) {
                System.out.println(cible.getNom() + " ne possède aucune arme.");
                return;
            }

            System.out.println("Choisissez une arme à améliorer :");
            for (int i = 0; i < armes.size(); i++) {
                System.out.println("[" + i + "] " + armes.get(i).getNom());
            }

            int choixArme = Integer.parseInt(Scan.ScanLine());
            Arme arme = armes.get(choixArme);

            arme.ajouterBonus(1, 1);
            System.out.println("L'arme de " + cible.getNom() + " a été enchantée ! (+1 attaque, +1 dégâts)");

        } catch (Exception e) {
            System.out.println("Erreur lors de l'enchantement de l'arme.");
        }
    }
}
