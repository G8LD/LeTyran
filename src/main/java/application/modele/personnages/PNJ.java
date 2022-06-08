package application.modele.personnages;

import application.modele.Environnement;
import application.modele.armes.Arme;

public abstract class PNJ extends Personnage {

    public PNJ(Environnement env, String id, Arme arme, int x, int y, int pv) {
        super(env, id, arme, x, y, pv);
    }

    public PNJ(Environnement env, String id, int x, int y, int pv) {
        super(env, id, x, y, pv);
    }
}
