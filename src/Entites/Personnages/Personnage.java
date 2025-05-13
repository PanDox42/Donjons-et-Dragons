package Entites.Personnages;
import Entites.Personnages.Classes.Classe;
import Entites.Personnages.Races.Race;
import Objets.Arme.Arme;
import Objets.Arme.ArmeDistance;
import Objets.Armure.Armure;
import Objets.Armure.ArmureLourde;
import Objets.Objet;
import Entites.Caracteristiques.Caracteristique;
import Objets.Arme.*;

import java.util.ArrayList;

public class Personnage {
    private String m_nom;
    private Race m_race;
    private Classe m_classe;
    private Caracteristique m_caracteristiques;
    private ArrayList<Objet> m_inventaire;

    public Personnage(String nom, Race race, Classe classe){
        m_nom = nom;
        m_race = race;
        m_classe = classe;

        m_caracteristiques = new Caracteristique(classe.getPvDepart(),2,3,4,5); // Rudimentaire encore

        m_inventaire = m_classe.getEquipementDepart();
    }

    public String getNom(){
        return m_nom;
    }

    public String afficherInventaire(){
        String inventaire = "";

        for (int i = 0; i < m_inventaire.size(); i++){
            inventaire += " | " + m_inventaire.get(i).getNom() ;
        }

        return inventaire;
    }

    public void afficherSituation(){

        System.out.println("\n" +
                m_nom + "\n" + // tabulation de 2 espaces à chaque fois
                "  Vie : " + m_caracteristiques.getPv() + "/" + m_classe.getPvDepart() + "\n" +
                "  Armure : Pas encore codé" + "\n" +
                "  Arme : Pas encore codé" + "\n" +
                "  Inventaire : " + "[" + m_inventaire.size() + "]" + afficherInventaire() + "  --> (c'est par rapport à la classe de King Kong, j'ai tout codé tu peux essayer de changer sa classe pour voir, le stuff sera different.\n" +
                "  Force : " + m_caracteristiques.getForce() + "\n" +
                "  Dextérité : " + m_caracteristiques.getDexterite() + "\n" +
                "  Vitesse : " + m_caracteristiques.getVitesse() + "\n");
    }
}