package Addon;

import java.util.Random;

// Cette classe sert à appeler la fonction lancer en faisant Addon.De.lancer(nbDes, nbFaces)
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
        return lancerDes(m_nbDes, m_nbFaces);
    }

    public static int lancer(int nbDes, int nbFaces){
        return lancerDes(nbDes, nbFaces);
    }

    // Partie logique : effectue les lancers de dés et retourne les valeurs
    private static int[] lancerDesValeurs(int nbDes, int nbFaces) {
        if (nbDes <= 0 || nbFaces <= 0) {
            return new int[0]; // tableau vide
        }

        Random random = new Random();
        int[] resultats = new int[nbDes];

        for (int i = 0; i < nbDes; i++) {
            resultats[i] = random.nextInt(1, nbFaces + 1);
        }

        return resultats;
    }

    // Partie affichage : affiche les lancers et retourne la somme
    private static int lancerDes(int nbDes, int nbFaces) {
        System.out.println("Lancer " + nbDes + " dé(s) de " + nbFaces + " faces (appuyer sur ENTRER)");
        Scan.ScanLine(); // Pause

        int[] resultats = lancerDesValeurs(nbDes, nbFaces);

        if (resultats.length == 0) {
            System.out.println("Paramètres invalides pour le lancer de dés.");
            return 0;
        }

        // Affichage des résultats
        System.out.print("--- Vous avez obtenu : ");
        int somme = 0;
        for (int val : resultats) {
            System.out.print(val + " ");
            somme += val;
        }
        System.out.println("---\n");

        return somme;
    }

    public static De convertirStringDe(String deString) {
        String saisie = deString.toLowerCase().trim();

        if (!saisie.matches("\\d+d\\d+")) {
            throw new IllegalArgumentException("Format invalide. Utilisez le format XdY, par exemple 2d6.");
        }

        String[] parts = saisie.split("d");
        int nbDes = Integer.parseInt(parts[0]);
        int nbFaces = Integer.parseInt(parts[1]);

        if (nbDes <= 0 || nbFaces <= 0) {
            throw new IllegalArgumentException("Le nombre de dés et de faces doit être supérieur à zéro.");
        }

        return new De(nbDes, nbFaces);
    }

    @Override
    public String toString() {
        return m_nbDes+"d"+m_nbFaces;
    }
}
