package Objets.Armure;

import Objets.Objet;

public abstract class Armure extends Objet {
    private final int m_classeArmure;

    protected Armure(String nom, int classeArmure) {
        super(nom);
        m_classeArmure = classeArmure;
    }

    public int getClasse() {
        return m_classeArmure;
    }

    public static Armure sansArmure(){
        return new Nu();
    }
}