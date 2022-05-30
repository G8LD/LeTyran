package application.modele.physique.primitives;

import application.modele.maths.Vecteur2D;
import application.modele.physique.rigidbody.Rigidbody2D;

public class Boite2D {
    private Vecteur2D size = new Vecteur2D();
    private Vecteur2D halfSize = new Vecteur2D();
    private Rigidbody2D rigibody = null;
    //private Vector2D


    public Boite2D() {
        this.halfSize = new Vecteur2D(size.multiplier(0.5f));
    }


    public Boite2D(Vecteur2D min, Vecteur2D max) {
        //this.size = new Vector(max).
        this.size = new Vecteur2D(max).soustraire(min);
        this.halfSize = new Vecteur2D(size).multiplier(0.5f);
    }

    public Vecteur2D getMin() {
        return new Vecteur2D(this.rigibody.getPosition().soustraire(this.halfSize));
    }

    public Vecteur2D getMax() {
        return new Vecteur2D(this.rigibody.getPosition().ajouter(this.halfSize));
    }

    public Vecteur2D[] getVertices() {
        Vecteur2D min = getMin();
        Vecteur2D max = getMax();

        Vecteur2D[] vertices = {
                new Vecteur2D(min.x, min.y), new Vecteur2D(min.x, max.y),
                new Vecteur2D(max.x, min.y), new Vecteur2D(max.x, max.y)
        };

        if(rigibody.getRotation() != 0.0f) {

            for (Vecteur2D vert : vertices) {
                //TMath.rotation(vert, this.rigibody.getPosition(), this.rigibody.getRotation());
            }
        }

        return vertices;
    }

    public Rigidbody2D getRigibody() {
        return rigibody;
    }
}
