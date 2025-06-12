package objets.armes;

import addon.De;
import objets.Objet;

public abstract class Arme extends Objet {
    private int m_porte;
    private De m_deAttaque;
    private int m_bonusDegats = 0;

    protected Arme(String nom, int porte, De deAttaque){
        super(nom, "arme");
        m_porte = porte;
        m_deAttaque = deAttaque;
    }

    public int getPorte(){
        return m_porte;
    }

    public int getDegat(){
        return m_deAttaque.lancer() + m_bonusDegats;
    }

    public De getDeAttaque(){
        return m_deAttaque;
    }

    public static Arme sansArme(){
        return new Poing();
    }

    public void ajouterBonus(int bonusAttaque, int bonusDegats) {
        // bonusAttaque ignoré ici, à utiliser dans le système d'attaque si besoin
        m_bonusDegats += bonusDegats;
    }
}
