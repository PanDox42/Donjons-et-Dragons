package Sorts;

import Addon.De;
import Addon.Scan;
import Donjons.Donjon;
import Entites.Personnages.Personnage;

import java.util.*;

public class Guerison implements Sort {
    public static void lancer(Personnage lanceur, Donjon donjon) {
        List<Personnage> personnages = donjon.getJoueur();
        System.out.println("Choisissez le personnage à soigner :");

        for (int i = 0; i < personnages.size(); i++) {
            System.out.println("[" + i + "] " + personnages.get(i).getNom() + " - " + personnages.get(i).getCaracteristiques().getPv() + " PV actuels");
        }

        try {
            int choix = Integer.parseInt(Scan.ScanLine());
            Personnage cible = personnages.get(choix);
            int soin = De.lancer(1, 10);
            int pvInitiaux = cible.getCaracteristiques().getPv();
            int pvActuels = cible.getCaracteristiques().getPv();
            cible.getCaracteristiques().changerPv(soin);
            System.out.println(lanceur.getNom() + " a soigné " + cible.getNom() + " de " + soin);
            cible.afficherSituation();
        } catch (Exception e) {
            System.out.println("Entrée invalide pour le soin.");
        }
    }
}