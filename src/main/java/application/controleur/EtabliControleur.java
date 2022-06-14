package application.controleur;

import application.modele.Environnement;
import application.vue.EtabliVue;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static application.modele.MapJeu.TUILE_TAILLE;

public class EtabliControleur {

    public EtabliControleur(Pane root, Environnement env, EtabliVue etabliVue) {
        VBox vBoxObjets = (VBox) ((ScrollPane) etabliVue.getbPaneEtabli().lookup("#sPObjets")).getContent();
        Button boutonFabriquer = (Button) etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer");

        env.getEtabli().getOuvertProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1)
                root.requestFocus();
            etabliVue.affichageEtabli();
        });

        vBoxObjets.getChildren().get(0).setOnMouseClicked(mouseEvent -> {
            etabliVue.affichageArmeSelected(Color.BLACK);
            env.getEtabli().setObjetSelected(((HBox)mouseEvent.getSource()).getId());
            etabliVue.affichageArmeSelected(Color.WHITE);
            if (env.getEtabli().getNiveau() < 3)
                etabliVue.affichageInfosArmeSelected();
            env.getEtabli().peutFabriquer();
        });

        //pour afficher les infos d'une arme lorsque cliquée
        for (int i = 1; i < vBoxObjets.getChildren().size(); i++) {
            vBoxObjets.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                etabliVue.affichageArmeSelected(Color.BLACK);
                env.getEtabli().setObjetSelected(((HBox)mouseEvent.getSource()).getId());
                etabliVue.affichageArmeSelected(Color.WHITE);
                etabliVue.affichageInfosArmeSelected();
                env.getEtabli().peutFabriquer();
            });
        }

        //pour lancer la fabrication
        boutonFabriquer.setOnAction(actionEvent -> {
            env.getEtabli().fabriquer();
            if (env.getEtabli().getObjetSelected().equals("Etabli"))
                if (env.getEtabli().getNiveau() < 3)
                    etabliVue.affichageInfosArmeSelected();
                else
                    ((ScrollPane) etabliVue.getbPaneEtabli().lookup("#sPObjets")).getContent().lookup("#Etabli").setOpacity(0.5);
            root.requestFocus();
        });

        ((Button) etabliVue.getbPaneEtabli().lookup("#boutonFermer")).setOnAction(actionEvent -> {
            env.getEtabli().interagir();
        });

        env.getEtabli().getNiveauProperty().addListener(((observableValue, number, t1) -> etabliVue.amelioration()));

        env.getEtabli().getFabricableProperty().addListener((observableValue, aBoolean, t1) -> {
            if (env.getEtabli().getFabricable()) {
                boutonFabriquer.setDisable(false);
                etabliVue.affichageBouton(1);
            } else {
                boutonFabriquer.setDisable(true);
                etabliVue.affichageBouton(0.5);
            }
        });

        //simule un clique pour l'initialisation
        vBoxObjets.lookup("#Etabli").fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                true, true, true, true, true, true, null));
    }
}
