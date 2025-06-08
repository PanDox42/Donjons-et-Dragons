package Objets;

import Donjons.Contenu;
import Donjons.Coordonnee;

public abstract class Objet implements Contenu {
    private String m_nom;
    private Coordonnee m_coordonnee;
    private boolean m_equipe;
    private String m_type;

    protected Objet(String nom, String type) {
        m_nom = nom;
        m_type = type;
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

    public String getType() {
        return m_type;
    }
}
