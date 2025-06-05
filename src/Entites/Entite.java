package Entites;

import Donjons.Coordonnee;
import Donjons.Contenu;

public abstract class Entite implements Contenu {
    public Coordonnee m_coordonnee;

    public Coordonnee getCoordonnee(){
        return m_coordonnee;
    }

    public void setCoordonnee(int x, int y){
        m_coordonnee = new Coordonnee(x, y);
    }
}
