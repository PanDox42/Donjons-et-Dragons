package Objets;

import Donjons.Coordonnee;
import Donjons.Contenu;

public abstract class Objet implements Contenu {
    private String m_nom;
    public Coordonnee m_coordonnee;

    protected Objet(String nom) {
        m_nom = nom;
    }

    public String getNom() {
        return m_nom;
    }

    // Servant à connaitre les objets que le joueur possède en main
    public boolean estEquipe() {
        return false;
    }

    @Override
    public String getSymbole() {
        return " * ";
    }
}
