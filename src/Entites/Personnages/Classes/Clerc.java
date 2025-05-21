package Entites.Personnages.Classes;

import Objets.Arme.ArmeCourantes.ArmeCourante;
import Objets.Arme.ArmeCourantes.MasseArme;
import Objets.Arme.ArmeDistances.ArbaleteLegere;
import Objets.Arme.ArmeDistances.ArmeDistance;
import Objets.Armure.ArmureLegeres.ArmureEcailles;
import Objets.Armure.ArmureLegeres.ArmureLegere;
import Objets.Objet;

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
        equipement.add(new MasseArme());
        equipement.add(new ArmureEcailles());
        equipement.add(new ArbaleteLegere());
        return equipement;
    }
}
