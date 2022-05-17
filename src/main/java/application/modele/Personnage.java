package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Direction direction;
    private MapJeu mapJeu;
    private Inventaire inventaire;

    private IntegerProperty pv;
    private int defense;
    private int attaque;

    public Personnage(MapJeu mapjeu, int pv, int defense, int attaque) {
        xProperty = new SimpleIntegerProperty(0);
        yProperty = new SimpleIntegerProperty(11);
        direction = Direction.Immobile;
        this.mapJeu = mapjeu;
        this.pv = new SimpleIntegerProperty(pv);
        this.attaque = attaque;
        this.defense = defense;

        this.inventaire = new Inventaire();
        inventaire.ajouterObjet();
    }

    public void perdrePV(){
            this.pv.setValue(this.pv.getValue()-10);
        System.out.println(this.getPv());
    }
    public int getPv(){
       return this.pv.getValue();
    }

    public Boolean estMort(int pv){
        if(pv<=0){
            return true;
        }else{
            return false;
        }
    }
    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public void seDeplacer() {
        int dX, dY;
        switch (direction) {
            case Haut:
                dX = 0;
                dY = -3;
                break;
            case HautGauche:
                dX = -1;
                dY = -3;
                break;
            case HautDroit:
                dX = 1;
                dY = -3;
                break;
            case Gauche:
                dX = -1;
                dY = 0;
                break;
            case Droit:
                dX = 1;
                dY = 0;
                break;
            default:
                dX = 0;
                dY = 0;
                break;
        }
        if (xProperty.getValue() + dX >= 0 && xProperty.getValue() + dX < MapJeu.WIDTH && yProperty.getValue() + dY >= 0 && yProperty.getValue() + dY < MapJeu.HEIGHT && mapJeu.getTabMap()[yProperty.getValue() + dY][xProperty.getValue() + dX] == 0) {
            xProperty.setValue(xProperty.getValue() + dX);
            yProperty.setValue(yProperty.getValue() + dY);
            System.out.println(xProperty.getValue() + "\t" + yProperty.getValue());
        }
    }

    public void sauter() {
        int hauteurSaut = 0;
        while (hauteurSaut < 3 && yProperty.getValue() - hauteurSaut - 1 > 0 && mapJeu.getTabMap()[yProperty.getValue() - hauteurSaut - 1][xProperty.getValue()] == 0)
            hauteurSaut++;
        yProperty.setValue(yProperty.getValue() - hauteurSaut);
    }

    public void tomber() {
        int hauteurChute = 0;
        while (hauteurChute < 3 && yProperty.getValue() + hauteurChute + 1 < MapJeu.HEIGHT && mapJeu.getTabMap()[yProperty.getValue() + hauteurChute + 1][xProperty.getValue()] == 0)
            hauteurChute++;
        yProperty.setValue(yProperty.getValue() + hauteurChute);
    }

    public IntegerProperty subirDegat(int attaque) {
        this.pv.setValue(this.pv.getValue() - (attaque - this.defense));
        return this.pv ;
    }

    public IntegerProperty getPvProperty() {
        return this.pv;
    }

    public IntegerProperty seSoigner(int nbPvRendu) {
        this.pv.setValue(this.pv.getValue()+nbPvRendu);
        return this.pv;

    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return xProperty.getValue();
    }

    public IntegerProperty getXProperty() {
        return xProperty;
    }

    public void setX(int x) {
        this.xProperty.set(x);
    }

    public int getY() {
        return yProperty.getValue();
    }

    public IntegerProperty getYProperty() {
        return yProperty;
    }

    public void setyPropertyProperty(int yProperty) {
        this.yProperty.set(yProperty);
    }

}
