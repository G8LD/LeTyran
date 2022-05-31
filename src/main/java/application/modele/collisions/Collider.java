package application.modele.collisions;

import application.modele.Entite;
import javafx.collections.ObservableList;

public class Collider {
    private RectangleCol hitbox;
    private Entite ent;
    private boolean ignoreCollision;
    private boolean activeVerifCollision;

    public Collider(Entite ent) {
        this.ent = ent;
        hitbox = new RectangleCol(32,32);
        this.ignoreCollision = false;
        this.activeVerifCollision = false;
    }

    public boolean getIgnoreCollision() {
        return this.ignoreCollision;
    }

    public void setIgnoreCollision(boolean ignore) {
        this.ignoreCollision = ignore;
    }

    public boolean getActiveVerifCollision() {
        return this.activeVerifCollision;
    }

    public void setActiveVerifCollision(boolean active) {
        this.activeVerifCollision = active;
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
        if(ent.getCollider() != null) {
            float posX = this.ent.getX() + this.hitbox.getX();
            float posY = this.ent.getY() + this.hitbox.getY();

            float autrePosX = ent.getX() + ent.getCollider().getHitBox().getX();
            float autrePosY = ent.getY() + ent.getCollider().getHitBox().getY();

            RectangleCol selfCol = this.getHitBox();
            RectangleCol autreCol = ent.getCollider().getHitBox();

            //H pour Haut, P pour +
            float positionRectangle = (posX + posY);
            float s_H = positionRectangle;
            float s_HP = positionRectangle + selfCol.getWidth();
            float s_B = positionRectangle + selfCol.getHeight();
            float s_BP = positionRectangle + selfCol.getHeight() + selfCol.getWidth();

            //System.out.println(String.format("%s %s %s %s", s_H, s_HP, s_B, s_BP));


            //On définit les bornes du deuxième objet à vérifier

            float positionRectangleAutre = (autrePosX + autrePosY);
            float a_H = positionRectangleAutre;
            float a_HP = positionRectangleAutre + autreCol.getWidth();
            float a_B = positionRectangleAutre + autreCol.getHeight();
            float a_BP = positionRectangleAutre + autreCol.getHeight() + autreCol.getWidth();

            //System.out.println(String.format("%s %s %s %s", a_H, a_HP, a_B, a_BP) + "------------------------\n");


            return s_H < a_HP && s_HP > a_H && s_B < a_BP && s_BP > a_B;
        }
        return false;
    }
    public boolean intersect(Entite ent, int addX, int addY) {
        float posX = this.ent.getX() + this.hitbox.getX();
        float posY = this.ent.getY() + this.hitbox.getY();

        float autrePosX = ent.getX() + ent.getCollider().getHitBox().getX() + addX;
        float autrePosY = ent.getY() + ent.getCollider().getHitBox().getY() + addY;

        RectangleCol selfCol = this.getHitBox();
        RectangleCol autreCol = ent.getCollider().getHitBox();

        //H pour Haut, P pour +
        float positionRectangle = (posX + posY);
        float s_H = positionRectangle;
        float s_HP = positionRectangle + selfCol.getWidth();
        float s_B = positionRectangle + selfCol.getHeight();
        float s_BP = positionRectangle + selfCol.getHeight() + selfCol.getWidth();


        //On définit les bornes du deuxième objet à vérifier

        float positionRectangleAutre = (autrePosX + autrePosY);
        float a_H = positionRectangleAutre;
        float a_HP = positionRectangleAutre + autreCol.getWidth();
        float a_B = positionRectangleAutre + autreCol.getHeight() ;
        float a_BP = positionRectangleAutre + autreCol.getHeight() + autreCol.getWidth();

        return s_H < a_HP && s_HP > a_H && s_B < a_BP && s_BP > a_B;

    }

    /*public Entite tracerLigne(int vecX, int vecY) {
        Entite entTrouve = null;
        int i = 0;

        ObservableList<Entite> entites = this.ent.getEnv().getEntites();
        while(i < entites.size() && entTrouve == null) {
            Entite ent = entites.get(i);
            if (ent != this.getEnt()) {
                if (this.intersect(ent, vecX, vecY)) {
                    entTrouve = ent;

                }
            }
            i++;
        }
        return entTrouve;
    }*/
}
