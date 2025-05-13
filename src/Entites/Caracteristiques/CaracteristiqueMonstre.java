package Entites.Caracteristiques;

public class CaracteristiqueMonstre extends Caracteristique{
    private int m_classeArmure;

    public CaracteristiqueMonstre(int pv, int force, int dexterite, int vitesse, int initiative, int classeArmure) {
        super(pv, force, dexterite, vitesse, initiative);
        m_classeArmure = classeArmure;
    }

    // Méthode getteur uniquement pour la propriété classeArmure uniquement présente chez les monstres
    public int getClasseArmure(){
        return m_classeArmure;
    }

    // Méthode de changement pour la propriété classeArmure uniquement présente chez les monstres
    public void changerClasseArmure(int changement){
        m_classeArmure += changement;
    }
}
