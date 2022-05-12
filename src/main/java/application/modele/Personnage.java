package application.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Personnage {
    private int x;
    private int y;
    private Direction direction;
    private BooleanProperty deplaceProperty;
    private MapJeu mapJeu;

    public Personnage(MapJeu mapjeu) {
        x = 0;
        y = 11;
        direction = Direction.Immobile;
        deplaceProperty = new SimpleBooleanProperty(false);
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
            deplaceProperty.setValue(true);
        }
    }


    private boolean tomber() {
        if (mapJeu.getTabMap()[y+1][x] == 0) {
            y++;
            return true;
        }
        return false;
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

    public final boolean isDeplaceProperty() {
        return deplaceProperty.get();
    }

    public final BooleanProperty getDeplaceProperty() {
        return deplaceProperty;
    }

    public void setDeplaceProperty(boolean deplaceProperty) {
        this.deplaceProperty.set(deplaceProperty);
    }
}
