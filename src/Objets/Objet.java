package Objets;


// Objet est le type abstrait de la classe armure et arme
// Cela servira à créer une ArrayListe de type <Objet> qui representera
// l'inventaire d'un Joueur, contenant potentiellement des armes ET
// des armures

public interface Objet {
    public String getNom();

    // Servant à connaitre les objets que le joueur possède en main
    public boolean estEquipe();
}
