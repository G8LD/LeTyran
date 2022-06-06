package application.controleur.listeners;

import application.modele.Environnement;
import application.modele.objets.Arbre;
import application.modele.objets.Materiau;
import application.modele.personnages.Ennemi;
import application.vue.EnvironnementVue;
import javafx.collections.ListChangeListener;

import static application.modele.MapJeu.WIDTH;

public class EnvironnementListeners {

    public EnvironnementListeners(EnvironnementVue envVue, Environnement env) {
        env.getListeMateriaux().addListener(new ListChangeListener<Materiau>() {
            @Override
            public void onChanged(Change<? extends Materiau> change) {
                change.next();
                for (int i = 0; i < change.getRemoved().size(); i++)
                    envVue.supprimerBloc((int)change.getRemoved().get(0).getY() * WIDTH + (int)change.getRemoved().get(0).getX());
            }
        });

        env.getListeArbres().addListener(new ListChangeListener<Arbre>() {
            @Override
            public void onChanged(Change<? extends Arbre> change) {
                change.next();
                for (int i = 0; i < change.getRemoved().size(); i++)
                    envVue.supprimerArbre((int)change.getRemoved().get(0).getY() * WIDTH + (int)change.getRemoved().get(0).getX());
            }
        });

        env.getListeEnnemis().addListener(new ListChangeListener<Ennemi>() {
            @Override
            public void onChanged(Change<? extends Ennemi> change) {
               change.next();
               for (int i = 0; i < change.getRemoved().size(); i++)
                    envVue.supprimerEnnemi(change.getRemoved().get(i).getId());
            }
        });
    }
}
