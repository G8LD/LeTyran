package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Entite {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Environnement env;

    public Entite(Environnement env) {
        this.xProperty = new SimpleIntegerProperty(0);
        this.yProperty = new SimpleIntegerProperty(0);
        this.env = env;
    }

    public int getX() {
        return xProperty.getValue();
    }



    public int getY() {
        return yProperty.getValue();
    }

    public void setX(int valeur) {
        xProperty.setValue(valeur);
    };

    public void setY(int valeur) {
        yProperty.setValue(valeur);
    };

    public IntegerProperty getYProperty() {
        return yProperty;
    }

    public IntegerProperty getXProperty() {
        return xProperty;
    }


    public Entite() {
        xProperty = new SimpleIntegerProperty(6 * TUILE_TAILLE);
        yProperty = new SimpleIntegerProperty(11 * TUILE_TAILLE);
    }
    public void update() {
    }


}
