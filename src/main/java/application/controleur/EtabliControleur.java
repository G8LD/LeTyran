package application.controleur;

import application.modele.Etabli;
import application.vue.EtabliVue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static javafx.scene.input.KeyCode.O;

public class EtabliControleur implements EventHandler<KeyEvent> {

    private EtabliVue etabliVue;

    public EtabliControleur(Etabli etabli, EtabliVue etabliVue) {
        this.etabliVue = etabliVue;

        for (Node hBoxArme : ((VBox) etabliVue.getbPaneEtabli().lookup("#VBoxArmes")).getChildren())
            ((HBox) hBoxArme).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    etabliVue.affichageArmeSelected(Color.BLACK);
                    etabli.setArmeSelected(((HBox)mouseEvent.getSource()).getId());
                    etabliVue.affichageArmeSelected(Color.WHITE);
                    etabliVue.affichageInfosArmeSelected();
                }
            });

        ((Button) etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer")).setOnAction(actionEvent -> {
            etabli.fabriquer();});
    }



    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == O)
            etabliVue.affichageEtabli();
    }
}
