package application.modele;

public class ObjetJeu {
    private String nom;
    private int place;
    private int stack;
    private static int slotInventaire = 0;

    public ObjetJeu(int place, String nom, int stack) {
        this.nom = nom;
        this.place = slotInventaire++;
        this.stack = stack;
    }

    public void setPlaceInventaire(int nouvellePlace) {
        this.place = nouvellePlace;
    }

    public int getPlaceInventaire() {
        return this.place;
    }

    public String toString() {
        return this.nom;
    }
}
