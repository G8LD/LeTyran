package application.modele;

import application.modele.maths.Vecteur2D;

public class Ligne2D {
    private Vecteur2D vientDe;
    private Vecteur2D vaA;


    public Ligne2D(Vecteur2D vientDe, Vecteur2D vaA) {
        this.vientDe = vientDe;
        this.vaA = vaA;
    }

    public Vecteur2D getDebut() {
        return this.vientDe;
    }

    public Vecteur2D getFin() {
        return this.vaA;
    }


}
