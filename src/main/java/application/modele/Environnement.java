package application.modele;

import application.modele.objets.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static application.modele.MapJeu.TUILE_TAILLE;
import static application.modele.MapJeu.WIDTH;

public class Environnement {

    private Personnage personnage;
    private MapJeu mapJeu;
    private Etabli etabli;
    private ObservableList<Entite> listeEntites;
    private ObservableList<Materiau> listeMateriaux;
    private ObservableList<Arbre> listeArbres;

    public Environnement() {
        personnage = new Personnage(this);
        mapJeu = new MapJeu();
        etabli = new Etabli(this);

        listeEntites = FXCollections.observableArrayList();
        ObjetJeu nouvObj = new ObjetJeu(this, "Epee", 1);
        ObjetJeu nouvObj2 = new ObjetJeu(this,  "Bois", 1);
        nouvObj.setX(2 * 32);
        nouvObj.setY(4 * 32);
        nouvObj2.setX(2 * 32);
        nouvObj2.setY(2 * 32);
        /*this.listeEntites.add(nouvObj);
        this.listeEntites.add(nouvObj2);*/
        this.listeEntites.add(personnage);

        initListeMinerais();
        initListeArbres();
    }

    private void initListeArbres() {
        listeArbres = FXCollections.observableArrayList();
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                if (mapJeu.getTabMap()[i][j] == 55) {
                    listeArbres.add(new Arbre(this, j, i));
                }
            }
        }
    }

    private void initListeMinerais() {
        listeMateriaux = FXCollections.observableArrayList();
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                switch (mapJeu.getTabMap()[i][j]) {
                    case 34:
                        listeMateriaux.add(new Terre(this, j, i));
                    case 52:
                        listeMateriaux.add(new Pierre(this, j, i));
                    case 53:
                        listeMateriaux.add(new Fer(this, j, i));
                    case 54:
                        listeMateriaux.add(new Platine(this, j, i));
                    default:
                        break;
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
            return true;
        }
        return false;
    }

    private boolean minage(int x, int y) {
        Materiau minerai = getMinerai(x,y);

        if (minerai != null) {
            minerai.frappe(personnage.getArme());
            return true;
        }
        return false;
    }

    public void supprimerObjetEnvironnement(Entite obj) {
        System.out.println(this.listeEntites.remove(obj));
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
        return mapJeu.getTabMap()[y][x] == 34 || mapJeu.getTabMap()[y][x] == 54 || mapJeu.getTabMap()[y][x] == 52 || mapJeu.getTabMap()[y][x] == 53;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public MapJeu getMapJeu() {
        return mapJeu;
    }

    public Etabli getEtabli() {
        return etabli;
    }

    public Materiau getMinerai(int x, int y) {
        for (Materiau minerai : listeMateriaux)
            if (minerai.getX() == x && minerai.getY() == y)
                return minerai;
        return null;
    }

    public Arbre getArbre(int x, int y) {
        if (mapJeu.getTabMap()[y][x] == 56) y++;
        else if (mapJeu.getTabMap()[y][x] == 57) y+=2;

        for (Arbre arbre : listeArbres)
            if (arbre.getX() == x && arbre.getY() == y)
                return arbre;
        return null;
    }

    public ObservableList<Entite> getListeEntites() {
        return listeEntites;
    }

    public ObservableList<Materiau> getListeMateriaux() {
        return listeMateriaux;
    }

    public ObservableList<Arbre> getListeArbres() {
        return listeArbres;
    }

    public void update() {
        for(int i = 0; i < this.listeEntites.size(); i++) {
            Entite obj = this.listeEntites.get(i);
            obj.update();

            //appliquerGravite();
        }
    }

    /*public void appliquerGravite() {
        for(int i = 0; i < this.listeEntites.size(); i++) {
            Entite ent = this.listeEntites.get(i);
            if(!ent.getIgnoreGravite()) {
                Entite collisionEntite = ent.getCollider().tracerLigne(0, 32);
                if(collisionEntite == null) {
                    ent.gravite();
                }
            };
        }
    }*/

}
