package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private int hauteurSaut;
    private Direction direction;
    private MapJeu mapJeu;

    public Personnage(MapJeu mapjeu) {
        xProperty = new SimpleIntegerProperty(0);
        yProperty = new SimpleIntegerProperty(11);
        hauteurSaut = 0;
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
        if (xProperty.getValue() +dX >= 0 && xProperty.getValue() +dX < MapJeu.WIDTH && yProperty.getValue() +dY >= 0 && yProperty.getValue() +dY < MapJeu.HEIGHT && mapJeu.getTabMap()[yProperty.getValue() +dY][xProperty.getValue() +dX] == 0) {
            xProperty.setValue(xProperty.getValue() + dX);
            yProperty.setValue(yProperty.getValue() + dY);
            System.out.println(xProperty.getValue() + "\t" + yProperty.getValue());
        }
    }

    public void sauter() {
        hauteurSaut = 0;
        while (hauteurSaut < 3 && yProperty.getValue() - hauteurSaut - 1 > 0 && mapJeu.getTabMap()[yProperty.getValue() - hauteurSaut - 1][xProperty.getValue()] == 0) {
            hauteurSaut++;
        }
        yProperty.setValue(yProperty.getValue() - hauteurSaut);
        System.out.println(xProperty.getValue() + "\t" + yProperty.getValue());
    }
    private boolean tomber() {
        if (mapJeu.getTabMap()[yProperty.getValue() + 1][xProperty.getValue()] == 0) {
            yProperty.setValue(yProperty.getValue() + 1);
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
        return xProperty.getValue();
    }

    public IntegerProperty getXProperty() {
        return xProperty;
    }

    public void setX(int x) {
        this.xProperty.set(x);
    }

    public int getY() {
        return yProperty.getValue();
    }

    public IntegerProperty getYProperty() {
        return yProperty;
    }

    public void setyProperty(int yProperty) {
        this.yProperty.set(yProperty);
    }

    public int getHauteurSaut() {
        return hauteurSaut;
    }
}
