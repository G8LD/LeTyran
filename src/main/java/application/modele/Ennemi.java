package application.modele;

import application.modele.armes.Armure;
import application.modele.armes.Epee;
import application.modele.objets.Pierre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Ennemi extends Personnage {
    private ObservableList<Ennemi> listeCadavres;
    private int x;
    private int y;
    private ArrayList<Entite> loot;

    public Ennemi(Environnement env, int x, int y) {
        super(env);
        this.x = x;
        this.y = y;
        listeCadavres = FXCollections.observableArrayList();
        loot = new ArrayList<Entite>();
        this.remplirLoot();

    }

    private ArrayList<Entite> remplirLoot() {
        int x=(int) (Math.random() * 3 + 1);
        this.loot.add(new Epee(getEnv(), x));
        this.loot.add(new Armure(getEnv(), (int) ((Math.random() * 3) + 1)));

        return this.loot;
    }

    public int attaqueJoueur(int nbdegat) {
        return this.getEnv().getPersonnage().getPv() - nbdegat;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public ArrayList<Entite> getLoot() {
        return this.loot;
    }
}