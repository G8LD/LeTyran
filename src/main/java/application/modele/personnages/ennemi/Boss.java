package application.modele.personnages.ennemi;


import application.modele.Environnement;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import static application.modele.Direction.Droit;
import static application.modele.Direction.Gauche;
import static application.modele.MapJeu.TUILE_TAILLE;

public class Boss extends Ennemi {
    private int delaiBoss ;
    private BooleanProperty attaqueProperty;



    public Boss(Environnement env, int x, int y, int distance) {
        super(env, x, y, distance);
        this.delaiBoss= 0;
        attaqueProperty = new SimpleBooleanProperty(false);}

    public void attaquer() {
        System.out.println("rentre dans attaquer");
        if (delaiBoss++ >= 30) {
            System.out.println("dans le if");
            if (joueurEnFace())
                attaqueDuBoss(this.getEnv().getJoueur());
            }
                attaqueProperty.setValue (false);
    }
    public boolean joueurEnFace() {
        return Math.abs(getEnv().getJoueur().getX() - getX()) <3 * TUILE_TAILLE
                && Math.abs(getEnv().getJoueur().getY() - getY()) < TUILE_TAILLE
                && ((getDirection() == Gauche && getEnv().getJoueur().getX() - getX() < 1)
                || (getDirection() == Droit && getEnv().getJoueur().getX() - getX() > -1));
    }
    public void deplacement() {
        retourneDansZone();
        poursuiteJoueur();
        if (!getPoursuitJoueur())
            deplacementAllerRetour();
        seDeplacer();
    }

    public void attaqueDuBoss(Personnage perso) {
        perso.decrementerPv(30);
    }
}
