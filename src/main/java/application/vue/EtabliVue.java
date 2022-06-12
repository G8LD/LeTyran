package application.vue;

import application.modele.Etabli;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Iterator;

import static application.modele.MapJeu.TUILE_TAILLE;
import static application.vue.ChargeurRessources.iconObjets;

public class EtabliVue {

    private Etabli etabli;
    private BorderPane bPaneEtabli;
    private ImageView spriteEtabli;

    public EtabliVue(Etabli etabli, ImageView spriteEtabli, BorderPane bPaneEtabli) {
        this.etabli = etabli;
        this.spriteEtabli = spriteEtabli;
        this.bPaneEtabli = bPaneEtabli;
        initSpriteEtabli();
        bPaneEtabli.setVisible(false);

        Iterator iterator = etabli.getObjetsID().iterator();
        while (iterator.hasNext()) {
            ((ScrollPane) bPaneEtabli.lookup("#sPObjets")).getContent().lookup("#" + iterator.next()).setOpacity(0.5);
        }
    }

    private void initSpriteEtabli() {
        spriteEtabli.setX(15 * TUILE_TAILLE);
        spriteEtabli.setY(11 * TUILE_TAILLE);
    }

    public void affichageArmeSelected(Color color) {
        ((HBox) ((ScrollPane) bPaneEtabli.lookup("#sPObjets")).getContent().lookup("#" + etabli.getObjetSelected()))
                .setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
    }

    public void affichageInfosArmeSelected() {
        Node vBoxFabriquer = bPaneEtabli.lookup("#VboxFabriquer");
        ((ImageView) vBoxFabriquer.lookup("#imgViewObjet")).setImage(iconObjets.get(etabli.getObjetSelected()));

        Node paneMateriaux = vBoxFabriquer.lookup("#PaneMateriaux");
        int i = 0;
        for (String materiau : etabli.getListeMateriauxObjetSelected().keySet()) {
            i++;
            ((ImageView) paneMateriaux.lookup("#imgViewMateriau" + i)).setImage(iconObjets.get(materiau));
            ((Label) paneMateriaux.lookup("#labelMateriau" + i)).setText(etabli.getListeMateriauxObjetSelected().get(materiau).toString());
        }

        if (i == 1) {
            i++;
            ((ImageView) paneMateriaux.lookup("#imgViewMateriau" + i)).setImage(null);
            ((Label) paneMateriaux.lookup("#labelMateriau" + i)).setText("");
        }
    }

    public void affichageBouton(double opacity) {
        bPaneEtabli.lookup("#VboxFabriquer").lookup("#boutonFabriquer").setOpacity(opacity);
    }

    public void affichageEtabli() {
        bPaneEtabli.setVisible(!bPaneEtabli.isVisible());
        if (bPaneEtabli.isVisible())
            affichageArmeSelected(Color.WHITE);
    }

    public BorderPane getbPaneEtabli() {
        return bPaneEtabli;
    }

    public void amelioration() {
        ((Label) bPaneEtabli.lookup("#labelEtabli")).setText("Etabli niveau " + etabli.getNiveau());
        Iterator iterator = etabli.getObjetsID().iterator();
        String idObjet;
        while (iterator.hasNext()) {
            idObjet = (String) iterator.next();
            if (Character.getNumericValue(idObjet.charAt(idObjet.length()-1)) == etabli.getNiveau()) {
                ((ScrollPane) bPaneEtabli.lookup("#sPObjets")).getContent().lookup("#" + idObjet).setOpacity(1);
            }
        }
    }
}
