package Donjons;

import Addon.Scan;
import Entites.Entite;
import Entites.Personnages.MaitreJeu;
import Entites.Personnages.Personnage;

import java.util.ArrayList;

public class Jouer {
    public static void jouer() {
        Donjon donjon = null;
        int nbTour = 1;

        while(!donjon.joueurMort()) {
            // Prepare le donjon et création des joueurs si c'est le premier tour
            if (nbTour <= 1) {
                preparer(donjon);
            }
            tour(donjon, nbTour);
            nbTour++;
        }
    }

    private static Donjon preparerDonjonManuellement() {
        MaitreJeu mdj = new MaitreJeu();
        Donjon donjon = mdj.creerDonjon();
        donjon.placerObstaclesAvecConfirmation();
        donjon.placerMonstresAvecConfirmation();
        donjon.placerJoueursAvecConfirmation();
        donjon.equiperObjet();
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

    public static void preparer(Donjon donjon) {
        while (true) {
            try {
                System.out.println("Maître du Jeu - Souhaitez-vous créer le donjon manuellement ou choisir celui par défault ?");
                System.out.println("1 - Manuellement");
                System.out.println("2 - Par Default");

                String saisie = Scan.ScanLine().trim();
                int choix = Integer.parseInt(saisie);

                if (choix == 1) {
                    donjon = preparerDonjonManuellement();
                } else if (choix == 2) {
                    donjon = preparerDonjonDefault();
                } else {
                    throw new NumberFormatException();
                }

                System.out.println("Vous avez bien créée le donjon, ses obstacles, ses monstres ainsi que les joueurs !");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre (1 ou 2).");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void tour(Donjon donjon, int nbTour) {
        System.out.println("\n\n----------------------- TOUR n°" + nbTour + " ----------------------\n");

        // Initialise la liste de l'ordre de jeu des joueurs
        ArrayList<Entite> ordreJeuEntite = donjon.getOrdreEntite();

        // Faire jouer les joueurs et le maitre du jeu
        for (int i = 0; i < ordreJeuEntite.size(); i++) {
            for (int action = 3; action > 0; action--) {
                if (ordreJeuEntite.get(i).getType() == "Personnage") {

                    // Faire jouer le personnage
                    Personnage personnage = (Personnage) ordreJeuEntite.get(i);

                    System.out.println("\n*** C'est à " + personnage.getNom() + " de jouer ***\n");

                    donjon.afficherCarte();
                    personnage.afficherSituation();
                    System.out.println("Vous pouvez vous déplacer de " + personnage.getCaracteristiques().getVitesse() / 3 + " cases");

                    System.out.println(personnage.getNom() + " - Vous avez " + action + " action(s), que voulez vous faire ?");

                    boolean continuer = false;
                    while (!continuer) {
                        try {
                            System.out.println("1 - S'équiper (1pt d'action)");
                            System.out.println("2 - Se déplacer (1pt d'action)");
                            System.out.println("3 - Attaquer (1pt d'action)");

                            String saisie = Scan.ScanLine().trim();
                            int choix = Integer.parseInt(saisie);


                            if (choix == 1) {
                                // S'équiper
                                personnage.sEquiper();
                                continuer = true;
                            }

                            else if (choix == 2) {
                                // Se déplacer
                                personnage.seDeplacer(donjon);
                                if (donjon.itemSurCoordonnee(personnage.getCoordonnee())){
                                    if (action > 1){
                                        System.out.println("Voulez vous récupérer l'objet : " + donjon.getObjet(personnage.getCoordonnee()).getNom() + " ? (o/n)");
                                        // Pas fini, il doit pouvoir récuperer l'objet
                                    }

                                }

                                continuer = true;
                            }

                            else if (choix == 3) {
                                // Attaquer
                                donjon.attaquerMonstre(personnage); // Prend pas encore en compte la mort des monstres (ca affiche juste il est mort mais est tjr sur la map)
                                continuer = true;
                            }

                            else {
                                throw new NumberFormatException();
                            }
                            System.out.println(personnage.getNom() + " - Voulez vous commenter votre action ? (o/n)");
                            if (!Scan.demanderChoix()) {
                                System.out.println("Maitre du jeu - Voulez vous commenter cette action ? (o/n)");
                                if(Scan.demanderChoix()) {

                                }
                            }
                            else {
                                
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Entrée invalide. Veuillez entrer un nombre (1, 2 ou 3).");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    // Faire jouer le maitre du jeu
                }

                System.out.println("Cet action a couté 1 point d'action");
            }
        }
    }
}
