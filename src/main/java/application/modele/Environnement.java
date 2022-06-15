package application.modele;

import application.modele.armes.arc.Fleche;
import application.modele.objets.*;
import application.modele.personnages.*;
import application.modele.personnages.ennemi.Archer;
import application.modele.personnages.ennemi.Ennemi;
import application.modele.personnages.ennemi.Epeiste;
import application.modele.personnages.ennemi.Lancier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

import static application.modele.MapJeu.TUILE_TAILLE;
import static application.modele.MapJeu.WIDTH;

public class Environnement {

    private Joueur joueur;
    private MapJeu mapJeu;
    private Etabli etabli;
    private FeuDeCamp feuDeCamp;
    private HashMap<String, ObservableList> hashMapListes;

    private Ennemie ennemie;

    public Environnement() {
        hashMapListes = new HashMap<>() {{
            put("listeEntites", FXCollections.observableArrayList());
            put("listeMateriaux", FXCollections.observableArrayList());
            put("listeArbres", FXCollections.observableArrayList());
            put("listeCoffres", FXCollections.observableArrayList());
            put("listeEnnemis", FXCollections.observableArrayList());
            put("listeFleches", FXCollections.observableArrayList());
        }};
        joueur = new Joueur(this);
        this.ennemie=new Ennemie(this, 500 ,350);

        mapJeu = new MapJeu();
        etabli = new Etabli(this);
        feuDeCamp = new FeuDeCamp(this, 13, 8);

        ObjetJeu nouvObj = new ObjetJeu(this, "Epee", 1);
        ObjetJeu nouvObj2 = new ObjetJeu(this,  "Bois", 1);
        nouvObj.setX(13 * 32);
        nouvObj.setY(10 * 32);
        nouvObj2.setX(2 * 32);
        nouvObj2.setY(2 * 32);
        this.getListeEntites().add(nouvObj);
        //this.getListeEntites().add(nouvObj2);*/
        getListeEntites().add(joueur);

        initListeMinerais();
        initListeArbres();
        initListeCoffres();
        initListeEnnemis();
    }

    private void initListeArbres() {
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                if (mapJeu.getTabMap()[i][j] == 55) {
                    getListeArbres().add(new Arbre(this, j, i));
                }
            }
        }
    }

    private void initListeMinerais() {
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                int posY = i * TUILE_TAILLE;

                int posX = j * TUILE_TAILLE;
                switch (mapJeu.getTabMap()[i][j]) {
                    case 34:
                        getListeMateriaux().add(new Terre(this, posX, posY));
                        break;
                    case 52:
                        getListeMateriaux().add(new Pierre(this, posX, posY));
                        break;
                    case 53:
                        getListeMateriaux().add(new Fer(this, posX, posY));
                        break;
                    case 54:
                        getListeMateriaux().add(new Platine(this, posX, posY));
                        break;
                    default: break;
                }
            }
        }
    }


    public void initListeEnnemis() {
        getListeEnnemis().clear();
        getListeEnnemis().add(new Archer(this, 1, 20, 11, 0));
        getListeEnnemis().add(new Lancier(this, 1, 15, 11, 0));
        getListeEnnemis().add(new Epeiste(this,1, 20, 0, 5));
        getListeEnnemis().add(new Epeiste(this,1, 18, 18, 10));
    }

    private void initListeCoffres() {
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                if (mapJeu.getTabMap()[i][j] == 58) {
                    getListeCoffres().add(new Coffre(this, j, i));
                }
            }
        }
    }

    public void supprimerObjetEnvironnement(Entite obj) {
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

    public Materiau getMinerai(int x, int y) {
        for (Materiau minerai : getListeMateriaux())
            if (minerai.getX() == x && minerai.getY() == y)
                return minerai;
        return null;
    }

    public Arbre getArbre(int x, int y) {
        if (mapJeu.getTabMap()[y][x] == 56) y++;
        else if (mapJeu.getTabMap()[y][x] == 57) y+=2;

        for (Arbre arbre : getListeArbres())
            if (arbre.getX() == x && arbre.getY() == y)
                return arbre;
        return null;
    }

    public Ennemi getEnnemi(int x, int y) {
        for (Ennemi ennemi : getListeEnnemis()) {
            if (Math.abs(ennemi.getX() / TUILE_TAILLE - x) < 1 && (int) ennemi.getY() / TUILE_TAILLE == y)
                return ennemi;
        }
        return null;
    }

    public Coffre getCoffre(int x, int y) {
        for (Coffre coffre : getListeCoffres())
            if (coffre.getX() == x && coffre.getY() == y)
                return coffre;
        return null;
    }

    public void update() {
        for(int i = 0; i < this.getListeEntites().size(); i++) {
            Entite obj = this.getListeEntites().get(i);
            obj.update();

            //appliquerGravite();
        }

        for (int i = 0; i < getListeEnnemis().size(); i++)
            getListeEnnemis().get(i).update();

        for (int i = 0; i < getListeFleches().size(); i++)
            getListeFleches().get(i).update();
    }

    /*public void appliquerGravite() {
        for(int i = 0; i < this.getListeEntites().size(); i++) {
            Entite ent = this.getListeEntites().get(i);
            if(!ent.getIgnoreGravite()) {
                Entite collisionEntite = ent.getCollider().tracerLigne(0, 32);
                if(collisionEntite == null) {
                    ent.gravite();
                }
            };
        }
    }*/

    //region Getter & Setter

    public HashMap<String, ObservableList> getHashMapListes() {
        return hashMapListes;
    }

    public ObservableList<Entite> getListeEntites() {
        return hashMapListes.get("listeEntites");
    }

    public ObservableList<Materiau> getListeMateriaux() {
        return hashMapListes.get("listeMateriaux");
    }

    public ObservableList<Arbre> getListeArbres() {
        return hashMapListes.get("listeArbres");
    }

    public ObservableList<Ennemi> getListeEnnemis() {
        return hashMapListes.get("listeEnnemis");
    }

    public ObservableList<Coffre> getListeCoffres() {
        return hashMapListes.get("listeCoffres");
    }

    public ObservableList<Fleche> getListeFleches() {
        return hashMapListes.get("listeFleches");
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public MapJeu getMapJeu() {
        return mapJeu;
    }

    public Etabli getEtabli() {
        return etabli;
    }

    public FeuDeCamp getFeuDeCamp() {
        return feuDeCamp;
    }

    public Ennemie getEnnemie(){
        return this.ennemie;
    }
    //endregion

}
