package application.controleur.listeners;

import application.modele.armes.Arme;
import application.vue.ArmeVue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ArmeListener implements ChangeListener<Arme> {

    private ArmeVue armeVue;

    public ArmeListener(ArmeVue armeVue) {
        this.armeVue = armeVue;
    }

    @Override
    public void changed(ObservableValue<? extends Arme> observableValue, Arme arme, Arme t1) {
        armeVue.getSpriteArme().setImage(armeVue.getListeSprites().get(arme.getClass().getSimpleName() + arme.getQualite()));
    }
}
