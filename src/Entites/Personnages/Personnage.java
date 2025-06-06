package Entites.Personnages;
import Entites.Entite;
import Entites.Personnages.Monstre.Monstre;
import Entites.Personnages.Classes.Classe;
import Entites.Personnages.Races.Race;
import Objets.Arme.Arme;
import Objets.Armure.Armure;
import Entites.Caracteristiques.Caracteristique;
import Objets.Objet;

import java.util.ArrayList;
import java.util.List;

public class Personnage extends Entite {
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

        // Appel d'une methode static permettant de créer les caractéristiques (lancés de dé)
        m_caracteristiques = Caracteristique.creerCaracteristique(this);

        // Initialise l'inventaire du joueur par rapport à sa classe
        m_inventaire = m_classe.getEquipementDepart();

        // On initialise l'arme de base par un poing qui fait aucun degat
        m_armeEquipe = Arme.sansArme();

        // On initialise l'armure de base par une armure qui protege rien
        m_armureEquipe = Armure.sansArmure();

        // Fait un affichage des stats du joueur
        afficherSituation();
    }

    public Classe getClasse(){
        return m_classe;
    }

    public String getNom(){
        return m_nom;
    }

    public Race getRace(){
        return m_race;
    }

    public Caracteristique getCaracteristiques(){
        return m_caracteristiques;
    }

    public ArrayList<Objet> getInventaire(){
        return m_inventaire;
    }

    public Arme getArmeEquipe(){
        return m_armeEquipe;
    }

    public Armure getArmureEquipe(){
        return m_armureEquipe;
    }

    public void attaquer(Monstre pasGentil){
        String nom = pasGentil.getEspece() + pasGentil.getNumero();
        System.out.println("Les dégâts que vous allez infliger à " + nom + " seront définie par un lancé " + m_armeEquipe.getDeAttaque().get_nbDes() + "d" +  m_armeEquipe.getDeAttaque().get_nbFaces());
        int degat = m_armeEquipe.getDegat();
        System.out.println("Vous avez infligé " + degat + " dégât(s) à " + nom);
        pasGentil.diminuerVie(degat);
    }

    public void diminuerVie(int degat){
        m_caracteristiques.changerPv(-degat);
    }

    public void afficherSituation(){

        System.out.println(
                m_nom + "\n" + // tabulation de 2 espaces à chaque fois
                "  Vie : " + m_caracteristiques.getPv() + "/" + m_classe.getPvDepart() + "\n" +
                "  Armure : " + m_armureEquipe.getNom() + "\n" +
                "  Arme : " + m_armeEquipe.getNom() + "\n" +
                "  Inventaire : " + "[" + m_inventaire.size() + "]");
        for (int i = 0; i < m_inventaire.size(); i++) {
            // Structure : | item | item | ...
            System.out.print(" | " + m_inventaire.get(i).getNom());
        }
        System.out.println();

        System.out.println(
                "  Force : " + m_caracteristiques.getForce() + "\n" +
                "  Dextérité : " + m_caracteristiques.getDexterite() + "\n" +
                "  Vitesse : " + m_caracteristiques.getVitesse() + "\n");
    }

    @Override
    public String getSymbole() {
        String afficherDonjon = "";
        String nom = getNom().toUpperCase().trim();
        if(getNom().length() == 1) {
            afficherDonjon = " "+nom+" ";
        }
        else if(getNom().length() == 2) {
            afficherDonjon = nom+" ";
        }
        else {
            afficherDonjon = getNom().substring(0,3).toUpperCase();
        }
        return afficherDonjon;
    }

    public void equiperObjet(int num) {
        m_inventaire.get(num).setEquipe();
    }

    public ArrayList<Objet> getObjetEquipe() {
        ArrayList<Objet> listObjetEquipe = new ArrayList<>();
        for(Objet o : m_inventaire) {
            if(o.estEquipe()) {
                listObjetEquipe.add(o);
            }
        }
        return listObjetEquipe;
    }

    public void tourPersonnage() {

    }
}