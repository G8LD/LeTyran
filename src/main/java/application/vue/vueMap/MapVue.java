package application.vue.vueMap;

import application.modele.Environnement;
import application.modele.MapJeu;
import application.modele.map.Block;
import application.modele.map.Chunk;
import application.modele.objets.Minerai;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static application.modele.MapJeu.*;

public class MapVue {
    private Environnement env;

    private int[][] tabMap;
    private TilePane tileSol;
    private TilePane tileDecors;
    private TilePane tileFond;

    private HashMap<String, Chunk> chunks;

    public MapVue(Environnement env, TilePane tileSol, TilePane tileDecors, TilePane tileFond) {
        this.env = env;
        this.tabMap = env.getMapJeu().getTabMap();
        this.tileSol = tileSol;
        this.tileDecors = tileDecors;
        this.tileFond = tileFond;

        tileSol.setMaxSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);
        tileDecors.setMaxSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);

        env.getListeMinerais().addListener(new ListChangeListener<Minerai>() {
            @Override
            public void onChanged(Change<? extends Minerai> change) {
                while (change.next()) {
                    if (change.wasRemoved()) {
                        supprimerBloc(change.getRemoved().get(0).getY() * WIDTH + change.getRemoved().get(0).getX());
                    }
                }
            }
        });

        this.chunks = this.env.getMapJeu().getMapChunks();
        construireMap();
        rendreMap();
        construireDecor();
        construireFond();
    }

    /*private void construireMap() {

    }*/

    public void rendreMap() {

        int x = 0;
        for (Chunk chunk : this.chunks.values()) {
            for (Block bloc : chunk.getBlocs().values()) {
                int id = bloc.getX() + bloc.getY() *  HEIGHT;
                //System.out.println("x: " + bloc.getX() + " y: " + bloc.getY() + " id " + id);
                ImageView img = (ImageView) tileSol.getChildren().get(id);
                if(bloc.getY() < tabMap.length && bloc.getX() < tabMap[0].length) {
                    int blocID = tabMap[bloc.getY()][bloc.getX()];
                    img.setImage(ChargeurRessources.tileMapAssets.get(blocID));
                    img.setVisible(true);
                }
                x++;

            }
        }

        System.out.println(x);
        /*
        int positionX = this.env.getPersonnage().getX() / TUILE_TAILLE;
        int positionY = this.env.getPersonnage().getY() / TUILE_TAILLE;

        int blocX = 0;
        int blocY = 0;

        for(int i = 0; i < tileSol.getChildren().size(); i++) {
            tileSol.getChildren().get(i).setVisible(false);
        }

        for(int y = positionY - DISTANCE_RENDU; y < positionY + DISTANCE_RENDU; y+= 5) {
            for(int x = positionX - DISTANCE_RENDU; x < positionX + DISTANCE_RENDU; x+=5) {
                blocX = (x/5) * 5;
                blocY = (y/5) * 5;
                //System.out.println("bloc a rendre : y " + blocY + "_" + blocX);
                Chunk chunk = this.chunks.get(blocY+"_"+blocX);
                for(Block bloc : chunk.getBlocs().values()) {
                    //System.out.println(bloc.getX());
                    //System.out.println(bloc.getX() + bloc.getY() * 5);
                    ImageView img = (ImageView) tileSol.getChildren().get(bloc.getX() + bloc.getY() * 5);
                    int blocID = tabMap[bloc.getY()][bloc.getX()];
                    img.setImage(ChargeurRessources.tileMapAssets.get(blocID));
                    img.setVisible(true);
                    //System.out.println(bloc);
                }
            }
        }*/




    }

    private void construireMap() {
        ChargeurRessources.charger();
        tileSol.setPrefSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);
        tileSol.setBackground(Background.fill(Color.LIGHTBLUE));
        ImageView img;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int indexImg = tabMap[i][j];
                img = new ImageView(ChargeurRessources.tileMapAssets.get(0));
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
                        case 0:
                            img = new ImageView(new Image("file:src/main/resources/application/pack1/tile_transparant.png"));
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
        Image imageTransparent = new Image("file:src/main/resources/application/pack1/tile_transparent.png");
        Image imageTerre = new Image("file:src/main/resources/application/pack1/Terre.png");
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
        Node node = tileSol.getChildren().get(id);
        if(node instanceof ImageView) {
            System.out.println(((ImageView) node).getImage().getUrl());
            ImageView imgView = (ImageView)node;
            imgView.setImage(new Image("file:src/main/resources/application/pack1/tile_transparent.png"));
        }
    }
}
