package Des;

import java.util.Random;
import java.util.Scanner;
import Scanner.Scan;

// Cette classe sert à appeler la fonction lancer en faisant De.lancer(nbDes, nbFaces)
// Cette fonction renvoie un nombre aléatoire entre 1 et (nbFace * nbDes)
public class De {
    private int m_nbDes;
    private int m_nbFaces;

    public De(int nbDes, int nbFaces){
       m_nbDes = nbDes;
       m_nbFaces = nbFaces;
    }

    public int get_nbDes(){
        return m_nbDes;
    }

    public int get_nbFaces(){
        return m_nbFaces;
    }

    public int lancer(){
        return lancerDes(m_nbDes, m_nbFaces);
    }

    public static int lancer(int nbDes, int nbFaces){
        return lancerDes(nbDes, nbFaces);
    }

    private static int lancerDes(int nbDes, int nbFaces){

        // Evite une erreur si la fonction est mal utilisée
        if (nbFaces <= 0 || nbDes <= 0){
            return 0;
        }

        Random random = new Random();

        // Somme des lancés (valeur finale)
        int valeur = 0;

        // Tableau qui stock les valeurs des lancée
        int[] tabValeur = new int[nbDes];

        System.out.println("Lancer " + nbDes + " dé(s) de " + nbFaces + " faces (appuyer sur ENTRER)");
        Scan.ScanLine(); // Attente d'une touche

        // Boucle calculant la valeur des lancées de dés aléatoire
        for (int i = 0; i < nbDes; i++){

            // Création de la variable lance et initialisation à un nombre aléatoire entre 1 et nbFabes inclu
            int lance = random.nextInt(1,nbFaces + 1);

            // Ajout de la valeur dans le tableau
            tabValeur[i] = lance;

            valeur += lance;
        }

        // Gere l'affichage des lancés executés à l'aide d'une boucle for qui parcourt le tableau tabValeur
        System.out.print("--- Vous avez obtenu : ");

        for (int i = 0; i < nbDes; i++){
            System.out.print(tabValeur[i] + " ");
        }
        System.out.println("---\n");

        return valeur;
    }
}
