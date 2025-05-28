package Entites.Personnages.Monstre;

import Entites.Caracteristiques.CaracteristiqueMonstre;
import Entites.Entite;
import Entites.Personnages.Personnage;

public class Monstre extends Entite {
    private int m_numero;
    private String m_espece;
    private Attaque m_attaque;
    private CaracteristiqueMonstre m_caracteristiques;

    public Monstre(int numero, String espece){
        m_numero = numero;
        m_espece = espece;
        m_attaque = Attaque.creerAttaque();
        m_caracteristiques = CaracteristiqueMonstre.creerCaracteristique(this);
    }

    public Monstre(int numero, String espece, Attaque attaque){
        m_numero = numero;
        m_espece = espece;
        m_attaque = attaque;
        m_caracteristiques = CaracteristiqueMonstre.creerCaracteristique(this);
    }

    public Monstre(int numero, String espece, Attaque attaque, CaracteristiqueMonstre caracteristique){
        m_numero = numero;
        m_espece = espece;
        m_attaque = attaque;
        m_caracteristiques = caracteristique;
    }

    public void Attaquer(Personnage perso){
        String nom = perso.getNom();
        int degat = m_attaque.getDeAttaque().lancer();
        System.out.println(m_espece + m_numero +" a infligé " + degat + " dégât(s) à " + nom);
        perso.diminuerVie(degat);
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
}
