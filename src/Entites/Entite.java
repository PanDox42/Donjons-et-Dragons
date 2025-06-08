package Entites;

import Donjons.Contenu;
import Donjons.Coordonnee;

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
}
