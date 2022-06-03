package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ObjetInventaire {
    private Entite entite;
    private int placeInventaire;
    private IntegerProperty stackActuel;

    public ObjetInventaire(Entite ent) {
        this.entite = ent;
        this.stackActuel = new SimpleIntegerProperty(1);

    }

    public int getPlaceInventaire() {
        return this.placeInventaire;
    }

    public void setPlaceInventaire(int nouvellePlace) {

        this.placeInventaire = nouvellePlace;
    }

    public IntegerProperty getStackActuelProperty() {
        return this.stackActuel;
    }

    public int getNombre() {
        return this.stackActuel.getValue();
    }

    public void ajouterDansStack() {
        this.stackActuel.setValue(this.stackActuel.getValue() + 1);
    }

    public void ajouterDansStack(int valeur) {
        this.stackActuel.setValue(valeur);
    }

    public Entite getEntite() {
        return this.entite;
    }
}
