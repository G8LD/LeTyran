package application.modele;

import application.modele.armes.Epee;
import application.modele.objets.*;
import application.modele.personnages.Ennemi;
import application.modele.personnages.Joueur;
import application.modele.personnages.Lapin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.AudioClip;

import static application.modele.MapJeu.TUILE_TAILLE;
import static application.modele.MapJeu.WIDTH;

public class Environnement {

    private Joueur joueur;
    private MapJeu mapJeu;
    private Etabli etabli;
    private ObservableList<Entite> listeEntites;
    private ObservableList<Materiau> listeMateriaux;
    private ObservableList<Arbre> listeArbres;
    private ObservableList< Coffre> listeCoffres;
    private ObservableList<Ennemi> listeEnnemis;


    public Environnement() {
        this.joueur = new Joueur(this);

        mapJeu = new MapJeu();
        etabli = new Etabli(this);

        listeEntites = FXCollections.observableArrayList();
        listeCoffres = FXCollections.observableArrayList();

        ObjetJeu nouvObj = new ObjetJeu(this, "Epee", 1);
        ObjetJeu nouvObj2 = new ObjetJeu(this,  "Bois", 1);
        nouvObj.setX(2 * 32);
        nouvObj.setY(4 * 32);
        nouvObj2.setX(2 * 32);
        nouvObj2.setY(2 * 32);
        /*this.listeEntites.add(nouvObj);
        this.listeEntites.add(nouvObj2);*/
        this.listeEntites.add(joueur);

        initListeMinerais();
        initListeArbres();
        initListeCoffres();
        initListeEnnemis();
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
                    case 34: listeMateriaux.add(new Terre(this, j, i)); break;
                    case 52: listeMateriaux.add(new Pierre(this, j, i)); break;
                    case 53: listeMateriaux.add(new Fer(this, j, i)); break;
                    case 54: listeMateriaux.add(new Platine(this, j, i)); break;
                    default: break;
                }
            }
        }
    }


    private void initListeEnnemis() {
        listeEnnemis = FXCollections.observableArrayList();
        listeEnnemis.add(new Ennemi(this, new Epee(this, 3), 15, 11, 5));
        listeEnnemis.add(new Ennemi(this, new Epee(this, 3), 20, 0, 5));
        listeEnnemis.add(new Ennemi(this, new Epee(this, 3), 18, 18, 10));
        listeEnnemis.add(new Lapin(this, 1, 0, 5));
    }

    private void initListeCoffres() {
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                if (mapJeu.getTabMap()[i][j] == 58) {
                    listeCoffres.add(new Coffre(this, j, i));
                }
            }
        }
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

    public Ennemi getEnnemi(int x, int y) {
        for (Ennemi ennemi : listeEnnemis) {
            if (Math.abs(ennemi.getX() / TUILE_TAILLE - x) < 1 && (int) ennemi.getY() / TUILE_TAILLE == y)
                return ennemi;
        }
        return null;
    }



    public Coffre getCoffre(int x, int y) {
        for (Coffre coffre : listeCoffres)
            if (coffre.getX() == x && coffre.getY() == y)
                return coffre;
        return null;
    }

    public void update() {
        for(int i = 0; i < this.listeEntites.size(); i++) {
            Entite obj = this.listeEntites.get(i);
            obj.update();

            //appliquerGravite();
        }

        for (Ennemi ennemi : listeEnnemis)
            ennemi.update();
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

    //region Getter & Setter
    public ObservableList<Entite> getListeEntites() {
        return listeEntites;
    }

    public ObservableList<Materiau> getListeMateriaux() {
        return listeMateriaux;
    }

    public ObservableList<Arbre> getListeArbres() {
        return listeArbres;
    }

    public ObservableList<Ennemi> getListeEnnemis() {
        return listeEnnemis;
    }

    public ObservableList<Coffre> getListeCoffres() {
        return listeCoffres;
    }

    public Joueur getJoueur() {
        return joueur;
    }


    //endregion

}
