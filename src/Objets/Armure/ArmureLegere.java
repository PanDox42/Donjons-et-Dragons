package Objets.Armure;

public class ArmureLegere extends Armure {
    public ArmureLegere(String nom, int classe) {
        super(nom, classe);
    }

    public static ArmureLegere creerArmureEcailles(){
        return new ArmureLegere("Armure d'écailles", 9);
    }
}
