package application.modele;

public class Personnage {
    private int x;
    private int y;
    private Direction direction;
    private MapJeu mapJeu;

    public Personnage(MapJeu mapjeu) {
        x = 0;
        y = 11;
        direction = Direction.Immobile;
        this.mapJeu = mapjeu;
    }

    public void seDeplacer() {
        int dX, dY;
        switch (direction) {
            case Haut: dX = 0; dY = -3; break;
            case HautGauche: dX = -1; dY = -3; break;
            case HautDroit: dX = 1; dY = -3; break;
            case Gauche: dX = -1; dY = 0; break;
            case Droit: dX = 1; dY = 0; break;
            default: dX = 0; dY = 0; break;
        }
        if (x+dX >= 0 && x+dX < MapJeu.WIDTH && y+dY >= 0 && y+dY < MapJeu.HEIGHT && mapJeu.getTabMap()[y+dY][x+dX] == 0) {
            x += dX;
            y += dY;
            tomber();
        }
    }

    private void tomber() {
        while (mapJeu.getTabMap()[y+1][x] == 0) y++;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
