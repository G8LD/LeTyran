package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Direction direction;
    private MapJeu mapJeu;
    private Inventaire inventaire;

    public Personnage(MapJeu mapjeu) {
        xProperty = new SimpleIntegerProperty(0);
        yProperty = new SimpleIntegerProperty(11);
        direction = Direction.Immobile;
        this.mapJeu = mapjeu;

        this.inventaire = new Inventaire();
        inventaire.ajouterObjet();
    }

    public Inventaire getInventaire() {
        return this.inventaire;
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
        int hauteurSaut = 0;
        while (hauteurSaut < 3 && yProperty.getValue() - hauteurSaut - 1 > 0 && mapJeu.getTabMap()[yProperty.getValue() - hauteurSaut - 1][xProperty.getValue()] == 0)
            hauteurSaut++;
        yProperty.setValue(yProperty.getValue() - hauteurSaut);
    }
    public void tomber() {
        int hauteurChute = 0;
        while (hauteurChute < 3 && yProperty.getValue() + hauteurChute + 1 < MapJeu.HEIGHT && mapJeu.getTabMap()[yProperty.getValue() + hauteurChute + 1][xProperty.getValue()] == 0)
            hauteurChute++;
        yProperty.setValue(yProperty.getValue() + hauteurChute);
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

}
