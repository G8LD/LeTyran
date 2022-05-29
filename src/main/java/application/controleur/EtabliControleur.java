package application.controleur;

import application.modele.Etabli;
import application.modele.ObjetJeu;
import application.modele.armes.Arme;
import application.vue.EtabliVue;
import javafx.collections.MapChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
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
            hBoxArme.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    etabliVue.affichageArmeSelected(Color.BLACK);
                    etabli.setArmeSelected(((HBox)mouseEvent.getSource()).getId());
                    etabliVue.affichageArmeSelected(Color.WHITE);
                    etabliVue.affichageInfosArmeSelected();
                    if (etabli.getFabricable()) {
                        etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer").setDisable(false);
                        etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer").setOpacity(1);
                    } else {
                        etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer").setDisable(true);
                        etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer").setOpacity(0.5);
                    }
                }
            });

        ((Button) etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer")).setOnAction(actionEvent -> {
            if (etabli.getFabricable()) {
                etabli.fabriquer();
                etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer").setDisable(true);
                etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer").setOpacity(0.5);
                etabliVue.getbPaneEtabli().lookup("#VBoxArmes").lookup("#" + etabli.getArmeSelected()).setOpacity(0.5);
            }

        });

        etabli.getListeArmes().addListener(new MapChangeListener<String, Arme>() {
            @Override
            public void onChanged(Change<? extends String, ? extends Arme> change) {
                System.out.println(change.getKey());
                if (change.wasAdded()) {
                    etabliVue.getbPaneEtabli().lookup("#VBoxArmes").lookup("#" + change.getKey()).setMouseTransparent(false);
                    etabliVue.getbPaneEtabli().lookup("#VBoxArmes").lookup("#" + change.getKey()).setOpacity(1);
                }
//                else {
//                    etabliVue.getbPaneEtabli().lookup("#VBoxArmes").lookup("#" + change.getKey()).setMouseTransparent(true);
//                    etabliVue.getbPaneEtabli().lookup("#VBoxArmes").lookup("#" + change.getKey()).setOpacity(0.5);
//                }

//                String s = change.getKey().substring(0, change.getKey().length()-1);
//                char c = change.getKey().charAt(change.getKey().length()-1);
//                System.out.println(change.getKey() + "\t" + s + "\t" + c);
            }
        });

        etabliVue.getbPaneEtabli().lookup("#VBoxArmes").lookup("#" + etabli.getArmeSelected()).fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                true, true, true, true, true, true, null));
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == O)
            etabliVue.affichageEtabli();
    }
}
