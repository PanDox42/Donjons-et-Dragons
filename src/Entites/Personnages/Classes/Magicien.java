package Entites.Personnages.Classes;

import Objets.Arme.ArmeCourante;
import Objets.Arme.ArmeDistance;
import Objets.Armure.ArmureLegere;
import Objets.Objet;
import Des.De;

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
        equipement.add(ArmeCourante.creerBaton());
        equipement.add(ArmeDistance.creerFronde());
        return equipement;
    }
}
