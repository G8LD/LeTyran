package application.vue;

import application.modele.Etabli;
import application.modele.MapJeu;
import application.modele.objets.*;
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

public class EtabliVue {

    private Etabli etabli;
    private BorderPane bPaneEtabli;
    private ImageView spriteEtabli;
    private HashMap<String, Image> listeSprites;
    private HashMap<String, Image> listeImagesMateriaux;

    public EtabliVue(Etabli etabli, ImageView spriteEtabli, BorderPane bPaneEtabli, ArmeVue armeVue) {
        this.etabli = etabli;
        this.spriteEtabli = spriteEtabli;
        this.bPaneEtabli = bPaneEtabli;
        listeSprites = new HashMap<>();
        listeSprites.putAll(armeVue.getListeSprites());
        listeSprites.put("Etabli", new Image("file:src/main/resources/application/sprite_etabli.png"));
        initSpriteEtabli();
        initListeImagesMateriaux();
        bPaneEtabli.setVisible(false);

        Iterator iterator = etabli.getListeMateriauxArmesID().iterator();
        while (iterator.hasNext()) {
            ((ScrollPane) bPaneEtabli.lookup("#sPArmes")).getContent().lookup("#" + (String) iterator.next()).setOpacity(0.5);
        }
    }

    private void initSpriteEtabli() {
        spriteEtabli.setX(15 * TUILE_TAILLE);
        spriteEtabli.setY(11 * TUILE_TAILLE);
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
        ((HBox) ((ScrollPane) bPaneEtabli.lookup("#sPArmes")).getContent().lookup("#" + etabli.getArmeSelected()))
                .setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        System.out.println("ok");
    }

    public void affichageInfosArmeSelected() {
        Node vBoxFabriquer = bPaneEtabli.lookup("#VboxFabriquer");
        ((ImageView) vBoxFabriquer.lookup("#imgViewArme")).setImage(listeSprites.get(etabli.getArmeSelected()));

        Node paneMateriaux = vBoxFabriquer.lookup("#PaneMateriaux");
        int i = 0;
        for (String materiau : etabli.getListeMateriauxArmeSelected().keySet()) {
            i++;
            ((ImageView) paneMateriaux.lookup("#imgViewMateriau" + i)).setImage(listeImagesMateriaux.get(materiau));
            ((Label) paneMateriaux.lookup("#labelMateriau" + i)).setText(etabli.getListeMateriauxArmeSelected().get(materiau).toString());
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
    }

    public BorderPane getbPaneEtabli() {
        return bPaneEtabli;
    }

    public void amelioration() {
        Iterator iterator = etabli.getListeMateriauxArmesID().iterator();
        String idArme;
        while (iterator.hasNext()) {
            idArme = (String) iterator.next();
            if ((int) idArme.charAt(idArme.length()-1) == etabli.getNiveau()) {
                ((ScrollPane) bPaneEtabli.lookup("#sPArmes")).getContent().lookup("#" + idArme).setOpacity(1);
            }
            System.out.println((int) idArme.charAt(idArme.length()-1));
        }
    }
}
