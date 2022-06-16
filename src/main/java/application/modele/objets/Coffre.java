package application.modele.objets;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.armes.Epee;

import java.util.ArrayList;


public class Coffre extends Entite {

    private ArrayList<Entite> loot;

    public Coffre(Environnement env, int x, int y) {
        super(env, x, y);
        loot= new ArrayList<Entite>();
        this.remplirLoot();

    }
    private ArrayList<Entite> remplirLoot(){
        int x=(int) (Math.random() * 3 + 1);
        this.loot.add(new Epee(getEnv(), x));
        for (int j=0 ; j<5;j++){
            this.loot.add(new Pierre(getEnv(),0,0 ));
        }
        return this.loot;
    }
    public float getX() {
        return super.getX();
    }

    public float getY() {
        return super.getY();
    }

    public ArrayList<Entite> getLoot() {
        return this.loot;
    }
}
