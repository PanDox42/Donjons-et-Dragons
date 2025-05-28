package Objets.Armure;

import Objets.Arme.Arme;
import Objets.Arme.Poing;
import Objets.Objet;

public abstract class Armure implements Objet {
    private final String m_nom;
    private final int m_classeArmure;

    protected Armure(String nom, int classeArmure) {
        m_nom = nom;
        m_classeArmure = classeArmure;
    }

    public String getNom() {
        return m_nom;
    }

    public int getClasse() {
        return m_classeArmure;
    }

    public boolean estEquipe() {
        return false;
    }

    public static Armure sansArmure(){
        return new Nu();
    }
}