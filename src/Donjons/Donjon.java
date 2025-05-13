package Donjons;

import Entites.Personnages.MaitreJeu;

import java.util.ArrayList;
import java.util.Arrays;

public class Donjon {
    int m_x;
    int m_y;
    String[][] m_donjon;

    public Donjon(int x, int y)
    {
        m_y = y;
        m_x = x;
        m_donjon =  new String[m_x][m_y];
    }

    public void creationObstacle() {
        afficherCarte();
        System.out.println("Veuillez indiquer les obstacles à créer dans le donjon : (exemple : pour mettre un obstacle à l'endroit A:5 vous devez indiquer A:5 :X");
    }

    public void afficherCarte(){
        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList(
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        ));
        System.out.print("\n      ");
        for(int i = 0; i < m_x; i++) {
            System.out.print(alphabet.get(i)+"  ");
        }
        System.out.println();

        System.out.print("   *--");
        for(int i = 0; i < m_x; i++) {
            System.out.print("---");
        }
        System.out.println("*");

        for(int i = 1; i < m_y+1; i++) {
            if(i<=9) {
                System.out.print(i+"  |");
            }
            else {
                System.out.print(i+" |");
            }
            for(int j = 0; j < m_x; j++) {
                System.out.print("  .");
            }
            System.out.println("  |");
        }

        System.out.print("   *--");
        for(int i = 0; i < m_x; i++) {
            System.out.print("---");
        }
        System.out.print("*\n* Equipement   |   [ ] Obstacle  |\n");
    }
}
