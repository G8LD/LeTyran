package application.controleur.listeners;

import application.modele.Environnement;
import application.modele.objets.Arbre;
import application.modele.objets.Coffre;
import application.modele.objets.Materiau;
import application.vue.vueEnv.EnvironnementVue;
import javafx.collections.ListChangeListener;

import static application.modele.MapJeu.WIDTH;

public class EnvironnementListener {

    public EnvironnementListener(EnvironnementVue envVue, Environnement env) {
        env.getListeMateriaux().addListener(new ListChangeListener<Materiau>() {
            @Override
            public void onChanged(Change<? extends Materiau> change) {
                while (change.next()) {
                    if (change.wasRemoved()) {
                        envVue.supprimerBloc((int)change.getRemoved().get(0).getY() * WIDTH + (int)change.getRemoved().get(0).getX());
                    }
                }
            }
        });
        env.getListeArbres().addListener(new ListChangeListener<Arbre>() {
            @Override
            public void onChanged(Change<? extends Arbre> change) {
                while (change.next()) {
                    if (change.wasRemoved()) {
                        envVue.supprimerArbre((int)change.getRemoved().get(0).getY() * WIDTH + (int)change.getRemoved().get(0).getX());
                    }
                }
            }
        });
        env.getListeCoffres().addListener(new ListChangeListener<Coffre>() {
            @Override
            public void onChanged(Change<? extends Coffre> change) {
                while (change.next()) {
                    if (change.wasRemoved()) {
                        envVue.changerImgCoffre((int)change.getRemoved().get(0).getY() * WIDTH + (int)change.getRemoved().get(0).getX());
                    }
                }
            }
        });
    }
}
