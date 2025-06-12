package entites.personnages.races;

public class Humain implements Race{

    @Override
    public String getRace() {
        return "Humain";
    }

    @Override
    public int getPvAugmente() {
        return 2;
    }

    @Override
    public int getForceAugmentee() {
        return 2;
    }

    @Override
    public int getDexteriteAugmentee() {
        return 2;
    }

    @Override
    public int getVitesseAugmentee() {
        return 2;
    }

}
