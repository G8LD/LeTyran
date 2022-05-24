package application.modele;

import application.modele.armes.Arme;
import application.modele.armes.Hache;
import application.modele.armes.Pioche;
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
    private Arme arme;


    public Personnage(Environnement env) {
        saute = false; tombe = false;
        avanceProperty = new SimpleBooleanProperty(false);
        xProperty = new SimpleIntegerProperty(6 * TUILE_TAILLE);
        yProperty = new SimpleIntegerProperty(11 * TUILE_TAILLE);
        direction = Direction.Droit;
        hauteurSaut = 0;
        arme = new Pioche(2);
        this.env = env;
        this.inventaire = new Inventaire();
        inventaire.ajouterObjet();
    }

    public void seDeplacer() {
        int distance;
        if (tombe || saute)
            distance = 2;
        else
            distance = 3;
        for (int i = 0; i < distance; i++)
        if(!env.entreEnCollision(xProperty.getValue(), yProperty.getValue(), direction)) {
            if (direction == Direction.Droit)
                xProperty.setValue(xProperty.getValue() + 1);
            else
                xProperty.setValue(xProperty.getValue() - 1);
        }
    }

    public void sauter() {
        for (int i = 0; i < 3; i++)
        if (!tombe && hauteurSaut < 2 * TUILE_TAILLE && !env.entreEnCollision(xProperty.getValue(), yProperty.getValue(), Direction.Haut)) {
            yProperty.setValue(yProperty.getValue() - 1);
            hauteurSaut +=1;
        } else if (saute) {
            saute = false;
        }
    }

    public void tomber() {
        for (int i = 0; i < 3; i++)
        if (!env.entreEnCollision(xProperty.getValue(), yProperty.getValue(), Direction.Bas)) {
            tombe = true;
            yProperty.setValue(yProperty.getValue() + 1);
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

    public int setX(int x) {
        this.xProperty.set(x);
        return x;
    }

    public int setY(int y) {
        this.xProperty.set(y);
        return y;
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

    public Arme getArme() {
        return arme;
    }

    //endregion
}
