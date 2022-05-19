package application.modele;

import javafx.beans.property.IntegerProperty;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Environnement {

    private Personnage personnage;
    private MapJeu mapJeu;

    public Environnement() {
        mapJeu = new MapJeu();
        personnage = new Personnage(this);
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public MapJeu getMapJeu() {
        return mapJeu;
    }

    public boolean entreEnCollision(int xPerso, int yPerso, Direction dir) {
        boolean collision = false;
        mapJeu.getTabMap();

        int x = xPerso/TUILE_TAILLE;
        int y = yPerso/TUILE_TAILLE;
        switch (dir) {
            case Droit:
                if ((xPerso + 2) / TUILE_TAILLE > x) x++;
                //if ((double) yPerso / TUILE_TAILLE >= 0.5) y++;
//                if (personnage.getSaute())
//                    System.out.println("saut x = " + (x+1) + " y = " + y);
                System.out.println((double) yPerso / TUILE_TAILLE);
                if (x + 1 >= MapJeu.WIDTH || mapJeu.getTabMap()[y][x + 1] != 0)
                    collision = true;
                System.out.println(collision);
                break;
            case Gauche:
                if ((double) (xPerso - 2) / TUILE_TAILLE < x) x--;
                if (x < 0 || mapJeu.getTabMap()[y][x] != 0)
                    collision = true;
                break;
            case Bas:
                if (y + 1 >= MapJeu.HEIGHT || mapJeu.getTabMap()[y + 1][x] != 0
                        || ( mapJeu.getTabMap()[y + 1][x + 1] != 0))
                    collision = true;
                break;
            case Haut:
                if (y < 0 || mapJeu.getTabMap()[y][x] != 0
                        || (mapJeu.getTabMap()[y][x + 1] != 0))
                    collision = true;
                System.out.println(x);
                break;
            default:
                break;
        }
        return collision;
    }
}
