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

    public boolean entreEnCollision(int x, int y, Direction dir) {
        boolean collision = false;
        int[][] intMap = mapJeu.getTabMap();

        switch (dir) {
            case Droit:
                if(x + 1 >= MapJeu.WIDTH || intMap[y][x + 1] != 0) {
                    //System.out.println("Collision avec " + intMap[y][x + 1]);
                    collision = true;
                }
                break;
            case Gauche:
                if(x - 1 < 0 || intMap[y][x -1] != 0) {
                    collision = true;
                }
                break;
            case Bas:
                //System.out.println(intMap[y + 1][x] == 0);
                if(y + 1 >= MapJeu.HEIGHT || intMap[y + 1][x] != 0) {
                    collision = true;
                }
                break;
            case Haut:
                if(y - 1 < 0 || intMap[y -1][x] == 0) {
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
