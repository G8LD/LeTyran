package application.vue.vueMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

import static application.modele.MapJeu.HEIGHT;
import static application.modele.MapJeu.WIDTH;

public class ChargeurRessources {

    public static ArrayList<Image> tileMapAssets = new ArrayList<Image>();
    public static HashMap<String, Image> listeObjetImage = new HashMap<String, Image>();

    public static void charger() {
        for (int i = 1; i < 57; i++) {
            tileMapAssets.add(null);
        }

        tileMapAssets.add(0, new Image("file:src/main/resources/application/pack1/tile_transparent.png"));
        tileMapAssets.add(2, new Image("file:src/main/resources/application/pack1/tile001.png"));
        for (int i = 2; i <= 51; i++) {
            String fileName = "0" + (i-1);
            if(i < 10) {
                fileName = "00" + (i-1);
            }
            String path = "file:src/main/resources/application/pack1/tile"+ fileName + ".png";
            tileMapAssets.add(i, new Image(path));
        }
        tileMapAssets.add(52, new Image("file:src/main/resources/application/pack1/Pierre.png"));
        listeObjetImage.put("Fer",new Image("file:src/main/resources/application/Ressources/Fer.png"));
        listeObjetImage.put("Bois",new Image("file:src/main/resources/application/Ressources/Bois.png"));
        listeObjetImage.put("Pierre",new Image("file:src/main/resources/application/Ressources/Pierre.png"));
        listeObjetImage.put("Platine",new Image("file:src/main/resources/application/Ressources/Platine.png"));
        listeObjetImage.put("Terre",new Image("file:src/main/resources/application/Ressources/Terre.png"));
    }


}