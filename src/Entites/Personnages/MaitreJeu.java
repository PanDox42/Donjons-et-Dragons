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

    public Donjon creerDonjon() {
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

        return donjon;
    }
}
