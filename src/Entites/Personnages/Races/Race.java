package Entites.Personnages.Races;

public interface Race {
    String getRace();

    // Les methodes qui renvoient les bonus des caracteristiques que donne les races
    int getPvAugmente();
    int getForceAugmentee();
    int getDexteriteAugmentee();
    int getVitesseAugmentee();
    int getInitiativeAugmentee();

}
