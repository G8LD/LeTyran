package application.modele.collisions;

import application.modele.Direction;
import application.modele.Entite;
import application.modele.MapJeu;
import javafx.collections.ObservableList;

public class Collider {
    private RectangleCol hitbox;
    private Entite ent;
    private boolean ignoreCollision;
    private boolean activeVerifCollision;

    public Collider(Entite ent) {
        this.ent = ent;
        hitbox = new RectangleCol(32, 32);
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
        if (ent.getCollider() != null) {
            Entite selfEnt = this.ent;
            Collider entCollider = ent.getCollider();

            if (selfEnt.getX() >= ent.getX() &&
                    selfEnt.getX() < ent.getX() + entCollider.getHitBox().getWidth()
                    && selfEnt.getY() >= ent.getY()
                    && selfEnt.getY() < ent.getY() + entCollider.getHitBox().getHeight())
                return true;


        }
        return false;
    }

    public double verificationX(Entite ent, double forceX) {
        double entRecX = ent.getX();
        double entRecY = ent.getY();
        double entColWidth = ent.getCollider().getHitBox().getWidth();
        double entColHeight = ent.getCollider().getHitBox().getHeight();

        double colRecX = this.getEnt().getX();
        double colRecY = this.getEnt().getY();
        double colWidth = this.getHitBox().getWidth();
        double colRecHeight = this.getHitBox().getHeight();


        if(colRecX + colWidth + forceX > entRecX
                && colRecX + forceX < entRecX + entColWidth
                && colRecY + colRecHeight > entRecY && colRecY < entRecY + entColHeight) {
            forceX *= -1;
        } /*else if(colRecX < 0 ||)*/

        return forceX;
    }

    public double verificationY(Entite ent, double forceY) {
        double entRecX = ent.getX();
        double entRecY = ent.getY();
        double entColWidth = ent.getCollider().getHitBox().getWidth();
        double entColHeight = ent.getCollider().getHitBox().getHeight();

        double colRecX = this.getEnt().getX();
        double colRecY = this.getEnt().getY();
        double colWidth = this.getHitBox().getWidth();
        double colRecHeight = this.getHitBox().getHeight();


        if(colRecX + colWidth > entRecX
                && colRecX < entRecX + entColWidth
                && colRecY + colRecHeight + forceY > entRecY &&
                colRecY + forceY < entRecY + entColHeight) {
            forceY *= -1;
        } /*else if(colRecX < 0 ||)*/

        return forceY;
    }

    public boolean intersect(Entite ent, double ajoutX, double ajoutY) {
        if (ent.getCollider() != null) {
            Entite selfEnt = this.ent;
            Collider entCollider = ent.getCollider();

            if (selfEnt.getX() >= ent.getX() + ajoutX &&
                    selfEnt.getX() < ent.getX() + entCollider.getHitBox().getWidth() + ajoutX
                    && selfEnt.getY() >= ent.getY() + ajoutY
                    && selfEnt.getY() < ent.getY() + entCollider.getHitBox().getHeight() + ajoutY)
                return true;


        }
        return false;
    }

    /*public Direction dectecterDirectionObjet(Entite ent) {
        Direction direction = Direction.AUCUNE;

        if(intersect(ent, 1,0)) {
            direction = Direction.Droit;
        }
        if(intersect(ent, -1, 0)) {
            direction = Direction.Gauche;
        }
        if(intersect(ent, ))
        //Verification direction HAUT


    }*/

    //public boolean LigneIntersectionLigne(double)
    public Entite tracerLigne2(double origineX, double origineY, double finX, double finY) {
        Entite entTrouvee = null;

        for (String nom : this.ent.getEnv().getHashMapListes().keySet())
            if (nom.equals("listeEntites") || nom.equals("listeMateriaux"))
                for(int i = 0; i < this.ent.getEnv().getHashMapListes().get(nom).size(); i++) {
                    Entite entAVerifier = (Entite) this.ent.getEnv().getHashMapListes().get(nom).get(i);

                }

        return null;
    }


    public Entite tracerLigne(double origineX, double origineY, double longueurX, double longueurY) {
        Entite entTrouve = null;

        double distanceDepuisOrigine = Math.abs(origineX) + Math.abs(origineY);
        double distanceDepuisEntite = 90000;

        for (String nom : this.ent.getEnv().getHashMapListes().keySet())
            if (nom.equals("listeEntites") || nom.equals("listeMateriaux"))
                for (int i = 0; i < this.ent.getEnv().getHashMapListes().get(nom).size(); i++) {
                    Entite entAVerifier = (Entite) this.ent.getEnv().getHashMapListes().get(nom).get(i);
                    if(entAVerifier != this.getEnt()) {
                        double differencePositiveX = Math.abs(origineX - entAVerifier.getX());
                        double differencePositiveY = Math.abs(origineY - entAVerifier.getY());
                        double absLongueurX = Math.abs(longueurX);
                        double absLongueurY = Math.abs(longueurY);


                        if (differencePositiveX <= absLongueurX && differencePositiveY <= absLongueurY) {
                            //System.out.println("on vérifie " + entAVerifier);
                            if(intersect(entAVerifier,-differencePositiveX, 0)
                                || intersect(entAVerifier,+differencePositiveX, 0)
                                ||intersect(entAVerifier,0, differencePositiveY)
                                    ||intersect(entAVerifier,0, -differencePositiveY)) {

                                if(Math.abs(entAVerifier.getX()) + Math.abs(entAVerifier.getY()) < distanceDepuisEntite) {
                                    distanceDepuisEntite = Math.abs(entAVerifier.getX()) + Math.abs(entAVerifier.getY());
                                    entTrouve = entAVerifier;
                                }


                            }
                            else {
                            }

                            /*if(intersect(entAVerifier,0, 0)
                                    || intersect(entAVerifier, 0, 0)
                                    || intersect(entAVerifier, 0,0)
                                    || intersect(entAVerifier, 0,0)) {

                            }*/


                        }
                    }
                }

        return entTrouve;
    }

    /*

    /*float posX = this.ent.getX() + this.hitbox.getX();
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


            return s_H < a_HP && s_HP > a_H && s_B < a_BP && s_BP > a_B;*/
}
