package objets.armes.armeCourantes;

import addon.De;
import objets.armes.Arme;

public abstract class ArmeCourante extends Arme {
    protected ArmeCourante(String nom, int porte, De deAttaque) {
        super(nom, porte, deAttaque);
    }
}
