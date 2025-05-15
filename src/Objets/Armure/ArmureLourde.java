package Objets.Armure;

public class ArmureLourde extends Armure {
    public ArmureLourde(String nom, int classe){
        super(nom, classe);
    }

    public static ArmureLourde creerCotteDeMaille() {
        return new ArmureLourde("Cotte de mailles", 11);
    }
}
