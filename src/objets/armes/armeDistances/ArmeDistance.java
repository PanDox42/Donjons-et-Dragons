package objets.armes.armeDistances;

import addon.De;
import objets.armes.Arme;

public abstract class ArmeDistance extends Arme {
    protected ArmeDistance(String nom, int porte, De deAttaque) {
        super(nom, porte, deAttaque);
    }
}

