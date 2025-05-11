package Objets.Arme;

import Objets.Objet;

public class Arme implements Objet {
    private String m_nom;
    private int m_porte;

    protected Arme(String nom, int porte){
        m_nom = nom;
        m_porte = porte;
    }

    public int getPorte(){
        return m_porte;
    }

    @Override
    public String getNom() {
        return m_nom;
    }

    @Override
    public boolean estEquipe() {
        return false;
    }
}
