package entites.personnages.classes;

import objets.armes.armeDistances.ArbaleteLegere;
import objets.armes.armeGuerres.EpeeLongue;
import objets.armures.armureLourdes.CotteMailles;
import objets.Objet;

import java.util.ArrayList;

public class Guerrier implements Classe {
    private int pvDepart = 20;

    @Override
    public int getPvDepart() {
        return pvDepart;
    }

    @Override
    public ArrayList<Objet> getEquipementDepart() {
        ArrayList<Objet> equipement = new ArrayList<>();
        equipement.add(new CotteMailles());
        equipement.add(new EpeeLongue());
        equipement.add(new ArbaleteLegere());
        return equipement;
    }
}
