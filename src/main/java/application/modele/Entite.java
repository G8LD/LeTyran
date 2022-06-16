package application.modele;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;
import application.modele.armes.arc.Arc;
import application.modele.armes.arc.Fleche;
import application.modele.collisions.Collider;
import application.modele.objets.Materiau;
import application.modele.personnages.Joueur;
import javafx.beans.property.*;
import javafx.geometry.Point2D;

public class Entite {
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private Environnement env;
    private Collider collider;
    private IntegerProperty pv;


    private boolean tombe = false;

    private double forceVertical = 0;
    private double forceHorizontal = 0;


    private double ancienneValeurVertical;
    private double ancienneValeurHorizontal;

    //Mouvement

    public double valeurVertical = 1;
    public double valeurHorizontal = 1;

    public boolean peutSauter = false;
    public boolean peutTomber = false;


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

    /*public void addTorque(double torqueForce) {
        double newTorque = forceTorqueActuel + torqueForce;
        if (torqueForce > 0) {
            forceTorqueActuel = Math.min(newTorque, TORQUE_MAX);
        } else {
            forceTorqueActuel = Math.max(newTorque, -TORQUE_MAX);
        }
    }


    public void ajouterThrust(double scalaire) {
        Point2D thrustVector = calculateNewThrustVector(scalaire, Math.toRadians(0));
        thrustActuel = thrustActuel.add(thrustVector);
        thrustActuel = clampToMaxSpeed(thrustActuel);
    }

    private Point2D calculateNewThrustVector(double scalar, double angle) {
        return new Point2D(
                (double) (Math.sin(angle) * scalar),
                (double) (Math.cos(angle) * scalar));
    }

    private Point2D clampToMaxSpeed(Point2D thrustVector) {
        if (thrustVector.magnitude() > VITESSE_MAX) {
            return thrustActuel = thrustVector.normalize().multiply(VITESSE_MAX);
        } else {
            return thrustActuel = thrustVector;
        }
    }

    private void applyDrag() {
        double movementDrag = thrustActuel.magnitude() < 0.5 ? 0.01f : 0.07f;
        double rotationDrag = forceTorqueActuel < 0.2f ? 0.05f : 0.1f;
        thrustActuel = new Point2D(
                reduceTowardsZero((double) thrustActuel.getX(), movementDrag),
                reduceTowardsZero((double) thrustActuel.getY(), movementDrag));

        forceTorqueActuel = reduceTowardsZero(forceTorqueActuel, rotationDrag);
    }

    private double reduceTowardsZero(double value, double modifier) {
        double newValue = 0;
        if (value > modifier) {
            newValue = value - modifier;
        } else if (value < -modifier) {
            newValue = value + modifier;
        }
        return newValue;
    }*/

    private void sedeplacer() {
        //this.setX(this.getX() + forceTorqueActuel);
        this.setX(this.getX() + valeurHorizontal);
    }

    private boolean tomber() {
        if(peutTomber) {
            this.setY(this.getY() + valeurVertical);

            return true;
        }

        return false;
    }



    public void update() {
        valeurVertical -= 1;
        collide();
        //double ancienDeplace = peutseDeplace;
        tomber();
            //entiteSauter();

        sedeplacer();

        //valeurVertical;
        valeurVertical *= 0.5f;

    }


    public void entiteSauter() {
        if(valeurVertical < 0 && !peutSauter) {
            this.setY(this.getY() + valeurVertical);
        }
    }

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

                    ancienneValeurVertical = valeurVertical;
                    ancienneValeurHorizontal = valeurHorizontal;
                    valeurHorizontal = this.getCollider().verificationX(ent, valeurHorizontal);
                    valeurVertical = this.getCollider().verificationY(ent, valeurVertical);



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
