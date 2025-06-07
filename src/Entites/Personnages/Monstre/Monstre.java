package Entites.Personnages.Monstre;

import Entites.Caracteristiques.CaracteristiqueMonstre;
import Entites.Entite;
import Entites.Personnages.Personnage;

public class Monstre extends Entite {
    private String m_nom;
    private int m_numero;
    private String m_espece;
    private Attaque m_attaque;
    private CaracteristiqueMonstre m_caracteristiques;

    public Monstre(int numero, String espece, String nom){
        m_nom = nom;
        m_numero = numero;
        m_espece = espece;
        m_attaque = Attaque.creerAttaque();
        m_caracteristiques = CaracteristiqueMonstre.creerCaracteristique();
        afficherSituation();
    }

    public Monstre(int numero, String espece, String nom, Attaque attaque){
        m_nom = nom;
        m_numero = numero;
        m_espece = espece;
        m_attaque = attaque;
        m_caracteristiques = CaracteristiqueMonstre.creerCaracteristique();
        afficherSituation();
    }

    public Monstre(int numero, String espece, String nom, Attaque attaque, CaracteristiqueMonstre caracteristique){
        m_nom = nom;
        m_numero = numero;
        m_espece = espece;
        m_attaque = attaque;
        m_caracteristiques = caracteristique;
        afficherSituation();
    }

    public void Attaquer(Personnage perso){
        String nom = perso.getNom();
        int degat = m_attaque.getDeAttaque().lancer();
        System.out.println(m_espece + m_numero +" a infligé " + degat + " dégât(s) à " + nom);
        perso.diminuerVie(degat);
    }

    public String getNom(){
        return m_nom;
    }

    public void diminuerVie(int degat){
        m_caracteristiques.changerPv(-degat);
    }

    public int getNumero(){
        return m_numero;
    }

    public String getEspece(){
        return m_espece;
    }

    public Attaque getDeAttaque(){
        return m_attaque;
    }

    public CaracteristiqueMonstre getCaracteristique(){
        return m_caracteristiques;
    }

    public void afficherSituation(){

        System.out.println(
                m_nom + "\n" + // tabulation de 2 espaces à chaque fois
                        "   Espèce : " + m_espece + "\n" +
                        "   Numéro : " + m_numero + "\n" +
                        "   Attaque : dégats = " + m_attaque.getDegat().toString() + " portée = " + m_attaque.getPorte() + "\n" +
                        "   Force : " + m_caracteristiques.getForce() + "\n" +
                        "   Dexterité : " + m_caracteristiques.getDexterite() + "\n" +
                        "   Classe Armure : " + m_caracteristiques.getClasseArmure() + "\n" +
                        "   Vitesse : " + m_caracteristiques.getVitesse() + "\n" +
                        "   Inititative : " + m_caracteristiques.getInitiative() + "\n");
    }

    @Override
    public String getSymbole() {
        String afficherDonjon = "";
        if(getNom().length() == 1) {
            afficherDonjon = " "+getNom().toUpperCase()+" ";
        }
        else if(getNom().length() == 2) {
            afficherDonjon = getNom().toUpperCase()+" ";
        }
        else {
            afficherDonjon = getNom().substring(0,3).toUpperCase();
        }
        return afficherDonjon;
    }
}
