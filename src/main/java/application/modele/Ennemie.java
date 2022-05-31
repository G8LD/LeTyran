package application.modele;

public class Ennemie extends Personnage {


    public Ennemie(Environnement env) {
        super(env);
    }

    public int attaqueJoueur(int nbdegat){
        return  this.getEnv().getPersonnage().getPv()-nbdegat;
    }
}
