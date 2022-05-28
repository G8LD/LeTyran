package application.controleur;

import application.modele.Environnement;
import application.modele.armes.Arme;
import application.modele.armes.Pioche;
import application.modele.objets.Materiau;
import application.modele.objets.Pierre;
import application.vue.EtabliVue;
import application.vue.vueEnv.EnvironnementVue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static javafx.scene.input.KeyCode.O;

public class EtabliControleur implements EventHandler<KeyEvent> {

    private Environnement env;
    private EtabliVue etabliVue;

    public EtabliControleur(Environnement env, EtabliVue etabliVue) {
        this.env = env;
        this.etabliVue = etabliVue;

        for (Node hBoxArme : ((VBox) etabliVue.getbPaneEtabli().lookup("#VBoxArmes")).getChildren())
            ((HBox) hBoxArme).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    etabliVue.affichageArmeSelected(Color.BLACK);
                    System.out.println(((HBox)mouseEvent.getSource()).getId());
                    env.getEtabli().setArmeSelected(((HBox)mouseEvent.getSource()).getId());
                    etabliVue.affichageArmeSelected(Color.WHITE);
                }
            });

    }



    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == O)
            etabliVue.affichageEtabli();
    }
}
