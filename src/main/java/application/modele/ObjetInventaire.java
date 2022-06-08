package application.modele;

public class ObjetInventaire {
    private Entite entite;
    private int placeInventaire;
    private int nombre;

    public ObjetInventaire(Entite ent) {
        this.entite = ent;
    }

    public int getPlaceInventaire() {
        return this.placeInventaire;
    }

    public void setPlaceInventaire(int nouvellePlace) {
        this.placeInventaire = nouvellePlace;
    }

    public int getNombre() {
        return this.nombre;
    }

    public void setNombre(int val) {
        this.nombre = val;
    }

    public Entite getEntite() {
        return this.entite;
    }
}
