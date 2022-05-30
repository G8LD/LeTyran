package application.modele.maths;

public class Vecteur2D {
    public float x = 0;
    public float y = 0;

    public Vecteur2D(float x, float y) {
        this.x = 0;
        this.y = 0;
    }

    public Vecteur2D() {
    }

    public Vecteur2D(Vecteur2D vec) {
        this.x = vec.x;
        this.y = vec.y;

    }

    public float tailleAuCarre() {
        return (this.x * this.x) + (this.y + this.y) ;
    }

    public Vecteur2D ajouter(Vecteur2D vec) {
        this.x += vec.x;
        this.y += vec.y;
        return this;
    }

    public Vecteur2D diviser(float nombre) {
        this.x /= nombre;
        this.y /= nombre;
        return this;
    }

    public float dot(Vecteur2D vec) {
        return this.x * vec.x + this.y * vec.y;
    }

    public Vecteur2D multiplier(float nombre) {
        this.x *= nombre;
        this.y *= nombre;
        return this;
    }

    public float multiplier(Vecteur2D vec) {
        return this.x * vec.x + this.y * this.y;
    }

    public Vecteur2D soustraire(Vecteur2D vec) {
        this.x -= vec.x;
        this.y -= vec.y;
        return this;
    }

    public void normaliser() {
        float taille = (float) Math.sqrt(tailleAuCarre());
        this.x = this.x / taille;
        this.y = this.y / taille;
    }
}
