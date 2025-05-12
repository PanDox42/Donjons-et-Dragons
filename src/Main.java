import Des.De;
import Donjons.Donjon;
import Entites.Personnages.Classes.Classe;
import Entites.Personnages.Classes.Clerc;
import Entites.Personnages.Classes.Guerrier;
import Entites.Personnages.Personnage;
import Entites.Personnages.Races.Elfe;
import Entites.Personnages.Races.Race;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons\n");

        // QUE DES TESTS
        System.out.println("Vous avez réalisé un score de " + De.lancer(4,10));

        Donjon d = new Donjon();
        d.afficherCarte();

        Race r = new Elfe(); // La dcp il aura 16pts de vie psk c'est un elfe
        Classe c = new Clerc();
        Personnage bob = new Personnage("King Kong", r, c);

        bob.afficherSituation();

        // FIN TESTS
    }
}