package application.vue.vuePerso;

import application.modele.Direction;
import application.modele.Personnage;
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
        if (personnage.getSaute()) {
            System.out.println((Integer) o - (Integer) t1);
            personnageVue.animationSaut((Integer) o - (Integer) t1);
            personnage.setSaute(false);
        }
        else if (personnage.getAvance())
            personnageVue.animationHorizontale();
    }
}
