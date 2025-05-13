package Entites.Caracteristiques;

public class Caracteristique {
    private int m_pv;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;

    public Caracteristique(int pv, int force, int dexterite, int vitesse, int initiative){
        m_pv = pv;
        m_force = force;
        m_dexterite = dexterite;
        m_vitesse = vitesse;
        m_initiative = initiative;
    }

    // Les méthodes getteurs
    public int getPv(){
        return m_pv;
    }

    public int getForce(){
        return m_force;
    }

    public int getDexterite(){
        return m_dexterite;
    }

    public int getVitesse(){
        return m_vitesse;
    }

    public int getInitiative(){
        return m_initiative;
    }


    // Les méthodes de modification des propriétés
    public void changerPv(int changement){
        m_pv += changement;
    }

    public void changerForce(int changement){
        m_force += changement;
    }

    public void changerDexterite(int changement){
        m_dexterite += changement;
    }

    public void changerVitesse(int changement){
        m_vitesse += changement;
    }

    public void changerInitiative(int changement){
        m_initiative += changement;
    }
}
