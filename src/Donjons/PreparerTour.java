package Donjons;

import Addon.Scan;
import Entites.Caracteristiques.Caracteristique;
import Entites.Caracteristiques.CaracteristiqueMonstre;
import Entites.Personnages.Monstre.Attaque;
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
    public static Personnage creerPersonnage(String nom, int race, int classe) {
        Race raceClasse;
        Classe classeClasse;

        switch (race) {
            case 0 -> raceClasse = new Elfe();
            case 1 -> raceClasse = new Halfelin();
            case 2 -> raceClasse = new Humain();
            case 3 -> raceClasse = new Nain();
            default -> throw new IllegalArgumentException("Race inconnue.");
        }

        switch (classe) {
            case 0 -> classeClasse = new Clerc();
            case 1 -> classeClasse = new Guerrier();
            case 2 -> classeClasse = new Magicien();
            case 3 -> classeClasse = new Roublard();
            default -> throw new IllegalArgumentException("Classe inconnue.");
        }

        return new Personnage(nom, raceClasse, classeClasse);
    }

    public static Personnage demanderJoueur() {
        try {
            String nom;
            int race = -1;
            int classe = -1;

            // Saisie du nom
            System.out.println("Quel sera le nom du joueur ?");
            nom = Scan.ScanLine();

            // Saisie de la race
            while (true) {
                try {
                    System.out.println("Quelle sera la race du joueur parmi celles-ci :");
                    System.out.println("0 - Elfe");
                    System.out.println("1 - Halfelin");
                    System.out.println("2 - Humain");
                    System.out.println("3 - Nain");

                    race = Integer.parseInt(Scan.ScanLine());
                    if (race < 0 || race > 3) throw new IllegalArgumentException();
                    break;
                } catch (Exception e) {
                    System.out.println("Erreur : veuillez entrer un nombre entier entre 0 et 3 inclus.");
                }
            }

            // Saisie de la classe
            while (true) {
                try {
                    System.out.println("Quelle sera la classe du joueur parmi celles-ci :");
                    System.out.println("0 - Clerc");
                    System.out.println("1 - Guerrier");
                    System.out.println("2 - Magicien");
                    System.out.println("3 - Roublard");

                    classe = Integer.parseInt(Scan.ScanLine());
                    if (classe < 0 || classe > 3) throw new IllegalArgumentException();
                    break;
                } catch (Exception e) {
                    System.out.println("Erreur : veuillez entrer un nombre entier entre 0 et 3 inclus.");
                }
            }

            return creerPersonnage(nom, race, classe);

        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
            return null;
        }
    }

    public static Monstre creerMonstreDepuisValeurs(int numero, String espece, String nom) {
        if (numero < 0) {
            throw new IllegalArgumentException("Le numéro doit être positif.");
        }
        if (espece == null || espece.trim().isEmpty()) {
            throw new IllegalArgumentException("L'espèce ne peut pas être vide.");
        }
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide.");
        }

        return new Monstre(numero, espece.trim(), nom.trim());
    }


    public static Monstre creerMonstre() {
        int numero = -1;
        String espece = "";
        String nom = "";

        // Saisie du numéro
        while (true) {
            try {
                System.out.print("Entrez un numéro pour le monstre : ");
                numero = Integer.parseInt(Scan.ScanLine());
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

        // Appel de la fonction logique
        try {
            return creerMonstreDepuisValeurs(numero, espece, nom);
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du monstre : " + e.getMessage());
        }

        return null;
    }

    public static Objet creerObjetDepuisChoix(int type, int id) {
        if (type == 0) { // ARMURE
            return switch (id) {
                case 1 -> new ArmureEcailles();
                case 2 -> new DemiPlate();
                case 3 -> new CotteMailles();
                case 4 -> new Harnois();
                default -> throw new IllegalArgumentException("ID d'armure invalide (1-4 attendu).");
            };
        } else if (type == 1) { // ARME
            return switch (id) {
                case 1 -> new Baton();
                case 2 -> new MasseArme();
                case 3 -> new EpeeLongue();
                case 4 -> new Rapiere();
                case 5 -> new ArbaleteLegere();
                case 6 -> new Fronde();
                case 7 -> new ArcCourt();
                default -> throw new IllegalArgumentException("ID d'arme invalide (1-7 attendu).");
            };
        } else {
            throw new IllegalArgumentException("Type d'objet invalide (0 = armure, 1 = arme).");
        }
    }


    public static Objet creerObjet() {
        int type = -1;

        while (true) {
            try {
                System.out.println("Quel objet voulez-vous placer dans le donjon ?");
                System.out.println("0 pour armure, 1 pour arme :");
                type = Integer.parseInt(Scan.ScanLine());

                if (type != 0 && type != 1) {
                    throw new IllegalArgumentException("Entrez 0 pour une armure ou 1 pour une arme.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        while (true) {
            try {
                if (type == 0) {
                    System.out.println("Choisissez une armure :");
                    System.out.println("1 - Armure d'écailles (CA 9)");
                    System.out.println("2 - Demi-plate (CA 10)");
                    System.out.println("3 - Cotte de mailles (CA 11)");
                    System.out.println("4 - Harnois (CA 12)");
                } else {
                    System.out.println("Choisissez une arme :");
                    System.out.println("1 - Bâton (1d6, portée 1)");
                    System.out.println("2 - Masse d'armes (1d6, portée 1)");
                    System.out.println("3 - Épée longue (1d8, portée 1)");
                    System.out.println("4 - Rapière (1d8, portée 1)");
                    System.out.println("5 - Arbalète légère (1d8, portée 16)");
                    System.out.println("6 - Fronde (1d4, portée 6)");
                    System.out.println("7 - Arc court (1d6, portée 16)");
                }

                int id = Integer.parseInt(Scan.ScanLine());
                return creerObjetDepuisChoix(type, id);

            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }
}
