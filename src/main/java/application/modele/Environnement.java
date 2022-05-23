package application.modele;

import application.modele.objets.*;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.crypto.AEADBadTagException;

import static application.modele.MapJeu.TUILE_TAILLE;
import static application.modele.MapJeu.WIDTH;

public class Environnement {

    private Personnage personnage;
    private MapJeu mapJeu;
    private ObservableList<Minerai> listeMinerais;
    private ObservableList<Arbre> listeArbres;

    public Environnement() {
        personnage = new Personnage(this);
        mapJeu = new MapJeu();
        listeMinerais = FXCollections.observableArrayList();
        initListeMinerais();
        listeArbres = FXCollections.observableArrayList();
        initListeArbres();
    }

    private void initListeArbres() {
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                if (mapJeu.getTabMap()[i][j] == 54) {
                    listeArbres.add(new Arbre(j, i, 3));
                }
            }
        }
    }

    private void initListeMinerais() {
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                switch (mapJeu.getTabMap()[i][j]) {
                    case 34: listeMinerais.add(new Terre(j,i));
                    case 42: listeMinerais.add(new Fer(j,i));
                    case 52: listeMinerais.add(new Pierre(j,i));
                    case 53: listeMinerais.add(new Platine(j,i));
                    default: break;
                }
            }
        }
    }
    public void interaction(int x, int y) {
        if (getMinerai(x, y) != null)
            minage(x,y);
        else
            couper(x,y);
    }

    private void couper(int x, int y) {
        Arbre arbre = getArbre(x,y);
        arbre.frappe(personnage.getArme());
        if (arbre.getPv() <= 0) {
            listeArbres.remove(arbre);
            mapJeu.getTabMap()[y][x] = 0;
            System.out.println("arbre coupé");
        }
    }

    public void minage(int x, int y) {
        Minerai minerai = getMinerai(x,y);
        minerai.frappe(personnage.getArme());
        if (minerai.getPv() <= 0) {
            listeMinerais.remove(minerai);
            mapJeu.getTabMap()[y][x] = 0;
            System.out.println("minerai cassé");
        }
    }

    public boolean entreEnCollision(int xPerso, int yPerso, Direction dir) {
        boolean collision = false;
        mapJeu.getTabMap();

        int x = xPerso/TUILE_TAILLE;
        int y = yPerso/TUILE_TAILLE;
        switch (dir) {
            case Droit:
                //regarde la case suivante lorque le joueur est sur le point de l'atteindre
                if ((xPerso + 1) / TUILE_TAILLE > x && (xPerso + 1) % TUILE_TAILLE != 0) x++;
                //pour le saut lorsque le perso est entre 1 cases verticalement
                if (x+1 >= 0 && x+1 < WIDTH && yPerso % TUILE_TAILLE != 0 && !estUnObstacle(x+1,y)) y++;
                //verifie la collision
                if (x + 1 >= MapJeu.WIDTH || estUnObstacle(x+1,y))
                    collision = true;
                break;
            case Gauche:
                if ((double) (xPerso - 1) / TUILE_TAILLE < x) x--;
                if (x >= 0 && x < WIDTH && yPerso % TUILE_TAILLE != 0 && !estUnObstacle(x,y)) y++;
                if (x < 0 || estUnObstacle(x,y))
                    collision = true;
                break;
            case Bas:
                if (x < 0 || x >= WIDTH || y + 1 >= MapJeu.HEIGHT || estUnObstacle(x,y+1)
                        || (xPerso % TUILE_TAILLE != 0 && estUnObstacle(x+1,y+1)))
                    collision = true;
                break;
            case Haut:
                //regarde la case suivante lorque le joueur est sur le point de l'atteindre
                if ((double) (yPerso - 1) / TUILE_TAILLE < y) y--;
                //vérifie de la case suivante si entre deux case horizontalement
                if (y < 0 || estUnObstacle(x,y)
                        || (xPerso % TUILE_TAILLE != 0 && estUnObstacle(x+1,y)))
                    collision = true;
                break;
            default:
                break;
        }
        return collision;
    }

    private boolean estUnObstacle(int x, int y) {
        return mapJeu.getTabMap()[y][x] == 34 || mapJeu.getTabMap()[y][x] == 42 || mapJeu.getTabMap()[y][x] == 52 || mapJeu.getTabMap()[y][x] == 53;
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

    public Arbre getArbre(int x, int y) {
        if (mapJeu.getTabMap()[y][x] == 55) y++;
        else if (mapJeu.getTabMap()[y][x] == 56) y+=2;

        for (Arbre arbre : listeArbres)
            if (arbre.getX() == x && arbre.getY() == y)
                return arbre;
        return null;
    }

    public ObservableList<Minerai> getListeMinerais() {
        return listeMinerais;
    }

    public ObservableList<Arbre> getListeArbres() {
        return listeArbres;
    }
}
