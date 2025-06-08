package Donjons;

import Addon.Scan;
import Entites.Entite;
import Entites.Personnages.MaitreJeu;
import Entites.Personnages.Monstre.Monstre;
import Entites.Personnages.Personnage;

import java.util.ArrayList;

public class Jouer {
    public static void jouer() {
        Donjon donjon = null;
        int nbTour = 0;
        // Fin de partie si un joueur meurt ou si tous les monstres sont mort
        while(true) {
            // Prepare le donjon et création des joueurs si c'est le premier tour
            if (nbTour == 0) {
                donjon = preparer(donjon);
                nbTour++;
            }
            else {
                if(donjon.joueurMort()) {
                    break;
                }
                if(donjon.monstreMort()) {
                    nbTour = 1;
                    System.out.println("Vous avez gagné");
                    donjon = preparerSuite(donjon);
                }
                tour(donjon, nbTour);
                nbTour++;
            }
        }
        System.out.println("Vous avez perdu");
    }

    private static Donjon preparerDonjonManuellement() {
        MaitreJeu mdj = new MaitreJeu();
        Donjon donjon = mdj.creerDonjon();
        donjon.placerObstaclesAvecConfirmation();
        donjon.placerMonstresAvecConfirmation();
        donjon.placerObjetsAvecConfirmation();
        donjon.afficherCarte();
        donjon.modifierContexte(donjon.raconterTourMdj());
        return donjon;
    }

    private static Donjon preparerDonjonDefault() {
        MaitreJeu mdj = new MaitreJeu();
        Donjon donjon = mdj.creerDonjonParDefaut();
        return donjon;
    }

