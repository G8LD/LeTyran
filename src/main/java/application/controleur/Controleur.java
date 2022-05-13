package application.controleur;

import application.modele.ChargeurRessources;
import application.modele.Jeu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
    private AnimationDeplacementJoueur animationDeplacementJoueur;

    @FXML private StackPane root;
    @FXML private TilePane tileSol;
    @FXML private TilePane tileDecors;
    @FXML private Pane paneJoueur;
    @FXML private StackPane spritesJoueur;
    @FXML private TilePane inventaireJoueur;

    private boolean inventaireAffiche = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChargeurRessources.charger();
        jeu = new Jeu();
        animationDeplacementJoueur = new AnimationDeplacementJoueur(jeu, spritesJoueur);

        paneJoueur.setMaxSize(WIDTH*TUILE_TAILLE,HEIGHT*TUILE_TAILLE);
        tileSol.setMaxSize(WIDTH*TUILE_TAILLE,HEIGHT*TUILE_TAILLE);
        tileDecors.setMaxSize(WIDTH*TUILE_TAILLE,HEIGHT*TUILE_TAILLE);

        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(this, jeu));
        root.addEventHandler(KeyEvent.KEY_RELEASED, new KeyReleased(this, jeu));
        root.addEventHandler(KeyEvent.KEY_RELEASED, new InventaireControleur(root, jeu));


        jeu.getPersonnage().getXProperty().addListener(new DeplaceListener(jeu, animationDeplacementJoueur));

        construireMap(); construireDecor(); construirePerso();
    }

    private void construireMap() {
        tileSol.setBackground(Background.fill(Color.LIGHTBLUE));
        ImageView img;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int index = jeu.getMapJeu().getTabMap()[i][j];
                img = new ImageView(ChargeurRessources.tileMapAssets.get(index));
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


    public void afficherInventaire() {
        System.out.println("Affichage de l'inventaire");

        if(!inventaireAffiche) {
            inventaireJoueur = new TilePane();
            inventaireJoueur.setAlignment(Pos.CENTER);
            inventaireJoueur.setPrefColumns(4);

            for (int i = 1; i < 10; i++) {
                inventaireJoueur.getChildren().add(new ImageView(new Image("file:src/main/resources/application/pack1/inv_slot.png")));
            }
            root.getChildren().add(inventaireJoueur);
        }
        else {
            root.getChildren().remove(inventaireJoueur);
        }

        inventaireAffiche = !inventaireAffiche;
    }
}
