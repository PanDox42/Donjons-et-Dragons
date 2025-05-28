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


    public static CaracteristiqueMonstre creerCaracteristique(Monstre monstre){

        // Definition de la vie
        System.out.println("Definition de la vie :");
        int vie = parseInt(Scan.ScanLine());

        // Definition de la force
        System.out.println("Definition de la force :");
        int force = parseInt(Scan.ScanLine());


        // Definition de la déxterité
        System.out.println("Definition de la déxterité :");
        int dexterite = parseInt(Scan.ScanLine());


        // Definition de la classe d'armure
        System.out.println("Definition de la classe d'armure :");
        int classeArmure = parseInt(Scan.ScanLine());


        // Definition de la vitesse
        System.out.println("Definition de la vitesse :");
        int vitesse = parseInt(Scan.ScanLine());


        // Definition de l'initiative
        System.out.println("Definition de l'initiative :");
        int initiative = parseInt(Scan.ScanLine());

        return new CaracteristiqueMonstre(vie, force, dexterite, classeArmure, vitesse, initiative);
    }
}
