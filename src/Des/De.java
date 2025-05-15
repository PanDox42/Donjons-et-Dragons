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

        // Evite une erreur si la fonction est mal utilisée
        if (m_nbFaces <= 0){
            return 0;
        }

        Random random = new Random();
        int valeur = 0;

        // Boucle calculant la valeur des lancées de dés aléatoire
        System.out.println("Vous allez lancer " + m_nbDes + " dé(s) de " + m_nbFaces + " faces\n");
        for (int i = 0; i < m_nbDes; i++){
            System.out.println("Lancer un dé de " + m_nbFaces + " (appuyer sur ENTRER)");
            Scan.ScanLine(); // Attente d'une touche

            int lance = random.nextInt(1,m_nbFaces + 1);

            System.out.println("--- Vous avez fait " + lance + " ---");
            valeur += lance;
        }

        return valeur;
    }


    public static int lancer(int nbDes, int nbFaces){

        // Evite une erreur si la fonction est mal utilisée
        if (nbFaces <= 0){
            return 0;
        }

        Random random = new Random();
        int valeur = 0;

        // Boucle calculant la valeur des lancées de dés aléatoire
        System.out.println("Vous allez lancer " + nbDes + " dé(s) de " + nbFaces + " faces\n");
        for (int i = 0; i < nbDes; i++){
            System.out.println("Lancer un dé de " + nbFaces + " (appuyer sur ENTRER)");
            Scan.ScanLine(); // Attente d'une touche

            int lance = random.nextInt(1,nbFaces + 1);

            System.out.println("--- Vous avez fait " + lance + " ---");
            valeur += lance;
        }

        return valeur;
    }
}
