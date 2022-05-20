package application.modele;

import application.modele.objets.Minerai;
import application.modele.objets.Pierre;
import application.modele.objets.Terre;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private Personnage personnage;
    private MapJeu mapJeu;
    private ObservableList<Minerai> listeMinerais;

    public Environnement() {
        personnage = new Personnage(this);
        mapJeu = new MapJeu();
        listeMinerais = FXCollections.observableArrayList();
        initListeMinerais();
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

    private void initListeMinerais() {
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                switch (mapJeu.getTabMap()[i][j]) {
                    case 34: listeMinerais.add(new Terre(j,i));
                    case 52: listeMinerais.add(new Pierre(j,i));
                    default: break;
                }
            }
        }
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public MapJeu getMapJeu() {
        return mapJeu;
    }

    public Minerai getMinerai(int x, int y) {
        for (Minerai minerai : listeMinerais)
            if (minerai.getX() == x && minerai.getY() == y)
                return minerai;

        return null;
    }
}
