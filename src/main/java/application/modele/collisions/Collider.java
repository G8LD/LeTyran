package application.modele.collisions;

import application.modele.Entite;
import javafx.geometry.Rectangle2D;

public class Collider {
    private RectangleCol hitbox;
    private Entite ent;

    public Collider(Entite ent) {
        this.ent = ent;
        hitbox = new RectangleCol(1,1);
    }

    public void scaleCollider(int x, int y) {
        this.hitbox.scale(x, y);
    }

    public RectangleCol getHitBox() {
        return this.hitbox;
    }

    public Entite getEnt() {
        return this.ent;
    }

    public boolean intersect(Entite ent) {
        int posX = this.ent.getX();
        int posY = this.ent.getY();

        int autrePosX = ent.getX();
        int autrePosY = ent.getY();

        RectangleCol selfCol = this.getHitBox();
        RectangleCol autreCol = ent.getCollider().getHitBox();

        //H pour Haut, P pour +
        int positionRectangle = (posX + posY);
        int s_H = positionRectangle;
        int s_HP = positionRectangle + selfCol.getWidth();
        int s_B = positionRectangle + selfCol.getHeight() ;
        int s_BP = positionRectangle + selfCol.getHeight() + selfCol.getWidth();


        //On définit les bornes du deuxième objet à vérifier

        int positionRectangleAutre = (autrePosX + autrePosY);
        int a_H = positionRectangleAutre;
        int a_HP = positionRectangleAutre + autreCol.getWidth();
        int a_B = positionRectangleAutre + autreCol.getHeight() ;
        int a_BP = positionRectangleAutre + autreCol.getHeight() + autreCol.getWidth();

        return s_H < a_HP && s_HP > a_H && s_B < a_BP && s_BP > a_B;

    }
}
