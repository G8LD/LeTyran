package application.controleur;

import application.controleur.listeners.PersonnageListeners;
import application.modele.Environnement;
import application.modele.armes.arc.Fleche;
import application.modele.objets.Arbre;
import application.modele.objets.Coffre;
import application.modele.objets.Materiau;
import application.modele.personnages.ennemi.Ennemi;
import application.vue.ArmeVue;
import application.vue.EnvironnementVue;
import application.vue.FlecheVue;
import application.vue.PersonnageVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import static application.modele.MapJeu.*;

public class EnvironnementControleur {

    public EnvironnementControleur(Pane root, EnvironnementVue envVue, Environnement env) {

        ((Pane) root.lookup("#paneEnnemis")).setPrefSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);

        root.lookup("#tileSol").translateXProperty().bind(env.getJoueur().getXProperty().multiply(-1).add(((TUILE_TAILLE * WIDTH)) / 2));
        root.lookup("#tileSol").translateYProperty().bind(env.getJoueur().getYProperty().multiply(-1).add(((TUILE_TAILLE * HEIGHT)) / 2));
        root.lookup("#paneDecors").translateXProperty().bind(env.getJoueur().getXProperty().multiply(-1).add(((TUILE_TAILLE * WIDTH)) /2));
        root.lookup("#paneDecors").translateYProperty().bind(env.getJoueur().getYProperty().multiply(-1).add(((TUILE_TAILLE * HEIGHT)) / 2));
        root.lookup("#paneEnnemis").translateXProperty().bind(env.getJoueur().getXProperty().multiply(-1).add(((TUILE_TAILLE * WIDTH)) / 2));
        root.lookup("#paneEnnemis").translateYProperty().bind(env.getJoueur().getYProperty().multiply(-1).add(((TUILE_TAILLE * HEIGHT)) / 2));

        env.getListeMateriaux().addListener(new ListChangeListener<Materiau>() {
            @Override
            public void onChanged(Change<? extends Materiau> change) {
                change.next();
                for (int i = 0; i < change.getRemovedSize(); i++)
                    envVue.supprimerBloc((int)change.getRemoved().get(i).getY() * WIDTH + (int)change.getRemoved().get(0).getX());
            }
        });

        env.getListeArbres().addListener(new ListChangeListener<Arbre>() {
            @Override
            public void onChanged(Change<? extends Arbre> change) {
                change.next();
                for (int i = 0; i < change.getRemovedSize(); i++)
                    envVue.supprimerArbre((int)change.getRemoved().get(i).getY() * WIDTH + (int)change.getRemoved().get(0).getX());
            }
        });

        env.getListeEnnemis().addListener(new ListChangeListener<Ennemi>() {
            @Override
            public void onChanged(Change<? extends Ennemi> change) {
               change.next();
               for (int i = 0; i < change.getRemovedSize(); i++)
                    envVue.supprimerEnnemi(change.getRemoved().get(i).getId());

                for (int i = 0; i < change.getAddedSize(); i++)
                    new PersonnageListeners(change.getAddedSubList().get(i), new PersonnageVue((Pane) root.lookup("#paneEnnemis"), change.getAddedSubList().get(i)), new ArmeVue((Pane) root.lookup("#paneEnnemis"),  change.getAddedSubList().get(i)));
            }
        });

        env.getListeCoffres().addListener(new ListChangeListener<Coffre>() {
            @Override
            public void onChanged(Change<? extends Coffre> change) {
                change.next();
                for (int i = 0; i < change.getRemovedSize(); i++)
                    envVue.changerImgCoffre((int)change.getRemoved().get(0).getY() * WIDTH + (int)change.getRemoved().get(0).getX());
            }
        });

        env.getListeFleches().addListener(new ListChangeListener<Fleche>() {
            @Override
            public void onChanged(Change<? extends Fleche> change) {
                change.next();
                for (int i = 0; i < change.getAddedSize(); i++)
                    new FlecheVue(((Pane) root.lookup("#paneEnnemis")), change.getAddedSubList().get(i));
                for (int i = 0; i < change.getRemovedSize(); i++)
                    envVue.supprimerFleche(change.getRemoved().get(i).getId());
            }
        });
    }
}
