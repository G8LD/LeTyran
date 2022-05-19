package application.modele;

import javafx.beans.property.IntegerProperty;

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
        int x = xPerso/32;
        int y = yPerso/32;
        //System.out.println("x = " + x + " y = " + y);
        switch (dir) {
            case Droit:
                if(x + 1 >= MapJeu.WIDTH || mapJeu.getTabMap()[y][x + 1] != 0) {
                    //System.out.println("Collision avec " + intMap[y][x + 1]);
                    collision = true;
                }
                break;
            case Gauche:
                if(x < 0 || mapJeu.getTabMap()[y][x] != 0) {
                    collision = true;
                }
                break;
            case Bas:
                //System.out.println(intMap[y + 1][x] == 0);
                if(y + 1 >= MapJeu.HEIGHT || mapJeu.getTabMap()[y + 1][x] != 0) {
                    collision = true;
                }
                break;
            case Haut:
                if(y < 0 || mapJeu.getTabMap()[y][x] != 0) {
                    collision = true;
                }
                break;
            default:
                break;
        }
        System.out.println(collision);
        return collision;
    }
}
