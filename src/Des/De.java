package Des;

import java.util.Random;
import java.util.Scanner;

// Cette classe sert à appeler la fonction lancer en faisant De.lancer(nbDes, nbFaces)
// Cette fonction renvoie un nombre aléatoire entre 1 et (nbFace * nbDes)
public class De {

    public static int lancer(int nbDes, int nbFaces){

        // Evite une erreur si la fonction est mal utilisée
        if (nbFaces <= 0){
            return 0;
        }

        Random random = new Random();
        int valeur = 0;

        // Boucle calculant la valeur des lancées de dés aléatoire
        System.out.println("Vous allez lancer " + nbDes + " dé(s) de " + nbFaces + " faces\n");

        Scanner scanner = new Scanner(System.in); // Création d'un scanner
        for (int i = 0; i < nbDes; i++){
            System.out.println("Lancer un dé de " + nbFaces + " (appuyer sur ENTRER)");
            scanner.nextLine(); // Attente d'une touche

            int lance = random.nextInt(1,nbFaces + 1);

            System.out.println("--- Vous avez fait " + lance + " ---");
            valeur += lance;
        }
        scanner.close(); // Fermer le scanner

        return valeur;
    }
}
