package application.vue;

import application.modele.Environnement;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class EtabliVue {

    private Environnement env;
    private BorderPane bPaneEtabli;

    public EtabliVue(Environnement env, BorderPane bdEtabli) {
        this.env = env;
        this.bPaneEtabli = bdEtabli;
        //bdEtabli.setVisible(false);
        affichageArmeSelected(Color.WHITE);
    }

    public void affichageArmeSelected(Color color) {
        ((HBox) bPaneEtabli.lookup("#VBoxArmes").lookup("#" + env.getEtabli().getArmeSelected().getClass().getSimpleName() + env.getEtabli().getArmeSelected().getQualite()))
                .setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
    }

    public void affichageInfosArmeSelected() {

    }

    public void affichageEtabli() {
        bPaneEtabli.setVisible(!bPaneEtabli.isVisible());
    }

    public BorderPane getbPaneEtabli() {
        return bPaneEtabli;
    }
}
