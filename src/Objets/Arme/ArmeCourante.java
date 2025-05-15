package Objets.Arme;

import Des.De;

public class ArmeCourante extends Arme {
    public ArmeCourante(String nom, int porte, De deAttaque) {
        super(nom, porte, deAttaque);
    }

    public static ArmeCourante creerMasseArmes(){
        return new ArmeCourante("Masse d'armes", 1, new De(1,6));
    }

    public static ArmeCourante creerBaton(){
        return new ArmeCourante("Bâton", 1, new De(1,6));
    }
}
