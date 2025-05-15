package Entites.Personnages.Classes;

import Des.De;
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
        equipement.add(new ArmeCourante("Masse d'armes", 1, new De(1,6)));
        equipement.add(new ArmureLegere("Armure d'écailles", 9));
        equipement.add(new ArmeDistance("Arbalète légère", 16, new De(1,8)));
        return equipement;
    }
}
