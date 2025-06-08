package Donjons;

public class Obstacle implements Contenu {
    private Coordonnee m_coordonnee;
    public Obstacle() {

    }

    public void setCoordonnee(Coordonnee coordonnee){
        m_coordonnee = coordonnee;
    }

    public Coordonnee getCoordonnee(){
        return m_coordonnee;
    }

    @Override
    public String getSymbole() {
        return "[ ]";
    }
}
