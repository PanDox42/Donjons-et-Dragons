package Personnages.Caracteristiques;

public interface Caracteristique {
    int getCaracteristique();
    void diminuerCaracteristique(int diminution);
    void augmenterCaracteristique(int augmentation);
}
