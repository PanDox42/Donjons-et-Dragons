package entites.personnages.races;

public class Halfelin implements Race{

    @Override
    public String getRace() {
        return "Halfelin";
    }

    @Override
    public int getPvAugmente() {
        return 0;
    }

    @Override
    public int getForceAugmentee() {
        return 0;
    }

    @Override
    public int getDexteriteAugmentee() {
        return 4;
    }

    @Override
    public int getVitesseAugmentee() {
        return 2;
    }

}
