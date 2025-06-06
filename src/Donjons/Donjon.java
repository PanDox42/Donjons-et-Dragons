package Donjons;

import Entites.Entite;
import Entites.Personnages.MaitreJeu;
import Entites.Personnages.Monstre.Monstre;
import Entites.Personnages.Personnage;
import Addon.Scan;
import Objets.Objet;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Donjon {
    private MaitreJeu m_mdj = null;
    private Coordonnee m_coordonnee = null;
    private Contenu[][][] m_donjon_contenu;
    private String m_contexte = "";
    private List<String> m_joueurNom;
    private List<Personnage> m_joueur;
    private Map<String, Integer> m_dicoMonstre;
    private ArrayList<String> m_alphabet = new ArrayList<>(Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    ));

    public Donjon(int x, int y, MaitreJeu mdj)
    {
        m_coordonnee = new Coordonnee(x, y);
        m_donjon_contenu = new Contenu[m_coordonnee.getX()][m_coordonnee.getY()][2];
        m_joueurNom = new ArrayList<>();
        m_joueur = new ArrayList<>();
        m_mdj = mdj;
        m_dicoMonstre = new HashMap<String, Integer>();
    }

    public Coordonnee getCoordonnee() {
        return m_coordonnee;
    }

    public void placerObstacle(int x, int y, Obstacle obstacle) {
        verifierDeplacerContenuValide(obstacle, new Coordonnee(x, y));

        obstacle.m_coordonnee = new Coordonnee(x, y);

        modifierDonjon(obstacle, obstacle.m_coordonnee);
    }

    public void placerObstaclesAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                Obstacle obstacle = new Obstacle();
                System.out.println("Maître du Jeu - Veuillez indiquer les obstacles à créer dans le donjon : \n(exemple : pour mettre un obstacle à l'endroit A:5 vous devez indiquer A:5)\n");
                int[] XY = convertirCoordonnnee(Scan.ScanLine());
                placerObstacle(XY[0], XY[1], obstacle);

                afficherCarte();

                System.out.println("Maître du Jeu - Voulez-vous ajouter un autre obstacle ? (o/n)");

                String reponse = Scan.ScanLine().trim().toLowerCase();

                while (!reponse.equals("o") && !reponse.equals("n")) {
                    System.out.println("Réponse invalide. Veuillez répondre par 'o' ou 'n'.");
                    reponse = Scan.ScanLine().trim().toLowerCase();
                }

                if (reponse.equals("n")) {
                    continuer = false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void placerJoueur(int x, int y, Personnage joueur) {
        verifierDeplacerContenuValide(joueur, new Coordonnee(x, y));

        joueur.m_coordonnee = new Coordonnee(x, y);

        modifierDonjon(joueur, joueur.m_coordonnee);

        m_joueurNom.add(joueur.getNom().toUpperCase());
        m_joueur.add(joueur);
    }

    public void placerJoueursAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                Personnage joueur = PreparerTour.demanderJoueur(m_joueurNom);

                while(true) {
                    try {
                        System.out.println("Ajout du/des Joueur(s) :\n\nVeuillez indiquer les coordonnées du joueur à placer dans le donjon : \n(exemple : pour placer le joueur à l'endroit A:5 vous devez indiquer A:5)\n");
                        int[] XY = convertirCoordonnnee(Scan.ScanLine());
                        placerJoueur(XY[0], XY[1], joueur); // Appel de votre fonction existante
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                afficherCarte();

                System.out.println("Maître du Jeu - Voulez-vous ajouter un autre joueur ? (o/n)");

                String reponse = Scan.ScanLine().trim().toLowerCase();

                while (!reponse.equals("o") && !reponse.equals("n")) {
                    System.out.println("Réponse invalide. Veuillez répondre par 'o' ou 'n'.");
                    reponse = Scan.ScanLine().trim().toLowerCase();
                }

                if (reponse.equals("n")) {
                    continuer = false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void placerMonstre(int x, int y, Monstre monstre) {
        verifierDeplacerContenuValide(monstre, new Coordonnee(x, y));

        monstre.m_coordonnee = new Coordonnee(x, y);

        modifierDonjon(monstre, monstre.m_coordonnee);
    }

    public void placerMonstresAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                Monstre monstre = PreparerTour.creerMonstre(this);
                ajouterEspeceDicoMonstre(monstre.getEspece().toUpperCase());

                while(true) {
                    try {
                        System.out.println("Veuillez indiquer les coordonnées du Monstre à placer dans le donjon : \n(exemple : pour placer le joueur à l'endroit A:5 vous devez indiquer A:5)\n");
                        int[] XY = convertirCoordonnnee(Scan.ScanLine());

                        placerMonstre(XY[0], XY[1], monstre);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                afficherCarte();

                System.out.println("Maître du Jeu - Voulez-vous ajouter un autre monstre ? (o/n)");

                String reponse = Scan.ScanLine().trim().toLowerCase();

                while (!reponse.equals("o") && !reponse.equals("n")) {
                    System.out.println("Réponse invalide. Veuillez répondre par 'o' ou 'n'.");
                    reponse = Scan.ScanLine().trim().toLowerCase();
                }

                if (reponse.equals("n")) {
                    continuer = false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void placerObjet(int x, int y, Objet objet) {
        verifierDeplacerContenuValide(objet, new Coordonnee(x, y));

        objet.setCoordonnee(new Coordonnee(x, y));
        modifierDonjon(objet, objet.getCoordonnee());
    }

    public void placerObjetsAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                Objet objet = PreparerTour.creerObjet();
                while(true) {
                    try {
                        System.out.println("Veuillez indiquer les coordonnées de l'objet à placer dans le donjon : \n(exemple : pour placer le joueur à l'endroit A:5 vous devez indiquer A:5)\n");
                        int[] XY = convertirCoordonnnee(Scan.ScanLine());

                        placerObjet(XY[0], XY[1], objet);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                afficherCarte();

                System.out.println("Maître du Jeu - Voulez-vous ajouter un autre objet ? (o/n)");

                String reponse = Scan.ScanLine().trim().toLowerCase();

                while (!reponse.equals("o") && !reponse.equals("n")) {
                    System.out.println("Réponse invalide. Veuillez répondre par 'o' ou 'n'.");
                    reponse = Scan.ScanLine().trim().toLowerCase();
                }

                if (reponse.equals("n")) {
                    continuer = false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void afficherCarte(){
        System.out.print("\n      ");
        for(int i = 0; i < m_coordonnee.getX(); i++) {
            System.out.print(m_alphabet.get(i)+"  ");
        }
        System.out.println();

        System.out.print("   *--");
        for(int i = 0; i < m_coordonnee.getX(); i++) {
            System.out.print("---");
        }
        System.out.println("*");

        for(int i = 0; i < m_coordonnee.getY(); i++) {
            if(i<=9) {
                System.out.print(i+"  | ");
            }
            else {
                System.out.print(i+" | ");
            }
            for(int j = 0; j < m_coordonnee.getX(); j++) {
                if (!(m_donjon_contenu[j][i][0]==null && m_donjon_contenu[j][i][1]==null)) {
                    System.out.print(quoiAfficher(m_donjon_contenu[j][i]).getSymbole());
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println(" |");
        }

        System.out.print("   *--");
        for(int i = 0; i < m_coordonnee.getX(); i++) {
            System.out.print("---");
        }
        System.out.println("*\n* Equipement   |   [ ] Obstacle  |\n");
    }

    public void deplacerEntite(Entite entite) {
        while(true) {
            int[] XY = convertirCoordonnnee(Scan.ScanLine());
            Coordonnee coordonnee = new Coordonnee(XY[0], XY[1]);
            if(verifierDeplacerContenuValide(entite, coordonnee)) {
                entite.m_coordonnee = coordonnee;
                afficherCarte();
                break;
            }
        }
    }

    public boolean detecterEntiteCase(Contenu[] listContenu) {
        if(Objects.equals(listContenu[1], null)) {
            return false;
        }
        else {
            return true;
        }
    }

    public Contenu quoiAfficher(Contenu[] listContenu) {
        if(detecterEntiteCase(listContenu)) {
            return listContenu[1];
        }
        else {
            return listContenu[0];
        }
    }

    public int[] convertirCoordonnnee(String coordonnee) {
        int[] result = new int[2];
        String[] coordonneeSplit = coordonnee.split(":");
        if (coordonneeSplit.length != 2) {
            throw new IllegalArgumentException("Format incorrect. Utilisez le format x:x.");
        }
        int x = -1;
        for(int i = 0; i < m_alphabet.size(); i++) {
            if(m_alphabet.get(i).equals(coordonneeSplit[0])) {
                x = i;
            }
        }
        if (x == -1) {
            throw new IllegalArgumentException("Colonne invalide.");
        }
        int y = Integer.parseInt(coordonneeSplit[1]);

        result[0] = x;
        result[1] = y;

        return result;
    }

    public boolean verifierDeplacerContenuValide(Contenu contenu, Coordonnee coordonnee) {
        if(coordonnee.getX()<0 || coordonnee.getY()<0 || coordonnee.getX()>m_coordonnee.getX() || coordonnee.getY()>m_coordonnee.getY()) {
            throw new IllegalArgumentException("L'emplacement est en dehors du donjon");
        }
        if(m_donjon_contenu[coordonnee.getX()][coordonnee.getY()][0] == null && m_donjon_contenu[coordonnee.getX()][coordonnee.getY()][1] == null) {
            return true;
        }
        else if(m_donjon_contenu[coordonnee.getX()][coordonnee.getY()][0] == null) {
            if(Objects.equals(contenu.getSymbole(), " * ")) {
                return true;
            }
            else if(Objects.equals(contenu.getSymbole(), "[ ]")) {
                return true;
            }
        }
        else if(Objects.equals(m_donjon_contenu[coordonnee.getX()][coordonnee.getY()][0].getSymbole(), " * ")) {
            if(Objects.equals(contenu.getSymbole(), " * ")) {
                throw new IllegalArgumentException("L'emplacement contient déjà un objet");
            }
            else if(Objects.equals(contenu.getSymbole(), "[ ]")) {
                throw new IllegalArgumentException("L'emplacement contient déjà un objet");
            }
        }
        else if(Objects.equals(m_donjon_contenu[coordonnee.getX()][coordonnee.getY()][0].getSymbole(), "[ ]")) {
            throw new IllegalArgumentException("L'emplacement contient déjà un obstacle");
        }
        else if(Objects.equals(m_donjon_contenu[coordonnee.getX()][coordonnee.getY()][1], null)) {
            if(!Objects.equals(contenu.getSymbole(), " * ") && !Objects.equals(contenu.getSymbole(), "[ ]")) {
                return true;
            }
        }
        throw new IllegalArgumentException("Endroit non disponible");
    }

    public void modifierDonjon(Contenu contenu, Coordonnee coordonee) {
        boolean verifDeplacement = verifierDeplacerContenuValide(contenu, coordonee);
        if(verifDeplacement) {
            if(Objects.equals(contenu.getSymbole(), "[ ]") || Objects.equals(contenu.getSymbole(), " * ")) {
                m_donjon_contenu[coordonee.getX()][coordonee.getY()][0] = contenu;
            }
            else {
                m_donjon_contenu[coordonee.getX()][coordonee.getY()][1] = contenu;
            }
        }
    }

    public void modifierContexte(String contexte) {
        m_contexte = contexte;
    }

    public String raconterTourMdj() {
        return m_mdj.raconterTour();
    }

    public Integer getNombreEspeceMonstre(String espece) {
        return m_dicoMonstre.getOrDefault(espece.toUpperCase(), 0);
    }

    public void ajouterEspeceDicoMonstre(String espece) {
        String cle = espece.toUpperCase();
        int compteur = m_dicoMonstre.getOrDefault(cle, 0);
        m_dicoMonstre.put(cle, compteur + 1);
    }

    public List<Personnage> getJoueur() {
        return m_joueur;
    }

    public void equiperArmureJoueur() {

    }

    public void equiperObjet() {
        System.out.println("Equiper les objets :");
        for(Personnage p : m_joueur) {
            System.out.println(p.getNom() + " - Voulez-vous équiper un objet ? (o/n)");

            String reponse = Scan.ScanLine().trim().toLowerCase();

            while (!reponse.equals("o") && !reponse.equals("n")) {
                System.out.println("Réponse invalide. Veuillez répondre par 'o' ou 'n'.");
                reponse = Scan.ScanLine().trim().toLowerCase();
            }

            if (reponse.equals("o")) {
                for(int i = 0; i<p.getInventaire().size(); i++) {
                    System.out.println("["+ i + "] " + p.getInventaire().get(i).getNom());
                }
                System.out.println(p.getNom() + " - Quel objet voulez-vous équiper ? (rentrer son numéro)");
                try {
                    int num = 0;
                    try {
                        num = parseInt(Scan.ScanLine());
                    } catch(Exception e) {
                        System.out.println("Le numéro n'est pas valide");
                    }
                    if(p.getInventaire().get(num)) {

                    }
                    p.equiperObjet(num);
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
