package application.modele;

import application.modele.objets.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static application.modele.MapJeu.TUILE_TAILLE;
import static application.modele.MapJeu.WIDTH;

public class Environnement {

    private Personnage personnage;
    private MapJeu mapJeu;
    private ObservableList<Materiau> listeMateriaux;
    private ObservableList<Arbre> listeArbres;

    public Environnement() {
        personnage = new Personnage(this);
        mapJeu = new MapJeu();
        listeMateriaux = FXCollections.observableArrayList();
        initListeMinerais();
        listeArbres = FXCollections.observableArrayList();
        initListeArbres();
    }

    private void initListeArbres() {
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                if (mapJeu.getTabMap()[i][j] == 54) {
                    listeArbres.add(new Arbre(j, i));
                }
            }
        }
    }

    private void initListeMinerais() {
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                switch (mapJeu.getTabMap()[i][j]) {
                    case 34: listeMateriaux.add(new Terre(j,i));
                    case 42: listeMateriaux.add(new Fer(j,i));
                    case 52: listeMateriaux.add(new Pierre(j,i));
                    case 53: listeMateriaux.add(new Platine(j,i));
                    default: break;
                }
            }
        }
    }

    public void interaction(int x, int y) {
        if (!minage(x,y))
            couper(x,y);
    }

    private boolean couper(int x, int y) {
        Arbre arbre = getArbre(x,y);
        if (arbre != null) {
            arbre.frappe(personnage.getArme());
            if (arbre.getPv() <= 0) {
                listeArbres.remove(arbre);
                mapJeu.getTabMap()[y][x] = 0;
                System.out.println("arbre coupé");
            }
            return true;
        }
        return false;
    }

    private boolean minage(int x, int y) {
        Materiau minerai = getMinerai(x,y);
        if (minerai != null) {
            minerai.frappe(personnage.getArme());
            if (minerai.getPv() <= 0) {
                listeMateriaux.remove(minerai);
                mapJeu.getTabMap()[y][x] = 0;
            }
            return true;
        }
        return false;
    }

    public boolean entreEnCollision(int xPerso, int yPerso, Direction dir) {
        boolean collision = false;
        int x,y;
        switch (dir) {
            case Droit:
                x = (xPerso+TUILE_TAILLE+1)/TUILE_TAILLE;
                y = yPerso/TUILE_TAILLE;
                if ((xPerso+TUILE_TAILLE+1) % TUILE_TAILLE == 0) x--;
                if (x >= WIDTH || estUnObstacle(x, y) || (yPerso % TUILE_TAILLE != 0 && estUnObstacle(x,y+1)))
                    collision = true;
                break;
            case Gauche:
                x = (xPerso-1)/TUILE_TAILLE;
                y = yPerso/TUILE_TAILLE;
                if ((xPerso-1) % TUILE_TAILLE == 0) x++;
                if (xPerso-1 < 0 || estUnObstacle(x, y) || (yPerso % TUILE_TAILLE != 0 && estUnObstacle(x,y+1)))
                    collision = true;
                break;
            case Bas:
                x = xPerso/TUILE_TAILLE;
                y = yPerso/TUILE_TAILLE;
                if (y + 1 >= MapJeu.HEIGHT || estUnObstacle(x,y+1) || (xPerso % TUILE_TAILLE != 0 && estUnObstacle(x+1,y+1)))
                    collision = true;
                break;
            case Haut:
                x = xPerso/TUILE_TAILLE;
                y = (yPerso-1)/TUILE_TAILLE;
                if (yPerso-1 < 0 || estUnObstacle(x,y) || (xPerso % TUILE_TAILLE != 0 && estUnObstacle(x+1,y)))
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

    public Materiau getMinerai(int x, int y) {
        for (Materiau minerai : listeMateriaux)
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

    public ObservableList<Materiau> getListeMateriaux() {
        return listeMateriaux;
    }

    public ObservableList<Arbre> getListeArbres() {
        return listeArbres;
    }
}
