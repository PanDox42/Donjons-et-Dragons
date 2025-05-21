import Addon.Scan;
import Entites.Personnages.Classes.*;
import Entites.Personnages.MaitreJeu;
import Entites.Personnages.Personnage;
import Entites.Personnages.Races.*;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args){
        try {
            System.out.println("Bienvenue dans DOOnjon et Dragons\n");
            MaitreJeu mdj = new MaitreJeu();

            int m_nbJoueur = 0;
            while(true) {
                try {
                    System.out.println("A combien de joueur voulez-vous jouer ?");
                    m_nbJoueur = parseInt(Scan.ScanLine());
                    if(m_nbJoueur<1) {
                        throw new IllegalArgumentException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Le format n'est pas valide");
                }
            }

            ArrayList<Personnage> m_personnages = new ArrayList<>();
            int classe = 0;
            int race = 0;
            Race raceClasse = null;
            Classe classeClasse = null;
            String nom = "";

            for(int i = 0; i<m_nbJoueur; i++) {
                while(true) {
                    try {
                        System.out.println("Quel sera le nom du joueur "+(i+1)+" ?");
                        nom = Scan.ScanLine();

                        System.out.println("Quel sera la race du joueur " + (i + 1) + " parmi cela ?");
                        System.out.println("Entrez 0 pour Elfe\nEntrez 1 pour Halfelin\nEntrez 2 pour Humain\nEntrez 3 pour Nain");

                        while(true) {
                            try {
                                race = parseInt(Scan.ScanLine());
                                if (race > 3 || race < 0) {
                                    throw new IllegalArgumentException("Veuillez entrer un nombre entier entre 0 et 3 inclus");
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Veuillez entrer un nombre entier entre 0 et 3 inclus");
                            }
                        }

                        while(true) {
                            try {
                                System.out.println("Quel sera la classe du joueur " + (i + 1) + " parmi cela ?");
                                System.out.println("Entrez 0 pour Clerc\nEntrez 1 pour Guerrier\nEntrez 2 pour Magicien\nEntrez 3 pour Roublard");
                                classe = parseInt(Scan.ScanLine());
                                if (classe > 3 || classe < 0) {
                                    throw new IllegalArgumentException("Veuillez entrer un nombre entier entre 0 et 3 inclus");
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Veuillez entrer un nombre entier entre 0 et 3 inclus");
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Erreur: " + e.getMessage());
                    }
                    break;
                }

                if(race == 0) {
                    raceClasse = new Elfe();
                }
                else if(race == 1) {
                    raceClasse = new Halfelin();
                }
                else if(race == 2) {
                    raceClasse = new Humain();
                }
                else if(race == 3) {
                    raceClasse = new Nain();
                }

                if(classe == 0) {
                    classeClasse = new Clerc();
                }
                else if(classe == 1) {
                    classeClasse =  new Guerrier();
                }
                else if(classe == 2) {
                    classeClasse = new Magicien();
                }
                else if(classe == 3) {
                    classeClasse = new Roublard();
                }

                m_personnages.add(new Personnage(nom, raceClasse, classeClasse));
            }

            // CREATION DONJON
            mdj.creerDonjon();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
