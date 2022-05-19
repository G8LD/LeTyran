package application.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Personnage {

    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Direction direction;
    private Environnement env;
    private Inventaire inventaire;
    private boolean saute;
    private boolean tombe;
    private BooleanProperty avanceProperty;
    private int hauteurSaut;

    public Personnage(Environnement env) {
        saute = false; tombe = false;
        avanceProperty = new SimpleBooleanProperty(false);
        xProperty = new SimpleIntegerProperty(6*32);
        yProperty = new SimpleIntegerProperty(11*32);
        direction = Direction.Droit;
        hauteurSaut = 0;
        this.env = env;
        this.inventaire = new Inventaire();
        inventaire.ajouterObjet();
    }

    public void seDeplacer() {
        if(!env.entreEnCollision(xProperty.getValue(), yProperty.getValue(), direction)) {
            if (direction == Direction.Droit)
                xProperty.setValue(xProperty.getValue() + 2);
            else
                xProperty.setValue(xProperty.getValue() - 2);
        }
    }

    public void sauter() {
        if (!tombe && hauteurSaut < 2 * TUILE_TAILLE && !env.entreEnCollision(xProperty.getValue(), yProperty.getValue(), Direction.Haut)) {
            yProperty.setValue(yProperty.getValue() - 2);
            hauteurSaut +=2;
        } else if (saute)
            saute = false;
    }

    public void tomber() {
        if (!env.entreEnCollision(xProperty.getValue(), yProperty.getValue(), Direction.Bas)) {
            tombe = true;
            yProperty.setValue(yProperty.getValue() + 2);
        } else {
            tombe = false;
            hauteurSaut = 0;
        }
    }

    public void update() {
        if (saute) sauter();
        else tomber();
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

    public Inventaire getInventaire() {
        return this.inventaire;
    }
    //endregion
}
