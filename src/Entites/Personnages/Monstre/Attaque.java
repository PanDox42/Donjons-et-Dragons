package Entites.Personnages.Monstre;

import Addon.De;
import Addon.Scan;

public class Attaque {
    private int m_portee;
    private De m_degat;

    public Attaque(int portee, De degat){
        m_portee = portee;
        m_degat = degat;
    }

    public int genererAttaque(){
        return m_degat.lancer();
    }

    public static Attaque creerAttaque() {
        int portee = 0;
        De degat = null;

        // Saisie de la portée
        while (true) {
            try {
                System.out.println("Veuillez saisir le dé pour la valeur de portée de l'attaque (au format 3d3 par exemple) :");
                portee = De.convertirStringDe(Scan.ScanLine()).lancer();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Saisie des dégâts
        while (true) {
            try {
                System.out.println("Veuillez saisir le dé pour les dégats de l'attaque (au format 3d3 par exemple) :");
                degat = De.convertirStringDe(Scan.ScanLine());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return new Attaque(portee, degat);
    }


    public De getDeAttaque(){
        return m_degat;
    }

    public De getDegat() {
        return m_degat;
    }

    public int getPorte() {
        return m_portee;
    }
}
