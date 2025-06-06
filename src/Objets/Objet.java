package Objets;

import Donjons.Coordonnee;
import Donjons.Contenu;

public abstract class Objet implements Contenu {
    private String m_nom;
    private Coordonnee m_coordonnee;
    private boolean m_equipe;

    protected Objet(String nom) {
        m_nom = nom;
    }

    public String getNom() {
        return m_nom;
    }

    // Servant à connaitre les objets que le joueur possède en main
    public boolean estEquipe() {
        return m_equipe;
    }

    @Override
    public String getSymbole() {
        return " * ";
    }

    public Coordonnee getCoordonnee() {
        return m_coordonnee;
    }

    public void setCoordonnee(Coordonnee c) {
        m_coordonnee = c;
    }

    public void setEquipe() {
        m_equipe = true;
    }

    public void setNonEquipe() {
        m_equipe = false;
    }
}
