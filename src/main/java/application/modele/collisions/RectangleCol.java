package application.modele.collisions;

public class RectangleCol {

    private int x;
    private int y;
    private int width;
    private int height;

    public RectangleCol(int width, int height) {
        //Par défaut le rectangle est situé en haut à gauche
        this.x = 1;
        this.y = 1;

        this.width = width;
        this.height = height;
    }

    public void scale(int x, int y) {
        this.width *= x;
        this.height *= y;

        this.x = -(this.width / 2);
        this.y = -(this.height / 2);
    }


    public String toString() {
        return "RectangleCol x:" + this.x + ", y:" + this.y + ", longueur:" + this.width + ", largeur:" + this.height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }


}
