package application.controleur;

import application.modele.Jeu;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

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
    private AnimationDeplacementJoueur animationDeplacementJoueur;
    private TranslateTransition tt;
    private KeyReleased keyReleased;

    @FXML private StackPane root;
    @FXML private TilePane tileSol;
    @FXML private TilePane tileDecors;
    @FXML private Pane paneJoueur;
    @FXML private StackPane spritesJoueur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jeu = new Jeu();
        animationDeplacementJoueur = new AnimationDeplacementJoueur(this, jeu, spritesJoueur);
        tt = new TranslateTransition();
        tt.setNode(spritesJoueur);

        paneJoueur.setMaxSize(WIDTH*TUILE_TAILLE,HEIGHT*TUILE_TAILLE);
        tileSol.setMaxSize(WIDTH*TUILE_TAILLE,HEIGHT*TUILE_TAILLE);
        tileDecors.setMaxSize(WIDTH*TUILE_TAILLE,HEIGHT*TUILE_TAILLE);

        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(this, jeu));
        keyReleased = new KeyReleased(this, jeu);
        root.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        jeu.getPersonnage().getXProperty().addListener(new DeplaceListener(this, jeu));
        jeu.getPersonnage().getYProperty().addListener(new DeplaceListener(this, jeu));
        construireMap(); construireDecor(); construirePerso();
    }

    private void construireMap() {
        tileSol.setBackground(Background.fill(Color.LIGHTBLUE));
        ImageView img;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                switch (jeu.getMapJeu().getTabMap()[i][j]) {
                    case 0: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile_transparant.png")); break;
                    case 2: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile001.png")); break;
                    case 17: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile016.png")); break;
                    case 18: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile017.png")); break;
                    case 19: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile018.png")); break;
                    case 21: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile020.png")); break;
                    case 22: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile021.png")); break;
                    case 23: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile022.png")); break;
                    case 33: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile032.png")); break;
                    case 34: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile033.png")); break;
                    case 35: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile034.png")); break;
                    case 49: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile048.png")); break;
                    case 50: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile049.png")); break;
                    case 51: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile050.png")); break;
                    default: img = null; break;
                }
                tileSol.getChildren().add(img);
            }
        }
    }

    private void construireDecor() {
        InputStream is = getClass().getResourceAsStream("/application/tiles/TileDecors.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        String[] tabLine;
        ImageView img;
        for (int i = 0; i < HEIGHT; i++) {
            try {
                line = br.readLine();
                tabLine = line.split(" ");
                for (int j = 0; j < WIDTH; j++) {
                    switch (Integer.parseInt(tabLine[j])) {
                        case 0: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile_transparant.png")); break;
                        case 1: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile000.png")); break;
                        case 3: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile002.png")); break;
                        case 5: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile004.png")); break;
                        case 8: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile007.png")); break;
                        default: img = null; break;
                    }
                    tileDecors.getChildren().add(img);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void construirePerso() {
        for (int i = 0; i < spritesJoueur.getChildren().size(); i++)
            spritesJoueur.getChildren().get(i).setVisible(false);
        spritesJoueur.getChildren().get(3).setVisible(true);
        spritesJoueur.setTranslateX(jeu.getPersonnage().getX() * TUILE_TAILLE);
        spritesJoueur.setTranslateY(jeu.getPersonnage().getY() * TUILE_TAILLE);
    }

    public AnimationDeplacementJoueur getAnimationDeplacementJoueur() {
        return animationDeplacementJoueur;
    }

    public KeyReleased getKeyReleased() {
        return keyReleased;
    }

    public boolean pasAnimations() {
        return !animationDeplacementJoueur.isRunning() && tt.getCurrentRate() == 0;
    }

    public void animationSaut() {
        if (pasAnimations()) {
            tt.setByY(-TUILE_TAILLE);
            tt.setByX(0);
            tt.play();
        }
    }
}
