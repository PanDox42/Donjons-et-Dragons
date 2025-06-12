package entites.personnages.classes;

import objets.armes.armeCourantes.MasseArme;
import objets.armes.armeDistances.ArbaleteLegere;
import objets.armures.armureLegeres.ArmureEcailles;
import objets.Objet;

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
