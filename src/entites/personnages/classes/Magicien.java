package entites.personnages.classes;

import objets.armes.armeCourantes.Baton;
import objets.armes.armeDistances.Fronde;
import objets.Objet;

import java.util.ArrayList;

public class Magicien implements Classe {
    private int pvDepart = 12;

    @Override
    public int getPvDepart() {
        return pvDepart;
    }

    @Override
    public ArrayList<Objet> getEquipementDepart() {
        ArrayList<Objet> equipement = new ArrayList<>();
        equipement.add(new Baton());
        equipement.add(new Fronde());
        return equipement;
    }
}
