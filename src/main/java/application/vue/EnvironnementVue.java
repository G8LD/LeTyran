package application.vue;

import application.controleur.listeners.EnvironnementListeners;
import application.controleur.listeners.PersonnageListeners;
import application.modele.Environnement;
import application.modele.personnages.Ennemi;
import application.modele.personnages.Lapin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static application.modele.MapJeu.*;

public class EnvironnementVue {

    private Environnement env;
    private Pane root;
    private TilePane tileSol;
    private TilePane tileDecors;
    private TilePane tileFond;

    public EnvironnementVue(Environnement env, Pane root, TilePane tileSol, TilePane tileDecors, TilePane tileFond) {
        this.env = env;
        this.root = root;
        this.tileSol = tileSol;
        this.tileDecors = tileDecors;
        this.tileFond = tileFond;

        new EnvironnementListeners(this, env);

        construireMap();
        construireDecor();
        construireFond();

        for (Ennemi ennemi : env.getListeEnnemis()) {
            if (ennemi instanceof Lapin)
                new PersonnageListeners(ennemi, new PersonnageVue(root, ennemi));
            else
                new PersonnageListeners(ennemi, new PersonnageVue(root, ennemi), new ArmeVue(root, ennemi));
        }
    }

    private void construireMap() {
        this.tileSol.setPrefSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);
        ChargeurRessources.charger();
        ImageView img;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int indexImg = env.getMapJeu().getTabMap()[i][j];
                img = new ImageView(ChargeurRessources.tileMapAssets.get(indexImg));
                tileSol.getChildren().add(img);
            }
        }
    }

    private void construireDecor() {
        this.tileDecors.setPrefSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);
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
                        case 0:
                            img = new ImageView(new Image("file:src/main/resources/application/pack1/tile_transparent.png"));
                            break;
                        case 1:
                            img = new ImageView(new Image("file:src/main/resources/application/pack1/tile000.png"));
                            break;
                        case 3:
                            img = new ImageView(new Image("file:src/main/resources/application/pack1/tile002.png"));
                            break;
                        case 5:
                            img = new ImageView(new Image("file:src/main/resources/application/pack1/tile004.png"));
                            break;
                        case 8:
                            img = new ImageView(new Image("file:src/main/resources/application/pack1/tile007.png"));
                            break;
                        default:
                            img = null;
                            break;
                    }
                    tileDecors.getChildren().add(img);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void construireFond() {
        this.tileFond.setPrefSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);
        Image imageTransparent = new Image("file:src/main/resources/application/pack1/tile_transparent.png");
        Image imageTerre = new Image("file:src/main/resources/application/pack1/Sol.png");
        tileFond.setBackground(Background.fill(Color.LIGHTBLUE));
        InputStream is = getClass().getResourceAsStream("/application/tiles/TileFond.txt");
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
                        case 0: img = new ImageView(imageTransparent); break;
                        case 1: img = new ImageView(imageTerre); break;
                        default: img = null; break;
                    }
                    tileFond.getChildren().add(img);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void supprimerBloc(int id) {
        ImageView img = (ImageView) tileSol.getChildren().get(id);
        img.setImage(new Image("file:src/main/resources/application/pack1/tile_transparent.png"));
    }

    public void supprimerArbre(int id) {
        ImageView img;
        for (int i = 0; i < 3; i++) {
            img = (ImageView) tileSol.getChildren().get(id - i * WIDTH);
            img.setImage(new Image("file:src/main/resources/application/pack1/tile_transparent.png"));
        }
    }

    public void supprimerEnnemi(String id) {
        root.getChildren().remove(root.lookup("#" + id));
        root.getChildren().remove(root.lookup("#" + id + "Arme"));
    }

    public void changerImgCoffre(int id) {
        ImageView img = (ImageView) tileSol.getChildren().get(id);
        img.setImage(new Image("file:src/main/resources/application/Coffre/CoffreOrOuv.png"));
    }

}
