package Donjons;

import Addon.Scan;
import Entites.Monstre;
import Entites.Personnages.Classes.*;
import Entites.Personnages.MaitreJeu;
import Entites.Personnages.Personnage;
import Entites.Personnages.Races.*;
import Objets.Arme.ArmeCourantes.Baton;
import Objets.Arme.ArmeCourantes.MasseArme;
import Objets.Arme.ArmeDistances.ArbaleteLegere;
import Objets.Arme.ArmeDistances.ArcCourt;
import Objets.Arme.ArmeDistances.Fronde;
import Objets.Arme.ArmeGuerres.EpeeLongue;
import Objets.Arme.ArmeGuerres.Rapiere;
import Objets.Arme.Poing;
import Objets.Armure.ArmureLegeres.ArmureEcailles;
import Objets.Armure.ArmureLegeres.DemiPlate;
import Objets.Armure.ArmureLourdes.CotteMailles;
import Objets.Armure.ArmureLourdes.Harnois;
import Objets.Armure.Nu;
import Objets.Objet;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class PreparerTour {
    public static ArrayList<Personnage> creerJoueur() {
        try {
            ArrayList<Personnage> m_personnages = new ArrayList<>();
            int classe = 0;
            int race = 0;
            Race raceClasse = null;
            Classe classeClasse = null;
            String nom = "";
            int m_nbJoueur = 0;

            System.out.println("Bienvenue dans DOOnjon et Dragons\n");

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
                return m_personnages;
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return new ArrayList<>();
    }

    public static ArrayList<Monstre> creerMonstre() {
        return new ArrayList<Monstre>();
    }

    public static void creerObjet(Donjon donjon) {
        Objet m_objets = null;
        while(true) {
            System.out.println("Quel objet voulez-vous placer dans le donjon ?");
            System.out.println("0 pour armure et 1 pour arme :");
            int type = parseInt(Scan.ScanLine());

            System.out.println("Indiquez le numéro de l'objet à placer parmi :");
            Objet armeObjet = new Poing();
            Objet armureObjet = new Nu();

            // CAS ARMURE
            if (type == 0) {
                System.out.println("Armures légères :");
                System.out.println("1 - Armure d'écailles, classe d'armure : 9");
                System.out.println("2 - Demi-plate, classe d'armure : 10");

                System.out.println("Armures lourdes :");
                System.out.println("3 - Cotte de mailles, classe d'armure : 11");
                System.out.println("4 - Harnois, classe d'armure : 12");
                int armure = parseInt(Scan.ScanLine());

                if (armure == 1) {
                    armureObjet = new ArmureEcailles();
                } else if (armure == 2) {
                    armureObjet = new DemiPlate();
                } else if (armure == 3) {
                    armureObjet = new CotteMailles();
                } else if (armure == 4) {
                    armureObjet = new Harnois();
                }

                m_objets.add(armureObjet);
            }

            // CAS ARME
            else if (type == 1) {
                System.out.println("Armes courantes au corps-à-corps :");
                System.out.println("1 - Bâton, dégât : 1d6, portée : 1 case");
                System.out.println("2 - Masse d'armes, dégât : 1d6, portée : 1 case");

                System.out.println("Armes de guerre au corps-à-corps :");
                System.out.println("3 - Épée longue, dégât : 1d8, portée : 1 case");
                System.out.println("4 - Rapière, dégât : 1d8, portée : 1 case");

                System.out.println("Armes à distance :");
                System.out.println("5 - Arbalète légère, dégât : 1d8, portée : 16 cases");
                System.out.println("6 - Fronde, dégât : 1d4, portée : 6 cases");
                System.out.println("7 - Arc court, dégât : 1d6, portée : 16 cases");

                int arme = parseInt(Scan.ScanLine());

                if (arme == 1) {
                    armeObjet = new Baton();
                } else if (arme == 2) {
                    armeObjet = new MasseArme();
                } else if (arme == 3) {
                    armeObjet = new EpeeLongue();
                } else if (arme == 4) {
                    armeObjet = new Rapiere();
                } else if (arme == 5) {
                    armeObjet = new ArbaleteLegere();
                } else if (arme == 6) {
                    armeObjet = new Fronde();
                } else if (arme == 7) {
                    armeObjet = new ArcCourt();
                }

                m_objets.add(armeObjet);
            }

            System.out.println("Voulez-vous continuer à placer des objets ?\nSi non, alors entrez 0\nSi oui, alors entrez 1");
            if(parseInt(Scan.ScanLine())==0) {
                break;
            }
        }
        donjon.placerMonstre(m_objets);
    }

    public static void creerObstacle(Donjon donjon) {
        // Appeler creationObstacle
        donjon.placerObstacle();
        while(true) {
            System.out.println("Voulez-vous continuer à créer un obstacle ?\nSi non, alors entrez 0\nSi oui, alors entrez 1");
            if(parseInt(Scan.ScanLine())==0) {
                break;
            }
            else {
                donjon.placerObstacle();
            }
        }
    }
}
