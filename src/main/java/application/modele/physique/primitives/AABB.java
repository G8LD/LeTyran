package application.modele.physique.primitives;


import application.modele.maths.Vecteur2D;
import application.modele.physique.rigidbody.Rigidbody2D;

//Axis aligned bouding box
public class AABB {
    private Vecteur2D center = new Vecteur2D();
    private Vecteur2D size = new Vecteur2D();
    private Vecteur2D halfSize = new Vecteur2D();
    private Rigidbody2D rigibody = null;
    //private Vector2D


    public AABB() {
        this.halfSize = new Vecteur2D(size.multiplier(0.5f));
    }


    public AABB(Vecteur2D min, Vecteur2D max) {
        //this.size = new Vector(max).
        this.size = new Vecteur2D(max).soustraire(min);
        this.halfSize = new Vecteur2D(size.multiplier(0.5f));

    }

    public Vecteur2D getMin() {
        return new Vecteur2D(this.rigibody.getPosition().soustraire(this.halfSize));
    }

    public Vecteur2D getMax() {
        return new Vecteur2D(this.rigibody.getPosition().ajouter(this.halfSize));
    }
}
