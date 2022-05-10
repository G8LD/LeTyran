package application.modele;

public class Personnage {
    private int x;
    private int y;
    private Direction direction;

    public Personnage() {
        x = 0;
        y = 0;
        direction = Direction.Immobile;
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
        x += dX;
        y += dY;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
