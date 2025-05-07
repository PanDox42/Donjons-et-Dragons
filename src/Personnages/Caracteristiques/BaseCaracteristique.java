package Personnages.Caracteristiques;

public class BaseCaracteristique implements Caracteristique{
    private int m_caracteristique;

    protected BaseCaracteristique(int caracteristique){
        m_caracteristique = caracteristique;
    }

    @Override
    public int getCaracteristique() {
        return m_caracteristique;
    }

    @Override
    public void diminuerCaracteristique(int diminution) {
        m_caracteristique -= diminution;
    }

    @Override
    public void augmenterCaracteristique(int augmentation) {
        m_caracteristique += augmentation;
    }
}
