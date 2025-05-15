package Entites.Personnages.Classes;

import Objets.Arme.ArmeCourante;
import Objets.Arme.ArmeDistance;
import Objets.Arme.ArmeGuerre;
import Objets.Armure.ArmureLegere;
import Objets.Armure.ArmureLourde;
import Objets.Objet;
import Des.De;

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
        equipement.add(ArmureLourde.creerCotteDeMaille());
        equipement.add(ArmeGuerre.creerEpeeLongue());
        equipement.add(ArmeDistance.creerArbaleteLegere());
        return equipement;
    }
}
