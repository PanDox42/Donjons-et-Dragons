package Entites.Caracteristiques;

import Addon.De;
import Entites.Personnages.Personnage;

public class Caracteristique {
    private int m_pv;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;

    public Caracteristique(int pv, int force, int dexterite, int vitesse, int initiative){
        m_pv = pv;
        m_force = force;
        m_dexterite = dexterite;
        m_vitesse = vitesse;
        m_initiative = initiative;
    }

    // Méthode en static permettant de créer les caractéristiques (lancés de dé)
    public static Caracteristique creerCaracteristique(Personnage personnage){

        // Création du dé qui servira à définir les caractéristiques (dé + 3 + bonus de la race)
        De deCaracteristique = new De(4,4);

        // Definition de la vie
        System.out.println("Definition de votre vie :");
        int vie = personnage.getClasse().getPvDepart();
        System.out.println("Votre classe vous donne " + vie + " PV\n" +
                "Votre race vous ajoute " + personnage.getRace().getPvAugmente());

        vie += personnage.getRace().getPvAugmente();

        System.out.println("Votre vie est donc définie à " + vie + "\n");


        // Definition de la force
        System.out.println("Definition de votre force :");
        int force = deCaracteristique.lancer();
        System.out.println("Vous avez obtenu " + force + " points avec vos lancés de dé. \n" +
                "Votre race vous ajoute " + personnage.getRace().getForceAugmentee() + " et nous ajoutons 3 au score total");

        force += 3 + personnage.getRace().getForceAugmentee();
        System.out.println("Votre force est donc définie à " + force + "\n");


        // Definition de la déxterité
        System.out.println("Definition de votre déxterité :");
        int dexterite = deCaracteristique.lancer();
        System.out.println("Vous avez obtenu " + dexterite + " points avec vos lancés de dé. \n" +
                "Votre race vous ajoute " + personnage.getRace().getDexteriteAugmentee() + " et nous ajoutons 3 au score total");

        dexterite += 3 + personnage.getRace().getDexteriteAugmentee();
        System.out.println("Votre déxterité est donc définie à " + dexterite + "\n");


        // Definition de la vitesse
        System.out.println("Definition de votre vitesse :");
        int vitesse = deCaracteristique.lancer();
        System.out.println("Vous avez obtenu " + vitesse + " points avec vos lancés de dé. \n" +
                "Votre race vous ajoute " + personnage.getRace().getVitesseAugmentee() + " et nous ajoutons 3 au score total");

        vitesse += 3 + personnage.getRace().getVitesseAugmentee();
        System.out.println("Votre vitesse est donc définie à " + vitesse + "\n");


        // Definition de la initiative
        System.out.println("Definition de votre initiative :");
        int initiative = deCaracteristique.lancer();
        System.out.println("Vous avez obtenu " + initiative + " points avec vos lancés de dé. \n" +
                "Votre race vous ajoute " + personnage.getRace().getInitiativeAugmentee() + " et nous ajoutons 3 au score total");

        initiative += 3 + personnage.getRace().getInitiativeAugmentee();
        System.out.println("Votre initiative est donc définie à " + initiative + "\n");

        return new Caracteristique(vie, force, dexterite, vitesse, initiative);
    }

    // Les méthodes getteurs
    public int getPv(){
        return m_pv;
    }

    public int getForce(){
        return m_force;
    }

    public int getDexterite(){
        return m_dexterite;
    }

    public int getVitesse(){
        return m_vitesse;
    }

    public int getInitiative(){
        return m_initiative;
    }


    // Les méthodes de modification des propriétés
    public void changerPv(int changement){
        m_pv += changement;
    }

    public void changerForce(int changement){
        m_force += changement;
    }

    public void changerDexterite(int changement){
        m_dexterite += changement;
    }

    public void changerVitesse(int changement){
        m_vitesse += changement;
    }

    public void changerInitiative(int changement){
        m_initiative += changement;
    }
}
