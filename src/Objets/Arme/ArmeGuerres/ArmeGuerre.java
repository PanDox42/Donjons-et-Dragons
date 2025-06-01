package Objets.Arme.ArmeGuerres;

import Addon.De;
import Objets.Arme.Arme;

public abstract class ArmeGuerre extends Arme {
    protected ArmeGuerre(String nom, int porte, De deAttaque) {
        super(nom, porte, deAttaque);
    }
}
