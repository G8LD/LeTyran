package application.modele.armes;

public class Arc extends Arme{

    private final static int DISTANCE = 6;

    public Arc(int qualite) {
        super(qualite);
    }

    @Override
    public void frapper() {
    }

    public int nbDegat() {
        if (getQualite() == 1) {
            return 3;
        } else if (getQualite() == 2) {
            return 5;
        } else {
            return 8;
        }
    }


    public int distance() {
        if (getQualite() == 1) {
            return 3;
        } else if (getQualite() == 2) {
            return 6;
        } else {
            return 9;
        }
    }
    //public int nombreDeFleche(int nbFleche){

   // }
}
