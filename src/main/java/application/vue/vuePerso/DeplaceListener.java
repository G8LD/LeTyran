package application.vue.vuePerso;

import application.controleur.Controleur;
import application.modele.Direction;
import application.modele.Jeu;
import application.modele.Personnage;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DeplaceListener implements ChangeListener {

    private Personnage personnage;
    private PersonnageVue personnageVue;

    public DeplaceListener(Personnage personnage, PersonnageVue personnageVue) {
        this.personnage = personnage;
        this.personnageVue = personnageVue;
    }

    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {
        if (personnage.getDirection() == Direction.Gauche || personnage.getDirection() == Direction.Droit)
            personnageVue.animationHorizontale();
        else if (personnage.getDirection() == Direction.Haut)
            personnageVue.animationSaut((Integer) o - (Integer) t1);
    }
}
