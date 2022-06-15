package application.modele;

public class Mathematiques {

    public static double clamp(double valeur, double min, double max) {
        double valeurClampe = valeur;

        if(valeur > max) {
            valeurClampe = max;
        }
        if(valeur < min) {
            valeurClampe = min;
        }

        return valeurClampe;
    }
}
