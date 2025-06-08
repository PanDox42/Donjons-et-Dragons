package Entites.Personnages;

import Addon.Scan;
import Donjons.Coordonnee;
import Donjons.Donjon;
import Entites.Caracteristiques.Caracteristique;
import Entites.Entite;
import Entites.Personnages.Classes.Classe;
import Entites.Personnages.Monstre.Monstre;
import Entites.Personnages.Races.Race;
import Objets.Arme.Arme;
import Objets.Armure.Armure;
import Objets.Objet;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Personnage extends Entite {
    private String m_nom;
    private Race m_race;
    private Classe m_classe;
    private Caracteristique m_caracteristiques;
    private ArrayList<Objet> m_inventaire;

    public Personnage(String nom, Race race, Classe classe){
        m_nom = nom;
        m_race = race;
        m_classe = classe;

        // Appel d'une methode static permettant de créer les caractéristiques (lancés de dé)
        m_caracteristiques = Caracteristique.creerCaracteristique(this);

        // Initialise l'inventaire du joueur par rapport à sa classe
        m_inventaire = m_classe.getEquipementDepart();

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
        for (int i = 0; i < m_inventaire.size(); i++) {
            if (m_inventaire.get(i) instanceof Arme && m_inventaire.get(i).estEquipe()){
                return (Arme) m_inventaire.get(i);
            }
        }
        return Arme.sansArme();
    }

    public Armure getArmureEquipe(){
        for (int i = 0; i < m_inventaire.size(); i++) {
            if (m_inventaire.get(i) instanceof Armure && m_inventaire.get(i).estEquipe()){
                return (Armure) m_inventaire.get(i);
            }
        }
        return Armure.sansArmure();
    }

    public void attaquer(Monstre pasGentil){
        String nom = pasGentil.getEspece() + pasGentil.getNumero();

        if (!estAportee(pasGentil)){
            System.out.println("Votre arme n'a pas la portée nécessaire pour attaquer " + pasGentil.getNom());
            return;
        }

        System.out.println("Les dégâts que vous allez infliger à " + nom + " seront définie par un lancé " + getArmeEquipe().getDeAttaque().get_nbDes() + "d" +  getArmeEquipe().getDeAttaque().get_nbFaces());
        int degat = getArmeEquipe().getDegat();
        System.out.println("Vous avez infligé " + degat + " dégât(s) à " + nom);
        System.out.println(nom + " est passé de " + pasGentil.getCaracteristiques().getPv() + "pv à " + (pasGentil.getCaracteristiques().getPv() - degat) + "pv");
        pasGentil.diminuerVie(degat);

        if (pasGentil.estMort()){
            System.out.println("Vous avez tué " + nom);

        }
    }

    private boolean estAportee(Monstre cible){
        Coordonnee c1 = getCoordonnee();
        Coordonnee c2 = cible.getCoordonnee();

        double distance = Math.sqrt(Math.pow(c1.getX() - c2.getX(), 2) + Math.pow(c1.getY() - c2.getY(), 2)); // Formule chat gpt

        return distance <= getPortee();
    }

    public void diminuerVie(int degat){
        m_caracteristiques.changerPv(-degat);
    }

    public void afficherInventaire(){
        System.out.print("  Inventaire : " + "[" + m_inventaire.size() + "]");
        for (int i = 0; i < m_inventaire.size(); i++) {
            // Structure : | item | item | ...
            String equipe = m_inventaire.get(i).estEquipe() ? " (équipé)" : "";
            System.out.print(" | " + m_inventaire.get(i).getNom() + equipe);
        }
        System.out.println();
    }

    public String afficherEquipement(){
        String equipements = "";

        for (int i = 0; i < m_inventaire.size(); i++) {
            if ( m_inventaire.get(i).estEquipe()){
                equipements += "| " + m_inventaire.get(i).getNom();
            }
        }
        if (equipements == ""){
            equipements = "Aucun objet encore équipé";
        }

        return equipements;
    }

    public void afficherSituation(){

        System.out.println(
                m_nom + "\n" + // tabulation de 2 espaces à chaque fois
                "  Vie : " + m_caracteristiques.getPv() + "/" + m_classe.getPvDepart() + "\n" +
                "  Armure : " + getArmureEquipe().getNom() + "\n" +
                "  Arme : " + getArmeEquipe().getNom());
        afficherInventaire();

        System.out.println(
                "  Force : " + m_caracteristiques.getForce() + "\n" +
                "  Dextérité : " + m_caracteristiques.getDexterite() + "\n" +
                "  Vitesse : " + m_caracteristiques.getVitesse() + "\n");
    }

    public void sEquiper(){
        System.out.println(getNom() + " - Quel objet voulez-vous équiper ? (rentrer son numéro)");

        for(int i = 0; i < getInventaire().size(); i++) {
            String equipe = getInventaire().get(i).estEquipe() ? " (équipé)" : "";
            System.out.println("["+ i + "] " + getInventaire().get(i).getNom() + equipe);
        }

        System.out.println("Equipement actuel : " + afficherEquipement());

        int num = 0;
        try {
            try {
                num = parseInt(Scan.ScanLine());
            } catch(Exception e) {
                System.out.println("Le numéro n'est pas valide");
            }
            equiperObjet(num);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    System.out.print("Objet(s) Equipé(s) : ");
    for(Objet o : getObjetEquipe()) {
        System.out.print(o.getNom()+" | ");
    }
    System.out.println();

    }

    public void equiperObjet(int num) {
        if(getInventaire().get(num).estEquipe()) {
            throw new IllegalArgumentException("L'objet est déjà équipé");
        }

        int i = 0;
        for(Objet o : getInventaire()) {
            if(o.getType() == getInventaire().get(num).getType()) {
                if(o.estEquipe()) {
                    System.out.println("Vous avez remplacé votre " + o.getNom() + " par votre " + getInventaire().get(num).getNom() + " comme équipement");
                    delEquipe(i);
                }
            }
            i++;
        }
        setEquipe(num);
        System.out.println("L'objet "+ getInventaire().get(num).getNom()+" est équipé");
    }

    public void seDeplacer(Donjon donjon){
        System.out.println("Où voulez vous vous déplacer ? (indiquez les coodronnées comme ça : A:5)");
        donjon.deplacerEntite(this);
    }

    @Override
    public int getInitiative(){
        return getCaracteristiques().getInitiative();
    }

    @Override
    public String getType() {
        return "Personnage";
    }

    @Override
    public int getPortee(){
        return getArmeEquipe().getPorte();
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

    private void setEquipe(int num) {
        m_inventaire.get(num).setEquipe();
    }

    private void delEquipe(int num) {
        m_inventaire.get(num).setNonEquipe();
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