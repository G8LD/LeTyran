package application.controleur.listeners;

import application.controleur.ControleurQuete;
import application.controleur.InventaireControleur;
import application.modele.ObjetInventaire;
import javafx.collections.ListChangeListener;

public class ObjetInventaireChanger implements ListChangeListener {

    private InventaireControleur controleur;
    private ControleurQuete controleurQuete;

    public ObjetInventaireChanger(InventaireControleur controleur, ControleurQuete controleurQuete) {
        this.controleur = controleur;
        this.controleurQuete = controleurQuete;
    }
    @Override
    public void onChanged(Change change) {
        change.next();

        for (int i = 0; i < change.getRemoved().size(); i++) {
            ObjetInventaire obj = (ObjetInventaire)change.getRemoved().get(i);
            //this.controleurQuete
            this.controleurQuete.objetAEteRetireeInventaire(obj);
            this.controleur.enleverObjetAffichage(obj.getPlaceInventaire());
        }

        for(int i = 0; i < change.getAddedSize(); i++) {

            ObjetInventaire obj = (ObjetInventaire) change.getAddedSubList().get(i);
            this.controleurQuete.objetAEteAjouteeInventaire(obj);
        }
    }
}
