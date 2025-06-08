package Donjons;

import Addon.Scan;
import Entites.Entite;
import Entites.Personnages.MaitreJeu;
import Entites.Personnages.Monstre.Monstre;
import Entites.Personnages.Personnage;
import Objets.Objet;

import java.util.*;

public class Donjon {
    private MaitreJeu m_mdj = null;
    private Coordonnee m_coordonnee = null;
    private Contenu[][][] m_donjon_contenu;
    private String m_contexte = "";
    private ArrayList<String> m_joueurNom;
    private ArrayList<Personnage> m_joueurs;
    private Map<String, Integer> m_dicoMonstre;
    private ArrayList<Monstre> m_monstres;
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
        m_joueurs = new ArrayList<>();
        m_monstres = new ArrayList<>();
        m_mdj = mdj;
        m_dicoMonstre = new HashMap<String, Integer>();
    }

    public Coordonnee getCoordonnee() {
        return m_coordonnee;
    }

    public void placerObstacle(int x, int y, Obstacle obstacle) {
        verifierDeplacerContenuValide(obstacle, new Coordonnee(x, y));

        obstacle.setCoordonnee(new Coordonnee(x, y));

        modifierDonjon(obstacle, obstacle.getCoordonnee());
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

                if (!Scan.demanderChoix()) {
                    continuer = false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void placerJoueur(int x, int y, Personnage joueur) {
        verifierDeplacerContenuValide(joueur, new Coordonnee(x, y));

        joueur.setCoordonnee(x, y);

        modifierDonjon(joueur, joueur.getCoordonnee());

        m_joueurNom.add(joueur.getNom().toUpperCase());
        m_joueurs.add(joueur);
    }

    public void placerJoueursAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                Personnage joueur = PreparerTour.demanderJoueur(m_joueurNom);

                while(true) {
                    afficherCarte();
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

                if (!Scan.demanderChoix()) {
                    continuer = false;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void placerMonstre(int x, int y, Monstre monstre) {
        verifierDeplacerContenuValide(monstre, new Coordonnee(x, y));

        monstre.setCoordonnee(x, y);
        m_monstres.add(monstre);

        modifierDonjon(monstre, monstre.getCoordonnee());
    }

    public void placerMonstresAvecConfirmation() {
        boolean continuer = true;

        while (continuer) {
            try {
                Monstre monstre = PreparerTour.creerMonstre(this);
                ajouterEspeceDicoMonstre(monstre.getEspece().toUpperCase());
                m_monstres.add(monstre);

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

                if (!Scan.demanderChoix()) {
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

                if (!Scan.demanderChoix()) {
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
                if(m_donjon_contenu[j][i]!=null) {
                    System.out.print(quoiAfficher(m_donjon_contenu[j][i]));
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
        while (true) {
            System.out.println("Où voulez vous vous déplacer ? (indiquez les coodronnées comme ça : A:5)");

            int[] XY = convertirCoordonnnee(Scan.ScanLine());
            Coordonnee newCoordonnee = new Coordonnee(XY[0], XY[1]);

            if (verifierDeplacerContenuValide(entite, newCoordonnee)) {
                if (entite instanceof Personnage){
                    Personnage perso = (Personnage) entite;
                    if ((double) perso.getCaracteristiques().getVitesse() / 3 < distanceEntreCoordonnee(entite.getCoordonnee(), newCoordonnee)) {
                        System.out.println("Votre vitesse ne permet pas d'atteindre cette case en un coup");

                        System.out.println("Où voulez vous vous déplacer ? (indiquez les coodronnées comme ça : A:5)");
                    }
                    else {
                        // Retirer l'entité de l'ancienne position
                        Coordonnee ancienneCoordonnee = entite.getCoordonnee();
                        m_donjon_contenu[ancienneCoordonnee.getX()][ancienneCoordonnee.getY()][1] = null;

                        // Ajouter l'entité à la nouvelle position
                        m_donjon_contenu[newCoordonnee.getX()][newCoordonnee.getY()][1] = entite;

                        // Mettre à jour sa coordonnée
                        entite.setCoordonnee(newCoordonnee);

                        // Afficher la carte
                        afficherCarte();
                        break;
                    }
                }
                if (entite instanceof Monstre){
                    Monstre monstre = (Monstre) entite;
                    if ((double) monstre.getCaracteristiques().getVitesse() / 3 < distanceEntreCoordonnee(entite.getCoordonnee(), newCoordonnee)) {
                        System.out.println("Votre vitesse ne permet pas d'atteindre cette case en un coup");

                        System.out.println("Où voulez vous vous déplacer ? (indiquez les coodronnées comme ça : A:5)");
                    }
                    else {
                        // Retirer l'entité de l'ancienne position
                        Coordonnee ancienneCoordonnee = entite.getCoordonnee();
                        m_donjon_contenu[ancienneCoordonnee.getX()][ancienneCoordonnee.getY()][1] = null;

                        // Ajouter l'entité à la nouvelle position
                        m_donjon_contenu[newCoordonnee.getX()][newCoordonnee.getY()][1] = entite;

                        // Mettre à jour sa coordonnée
                        entite.setCoordonnee(newCoordonnee);

                        // Afficher la carte
                        afficherCarte();
                        break;
                    }
                }
            }
        }
    }

    private double distanceEntreCoordonnee(Coordonnee depart, Coordonnee arrivee){
        return Math.sqrt(Math.pow(depart.getX() - arrivee.getX(), 2) + Math.pow(depart.getY() - arrivee.getY(), 2)); // Formule chat gpt
    }

    public boolean detecterEntiteCase(Contenu[] listContenu) {
        if(Objects.equals(listContenu[1], null)) {
            return false;
        }
        else {
            return true;
        }
    }

    public String quoiAfficher(Contenu[] listContenu) {
        if(detecterEntiteCase(listContenu)) {
            return listContenu[1].getSymbole();
        }
        else {
            if(listContenu[0]==null) {
                return " . ";
            }
            else {
                return listContenu[0].getSymbole();
            }
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

    public void tuerEntite(Entite entite){
        int x = entite.getCoordonnee().getX();
        int y = entite.getCoordonnee().getY();

        if (entite instanceof Monstre) {
            m_monstres.remove(entite);
            if (m_donjon_contenu[x][y][0] == entite) m_donjon_contenu[x][y][0] = null;
            if (m_donjon_contenu[x][y][1] == entite) m_donjon_contenu[x][y][1] = null;

            System.out.println("Vous avez tué " + ((Monstre) entite).getNom());
        }

        else if (entite instanceof Personnage) {
            m_joueurs.remove(entite);
            m_joueurNom.remove(((Personnage) entite).getNom().toUpperCase());
            if (m_donjon_contenu[x][y][0] == entite) m_donjon_contenu[x][y][0] = null;
            if (m_donjon_contenu[x][y][1] == entite) m_donjon_contenu[x][y][1] = null;

            System.out.println("Le joueur " + ((Personnage) entite).getNom() + " a été éliminé.");
        }

        else {
            System.out.println("Type d'entité inconnu ou non géré.");
        }
    }

    public void enleverObjet(Coordonnee coordonnee){
        int x = coordonnee.getX();
        int y = coordonnee.getY();

        if (m_donjon_contenu[x][y][0] instanceof Objet) m_donjon_contenu[x][y][0] = null;
        else if (m_donjon_contenu[x][y][1] instanceof Objet) m_donjon_contenu[x][y][1] = null;
    }

    public boolean verifierDeplacerContenuValide(Contenu contenu, Coordonnee coordonnee) {
        int x = coordonnee.getX();
        int y = coordonnee.getY();

        // Vérifie que les coordonnées sont dans les limites du donjon
        if (x < 0 || y < 0 || x >= m_coordonnee.getX() || y >= m_coordonnee.getY()) {
            System.out.println("L'emplacement est en dehors du donjon");
            return false;
        }

        Contenu couche0 = m_donjon_contenu[x][y][0]; // sol, objet, obstacle
        Contenu couche1 = m_donjon_contenu[x][y][1]; // personnage, monstre

        String symbole = contenu.getSymbole();

        // Si les deux couches sont vides, on peut placer n'importe quoi
        if (couche0 == null && couche1 == null) {
            return true;
        }

        // Gestion des objets (" * ") et obstacles ("[ ]")
        if (couche0 == null) {
            // Rien au sol : on peut poser un objet ou un obstacle
            if (symbole.equals(" * ") || symbole.equals("[ ]")) {
                return true;
            }
        } else {
            // Il y a déjà un objet ou obstacle
            if (couche0.getSymbole().equals(" * ") || couche0.getSymbole().equals("[ ]")) {
                if (symbole.equals(" * ") || symbole.equals("[ ]")) {
                    System.out.println("L'emplacement contient déjà un objet ou un obstacle");
                    return false;
                }
            }
        }

        // S'il n'y a personne sur la couche 1, on peut placer un personnage/monstre
        if (couche1 == null) {
            if (!symbole.equals(" * ") && !symbole.equals("[ ]")) {
                return true;
            }
        }

        // Par défaut, l'emplacement est considéré comme indisponible
        System.out.println("Endroit non disponible pour le contenu : " + symbole.trim());
        return false;
    }


    public void modifierDonjon(Contenu contenu, Coordonnee coordonee) {
        if(Objects.equals(contenu.getSymbole(), "[ ]") || Objects.equals(contenu.getSymbole(), " * ")) {
            m_donjon_contenu[coordonee.getX()][coordonee.getY()][0] = contenu;
        }
        else {
            m_donjon_contenu[coordonee.getX()][coordonee.getY()][1] = contenu;
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

    public ArrayList<Personnage> getJoueur() {
        return m_joueurs;
    }

    public void equiperObjet() {
        System.out.println("Equiper les objets :");
        for(Personnage p : m_joueurs) {

            while (true) {
                System.out.println(p.getNom() + " - Voulez-vous équiper un objet ? (o/n)");
                String reponse = Scan.ScanLine().trim().toLowerCase();

                while (!reponse.equals("o") && !reponse.equals("n")) {
                    System.out.println("Réponse invalide. Veuillez répondre par 'o' ou 'n'.");
                    reponse = Scan.ScanLine().trim().toLowerCase();
                }

                if (reponse.equals("o")) {
                    p.sEquiper();
                }

                if (reponse.equals("n")) {
                    break;
                }

                System.out.println(p.getNom() + " - Voulez-vous continuer à équiper un objet ? (o/n)");

                reponse = Scan.ScanLine().trim().toLowerCase();

                while (!reponse.equals("o") && !reponse.equals("n")) {
                    System.out.println("Réponse invalide. Veuillez répondre par 'o' ou 'n'.");
                    reponse = Scan.ScanLine().trim().toLowerCase();
                }

                if (reponse.equals("n")) {
                    break;
                }
                p.sEquiper();
            }
        }
    }

    public void attaquerMonstre(Personnage personnage){
        System.out.println("Veuillez sélectionner le monstre que vous voulez attaquer");
        for (int i = 0; i < m_monstres.size(); i++) {
            System.out.print("[" + i + "] ");
            m_monstres.get(i).afficherSituation();
            System.out.println();
        }

        System.out.println("Entrez le numéro du monstre");
        try {
            int num = Integer.parseInt(Scan.ScanLine());

            if (num >= 0 && num < m_monstres.size()) {
                Monstre monstre = m_monstres.get(num);
                personnage.attaquer(monstre);

                if (monstre.estMort()){
                    // Ca va le tuer et afficher qu'il l'a tué
                    tuerEntite(monstre);
                }
            }
            else {
                System.out.println("Numéro invalide. Veuillez saisir un numéro entre 0 et " + (m_monstres.size() - 1));
            }
        }
        catch (Exception e) {
            System.out.println("Entrée non valide. Veuillez entrer un nombre entier.");
        }
    }

    public void attaquerPersonnage(Monstre monstre){
        System.out.println("Veuillez sélectionner le personnage que vous voulez attaquer");
        for (int i = 0; i < m_joueurs.size(); i++) {
            System.out.print("[" + i + "] ");
            m_joueurs.get(i).afficherSituation();
            System.out.println();
        }

        System.out.println("Entrez le numéro du personnage :");
        try {
            int num = Integer.parseInt(Scan.ScanLine());

            if (num >= 0 && num < m_joueurs.size()) {
                Personnage personnage = m_joueurs.get(num);
                monstre.attaquer(personnage);

                if (personnage.estMort()){
                    // Ca va le tuer et afficher qu'il l'a tué
                    tuerEntite(personnage);
                }
            }
            else {
                System.out.println("Numéro invalide. Veuillez saisir un numéro entre 0 et " + (m_joueurs.size() - 1));
            }
        }
        catch (Exception e) {
            System.out.println("Entrée non valide. Veuillez entrer un nombre entier.");
        }
    }

    public ArrayList<Monstre> getMonstres(){
        return m_monstres;
    }

    public boolean itemSurCoordonnee(Coordonnee coordonnee){
        return m_donjon_contenu[coordonnee.getX()][coordonnee.getY()][0] instanceof Objet;
    }

    public Objet getObjet(Coordonnee coordonnee){
        return (Objet) m_donjon_contenu[coordonnee.getX()][coordonnee.getY()][0];
    }

    public ArrayList<Entite> getOrdreEntite() {
        ArrayList<Entite> ordre = new ArrayList<>();
        ordre.addAll(m_joueurs);  // ajoute tous les joueurs
        ordre.addAll(m_monstres); // ajoute tous les monstres

        // copie de la liste
        ordre.sort((p1, p2) -> Integer.compare(
                p2.getInitiative(),
                p1.getInitiative()
        ));

        return ordre;
    }

    public boolean joueurMort() {
        for(Personnage p : m_joueurs) {
            if(p.getCaracteristiques().getPv() <= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean monstreMort() {
        for(Monstre m : m_monstres) {
            if(m.getCaracteristiques().getPv() <= 0) {
                return true;
            }
        }
        return false;
    }

    public void setToutLesPersonnages(ArrayList<Personnage> ListPersonnages) {
        m_joueurs = ListPersonnages;
    }
}
