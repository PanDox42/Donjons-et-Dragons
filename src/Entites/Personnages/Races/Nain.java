package Entites.Personnages.Races;

public class Nain implements Race{

    @Override
    public String getRace() {
        return "Nain";
    }

    @Override
    public int getPvAugmente() {
        return 0;
    }

    @Override
    public int getForceAugmentee() {
        return 6;
    }

    @Override
    public int getDexteriteAugmentee() {
        return 0;
    }

    @Override
    public int getVitesseAugmentee() {
        return 0;
    }


}
