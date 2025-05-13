package Entites.Personnages;
import Des.De;
import Entites.Monstres.Monstre;
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
    private Arme m_armeEquipe;
    private Armure m_armureEquipe;


    public Personnage(String nom, Race race, Classe classe){
        m_nom = nom;
        m_race = race;
        m_classe = classe;

        m_caracteristiques = new Caracteristique(classe.getPvDepart(),2,3,4,5); // Rudimentaire encore

        m_inventaire = m_classe.getEquipementDepart();

        // On initialise l'arme de base par un poing qui fait aucun degat
        m_armeEquipe = new Arme("Poing", 0, new De(0,0));

        // On initialise l'armure de base par une armure qui protege rien
        m_armureEquipe = new Armure("À poil", 0);
    }

    public String getNom(){
        return m_nom;
    }

    public String afficherInventaire(){

        // Variable inventaire qui sera construite dans la boucle
        String inventaire = "";

        for (int i = 0; i < m_inventaire.size(); i++){

            // Structure : | item | item | ...
            inventaire += " | " + m_inventaire.get(i).getNom() ;
        }

        return inventaire;
    }

    public Arme getArmeEquipe(){
        return m_armeEquipe;
    }

    public Armure getArmureEquipe(){
        return m_armureEquipe;
    }

    public void attaquer(Monstre pasGentil){
        System.out.println("Les dégâts que vous allez infliger à " + pasGentil.getNom() + " seront définie par un lancé " + m_armeEquipe.getDeAttque().get_nbDes() + "d" +  m_armeEquipe.getDeAttque().get_nbFaces());
        int degat = m_armeEquipe.getDegat();
        System.out.println("Vous avez infligé " + degat + " dégât(s) à " + pasGentil.getNom());
        // pasGentil.diminuerVie(degat);
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