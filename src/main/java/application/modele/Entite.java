package application.modele;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;
import application.modele.armes.arc.Arc;
import application.modele.armes.arc.Fleche;
import application.modele.collisions.Collider;
import application.modele.objets.Materiau;
import application.modele.personnages.Joueur;
import javafx.beans.property.*;

public class Entite {
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private Environnement env;
    private Collider collider;
    private IntegerProperty pv;


    private boolean tombe = false;

    private double forceVertical = 0;
    private double forceHorizontal = 0;

    public Entite(Environnement env) {
        pv= new SimpleIntegerProperty(100);
        this.xProperty = new SimpleDoubleProperty(0);
        this.yProperty = new SimpleDoubleProperty(0);
        this.collider = new Collider(this);
        //this.getCollider().scaleCollider(32,32);
        this.env = env;
        this.pv = new SimpleIntegerProperty(30);
    }


    //Pour les matériaux
    public Entite(Environnement env, int x, int y) {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
        this.env = env;
        this.collider = new Collider(this);
        this.pv = new SimpleIntegerProperty(100);
    }

    public Entite(Environnement env, int x, int y, int pv) {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
        this.env = env;
        this.collider = new Collider(this);
        this.pv = new SimpleIntegerProperty(pv);
    }

    public Entite() {
        this.xProperty = new SimpleDoubleProperty(0);
        this.yProperty = new SimpleDoubleProperty(0);
    }

    public void ajouterForceHorizontal(double force) {
        this.forceHorizontal += force;
    }

    public void ajouterForceVertical(double force) {
        this.forceVertical += force;
    }

    public void update() {

        //double forceTotal = gravite();
        //System.out.println(forceTotal);
        ajouterForceVertical(-5);

        double ancienneForceV = forceVertical;
        double ancienneForceH = forceHorizontal;

        //System.out.println(ancienneForceH + " " + ancienneForceV);

        if(this.getCollider() != null) {
            collide();
        }




        if (forceHorizontal != 0 && ancienneForceH != forceHorizontal){
            this.setX(this.getX() + forceHorizontal);
        }
        if(forceVertical != 0 && ancienneForceV != forceVertical) {
            this.setY(this.getY() + forceVertical);
        }

        //this.getCollider().tracerLigne(this.getX(), this.getY(), 32,0);

        forceVertical *= 0.5f;
        forceHorizontal *= 0.5f;



        //tomber();


    }

    /*public boolean appliquerHorizontal() {
        boolean doitAppliquer = true;

        if (this instanceof Joueur) {
            if (this.getCollider().tracerLigne(this.getX(), this.getY(), Mathematiques.clamp(forceHorizontal, -1, 1) * 32, 1 * 32 - 10) != null) {
                doitAppliquer = false;
            }
        }

        return doitAppliquer;
    }


    public boolean appliquerGravite() {
        boolean doitAppliquer = true;
        if (this instanceof Joueur) {
            if (this.getCollider().tracerLigne(this.getX(), this.getY(), 32, 32) != null) {
                doitAppliquer = false;
            }
        }

        return doitAppliquer;
    }*/

    /*private void tomber() {
        if (!(this instanceof Materiau));
            this.setY(this.getY() + forceVertical);


    }*/
    /*private void tomber() {
        for (int i = 0; i < 3; i++)
        if (!env.entreEnCollision((int) this.getX(), (int)this.getY(), Direction.Bas)) {
            tombe = true;
            this.setY(this.getY() + 1);
        }
        tombe = false;

    }*/

    public void detruire() {
    }

    public void collide() {
        if(!this.getCollider().getIgnoreCollision() && !(this instanceof Materiau)) {
            for (String nom : env.getHashMapListes().keySet())
                if (nom.equals("listeEntites") || nom.equals("listeMateriaux"))
                for (int i = 0; i < env.getHashMapListes().get(nom).size(); i++) {
                    Entite ent = (Entite) env.getHashMapListes().get(nom).get(i);
                    //Pour éviter de faire trop de tour de boucle, on va vérifier aussi si les cases autour de l'entite peuvent potentiellement la bloquer,
                    //ça sera utile quand on voudra vérifié la possibilité de se déplacer dans une direction
                    forceHorizontal = this.getCollider().verificationX(ent, forceHorizontal);

                    double ancien = forceVertical;
                    forceVertical = this.getCollider().verificationY(ent, forceVertical);
                    if(forceVertical != ancien) {
                        //this.setY(ent.getY() + forceVertica);
                    }



                    //Là c'est surtout pour savoir si un objet est rentré dans l'entité (comme par exemple le joueur)
                    if (ent != this && !ent.getCollider().getIgnoreCollision() && this.getCollider().intersect(ent)) {
                        //System.out.println("x:" + this.getX() + " y : " + this.getY() + " x:" + ent.getX() + " y :" + ent.getY());
                        //this.forceVertical = -1;
                        this.quandCollisionDetectee(ent);
                    }
                }
        }
    }

    public void decrementerPv() {
        pv.setValue(pv.getValue() - 1);
        if (getPv() <= 0)
            detruire();
    }

    public void decrementerPv(int degat) {
        pv.setValue(pv.getValue() - degat);
        if (getPv() <= 0)
            detruire();
    }

    //Fonctions qui ont pour but d'être override
    public void quandCollisionDetectee(Entite ent) {}

    //region Getter & Setter
    public double getX() {
        return xProperty.getValue();
    }

    public boolean getTombe() {
        return this.tombe;
    }

    public double getY() {
        return yProperty.getValue();
    }

    public void setX(double valeur) {
        xProperty.setValue(valeur);
    };

    public void setY(double valeur) {
        yProperty.setValue(valeur);
    };

    public DoubleProperty getYProperty() {
        return yProperty;
    }

    public DoubleProperty getXProperty() {
        return xProperty;
    }

    public Collider getCollider() {
        return this.collider;
    }

    public Environnement getEnv() {
        return env;
    }

    public void setEnv(Environnement nouvEnv) {
        this.env = nouvEnv;
    }

    public int getPv() {
        return this.pv.getValue();
    }

    public IntegerProperty getPVProperty()  {
        return this.pv;
    }

    public void setPv(int value) {
        this.pv.setValue(value);
    }

    public String toString() {
        return "\n[" + this.getClass().getSimpleName() + "]\ny:" + this.getY() + "\nx:" + this.getX() + "\n";
    }
    //endregion
}
