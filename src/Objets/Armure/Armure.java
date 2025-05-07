package Objets.Armure;

import Objets.Objet;

public class Armure implements Objet {
    String m_nom;
    int m_classe;

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
}
