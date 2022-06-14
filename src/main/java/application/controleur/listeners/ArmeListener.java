package application.controleur.listeners;

import application.modele.ObjetInventaire;
import application.vue.ArmeVue;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ArmeListener implements ChangeListener<ObjetInventaire> {

    private ArmeVue armeVue;

    public ArmeListener(ArmeVue armeVue) {
        this.armeVue = armeVue;
    }

    @Override
    public void changed(ObservableValue<? extends ObjetInventaire> observableValue, ObjetInventaire objetInventaire, ObjetInventaire t1) {
        if (t1 != null)
            armeVue.changementArme();
    }
}
