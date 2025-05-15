package Objets.Armure;

import Objets.Objet;

// Type concret de Objet
public class Armure implements Objet {
    private String m_nom;
    private int m_classe;

    public Armure(String nom, int classe){
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
