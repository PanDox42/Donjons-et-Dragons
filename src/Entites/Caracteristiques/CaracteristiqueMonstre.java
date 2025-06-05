package Entites.Caracteristiques;

import Addon.De;
import Addon.Scan;
import Entites.Personnages.Monstre.Monstre;

import static java.lang.Integer.parseInt;

public class CaracteristiqueMonstre extends Caracteristique{
    private int m_classeArmure;

    public CaracteristiqueMonstre(int pv, int force, int dexterite, int classeArmure, int vitesse, int initiative) {
        super(pv, force, dexterite, vitesse, initiative);
        m_classeArmure = classeArmure;
    }

    // Méthode getteur uniquement pour la propriété classeArmure uniquement présente chez les monstres
    public int getClasseArmure(){
        return m_classeArmure;
    }

    // Méthode de changement pour la propriété classeArmure uniquement présente chez les monstres
    public void changerClasseArmure(int changement){
        m_classeArmure += changement;
    }


    public static CaracteristiqueMonstre creerCaracteristique() {
        int vie = demanderEntier("Définition de la vie :");
        int force = demanderEntier("Définition de la force :");
        int dexterite = demanderEntier("Définition de la dextérité :");
        int classeArmure = demanderEntier("Définition de la classe d'armure :");
        int vitesse = demanderEntier("Définition de la vitesse :");
        int initiative = demanderEntier("Définition de l’initiative :");

        return new CaracteristiqueMonstre(vie, force, dexterite, classeArmure, vitesse, initiative);
    }

    private static int demanderEntier(String message) {
        while (true) {
            try {
                System.out.println(message);
                String ligne = Scan.ScanLine(); // suppose que ScanLine() lit une ligne depuis l'entrée standard
                return Integer.parseInt(ligne);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : veuillez entrer un nombre entier valide.");
            } catch (Exception e) {
                System.out.println("Erreur inattendue : " + e.getMessage());
            }
        }
    }
}
