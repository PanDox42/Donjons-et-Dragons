package Entites;

public abstract class Entite {
    private Coordonnee m_coordonnee;

    public Coordonnee getCoordonnee(){
        return m_coordonnee;
    }

    public void setCoordonnee(int x, int y){
        m_coordonnee = new Coordonnee(x, y);
    }
}
