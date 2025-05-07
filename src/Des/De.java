package Des;

import java.util.Random;


public class De {

    public static int lancer(int nbDes, int nbFaces){
        Random random = new Random();
        int valeur = 0;

        for (int i = 0; i < nbDes; i++){
            valeur += random.nextInt(1,nbFaces + 1);
        }

        return valeur;
    }
}
