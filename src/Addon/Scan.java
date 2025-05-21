package Addon;

import java.util.Scanner;

// Cette classe sert à éviter les problèmes lorsqu'on utilise plusieurs Scanner(System.in)
// en centralisant toutes les lectures dans une seule instance partagé dans le programme.
// Si on crée plusieurs objets Scanner avec System.in
// et qu’on en ferme un avec .close ca ferme également System.in lui-même.
// Une fois System.in fermé toute tentative de lecture échoue et c'est fortement casse couille.

public class Scan {
    private static Scanner scanner = new Scanner(System.in);

    public static String ScanLine() {
        return scanner.nextLine();
    }
}
