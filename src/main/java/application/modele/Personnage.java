package application.modele;

import application.modele.armes.Arme;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Personnage extends Entite {

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
        super(env);

        saute = false; tombe = false;
        avanceProperty = new SimpleBooleanProperty(false);
        /*xProperty = new SimpleIntegerProperty(6 * TUILE_TAILLE);
        yProperty = new SimpleIntegerProperty(11* TUILE_TAILLE);*/
        super.setX(6 * TUILE_TAILLE);
        super.setY(11 * TUILE_TAILLE);
        direction = Direction.Droit;
        hauteurSaut = 0;
        arme = null;
        this.env = env;
        this.inventaire = new Inventaire(this.env);
    }

    public void miner(int x, int y) {
        env.minage(x,y);
    }

    public void seDeplacer() {
        int distance;
        if (tombe || saute)
            distance = 2;
        else
            distance = 3;
        for (int i = 0; i < distance; i++)
        //if(!env.entreEnCollision(xProperty.getValue(), yProperty.getValue(), direction)) {
            if (direction == Direction.Droit)
                this.getXProperty().setValue(this.getXProperty().getValue() + 1);
            else
                this.getXProperty().setValue(this.getXProperty().getValue() - 1);
        //}


        /*if (xProperty.getValue() +dX >= 0 && xProperty.getValue() +dX < MapJeu.WIDTH && yProperty.getValue() +dY >= 0 && yProperty.getValue() +dY < MapJeu.HEIGHT && env.getMapJeu().getTabMap()[yProperty.getValue() +dY][xProperty.getValue() +dX] == 0) {
            xProperty.setValue(xProperty.getValue() + dX);
            yProperty.setValue(yProperty.getValue() + dY);
            //System.out.println("seDeplacer : " + xProperty.getValue() + "\t" + yProperty.getValue());
        }*/
    }

    public void sauter() {
        for (int i = 0; i < 3; i++)
        if (!tombe && hauteurSaut < 2 * TUILE_TAILLE && !env.entreEnCollision(xProperty.getValue(), yProperty.getValue(), Direction.Haut)) {
            yProperty.setValue(yProperty.getValue() - 1);
            hauteurSaut +=1;
        } else if (saute)
            saute = false;
    }

    public void tomber() {
        /*for (int i = 0; i < 3; i++)
        if (!env.entreEnCollision(xProperty.getValue(), yProperty.getValue(), Direction.Bas)) {
            tombe = true;
            yProperty.setValue(yProperty.getValue() + 1);
        } else {
            tombe = false;
            hauteurSaut = 0;
        }*/
    }

    public void update() {
        super.update();
        if (saute) sauter();
        else tomber();
        if (avanceProperty.getValue()) seDeplacer();

    }

//region Getter & Setter


    @Override
    public IntegerProperty getXProperty() {
        return super.getXProperty();
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public IntegerProperty getYProperty() {
        return super.getYProperty();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    @Override
    public void quandCollisionDetecte(Entite ent) {
        if(ent instanceof ObjetJeu) {
            this.inventaire.ajouterObjet((ObjetJeu)ent);
        }
    }

    //endregion
}
