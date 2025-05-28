package Entites.Personnages.Monstre;

import Addon.De;
import Addon.Scan;

import static java.lang.Integer.parseInt;

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
        int portee;
        De de;
        System.out.println("Veuillez saisir la portée de l'attaque");
        portee = parseInt(Scan.ScanLine());

        System.out.println("Veuillez saisir le nombre de dé et le nombre de face qui représentera l'attaque (de la manière 3d4");
        String tmp = Scan.ScanLine();

        de = new De(parseInt(tmp.split("d")[0]), parseInt(tmp.split("d")[1]));

        return new Attaque(portee, de);
    }

    public De getDeAttaque(){
        return m_degat;
    }
}