    public static Donjon preparer(Donjon donjon) {
        while (true) {
            try {
                System.out.println("Maître du Jeu - Souhaitez-vous créer le donjon manuellement ou choisir celui par défault ?");
                System.out.println("1 - Manuellement");
                System.out.println("2 - Par Défaut");

                String saisie = Scan.ScanLine().trim();
                int choix = Integer.parseInt(saisie);

                if (choix == 1) {
                    donjon = preparerDonjonManuellement();
                } else if (choix == 2) {
                    donjon = preparerDonjonDefault();
                } else {
                    throw new NumberFormatException();
                }
                donjon.placerJoueursAvecConfirmation();
                donjon.equiperObjet();

                System.out.println("Vous avez bien créée le donjon, ses obstacles, ses monstres ainsi que les joueurs !");
                return donjon;
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre (1 ou 2).");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Donjon preparerSuite(Donjon donjon) {
        while (true) {
            try {
                System.out.println("Maître du Jeu - Souhaitez-vous créer le donjon manuellement ou choisir celui par défault ?\nOn garde les même personnage mais on change les monstres et les équipements sur le donjon");
                System.out.println("1 - Manuellement");
                System.out.println("2 - Par Défaut");

                String saisie = Scan.ScanLine().trim();
                int choix = Integer.parseInt(saisie);

                Donjon donjonDefault = null;

                if (choix == 1) {
                    donjonDefault = preparerDonjonManuellement();
                } else if (choix == 2) {
                    donjonDefault = preparerDonjonDefault();
                } else {
                    throw new NumberFormatException();
                }

                donjonDefault.setToutLesPersonnages(donjon.getJoueur());

                System.out.println("Vous avez bien créée le donjon, ses obstacles, ses monstres ainsi que les joueurs !");
                return donjon;
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre (1 ou 2).");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void tour(Donjon donjon, int nbTour) {
        System.out.println("\n\n----------------------- TOUR n°" + nbTour + " ----------------------\n");

        ArrayList<Entite> ordreJeu = donjon.getOrdreEntite();

        for (Entite entite : ordreJeu) {
            for (int action = 3; action > 0; action--) {
                if (entite instanceof Personnage personnage) {
                    jouerTourPersonnage(donjon, personnage, action, nbTour);
                } else if (entite instanceof Monstre monstre) {
                    jouerTourMonstre(donjon, monstre, action);
                }
            }
        }
    }

    private static void jouerTourPersonnage(Donjon donjon, Personnage personnage, int action, int numTour) {
        System.out.println("\n*** C'est à " + personnage.getNom() + " de jouer ***\n");
        donjon.afficherCarte();
        personnage.afficherSituation();
        System.out.println("Vous pouvez vous déplacer de " + personnage.getCaracteristiques().getVitesse() / 3 + " cases");

        System.out.println(personnage.getNom() + " - Vous avez " + action + " action(s), que voulez-vous faire ?");

        boolean actionEffectuee = false;

        while (!actionEffectuee) {
            try {
                afficherMenuActionsPersonnage(donjon, personnage, action, numTour);
                int choix = Integer.parseInt(Scan.ScanLine().trim());

                actionEffectuee = traiterActionPersonnage(donjon, personnage, choix, action, numTour);
            } catch (Exception e) {
                System.out.println("Entrée invalide ou erreur : " + e.getMessage());
            }
        }
    }

    private static void afficherMenuActionsPersonnage(Donjon donjon, Personnage personnage, int action, int numTour) {
        if(numTour==0) {
            System.out.println("1 - laisser le maître du jeu commenter l'action précédente");
            System.out.println("2 - commenter action précédente");
            System.out.println("3 - S'équiper");
            System.out.println("4 - Se déplacer");
            System.out.println("5 - Attaquer");
            if (donjon.itemSurCoordonnee(personnage.getCoordonnee()) && action > 1) {
                System.out.println("6 - Récupérer " + donjon.getObjet(personnage.getCoordonnee()).getNom());
            }
        }
        else {
            System.out.println("1 - S'équiper");
            System.out.println("2 - Se déplacer");
            System.out.println("3 - Attaquer");
            if (donjon.itemSurCoordonnee(personnage.getCoordonnee()) && action > 1) {
                System.out.println("4 - Récupérer " + donjon.getObjet(personnage.getCoordonnee()).getNom());
            }
        }
    }

    private static boolean traiterActionPersonnage(Donjon donjon, Personnage personnage, int choix, int action, int numTour) {
        if(numTour!=0) {
            switch (choix) {
                case 1 -> demanderCommentaire("Maitre du jeu");
                case 2 -> demanderCommentaire(personnage.getNom());
                case 3 -> personnage.sEquiper();
                case 4 -> personnage.seDeplacer(donjon);
                case 5 -> donjon.attaquerMonstre(personnage);
                case 6 -> {
                    if (donjon.itemSurCoordonnee(personnage.getCoordonnee()) && action > 1) {
                        System.out.println("Récupérer l'objet ? (o/n)");
                        if (Scan.demanderChoix()) {
                            personnage.recupererObjet(donjon, personnage.getCoordonnee());
                            return true;
                        } else return false;
                    } else throw new IllegalArgumentException("Action non disponible.");
                }
                default -> throw new NumberFormatException();
            }
        }
        else {
            switch (choix) {
                case 1 -> personnage.sEquiper();
                case 2 -> personnage.seDeplacer(donjon);
                case 3 -> donjon.attaquerMonstre(personnage);
                case 4 -> {
                    if (donjon.itemSurCoordonnee(personnage.getCoordonnee()) && action > 1) {
                        System.out.println("Récupérer l'objet ? (o/n)");
                        if (Scan.demanderChoix()) {
                            personnage.recupererObjet(donjon, personnage.getCoordonnee());
                            return true;
                        } else return false;
                    } else throw new IllegalArgumentException("Action non disponible.");
                }
                default -> throw new NumberFormatException();
            }
        }
        return true;
    }

    private static void jouerTourMonstre(Donjon donjon, Monstre monstre, int action) {
        System.out.println("Maitre du jeu - Jouer le monstre " + monstre.getNom());
        donjon.afficherCarte();
        monstre.afficherSituation();
        System.out.println(monstre.getNom() + " - Vous avez " + action + " action(s), que voulez-vous faire ?");

        boolean actionEffectuee = false;

        while (!actionEffectuee) {
            try {
                System.out.println("1 - Se déplacer");
                System.out.println("2 - Attaquer");

                int choix = Integer.parseInt(Scan.ScanLine().trim());

                switch (choix) {
                    case 1 -> monstre.seDeplacer(donjon);
                    case 2 -> donjon.attaquerPersonnage(monstre);
                    default -> throw new NumberFormatException();
                }

                actionEffectuee = true;

            } catch (Exception e) {
                System.out.println("Entrée invalide ou erreur : " + e.getMessage());
            }
        }
    }

    private static void demanderCommentaire(String auteur) {
        System.out.println(auteur + " - Écrivez votre commentaire :");
        System.out.println(auteur + " - " + Scan.ScanLine());
    }

}
