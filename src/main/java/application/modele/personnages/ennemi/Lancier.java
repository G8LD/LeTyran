package application.modele.personnages.ennemi;

import application.modele.Environnement;
import application.modele.armes.Lance;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Lancier extends Ennemi {

    public Lancier(Environnement env, int niveau, int x, int y, int distance) {
        super(env, x, y, distance);
        setPv(niveau * 20);
        setArme(new Lance(getEnv(), niveau));
    }

    protected void deplacement() {
        poursuiteJoueur();
        if (!getPoursuitJoueur())
            retourOrigine();
        if (getPoursuitJoueur() || getRetourZone() || (Math.abs(getX() - getOrigineX()) > 1)) {
            seDeplacer();
        }
    }
}
