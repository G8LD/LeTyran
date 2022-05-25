package application.modele;

import application.modele.objets.*;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static application.modele.MapJeu.TUILE_TAILLE;
import static application.modele.MapJeu.WIDTH;

public class Environnement {

    private Personnage personnage;
    private MapJeu mapJeu;
    private ObservableList<Minerai> listeMinerais;
    private ObservableList<ObjetJeu> listeObjets;

    public Environnement() {
        personnage = new Personnage(this);
        mapJeu = new MapJeu();
        listeMinerais = FXCollections.observableArrayList();
        listeObjets = FXCollections.observableArrayList();


        ObjetJeu nouvObj = new ObjetJeu(this, 1, "Epee", 1);
        nouvObj.setX(2 * 32);
        nouvObj.setY(2 * 10);
        listeObjets.add(nouvObj);

        initListeMinerais();
    }

    public ObservableList<ObjetJeu> getObjets() {
        return this.listeObjets;
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
                    case 42: listeMinerais.add(new Fer(j,i));
                    case 52: listeMinerais.add(new Pierre(j,i));
                    case 53: listeMinerais.add(new Platine(j,i));
                    default: break;
                }
            }
        }
    }

    public void supprimerObjetEnvironnement(ObjetJeu obj) {
        this.listeObjets.remove(obj);
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

    public void update() {
        for(int i = 0; i < this.listeObjets.size(); i++) {
            ObjetJeu obj = this.listeObjets.get(i);
            obj.update();

            //appliquerGravite();
        }
    }

    public void appliquerGravite() {
        for(int i = 0; i < this.listeObjets.size(); i++) {
            Entite ent = this.listeObjets.get(i);
            if(!ent.getIgnoreGravite()) {
                Entite collisionEntite = ent.getCollider().tracerLigne(0, -10);
                if(collisionEntite != null) {
                    System.out.println("je dois tomberd");
                }
            };
        }
    }

    public ObservableList<Minerai> getListeMinerais() {
        return listeMinerais;
    }
}
