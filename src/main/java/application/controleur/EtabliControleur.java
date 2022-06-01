package application.controleur;

import application.modele.Etabli;
import application.vue.EtabliVue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static javafx.scene.input.KeyCode.O;

public class EtabliControleur implements EventHandler<KeyEvent> {

    private Pane root;
    private Etabli etabli;
    private EtabliVue etabliVue;
    private VBox vBoxArmes;
    Button boutonFabriquer;

    public EtabliControleur(Pane root, Etabli etabli, EtabliVue etabliVue) {
        this.root = root;
        this.etabliVue = etabliVue;
        this.etabli = etabli;
        vBoxArmes = (VBox) ((ScrollPane) etabliVue.getbPaneEtabli().lookup("#sPArmes")).getContent();
        boutonFabriquer = (Button) etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer");

        //pour afficher les infos d'une arme lorsque cliquée
        for (Node hBoxArme : vBoxArmes.getChildren())
            hBoxArme.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    etabliVue.affichageArmeSelected(Color.BLACK);
                    etabli.setArmeSelected(((HBox)mouseEvent.getSource()).getId());
                    etabliVue.affichageArmeSelected(Color.WHITE);
                    etabliVue.affichageInfosArmeSelected();
                    fabricable();
                }
            });

        //pour lancer la fabrication et la rendre indisponible après
        boutonFabriquer.setOnAction(actionEvent -> {
            etabli.fabriquer();
            fabricable();
        });

        //simule un clique pour l'initialisation
        vBoxArmes.lookup("#" + etabli.getArmeSelectedNom()).fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                true, true, true, true, true, true, null));
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == O) {
            fabricable();
            etabliVue.affichageEtabli();
            root.requestFocus();
        }
    }

    private void fabricable() {
        if (etabli.peutFabriquer()) {
            boutonFabriquer.setDisable(false);
            etabliVue.affichageBouton(1);
        } else {
            boutonFabriquer.setDisable(true);
            etabliVue.affichageBouton(0.5);
        }
    }
}
