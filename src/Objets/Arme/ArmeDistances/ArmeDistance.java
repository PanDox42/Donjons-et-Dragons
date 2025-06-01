package Objets.Arme.ArmeDistances;

import Addon.De;
import Objets.Arme.Arme;

public abstract class ArmeDistance extends Arme {
    protected ArmeDistance(String nom, int porte, De deAttaque) {
        super(nom, porte, deAttaque);
    }
}

