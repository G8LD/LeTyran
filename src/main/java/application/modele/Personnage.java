package application.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Direction direction;
    private Environnement env;
    private Inventaire inventaire;
    private boolean saute;
    private boolean tombe;
    private BooleanProperty avanceProperty;

    public Personnage(Environnement env) {
        saute = false; tombe = false;
        avanceProperty = new SimpleBooleanProperty(false);
        xProperty = new SimpleIntegerProperty(1);
        yProperty = new SimpleIntegerProperty(11);
        direction = Direction.Droit;
        this.env = env;

        this.inventaire = new Inventaire();
        inventaire.ajouterObjet();
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public void seDeplacer() {
        System.out.println("deplacer");
        int dX, dY;
        switch (direction) {
            case Gauche:
                dX = -1;
                dY = 0;
                break;
            case Droit:
                dX = 1;
                dY = 0;
                break;
            default:
                dX = 0;
                dY = 0;
                break;
        }

        if(!env.entreEnCollision(this, direction)) {
            xProperty.setValue(xProperty.getValue() + dX);
            yProperty.setValue(yProperty.getValue() + dY);
            System.out.println(this.getX() + " " + this.getY());
        }


        /*if (xProperty.getValue() +dX >= 0 && xProperty.getValue() +dX < MapJeu.WIDTH && yProperty.getValue() +dY >= 0 && yProperty.getValue() +dY < MapJeu.HEIGHT && env.getMapJeu().getTabMap()[yProperty.getValue() +dY][xProperty.getValue() +dX] == 0) {
            xProperty.setValue(xProperty.getValue() + dX);
            yProperty.setValue(yProperty.getValue() + dY);
            //System.out.println("seDeplacer : " + xProperty.getValue() + "\t" + yProperty.getValue());
        }*/
    }

    public void sauter() {
        System.out.println("sauter");
        int hauteurSaut = 0;
        while (hauteurSaut < 3 && yProperty.getValue() - hauteurSaut - 1 > 0
                && env.getMapJeu().getTabMap()[yProperty.getValue() - hauteurSaut - 1][xProperty.getValue()] == 0)
            hauteurSaut++;
        if (hauteurSaut == 0) saute = false;
        else {
            yProperty.setValue(yProperty.getValue() - hauteurSaut);
            //System.out.println("sauter : " + xProperty.getValue() + "\t" + yProperty.getValue());
        }
    }

    public void tomber() {
        if(!env.entreEnCollision(this, Direction.Bas)) {
            tombe = true;
        } else {
            tombe = false;
        }

        if(tombe) {
            yProperty.setValue(yProperty.getValue() + 1);
        }
    }

    public void update() {
        tomber();
        if (saute) sauter();
        if (avanceProperty.getValue()) seDeplacer();

    }

//region Getter & Setter
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

    public boolean getSaute() {
        return saute;
    }

    public void setSaute(boolean saute) {
        this.saute = saute;
    }

    public final boolean getAvance() {
        return avanceProperty.getValue();
    }

    public final BooleanProperty getAvanceProperty() {
        return avanceProperty;
    }

    public void setAvance(boolean avance) {
        this.avanceProperty.setValue(avance);
    }

    public boolean getTombe() {
        return tombe;
    }

    public void setTombe(boolean tombe) {
        this.tombe = tombe;
    }

    //endregion
}
