package Objets.Arme;

import Des.De;
import Objets.Objet;

public class Arme implements Objet {
    private String m_nom;
    private int m_porte;
    private De m_deAttaque;

    public Arme(String nom, int porte, De deAttaque){
        m_nom = nom;
        m_porte = porte;
        m_deAttaque = deAttaque;
    }

    public int getPorte(){
        return m_porte;
    }

    public int getDegat(){
        return m_deAttaque.lancer();
    }

    public De getDeAttque(){
        return m_deAttaque;
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
