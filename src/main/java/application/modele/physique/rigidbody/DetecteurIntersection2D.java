package application.modele.physique.rigidbody;

import application.modele.Ligne2D;
import application.modele.maths.TMath;
import application.modele.maths.Vecteur2D;
import application.modele.physique.primitives.AABB;
import application.modele.physique.primitives.Boite2D;
import application.modele.physique.primitives.Cercle2D;

public class DetecteurIntersection2D {
    public static boolean pointDansLigne(Vecteur2D point, Ligne2D ligne) {
        float dy = ligne.getFin().y - ligne.getDebut().y;
        float dx = ligne.getFin().x - ligne.getDebut().x;
        float m = dy / dx;

        if (dx == 0f) {
            return TMath.comparer(point.x, ligne.getDebut().x);
        }

        float b = ligne.getFin().y - (m * ligne.getFin().x);

        //On vérifie l'équation

        return point.y == m * point.x + b;
    }

    public static boolean pointDansCercle(Vecteur2D point, Cercle2D cercle) {
        Vecteur2D centreCercle = cercle.getCentre();
        Vecteur2D centreAuPoint = new Vecteur2D(point).ajouter(centreCercle);

        return centreAuPoint.tailleAuCarre() <= cercle.getRadius() * cercle.getRadius();
    }

    public static boolean pointDansAABB(Vecteur2D point, AABB boite) {
        Vecteur2D min = boite.getMin();
        Vecteur2D max = boite.getMax();

        return point.x <= max.x && min.x <= point.x && point.y <= max.y && min.y <= point.y;
    }

    public static boolean pointDansBoite2D(Vecteur2D point, Boite2D boite) {
        //On déplace le point dans le même plan
        Vecteur2D pointAjusterPlanBoite = new Vecteur2D(point);
        TMath.rotation(pointAjusterPlanBoite, boite.getRigibody().getRotation(), boite.getRigibody().getPosition());

        Vecteur2D min = boite.getMin();
        Vecteur2D max = boite.getMax();

        return pointAjusterPlanBoite.x <= max.x && min.x <= pointAjusterPlanBoite.x
                && pointAjusterPlanBoite.y <= max.y && min.y <= pointAjusterPlanBoite.y;
    }

    public static boolean ligneEtCercle(Ligne2D ligne, Cercle2D cercle) {
        if (pointDansCercle(ligne.getDebut(), cercle) || pointDansCercle(ligne.getFin(), cercle)) {
            return true;
        }
        Vecteur2D ab = new Vecteur2D(ligne.getFin()).soustraire(ligne.getDebut());

        Vecteur2D centreCercle = cercle.getCentre();
        Vecteur2D centrerSurDebutLigne = new Vecteur2D(centreCercle).soustraire(ligne.getDebut());

        float t = centrerSurDebutLigne.dot(ab) / ab.dot(ab);

        if (t < 0.0f || t > 1.0f) {
            return false;
        }

        Vecteur2D ligneSegment = new Vecteur2D(ligne.getDebut().ajouter(ab.multiplier(t)));

        return pointDansCercle(ligneSegment, cercle);
    }

    public static boolean ligneEtAABB(Ligne2D ligne, AABB boite) {
        if(pointDansAABB(ligne.getDebut(), boite) || pointDansAABB(ligne.getFin(), boite)) {
            return true;
        }

        Vecteur2D unitVector = new Vecteur2D(ligne.getFin().soustraire(ligne.getDebut()));
        unitVector.normaliser();
        unitVector.x = (unitVector.x !=0) ? 1.0f / unitVector.x : 0f;
        unitVector.y = (unitVector.y !=0) ? 1.0f / unitVector.y : 0f;

        Vecteur2D min = boite.getMin();
        min.soustraire(ligne.getDebut()).multiplier(unitVector);
        Vecteur2D max = boite.getMax();
        max.soustraire(ligne.getDebut()).multiplier(unitVector);

        float tmin = Math.max(Math.min(min.x, max.x), Math.min(min.y, max.y));
        float tmax = Math.min(Math.max(min.x, max.x), Math.max(min.y, max.y));

        if(tmax < 0 || tmin > tmax) {
            return false;
        }

        float t = (tmin < 0f) ? tmax : tmin;
        //return t > 0f && t *t < ligne.ta

    }
}
