package application.modele.physique.primitives;

import application.modele.maths.Vecteur2D;
import application.modele.physique.rigidbody.Rigidbody2D;

public class Cercle2D {
    private float radius = 1.0f;
    private Rigidbody2D body = null;

    public Cercle2D(float radius) {
    }

    public float getRadius() {
        return this.radius;
    }

    public Vecteur2D getCentre() {
        return body.getPosition();
    }
}
