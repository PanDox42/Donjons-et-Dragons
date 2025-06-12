package objets.armes.armeGuerres;

import addon.De;
import objets.armes.Arme;

public abstract class ArmeGuerre extends Arme {
    protected ArmeGuerre(String nom, int porte, De deAttaque) {
        super(nom, porte, deAttaque);
    }
}
