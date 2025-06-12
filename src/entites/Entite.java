package entites;

import addon.Scan;
import donjons.Contenu;
import donjons.Coordonnee;
import donjons.Donjon;
import entites.caracteristiques.Caracteristique;

public abstract class Entite implements Contenu {
    private Coordonnee m_coordonnee;

    public Coordonnee getCoordonnee(){
        return m_coordonnee;
    }

    public void setCoordonnee(int x, int y){
        m_coordonnee = new Coordonnee(x, y);
    }

    public void setCoordonnee(Coordonnee coordonnee){
        m_coordonnee = coordonnee;
    }

    public abstract int getInitiative();

    public abstract int getPortee();

    public abstract String getType();

    public abstract String getNom();

    public abstract String getSymbole();

    public abstract Caracteristique getCaracteristiques();

    public void seDeplacer(Donjon donjon) {
        while(true) {
            try {
                System.out.println("Où voulez vous vous déplacer ? (indiquez les coodronnées comme ça : A:5)");
                int[] XY = Donjon.convertirCoordonnnee(Scan.ScanLine());
                Coordonnee newCoordonnee = new Coordonnee(XY[0], XY[1]);
                donjon.deplacerEntite(newCoordonnee, this);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
