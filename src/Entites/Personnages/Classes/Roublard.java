package Entites.Personnages.Classes;

import Objets.Arme.ArmeDistances.ArcCourt;
import Objets.Arme.ArmeGuerres.Rapiere;
import Objets.Objet;

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
        equipement.add(new Rapiere());
        equipement.add(new ArcCourt());
        return equipement;
    }
}
