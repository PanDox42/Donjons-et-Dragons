package Donjons;

import Entites.Personnages.MaitreJeu;
import Scanner.Scan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Donjon {
    private int m_x;
    private int m_y;
    private String[][] m_donjon;
    private ArrayList<String> alphabet = new ArrayList<>(Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    ));

    public Donjon(int x, int y)
    {
        m_y = y;
        m_x = x;
        m_donjon =  new String[m_x][m_y];
        for(int i = 0; i<m_donjon.length; i++) {
            for(int j = 0; j<m_donjon[i].length; j++) {
                m_donjon[i][j] = " . ";
            }
        }
    }

    public void creationObstacle() {
        afficherCarte();

        System.out.println("Veuillez indiquer les obstacles à créer dans le donjon : \n(exemple : pour mettre un obstacle à l'endroit A:5 vous devez indiquer A:5)\n");
        String[] obstacleSplit = Scan.ScanLine().split(":");
        int x = 0;
        for(int i = 0; i < alphabet.size(); i++) {
            if(alphabet.get(i).equals(obstacleSplit[0])) {
                x = i + 1;
            }
        }
        int y = Integer.parseInt(obstacleSplit[1]);
        m_donjon[x][y] = "[ ]";
    }

    public void afficherCarte(){
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

        for(int i = 1; i < m_y; i++) {
            if(i<=9) {
                System.out.print(i+"  | ");
            }
            else {
                System.out.print(i+" | ");
            }
            for(int j = 0; j < m_x; j++) {
                System.out.print(m_donjon[i][j]);
            }
            System.out.println(" |");
        }

        System.out.print("   *--");
        for(int i = 0; i < m_x; i++) {
            System.out.print("---");
        }
        System.out.println("*\n* Equipement   |   [ ] Obstacle  |\n");
    }
}
