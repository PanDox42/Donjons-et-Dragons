package Entites.Personnages.Classes;

import Objets.Arme.ArmeCourantes.ArmeCourante;
import Objets.Arme.ArmeCourantes.Baton;
import Objets.Arme.ArmeDistances.ArmeDistance;
import Objets.Arme.ArmeDistances.Fronde;
import Objets.Objet;

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
