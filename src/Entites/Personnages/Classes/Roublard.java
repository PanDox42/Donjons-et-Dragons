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
        equipement.add(new ArmeGuerre("Rapière", 1, new De(1,8)));
        equipement.add(new ArmeDistance("Arc court", 16, new De(1,6)));
        return equipement;
    }
}
