package application.controleur.listeners;

import application.modele.ObjetInventaire;
import application.vue.inventaire.InventaireVue;
import javafx.collections.ListChangeListener;

public class InventaireListener implements ListChangeListener {

    private InventaireVue invVue;

    public InventaireListener(InventaireVue invVue) {
        this.invVue = invVue;
    }
    @Override
    public void onChanged(Change change) {
        change.next();
//        for (int i = 0; i < change.getRemoved().size(); i++)
//            invVue.enleverObjetAffichage(((ObjetInventaire) change.getRemoved().get(i)).getPlaceInventaire());
        for (int i = 0; i < change.getRemovedSize(); i++)
            invVue.enleverObjetAffichage(((ObjetInventaire) change.getRemoved().get(i)).getPlaceInventaire());
        for (int i = 0; i < change.getAddedSize(); i++)
            invVue.ajouterUnObjet((ObjetInventaire) change.getAddedSubList().get(i));
    }
}
