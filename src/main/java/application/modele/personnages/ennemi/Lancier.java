package application.modele.personnages.ennemi;

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
        if (getPoursuitJoueur() || getRetourZone() || (Math.abs(getX() - getOrigineX()) > 3 || Math.abs(getY() - getOrigineY()) > 3))
            seDeplacer();
    }
}
