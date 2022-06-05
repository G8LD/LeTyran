package application.controleur.listeners;

import application.modele.personnages.Ennemi;
import application.modele.personnages.Personnage;
import application.vue.PersonnageVue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PersonnageListener {

    public PersonnageListener(Personnage perso, PersonnageVue persoVue) {
        //appel la méthode animationDeplacement à chaque fois que x change et donc que le joueur se déplace
        perso.getXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                persoVue.animerDeplacement();
            }
        });
        //si le joueur n'avance plus pour mettre le sprite du personnage immobile
        perso.getAvanceProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (!t1) persoVue.immobile();
            }
        });
        //retourne le sprite du perso
        perso.getDirectionProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                persoVue.inverserSprite();
            }
        });
        if (perso instanceof Ennemi)
            perso.getPVProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    if ((int) t1 <= 0)
                        perso.getEnv().getListeEnnemis().remove(perso);
                }
            });
    }
}
