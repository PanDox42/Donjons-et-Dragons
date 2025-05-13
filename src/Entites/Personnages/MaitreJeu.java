package Entites.Personnages;

import Entites.Personnages.Classes.Classe;
import Entites.Personnages.Races.Race;
import java.util.ArrayList;

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
}
