package entites.personnages.monstres;

import addon.De;
import donjons.Coordonnee;
import entites.caracteristiques.CaracteristiqueMonstre;
import entites.Entite;
import entites.personnages.Personnage;

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
        System.out.println(m_nom +" a infligé " + degat + " dégât(s) à " + nom);
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

    @Override
    public CaracteristiqueMonstre getCaracteristiques(){
        return m_caracteristiques;
    }

    public void afficherSituation(){

        System.out.println(
                m_nom + "\n" + // tabulation de 2 espaces à chaque fois
                        "   Espèce : " + m_espece + "\n" +
                        "   Numéro : " + m_numero + "\n" +
                        "   Attaque : " + "\n" +
                        "       Dégâts : " + m_attaque.getDegat().toString() + "\n" +
                        "       Portée : " + m_attaque.getPorte() + "\n" +
                        "   Force : " + m_caracteristiques.getForce() + "\n" +
                        "   Dexterité : " + m_caracteristiques.getDexterite() + "\n" +
                        "   Classe Armure : " + m_caracteristiques.getClasseArmure() + "\n" +
                        "   Vitesse : " + m_caracteristiques.getVitesse() + "\n" +
                        "   Inititative : " + m_caracteristiques.getInitiative() + "\n");
    }

    private boolean estAportee(Personnage personnage){
        Coordonnee c1 = getCoordonnee();
        Coordonnee c2 = personnage.getCoordonnee();

        double distance = Math.sqrt(Math.pow(c1.getX() - c2.getX(), 2) + Math.pow(c1.getY() - c2.getY(), 2)); // Formule chat gpt

        return distance <= getPortee();
    }

    public boolean estMort(){
        return m_caracteristiques.getPv() <= 0;
    }

    @Override
    public int getInitiative(){
        return getCaracteristiques().getInitiative();
    }

    @Override
    public String getType() {
        return "Monstre";
    }

    @Override
    public int getPortee(){
        return m_attaque.getPorte();
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

    public void attaquer(Personnage personnage){
        String nom = personnage.getNom();

        // Vérifie la portée
        if (!estAportee(personnage)) {
            System.out.println(m_nom + " est trop loin pour attaquer " + nom + ".");
            return;
        }

        System.out.println(m_nom + " attaque " + nom + " !");
        System.out.println("Jet pour toucher : lancer 1d20...");

        int jet = De.lancer(1, 20);
        int bonusAttaque = m_caracteristiques.getForce();
        int total = jet + bonusAttaque;

        System.out.println("Résultat du jet : " + jet + " + bonus de Force (" + bonusAttaque + ") = " + total);

        // Classe d'armure du personnage : 10 + Dextérité
        int classeArmure = 10 + personnage.getCaracteristiques().getDexterite();
        System.out.println("Classe d'armure de " + nom + " : " + classeArmure);

        if (total > classeArmure) {
            int degats = m_attaque.getDeAttaque().lancer();
            System.out.println("Attaque réussie ! " + m_nom + " inflige " + degats + " dégât(s) à " + nom);
            int pvAvant = personnage.getCaracteristiques().getPv();
            personnage.diminuerVie(degats);
            System.out.println(nom + " passe de " + pvAvant + " pv à " + personnage.getCaracteristiques().getPv() + " pv.");
        } else {
            System.out.println("Attaque ratée ! " + m_nom + " ne parvient pas à toucher " + nom);
        }
    }


}
