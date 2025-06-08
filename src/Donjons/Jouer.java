package Donjons;

import Addon.Scan;
import Entites.Entite;
import Entites.Personnages.MaitreJeu;
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
                tour(donjon, nbTour);
                nbTour++;
            }
        }
        System.out.println("La partie est temrinée");
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

        // Initialise la liste de l'ordre de jeu des joueurs et des monstres
        ArrayList<Entite> ordreJeuEntite = donjon.getOrdreEntite();

        // Faire jouer les joueurs et le maitre du jeu
        for(int i = 0; i < ordreJeuEntite.size(); i++) {
            for(int action = 3; action > 0; action--) {
                if (ordreJeuEntite.get(i).getType() == "Personnage") {

                    // Faire jouer le personnage
                    Personnage personnage = (Personnage) ordreJeuEntite.get(i);

                    // Affichage de qui joue
                    System.out.println("\n*** C'est à " + personnage.getNom() + " de jouer ***\n");

                    // Affichage des infos pour prendre un décision de jeu
                    donjon.afficherCarte();
                    personnage.afficherSituation();
                    System.out.println("Vous pouvez vous déplacer de " + personnage.getCaracteristiques().getVitesse() / 3 + " cases");

                    // Affichage du nombre d'action encore disponible
                    System.out.println(personnage.getNom() + " - Vous avez " + action + " action(s), que voulez vous faire ?");

                    boolean continuer = false;

                    while (!continuer) {
                        try {
                            System.out.println("1 - S'équiper (1pt d'action)");
                            System.out.println("2 - Se déplacer (1pt d'action)");
                            System.out.println("3 - Attaquer (1pt d'action)");

                            // Il faut que le joueur soit sur une case avec un item ET qu'il ait encore assez d'action pour le recuperer
                            boolean recupItemSurJoueurPossible = donjon.itemSurCoordonnee(personnage.getCoordonnee()) && action > 1;
                            if(recupItemSurJoueurPossible){
                                System.out.println("4 - Récupérer " + donjon.getObjet(personnage.getCoordonnee()).getNom() + " se trouvant sur votre case (1pt d'action)");
                            }

                            String saisie = Scan.ScanLine().trim();
                            int choix = Integer.parseInt(saisie);


                            if (choix == 1) {

                                // S'équiper
                                personnage.sEquiper();
                                continuer = true;

                                System.out.println("Cet action a couté 1 point d'action");
                            }

                            else if (choix == 2) {

                                // Se déplacer
                                personnage.seDeplacer(donjon);

                                continuer = true;
                                System.out.println("Cet action a couté 1 point d'action");
                            }

                            else if (choix == 3) {

                                // Attaquer
                                donjon.attaquerMonstre(personnage); // Prend en compte la mort
                                continuer = true;
                                System.out.println("Cet action a couté 1 point d'action");
                            }

                            else if(choix == 4 && recupItemSurJoueurPossible) {
                                System.out.println("il vous reste " + action + " pt(s) d'action");

                                System.out.println("Voulez vous récupérer l'objet : " + donjon.getObjet(personnage.getCoordonnee()).getNom() + " ? (1pt d'action) (o/n)");

                                boolean choixRecupererObjet = Scan.demanderChoix();

                                if(choixRecupererObjet) {
                                    personnage.recupererObjet(donjon, personnage.getCoordonnee());
                                    continuer = true;
                                    System.out.println("Cet action a couté 1 point d'action");
                                }

                                else{
                                    System.out.println("Cet action n'a couté aucun point d'action");
                                }
                            }

                            else {
                                throw new NumberFormatException();
                            }

                            System.out.println(personnage.getNom() + " - Voulez vous commenter votre action ? (o/n)");
                            if (!Scan.demanderChoix()) {
                                System.out.println("Maitre du jeu - Voulez vous commenter cette action ? (o/n)");
                                if(Scan.demanderChoix()) {
                                    System.out.println("Maitre du jeu - Écrivez votre commentaire :");
                                    System.out.println("Maitre du jeu - " + Scan.ScanLine());
                                }
                            }
                            else {
                                System.out.println(personnage.getNom() + " - Écrivez votre commentaire :");
                                System.out.println(personnage.getNom() + " - " + Scan.ScanLine());
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
            }
        }
    }
}
