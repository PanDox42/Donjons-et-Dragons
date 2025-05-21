package Entites.Personnages;

import Donjons.Donjon;
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
import Addon.Scan;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MaitreJeu{
    private static ArrayList<String> tour = new ArrayList<>();

    public MaitreJeu(){};

    public static void ajouterLignes(String phrase){
        tour.add(phrase);
    }

    public void raconterTour(){
        for (int i = 0; i < tour.size(); i++){
            System.out.println(tour.get(i));
        }
        tour.clear();
    }

    public void creerDonjon() {
        int x;
        int y;
        // Afficher message de création du donjon pour le maitre du jeu
        System.out.println("Veuillez indiquer la dimension du donjon entre 15 et 25\nExemple : vous voulez créer un donjon de la taille 25x25 alors rentrer : 25:25");
        while(true) {
            try {
                String dimensions = Scan.ScanLine();
                x = parseInt(dimensions.split(":")[0]);
                y = parseInt(dimensions.split(":")[1]);
                if(x < 15 || y < 15 || x > 25 || y> 25) {
                    throw new IllegalAccessException("La taille du donjon doit être compris entre 15 et 25");
                }
                break;
            }
            catch(IllegalAccessException iae) {
                System.out.println(iae.getMessage());
            }
            catch(Exception e) {
                System.out.println("Le format n'est pas le bon, merci de mettre x:x");
            }
        }

        // Appeler creator de donjon
        Donjon donjon = new Donjon(x,y);

        // Appeler creationObstacle
        donjon.creationObstacle();
        while(true) {
            System.out.println("Voulez-vous continuer à créer un obstacle ?\nSi non, alors entrez 0\nSi oui, alors entrez 1");
            if(parseInt(Scan.ScanLine())==0) {
                break;
            }
            else {
                donjon.creationObstacle();
            }
        }

        while(true) {
            // Appeler placerEquipement
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

                donjon.placerEquipement(armureObjet);
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

                donjon.placerEquipement(armeObjet);
            }

            System.out.println("Voulez-vous continuer à placer des objets ?\nSi non, alors entrez 0\nSi oui, alors entrez 1");
            if(parseInt(Scan.ScanLine())==0) {
                break;
            }
        }
    }
}
