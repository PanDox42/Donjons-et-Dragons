package Monstres;

import Des.De;

public class Monstre {
    String m_espece;
    int m_numero;
    int m_initiative;

    public Monstre(String espece, int numero, Attaque attaque, int classeArmure){
        m_espece = espece;
        m_numero = numero;
    }
}
