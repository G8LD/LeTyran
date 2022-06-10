package application.modele.personnages;

import application.modele.Direction;
import application.modele.Entite;
import application.modele.Environnement;
import application.modele.armes.Arme;
import application.modele.armes.Armure;
import application.modele.armes.Epee;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

import static application.modele.Direction.Droit;
import static application.modele.Direction.Gauche;
import static application.modele.MapJeu.TUILE_TAILLE;

public class Ennemi extends PNJ {

    private static int id = 0;

    private float origineX;
    private float origineY;
    private int distance;
    private BooleanProperty attaqueProperty;
    private int delai;
    private boolean retourZone;
    private ArrayList<Entite> loot ;
    private ObservableList<Ennemi> listeCadavres;


    public Ennemi(Environnement env, Arme arme, int x, int y, int distance) {
        super(env, "Ennemi" + id++, arme, x * TUILE_TAILLE, y * TUILE_TAILLE, 20);
        origineX = x * TUILE_TAILLE;
        origineY = y * TUILE_TAILLE;
        this.distance = distance * TUILE_TAILLE;
        attaqueProperty = new SimpleBooleanProperty(false);
        delai = 0;
        retourZone = false;
        listeCadavres = FXCollections.observableArrayList();
        loot = new ArrayList<Entite>();
        this.remplirLoot();

    }

    private ArrayList<Entite> remplirLoot() {
        int x=(int) (Math.random() * 3 + 1);
        this.loot.add(new Epee(getEnv(), x));
        this.loot.add(new Armure(getEnv(), (int) ((Math.random() * 3) + 1)));

        return this.loot;
    }


    public Ennemi(Environnement env, String id, int x, int y, int distance) {
        super(env, id, x * TUILE_TAILLE, y * TUILE_TAILLE, 20);
        origineX = x * TUILE_TAILLE;
        origineY = y * TUILE_TAILLE;
        this.distance = distance * TUILE_TAILLE;
        attaqueProperty = new SimpleBooleanProperty(false);
        delai = 0;
        retourZone = false;
    }

    protected void deplacement() {
        if (((getX() < origineX - 10 * TUILE_TAILLE && getDirection() == Gauche)
                || (getX() > origineX + 15 * TUILE_TAILLE  && getDirection() == Droit)) && !retourZone) {
            setDirection(getDirectionOpposee());
            retourZone = true;
        } else if (getX() >= origineX && getX() <= origineX + distance && getY() == origineY && retourZone)
            retourZone = false;

        if (!retourZone)
            if (Math.abs(getEnv().getJoueur().getX() - getX()) < distance && Math.abs(getEnv().getJoueur().getY() - getY()) < 2 * TUILE_TAILLE)
                if (getEnv().getJoueur().getX() - getX() > 0)
                    setDirection(Droit);
                else
                    setDirection(Gauche);
            else if (getX() >= origineX && getX() <= origineX + distance && getY() == origineY && estBloque())
                setDirection(getDirectionOpposee());
            else if (((getX() < origineX && getDirection() == Gauche) || (getX() > origineX + distance && getDirection() == Droit)))
                setDirection(getDirectionOpposee());
            else if (estBloque()) {
                if (getDirection() == Gauche)
                    origineX = getX();
                else
                    origineX = getX() - distance;
                origineY = getY();
            }
        seDeplacer();
    }

    protected boolean estBloque() {
        return super.getEnv().entreEnCollision((int) super.getX(), (int) super.getY(), getDirection())
                && !super.getEnv().entreEnCollision((int) super.getX(), (int) super.getY(), getDirectionOpposee());
    }

    protected Direction getDirectionOpposee() {
        if (getDirection() == Droit)
            return Gauche;
        else
            return Droit;
    }

    private void detectionJoueur() {
        if (joueurEnFace()) {
            attaqueProperty.setValue(true);
            delai = 0;
        }
    }

    private void attaquer() {
        if (delai++ >= 20) {
            if (joueurEnFace())
                getArme().frapper(getEnv().getJoueur());
            attaqueProperty.setValue(false);
        }
    }

    private boolean joueurEnFace() {
        return Math.abs(getEnv().getJoueur().getX() - getX()) < TUILE_TAILLE
                && Math.abs(getEnv().getJoueur().getY() - getY()) < TUILE_TAILLE
                && ((getDirection() == Gauche && getEnv().getJoueur().getX() - getX() <= 0)
                || (getDirection() == Droit && getEnv().getJoueur().getX() - getX() >= 0));
    }
    
    @Override
    public void update() {
        tomber();
        if (attaqueProperty.getValue())
            attaquer();
        if (!attaqueProperty.getValue())
            detectionJoueur();
        if (!attaqueProperty.getValue())
            deplacement();
    }


    @Override
    protected int getHauteurMax() {
        return 2 * TUILE_TAILLE;
    }

    @Override
    protected int getVitesse() {
        return 4;
    }

    public ArrayList<Entite> getLoot() {
        return this.loot;
    }

    public final boolean getAttaque() {
        return attaqueProperty.getValue();
    }
    
    public final BooleanProperty getAttaqueProperty() {
        return attaqueProperty;
    }

    public float getOrigineX() {
        return origineX;
    }

    public float getOrigineY() {
        return origineY;
    }

    public void setOrigineX(float origineX) {
        this.origineX = origineX;
    }

    public void setOrigineY(float origineY) {
        this.origineY = origineY;
    }

    public int getDistance() {
        return distance;
    }
}
