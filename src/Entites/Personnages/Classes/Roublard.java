package Entites.Personnages.Classes;

import Objets.Arme.ArmeCourante;
import Objets.Arme.ArmeDistance;
import Objets.Arme.ArmeGuerre;
import Objets.Armure.ArmureLegere;
import Objets.Objet;
import Des.De;


import java.util.ArrayList;

public class Roublard implements Classe {
    private int pvDepart = 16;

    @Override
    public int getPvDepart() {
        return pvDepart;
    }

    @Override
    public ArrayList<Objet> getEquipementDepart() {
        ArrayList<Objet> equipement = new ArrayList<>();
        equipement.add(ArmeGuerre.creerRapiere());
        equipement.add(ArmeDistance.creerArcCourt());
        return equipement;
    }
}
