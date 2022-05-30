package application.controleur;

import application.modele.Etabli;
import application.modele.armes.Arme;
import application.modele.objets.Materiau;
import application.vue.EtabliVue;
import javafx.collections.MapChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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

        VBox vBoxArmes = (VBox) ((ScrollPane) etabliVue.getbPaneEtabli().lookup("#sPArmes")).getContent();
        Button boutonFabriquer = (Button) etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer");

        //pour afficher les infos d'une arme lorsque cliquée
        for (Node hBoxArme : vBoxArmes.getChildren())
            hBoxArme.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    etabliVue.affichageArmeSelected(Color.BLACK);
                    etabli.setArmeSelected(((HBox)mouseEvent.getSource()).getId());
                    etabliVue.affichageArmeSelected(Color.WHITE);
                    etabliVue.affichageInfosArmeSelected();
                    if (etabli.getFabricable()) {
                        boutonFabriquer.setDisable(false);
                        boutonFabriquer.setOpacity(1);
                    } else {
                        boutonFabriquer.setDisable(true);
                        boutonFabriquer.setOpacity(0.5);
                    }
                    if (!etabli.armeDispo()) {
                        etabliVue.affichageArmeNonDispo();
                    }
                }
            });

        //pour lancer la fabrication et la rendre indisponible après
        boutonFabriquer.setOnAction(actionEvent -> {
            etabli.fabriquer();
            boutonFabriquer.setDisable(true);
            etabliVue.affichageArmeNonDispo();
        });

        //simule un clique pour l'initialisation
        vBoxArmes.lookup("#" + etabli.getArmeSelectedNom()).fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                true, true, true, true, true, true, null));
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == O)
            etabliVue.affichageEtabli();
    }
}
