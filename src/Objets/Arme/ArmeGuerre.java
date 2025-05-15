package Objets.Arme;

import Des.De;

public class ArmeGuerre extends Arme {
    public ArmeGuerre(String nom, int porte, De deAttaque) {
        super(nom, porte, deAttaque);
    }

    public static ArmeGuerre creerEpeeLongue(){
        return new ArmeGuerre("Épée longue", 1, new De(1,8));
    }

    public static ArmeGuerre creerRapiere(){
        return new ArmeGuerre("Rapière", 1, new De(1,8));
    }
}
