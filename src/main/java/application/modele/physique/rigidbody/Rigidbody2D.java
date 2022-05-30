package application.modele.physique.rigidbody;

import application.modele.maths.Vecteur2D;

public class Rigidbody2D {
    private Vecteur2D position = new Vecteur2D();
    private float rotation = 0.0f;

    public Vecteur2D getPosition() {
        return position;
    }

    public void setPosition(Vecteur2D position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
