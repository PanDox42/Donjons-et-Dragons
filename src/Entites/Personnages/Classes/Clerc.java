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
        equipement.add(ArmeCourante.creerMasseArmes());
        equipement.add(ArmureLegere.creerArmureEcailles());
        equipement.add(ArmeDistance.creerArbaleteLegere());
        return equipement;
    }
}
