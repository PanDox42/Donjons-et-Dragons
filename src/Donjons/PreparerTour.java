package Donjons;

import Addon.Scan;
import Entites.Personnages.Monstre.Monstre;
import Entites.Personnages.Classes.*;
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

import static java.lang.Integer.parseInt;

public class PreparerTour {
    public static Personnage creerJoueur() {
        try {
            String nom = "";
            int race = 0;
            int classe = 0;
            Race raceClasse = null;
            Classe classeClasse = null;

            // Nom du joueur
            System.out.println("Quel sera le nom du joueur ?");
            nom = Scan.ScanLine();

            // Choix de la race
            while (true) {
                try {
                    System.out.println("Quelle sera la race du joueur parmi celles-ci :");
                    System.out.println("Entrez 0 pour Elfe\nEntrez 1 pour Halfelin\nEntrez 2 pour Humain\nEntrez 3 pour Nain");

                    race = Integer.parseInt(Scan.ScanLine());
                    if (race < 0 || race > 3) {
                        throw new IllegalArgumentException("Veuillez entrer un nombre entier entre 0 et 3 inclus");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Erreur : Veuillez entrer un nombre entier entre 0 et 3 inclus");
                }
            }

            // Choix de la classe
            while (true) {
                try {
                    System.out.println("Quelle sera la classe du joueur parmi celles-ci :");
                    System.out.println("Entrez 0 pour Clerc\nEntrez 1 pour Guerrier\nEntrez 2 pour Magicien\nEntrez 3 pour Roublard");

                    classe = Integer.parseInt(Scan.ScanLine());
                    if (classe < 0 || classe > 3) {
                        throw new IllegalArgumentException("Veuillez entrer un nombre entier entre 0 et 3 inclus");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Erreur : Veuillez entrer un nombre entier entre 0 et 3 inclus");
                }
            }

            // Instanciation de la race
            switch (race) {
                case 0 -> raceClasse = new Elfe();
                case 1 -> raceClasse = new Halfelin();
                case 2 -> raceClasse = new Humain();
                case 3 -> raceClasse = new Nain();
            }

            // Instanciation de la classe
            switch (classe) {
                case 0 -> classeClasse = new Clerc();
                case 1 -> classeClasse = new Guerrier();
                case 2 -> classeClasse = new Magicien();
                case 3 -> classeClasse = new Roublard();
            }

            return new Personnage(nom, raceClasse, classeClasse);

        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
            return null;
        }
    }

    public static Monstre creerMonstre() {
        int numero = -1;
        String espece = "";
        String nom = "";

        try {
            // Saisie du numéro
            while (true) {
                try {
                    System.out.print("Entrez un numéro pour le monstre : ");
                    numero = parseInt(Scan.ScanLine());
                    if (numero < 0) throw new IllegalArgumentException("Le numéro doit être positif.");
                    break;
                } catch (Exception e) {
                    System.out.println("Erreur : Veuillez entrer un entier valide pour le numéro.");
                }
            }

            // Saisie de l'espèce
            while (true) {
                try {
                    System.out.print("Entrez l'espèce du monstre : ");
                    espece = Scan.ScanLine().trim();
                    if (espece.isEmpty()) throw new IllegalArgumentException("L'espèce ne peut pas être vide.");
                    break;
                } catch (Exception e) {
                    System.out.println("Erreur : " + e.getMessage());
                }
            }

            // Saisie du nom
            while (true) {
                try {
                    System.out.print("Entrez le nom du monstre : ");
                    nom = Scan.ScanLine().trim();
                    if (nom.isEmpty()) throw new IllegalArgumentException("Le nom ne peut pas être vide.");
                    break;
                } catch (Exception e) {
                    System.out.println("Erreur : " + e.getMessage());
                }
            }

            // Création de l’objet Monstre
            return new Monstre(numero, espece, nom);

        } catch (Exception e) {
            System.out.println("Erreur inattendue lors de la création du monstre : " + e.getMessage());
        }

        return null;
    }

    public static Objet creerObjet() {
        Objet m_objets = null;
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

            return armureObjet;
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

            return armeObjet;
        }
        return null;
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
