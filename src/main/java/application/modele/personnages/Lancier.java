package application.modele.personnages;

import application.modele.Environnement;
import application.modele.armes.Lance;

public class Lancier extends Ennemi {

    public Lancier(Environnement env, int niveau, int x, int y, int distance) {
        super(env, x, y, distance);
        setPv(niveau * 20);
        setArme(new Lance(getEnv(), niveau));
    }

    protected void deplacement() {
        retourneDansZone();
        poursuiteJoueur();
        if (!getPoursuitJoueur())
            deplacementAllerRetour();
        if (getPoursuitJoueur() || getRetourZone() || (getX() != getOrigineX() || getY() != getOrigineY()))
            seDeplacer();
    }
}
