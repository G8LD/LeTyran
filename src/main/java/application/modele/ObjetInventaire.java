package application.modele;

public class ObjetInventaire {
    private Entite entite;
    private int placeInventaire;

    public ObjetInventaire(Entite ent) {
        this.entite = ent;
    }

    public int getPlaceInventaire() {
        return this.placeInventaire;
    }

    public void setPlaceInventaire(int nouvellePlace) {
        this.placeInventaire = nouvellePlace;
    }

    public Entite getEntite() {
        return this.entite;
    }
}
