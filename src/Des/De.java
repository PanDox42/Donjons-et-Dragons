package Des;

import java.util.Random;

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
        for (int i = 0; i < nbDes; i++){
            valeur += random.nextInt(1,nbFaces + 1);
        }

        return valeur;
    }
}
