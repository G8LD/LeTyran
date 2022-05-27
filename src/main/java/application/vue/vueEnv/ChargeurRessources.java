package application.vue.vueEnv;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class ChargeurRessources {

    public static ArrayList<Image> tileMapAssets = new ArrayList<Image>();

    public static void charger() {
        for (int i = 1; i < 57; i++) {
            tileMapAssets.add(null);
        }

        tileMapAssets.add(0, new Image("file:src/main/resources/application/pack1/tile_transparent.png"));
        //tileMapAssets.add(2, new Image("file:src/main/resources/application/pack1/tile001.png"));
        for (int i = 2; i <= 51; i++) {
            String fileName = "0" + (i-1);
            if(i < 10) {
                fileName = "00" + (i-1);
            }
            String path = "file:src/main/resources/application/pack1/tile"+ fileName + ".png";
            tileMapAssets.add(i, new Image(path));
        }
        tileMapAssets.add(52, new Image("file:src/main/resources/application/pack1/Pierre.png"));
        tileMapAssets.add(53, new Image("file:src/main/resources/application/pack1/tile169.png"));
        tileMapAssets.add(54, new Image("file:src/main/resources/application/pack1/arbre_bas.png"));
        tileMapAssets.add(55, new Image("file:src/main/resources/application/pack1/arbre_milieu.png"));
        tileMapAssets.add(56, new Image("file:src/main/resources/application/pack1/arbre_haut.png"));
    }
}