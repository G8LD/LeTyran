package application.modele;

import application.modele.objets.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static application.modele.MapJeu.TUILE_TAILLE;
import static application.modele.MapJeu.WIDTH;

public class Environnement {

    private Personnage personnage;
    private MapJeu mapJeu;
    private ObservableList<Entite> listeEntites;
    private ObservableList<Materiau> listeMateriaux;
    private ObservableList<Arbre> listeArbres;

    public Environnement() {
        personnage = new Personnage(this);
        mapJeu = new MapJeu();
        listeMateriaux = FXCollections.observableArrayList();
        listeEntites = FXCollections.observableArrayList();
        listeArbres = FXCollections.observableArrayList();


        ObjetJeu nouvObj = new ObjetJeu(this, "Epee", 1);
        ObjetJeu nouvObj2 = new ObjetJeu(this,  "Bois", 1);
        nouvObj.setX(2 * 32);
        nouvObj.setY(4 * 32);

        nouvObj2.setX(2 * 32);
        nouvObj2.setY(2 * 32);


        this.listeEntites.add(nouvObj);
        this.listeEntites.add(nouvObj2);
        this.listeEntites.add(personnage);
        initListeMinerais();
    }

    public ObservableList<Entite> getObjets() {
        return this.listeEntites;
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
                System.out.println("minerai cassé");
            }
            return true;
        }
        return false;
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

    public void supprimerObjetEnvironnement(ObjetJeu obj) {
        this.listeEntites.remove(obj);
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

    public Arbre getArbre(int x, int y) {
        if (mapJeu.getTabMap()[y][x] == 55) y++;
        else if (mapJeu.getTabMap()[y][x] == 56) y+=2;

        for (Arbre arbre : listeArbres)
            if (arbre.getX() == x && arbre.getY() == y)
                return arbre;
        return null;
    }

    public ObservableList<Arbre> getListeArbres() {
        return listeArbres;
    }

    public ObservableList<Materiau> getListeMateriaux() {
        return listeMateriaux;
    }
}