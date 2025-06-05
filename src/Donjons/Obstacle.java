package Donjons;

import Donjons.Contenu;

public class Obstacle implements Contenu {
    public Coordonnee m_coordonnee;
    public Obstacle() {

    }

    @Override
    public String getSymbole() {
        return "[ ]";
    }
}
