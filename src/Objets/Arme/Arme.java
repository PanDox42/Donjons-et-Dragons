package Objets.Arme;

import Addon.De;
import Objets.Objet;

public abstract class Arme extends Objet {
    private int m_porte;
    private De m_deAttaque;

    protected Arme(String nom, int porte, De deAttaque){
        super(nom, "arme");
        m_porte = porte;
        m_deAttaque = deAttaque;
    }

    public int getPorte(){
        return m_porte;
    }

    public int getDegat(){
        return m_deAttaque.lancer();
    }

    public De getDeAttaque(){
        return m_deAttaque;
    }

    public static Arme sansArme(){
        return new Poing();
    }
}
