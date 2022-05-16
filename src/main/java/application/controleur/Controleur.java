package application.controleur;

import application.modele.Jeu;
import application.vue.MapVue;
import application.vue.vuePerso.AnimationDeplacementJoueur;
import application.vue.vuePerso.ChargeurRessources;
import application.vue.vuePerso.DeplaceListener;
import application.vue.vuePerso.PersonnageVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import static application.modele.MapJeu.HEIGHT;
import static application.modele.MapJeu.WIDTH;

public class Controleur implements Initializable {
    public final static int TUILE_TAILLE = 32;

    private Jeu jeu;
    private KeyReleased keyReleased;
    private PersonnageVue personnageVue;
    private MapVue mapVue;

    @FXML
    private StackPane root;
    @FXML
    private TilePane tileSol;
    @FXML
    private TilePane tileDecors;
    @FXML
    private Pane paneJoueur;
    @FXML
    private StackPane spritesJoueur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jeu = new Jeu();
        keyReleased = new KeyReleased(this, jeu);
        personnageVue = new PersonnageVue(jeu.getPersonnage(), spritesJoueur, paneJoueur, new AnimationDeplacementJoueur(this, jeu, spritesJoueur));
        mapVue = new MapVue(jeu.getMapJeu().getTabMap(), tileSol, tileDecors);

        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(this, jeu));
        root.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        root.addEventHandler(KeyEvent.KEY_PRESSED, new InventaireControleur(root, jeu));
    }

    public PersonnageVue getPersonnageVue() {
        return personnageVue;
    }

    public KeyReleased getKeyReleased() {
        return keyReleased;
    }
}
