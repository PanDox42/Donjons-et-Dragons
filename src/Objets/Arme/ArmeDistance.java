package Objets.Arme;

import Des.De;

public class ArmeDistance extends Arme {
    public ArmeDistance(String nom, int porte, De deAttaque) {
        super(nom, porte, deAttaque);
    }

    public static ArmeDistance creerArbaleteLegere(){
        return new ArmeDistance("Arbalète légère", 16, new De(1,8));
    }

    public static ArmeDistance creerFronde(){
        return new ArmeDistance("Fronde", 6, new De(1,4));
    }

    public static ArmeDistance creerArcCourt(){
        return new ArmeDistance("Arc court", 16, new De(1,6));
    }
}

