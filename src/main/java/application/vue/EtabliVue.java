package application.vue;

import application.modele.Etabli;
import application.modele.objets.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class EtabliVue {

    private Etabli etabli;
    private BorderPane bPaneEtabli;
    private HashMap<String, Image> listeSprites;
    private HashMap<String, Image> listeImagesMateriaux;

    public EtabliVue(Etabli etabli, BorderPane bdEtabli, ArmeVue armeVue) {
        this.etabli = etabli;
        this.bPaneEtabli = bdEtabli;
        listeSprites = new HashMap<>();
        listeSprites.putAll(armeVue.getListeSprites());
        initListeImagesMateriaux();
        bdEtabli.setVisible(false);
    }

    private void initListeImagesMateriaux() {
        listeImagesMateriaux = new HashMap<>() {{
            put("Bois", new Image("file:src/main/resources/application/pack1/Bois.png"));
            put("Pierre", new Image("file:src/main/resources/application/pack1/Pierre.png"));
            put("Fer", new Image("file:src/main/resources/application/pack1/Fer.png"));
            put("Platine", new Image("file:src/main/resources/application/pack1/Platine.png"));
        }};
    }

    public void affichageArmeSelected(Color color) {
        ((HBox) ((ScrollPane) bPaneEtabli.lookup("#sPArmes")).getContent().lookup("#" + etabli.getArmeSelectedNom()))
                .setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
    }

    public void affichageInfosArmeSelected() {
        Node vBoxFabriquer = bPaneEtabli.lookup("#VboxFabriquer");
        vBoxFabriquer.setOpacity(1);
        ((ImageView) vBoxFabriquer.lookup("#imgViewArme")).setImage(listeSprites.get(etabli.getArmeSelectedNom()));

        Node paneMateriaux = vBoxFabriquer.lookup("#PaneMateriaux");
        int i = 0;
        for (Materiau materiau : etabli.getListeMateriauxArmeSelected().keySet()) {
            i++;
            ((ImageView) paneMateriaux.lookup("#imgViewMateriau" + i)).setImage(listeImagesMateriaux.get(materiau.getClass().getSimpleName()));
            ((Label) paneMateriaux.lookup("#labelMateriau" + i)).setText(etabli.getListeMateriauxArmeSelected().get(materiau).toString());
        }

        if (i == 1) {
            i++;
            ((ImageView) paneMateriaux.lookup("#imgViewMateriau" + i)).setImage(null);
            ((Label) paneMateriaux.lookup("#labelMateriau" + i)).setText("");
        }
    }

    public void affichageArmeNonDispo() {
        ((ScrollPane) bPaneEtabli.lookup("#sPArmes")).getContent().lookup("#" + etabli.getArmeSelectedNom()).setOpacity(0.5);
        bPaneEtabli.lookup("#VboxFabriquer").setOpacity(0.5);
    }

    public void affichageEtabli() {
        bPaneEtabli.setVisible(!bPaneEtabli.isVisible());
    }

    public BorderPane getbPaneEtabli() {
        return bPaneEtabli;
    }
}
