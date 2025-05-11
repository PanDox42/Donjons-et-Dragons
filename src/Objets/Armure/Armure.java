package Objets.Armure;

import Objets.Objet;

public class Armure implements Objet {
    private String m_nom;
    private int m_classe;

    protected Armure(String nom, int classe){
        m_nom = nom;
        m_classe = classe;
    }

    public int getClasse(){
        return m_classe;
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
