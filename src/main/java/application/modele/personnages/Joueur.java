package application.modele.personnages;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.ObjetJeu;
import application.modele.armes.Pioche;
import application.modele.armes.arc.Arc;
import application.modele.armes.arc.Fleche;
import application.modele.objets.Arbre;
import application.modele.objets.Materiau;
import application.modele.personnages.ennemi.Ennemi;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Joueur extends Personnage {

    private Inventaire inventaire;
    private boolean freeze;
    private BooleanProperty mortProperty;
    private long delaiMort;
    private BooleanProperty avanceProperty;

    public Joueur(Environnement env) {
        super(env, new Arc(env, 1));
        this.inventaire = new Inventaire(super.getEnv());
        this.inventaire.ajouterObjet(getArme());
        freeze = false;
        mortProperty = new SimpleBooleanProperty();
        delaiMort = 0;
        avanceProperty = new SimpleBooleanProperty(false);
    }

    public void interagit(int x, int y) {
        if (!frapper(x, y))
            if (!miner(x, y))
                couper(x, y);
    }

    private boolean frapper(int x, int y) {
        if (getArme() instanceof Arc) {
            getArme().frapper(this, null);
            return true;
        } else {
            Ennemi ennemi = getEnv().getEnnemi(x, y);
            if (ennemi != null) {
                getArme().frapper(this, ennemi);
                return true;
            }
            return false;
        }
    }

    private boolean couper(int x, int y) {
        Arbre arbre = getEnv().getArbre(x, y);
        if (arbre != null) {
            arbre.estFrappe();
            return true;
        }
        return false;
    }

    private boolean miner(int x, int y) {
        Materiau minerai = getEnv().getMinerai(x, y);
        if (minerai != null) {
            minerai.estFrappe();
            return true;
        }
        return false;
    }

    @Override
    public void quandCollisionDetectee(Entite ent) {
        if (ent instanceof ObjetJeu || ent instanceof Materiau) {
            this.inventaire.ajouterObjet(ent);
        }
    }

    @Override
    public void update() {
        super.collide();
        if (mortProperty.getValue())
            detruire();
        else if (getDistancePoussee() != 0)
            estPoussee();
        else {
            if (!freeze) {
                if (getSaute()) sauter();
                if (getAvance()) seDeplacer();
            }
            if (!getSaute()) tomber();
        }
    }

    @Override
    public void detruire() {
        if (!mortProperty.getValue()) {
            mortProperty.setValue(true);
            delaiMort = System.currentTimeMillis();
        } else if (System.currentTimeMillis() - delaiMort >= 5_000) {
            mortProperty.setValue(false);
        } else if (System.currentTimeMillis() - delaiMort >= 2_000 && getX() != getEnv().getFeuDeCamp().getX()) {
            setSaute(false); setAvance(false); setDistancePoussee(0);
            setX(getEnv().getFeuDeCamp().getX());
            setY(getEnv().getFeuDeCamp().getY());
            getEnv().getFeuDeCamp().seReposer();
        }
    }

    public void freezer() {
        freeze = !freeze;
    }

    @Override
    protected int getHauteurMax() {
        return 2 * TUILE_TAILLE;
    }

    @Override
    protected int getVitesse() {
        return 3;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public final boolean getMort() {
        return mortProperty.getValue();
    }

    public final BooleanProperty getMortProperty() {
        return mortProperty;
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
}
