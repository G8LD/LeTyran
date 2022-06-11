package application.modele.personnages;

import application.modele.Environnement;

public abstract class Animal extends PNJ {
    public Animal(Environnement env, String id, int x, int y, int pv) {
        super(env, id, x, y, pv);
    }
}
