package application.controleur.listeners;

import application.controleur.InventaireControleur;
import application.modele.ObjetInventaire;
import application.modele.ObjetJeu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;

public class ObjetSupprimeListener implements ListChangeListener {

    private InventaireControleur controleur;

    public ObjetSupprimeListener(InventaireControleur controleur) {
        this.controleur = controleur;
    }
    @Override
    public void onChanged(Change change) {
        change.next();

        for (int i = 0; i < change.getRemoved().size(); i++) {
            ObjetInventaire obj = (ObjetInventaire)change.getRemoved().get(i);
            this.controleur.enleverObjetAffichage(obj.getPlaceInventaire());
        }



    }
}
