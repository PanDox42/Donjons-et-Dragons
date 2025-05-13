package Entites.Personnages.Classes;

import Objets.Objet;
import Objets.Arme.*;
import Objets.Armure.*;

import java.util.ArrayList;

public class Clerc implements Classe {
    private int pvDepart = 16;

    @Override
    public int getPvDepart(){
        return pvDepart;
    }

    @Override
    public ArrayList<Objet> getEquipementDepart() {
        ArrayList<Objet> equipement = new ArrayList<>();
        equipement.add(new ArmeCourante("Masse d'armes", 1));
        equipement.add(new ArmureLegere("Armure d'écailles", 9));
        equipement.add(new ArmeDistance("Arbalète légère", 16));
        return equipement;
    }
}
