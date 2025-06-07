package Entites.Caracteristiques;

import Addon.De;
import Addon.Scan;
import Entites.Personnages.Monstre.Attaque;
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
        int vie = 0, force = 0, dexterite = 0, classeArmure = 0, vitesse = 0, initiative = 0;

        while (true) {
            try {
                System.out.println("Veuillez saisir le dé pour la valeur de vie (au format 3d3 par exemple) :");
                vie = De.convertirStringDe(Scan.ScanLine()).lancer();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Veuillez saisir le dé pour la valeur de force (au format 3d3 par exemple) :");
                force = De.convertirStringDe(Scan.ScanLine()).lancer();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Veuillez saisir le dé pour la valeur de dexterite (au format 3d3 par exemple) :");
                dexterite = De.convertirStringDe(Scan.ScanLine()).lancer();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Veuillez saisir le dé pour la valeur de la classe d'armure (au format 3d3 par exemple) :");
                classeArmure = De.convertirStringDe(Scan.ScanLine()).lancer();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Veuillez saisir le dé pour la valeur de vitesse (au format 3d3 par exemple) :");
                vitesse = De.convertirStringDe(Scan.ScanLine()).lancer();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Definition de votre initiative avec un dé '1d20'");
        initiative = new De(1,20).lancer();

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
