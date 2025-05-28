package Donjons;

public class Coordonnee {
    private int m_x;
    private int m_y;

    private Coordonnee(int x, int y) {
        m_x = x;
        m_y = y;
    }

    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    }

    public void SetX(int x) {
        m_x = x;
    }

    public void SetY(int y) {
        m_y = y;
    }
}
