package entites.personnages.classes;

import objets.Objet;

import java.util.ArrayList;

public interface Classe {

    // Les Pv attribués au debut de la partie sont reliés à la classe choisie
    int getPvDepart();

    // Le stuff de base au début de la partie est également relié à la classe choisie
    ArrayList<Objet> getEquipementDepart();
}
