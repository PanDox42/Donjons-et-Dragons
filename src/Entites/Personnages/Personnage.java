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

        // Création du dé qui servira à definir les caracteristiques (dé + 3 + bonus de la race)
        De deCaracteristique = new De(4,4);


        // Definition de la vie
        System.out.println("Definition de votre vie :");
        int vie = classe.getPvDepart();
        System.out.println("Votre classe vous donne " + vie + " PV\n" +
                "Votre race vous ajoute " + m_race.getPvAugmente());

        System.out.println("Votre vie est donc définie à " + vie + "\n");


        // Definition de la force
        System.out.println("Definition de votre force :");
        int force = deCaracteristique.lancer();
        System.out.println("Vous avez obtenu " + force + " points avec vos lancés de dé. \n" +
                "Votre race vous ajoute " + m_race.getForceAugmentee() + " et nous ajoutons 3 au score total");

        force += 3 + m_race.getForceAugmentee();
        System.out.println("Votre force est donc définie à " + force + "\n");


        // Definition de la déxterité
        System.out.println("Definition de votre déxterité :");
        int dexterite = deCaracteristique.lancer();
        System.out.println("Vous avez obtenu " + dexterite + " points avec vos lancés de dé. \n" +
                "Votre race vous ajoute " + m_race.getDexteriteAugmentee() + " et nous ajoutons 3 au score total");

        dexterite += 3 + m_race.getDexteriteAugmentee();
        System.out.println("Votre déxterité est donc définie à " + dexterite + "\n");


        // Definition de la vitesse
        System.out.println("Definition de votre vitesse :");
        int vitesse = deCaracteristique.lancer();
        System.out.println("Vous avez obtenu " + vitesse + " points avec vos lancés de dé. \n" +
                "Votre race vous ajoute " + m_race.getVitesseAugmentee() + " et nous ajoutons 3 au score total");

        vitesse += 3 + m_race.getVitesseAugmentee();
        System.out.println("Votre vitesse est donc définie à " + vitesse + "\n");


        // Definition de la initiative
        System.out.println("Definition de votre initiative :");
        int initiative = deCaracteristique.lancer();
        System.out.println("Vous avez obtenu " + initiative + " points avec vos lancés de dé. \n" +
                "Votre race vous ajoute " + m_race.getInitiativeAugmentee() + " et nous ajoutons 3 au score total");

        initiative += 3 + m_race.getInitiativeAugmentee();
        System.out.println("Votre initiative est donc définie à " + initiative + "\n");


        m_caracteristiques = new Caracteristique(vie, force, dexterite, vitesse, initiative);

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
                "  Armure : " + m_armureEquipe.getNom() + "\n" +
                "  Arme : " + m_armeEquipe.getNom() + "\n" +
                "  Inventaire : " + "[" + m_inventaire.size() + "]" + afficherInventaire() + "  --> (c'est par rapport à la classe de King Kong, j'ai tout codé tu peux essayer de changer sa classe pour voir, le stuff sera different.\n" +
                "  Force : " + m_caracteristiques.getForce() + "\n" +
                "  Dextérité : " + m_caracteristiques.getDexterite() + "\n" +
                "  Vitesse : " + m_caracteristiques.getVitesse() + "\n");
    }
}