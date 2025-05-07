package Personnages.Caracteristiques;

public class BaseCaracteristique implements Caracteristique{
    private int m_caracteristique;

    protected BaseCaracteristique(int caracteristique){
        m_caracteristique = caracteristique;
    }

    @Override
    public int getCaracteristique() {
        return 0;
    }

    @Override
    public void diminuerCaracteristique() {
        m_caracteristique -= 1;
    }

    @Override
    public void augmenterCaracteristique() {
        m_caracteristique += 1;
    }
}
