package Entites.Personnages.Classes;

import Objets.Arme.ArmeDistances.ArbaleteLegere;
import Objets.Arme.ArmeDistances.ArmeDistance;
import Objets.Arme.ArmeGuerres.ArmeGuerre;
import Objets.Arme.ArmeGuerres.EpeeLongue;
import Objets.Armure.ArmureLourdes.ArmureLourde;
import Objets.Armure.ArmureLourdes.CotteMailles;
import Objets.Objet;

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
