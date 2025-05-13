package Entites.Personnages.Classes;

import Objets.Arme.ArmeCourante;
import Objets.Arme.ArmeDistance;
import Objets.Arme.ArmeGuerre;
import Objets.Armure.ArmureLegere;
import Objets.Armure.ArmureLourde;
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
        equipement.add(new ArmureLourde("Cotte de mailles", 11));
        equipement.add(new ArmeGuerre("Épée longue", 1));
        equipement.add(new ArmeDistance("Arbalète légère", 16));
        return equipement;
    }
}
