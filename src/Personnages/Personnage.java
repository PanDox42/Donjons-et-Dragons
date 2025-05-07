package Personnages;
import Objets.Objet;
import Personnages.Caracteristiques.Caracteristique;

import java.util.ArrayList;

public class Personnage {
    private String m_nom;
    private Race m_race;
    private Classe m_classe;
    private ArrayList<Caracteristique> caracteristiques = new ArrayList<>();
    private ArrayList<Objet> m_inventaire = new ArrayList<>();
    private ArrayList<Objet> m_equipement = new ArrayList<>();


    public Personnage(String nom, Race race, Classe classe){
        m_nom = nom;
        m_race = race;
        m_classe = classe;
    }
}
