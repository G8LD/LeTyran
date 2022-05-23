package application.modele;

import application.modele.objets.Minerai;
import application.modele.objets.Pierre;
import application.modele.objets.Terre;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static application.modele.MapJeu.TUILE_TAILLE;
import static application.modele.MapJeu.WIDTH;

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
                if (x+1 >= 0 && x+1 < WIDTH && yPerso % TUILE_TAILLE != 0 && mapJeu.getTabMap()[y][x + 1] == 0) y++;
                //verifie la collision
                if (x + 1 >= MapJeu.WIDTH || mapJeu.getTabMap()[y][x + 1] != 0)
                    collision = true;
                break;
            case Gauche:
                if ((double) (xPerso - 1) / TUILE_TAILLE < x) x--;
                if (x >= 0 && x < WIDTH && yPerso % TUILE_TAILLE != 0 && mapJeu.getTabMap()[y][x] == 0) y++;
                if (x < 0 || mapJeu.getTabMap()[y][x] != 0)
                    collision = true;
                break;
            case Bas:
                if (x < 0 || x >= WIDTH || y + 1 >= MapJeu.HEIGHT || mapJeu.getTabMap()[y + 1][x] != 0
                        || (xPerso % TUILE_TAILLE != 0 && mapJeu.getTabMap()[y+1][x+1] != 0))
                    collision = true;
                break;
            case Haut:
                //regarde la case suivante lorque le joueur est sur le point de l'atteindre
                if ((double) (yPerso - 1) / TUILE_TAILLE < y) y--;
                //vérifie de la case suivante si entre deux case horizontalement
                if (y < 0 || mapJeu.getTabMap()[y][x] != 0
                        || (xPerso % TUILE_TAILLE != 0 && mapJeu.getTabMap()[y][x+1] != 0))
                    collision = true;
                break;
            default:
                break;
        }
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

    public void minage(int x, int y) {
        Minerai minerai = getMinerai(x,y);
        minerai.frappe(personnage.getArme());
        if (minerai.getPv() <= 0) {
            listeMinerais.remove(minerai);
            mapJeu.getTabMap()[y][x] = 0;
            personnage.getInventaire().ajouterObjet(minerai.getClass().getSimpleName());
            System.out.println("minerai cassé");
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
            if (minerai.getX() == x && minerai.getY() == y) {
                return minerai;
            }

        return null;
    }

    public ObservableList<Minerai> getListeMinerais() {
        return listeMinerais;
    }
}
