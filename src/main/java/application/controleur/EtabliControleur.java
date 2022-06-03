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

import java.util.Iterator;

import static application.modele.MapJeu.TUILE_TAILLE;

public class EtabliControleur {

    private Pane root;
    private Environnement env;
    private EtabliVue etabliVue;
    private VBox vBoxArmes;
    Button boutonFabriquer;

    public EtabliControleur(Pane root, Environnement env, EtabliVue etabliVue) {
        this.root = root;
        this.env = env;
        this.etabliVue = etabliVue;
        vBoxArmes = (VBox) ((ScrollPane) etabliVue.getbPaneEtabli().lookup("#sPArmes")).getContent();
        boutonFabriquer = (Button) etabliVue.getbPaneEtabli().lookup("#VboxFabriquer").lookup("#boutonFabriquer");

        root.lookup("#spriteEtabli").setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getX() <= env.getPersonnage().getX()+2*TUILE_TAILLE && mouseEvent.getX() >= env.getPersonnage().getX()-TUILE_TAILLE
                    && mouseEvent.getY() <= env.getPersonnage().getY()+2*TUILE_TAILLE && mouseEvent.getY() >= env.getPersonnage().getY()-TUILE_TAILLE) {
                fabricable();
                etabliVue.affichageEtabli();
                env.getPersonnage().freezer();
            }
        });

        vBoxArmes.getChildren().get(0).setOnMouseClicked(mouseEvent -> {
            etabliVue.affichageArmeSelected(Color.BLACK);
            env.getEtabli().setArmeSelected(((HBox)mouseEvent.getSource()).getId());
            etabliVue.affichageArmeSelected(Color.WHITE);
            if (env.getEtabli().getNiveau() < 3)
                etabliVue.affichageInfosArmeSelected();
            fabricable();
        });
        //pour afficher les infos d'une arme lorsque cliquée
        for (int i = 1; i < vBoxArmes.getChildren().size(); i++) {
            vBoxArmes.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                etabliVue.affichageArmeSelected(Color.BLACK);
                env.getEtabli().setArmeSelected(((HBox)mouseEvent.getSource()).getId());
                etabliVue.affichageArmeSelected(Color.WHITE);
                etabliVue.affichageInfosArmeSelected();
                fabricable();
            });
        }

        //pour lancer la fabrication
        boutonFabriquer.setOnAction(actionEvent -> {
            env.getEtabli().fabriquer();
            if (env.getEtabli().getArmeSelected().equals("Etabli"))
                if (env.getEtabli().getNiveau() < 3)
                    etabliVue.affichageInfosArmeSelected();
                else
                    ((ScrollPane) etabliVue.getbPaneEtabli().lookup("#sPArmes")).getContent().lookup("#Etabli").setOpacity(0.5);
            fabricable();
            root.requestFocus();
        });

        ((Button) etabliVue.getbPaneEtabli().lookup("#boutonFermer")).setOnAction(actionEvent -> {
            etabliVue.affichageEtabli();
            env.getPersonnage().freezer();
            root.requestFocus();
        });

        env.getEtabli().getNiveauProperty().addListener(((observableValue, number, t1) -> etabliVue.amelioration()));

        //simule un clique pour l'initialisation
        vBoxArmes.lookup("#" + env.getEtabli().getArmeSelected()).fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                true, true, true, true, true, true, null));
    }

    private void fabricable() {
        System.out.println(env.getEtabli().peutFabriquer());
        if (env.getEtabli().peutFabriquer()) {
            boutonFabriquer.setDisable(false);
            etabliVue.affichageBouton(1);
        } else {
            boutonFabriquer.setDisable(true);
            etabliVue.affichageBouton(0.5);
        }
    }
}
