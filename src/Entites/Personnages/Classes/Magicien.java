package Entites.Personnages.Classes;

import Objets.Arme.ArmeCourante;
import Objets.Arme.ArmeDistance;
import Objets.Armure.ArmureLegere;
import Objets.Objet;

import java.util.ArrayList;

public class Magicien implements Classe {
    int pvDepart = 12;

    @Override
    public int getPvDepart() {
        return pvDepart;
    }

    @Override
    public ArrayList<Objet> getEquipementDepart() {
        ArrayList<Objet> equipement = new ArrayList<>();
        equipement.add(new ArmeCourante("Bâton", 1));
        equipement.add(new ArmeDistance("Fronde", 6));
        return equipement;
    }
}
