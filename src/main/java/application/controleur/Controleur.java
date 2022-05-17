package application.controleur;

import application.modele.Environnement;
import javafx.animation.TranslateTransition;
import application.vue.vueMap.MapVue;
import application.vue.vuePerso.PersonnageVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    public final static int TUILE_TAILLE = 32;

    private Environnement jeu;
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
        jeu = new Environnement();
        keyReleased = new KeyReleased(this, jeu);
        personnageVue = new PersonnageVue(jeu.getPersonnage(), spritesJoueur, paneJoueur);
        mapVue = new MapVue(jeu.getMapJeu().getTabMap(), tileSol, tileDecors);

        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(this, jeu));
        root.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        root.addEventHandler(KeyEvent.KEY_PRESSED, new InventaireControleur(root, jeu));

        root.addEventHandler(MouseEvent.MOUSE_PRESSED, new MousePressed(this, jeu));
    }

    public MapVue getMapVue() {
        return this.mapVue;
    }

    public PersonnageVue getPersonnageVue() {
        return personnageVue;
    }

    public KeyReleased getKeyReleased() {
        return keyReleased;
    }
}
