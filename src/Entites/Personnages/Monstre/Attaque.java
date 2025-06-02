package Entites.Personnages.Monstre;

import Addon.De;
import Addon.Scan;

import static java.lang.Integer.parseInt;

public class Attaque {
    private int m_portee;
    private De m_degat;

    public Attaque(int portee, De degat){
        m_portee = portee;
        m_degat = degat;
    }

    public int genererAttaque(){
        return m_degat.lancer();
    }

    public static Attaque creerAttaque() {
        while (true) {
            try {
                // Saisie de la portée
                System.out.println("Veuillez saisir la portée de l'attaque :");
                int portee = Integer.parseInt(Scan.ScanLine());

                // Saisie du dé sous forme XdY
                System.out.println("Veuillez saisir le nombre de dés et de faces (exemple : 3d4) :");
                String saisie = Scan.ScanLine().toLowerCase().trim();

                if (!saisie.matches("\\d+d\\d+")) {
                    throw new IllegalArgumentException("Format invalide. Utilisez le format XdY, par exemple 2d6.");
                }

                String[] parts = saisie.split("d");
                int nbDes = Integer.parseInt(parts[0]);
                int nbFaces = Integer.parseInt(parts[1]);

                if (nbDes <= 0 || nbFaces <= 0) {
                    throw new IllegalArgumentException("Le nombre de dés et de faces doit être supérieur à zéro.");
                }

                De de = new De(nbDes, nbFaces);
                return new Attaque(portee, de);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : vous devez entrer un nombre entier valide.");
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erreur inattendue : " + e.getMessage());
            }
        }
    }


    public De getDeAttaque(){
        return m_degat;
    }
}
