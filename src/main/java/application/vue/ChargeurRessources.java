package application.vue;

import application.modele.armes.Arme;
import application.vue.ArmeVue;
import application.vue.ArmeVue;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class ChargeurRessources {

    public static ArrayList<Image> tileMapAssets = new ArrayList<Image>();
    public static HashMap<String, Image> iconObjets = new HashMap<>();

    public static void charger() {
        for (int i = 1; i < 57; i++) {
            tileMapAssets.add(null);
        }

        tileMapAssets.add(0, new Image("file:src/main/resources/application/pack1/tile_transparent.png"));
        //tileMapAssets.add(2, new Image("file:src/main/resources/application/pack1/sprite_arc2.png"));
        for (int i = 2; i <= 51; i++) {
            String fileName = "0" + (i-1);
            if(i < 10) {
                fileName = "00" + (i-1);
            }
            String path = "file:src/main/resources/application/pack1/tile"+ fileName + ".png";
            tileMapAssets.add(i, new Image(path));
        }
        tileMapAssets.add(34, new Image("file:src/main/resources/application/pack1/Terre.png"));
        tileMapAssets.add(52, new Image("file:src/main/resources/application/pack1/Pierre.png"));
        tileMapAssets.add(53, new Image("file:src/main/resources/application/pack1/Fer.png"));
        tileMapAssets.add(54, new Image("file:src/main/resources/application/pack1/Platine.png"));
        tileMapAssets.add(55, new Image("file:src/main/resources/application/pack1/arbre_bas.png"));
        tileMapAssets.add(56, new Image("file:src/main/resources/application/pack1/arbre_milieu.png"));
        tileMapAssets.add(57, new Image("file:src/main/resources/application/pack1/arbre_haut.png"));
        tileMapAssets.add(58, new Image("file:src/main/resources/application/Coffre/CoffreOrFerm.png"));




        //Icon objets
//        iconObjets.put("Terre", new Image("file:src/main/resources/application/ressources/Terre.png"));
//        iconObjets.put("Bois", new Image("file:src/main/resources/application/ressources/Bois.png"));
//        iconObjets.put("Pierre", new Image("file:src/main/resources/application/ressources/Pierre.png"));
//        iconObjets.put("Fer", new Image("file:src/main/resources/application/ressources/Fer.png"));
//        iconObjets.put("Platine", new Image("file:src/main/resources/application/ressources/Platine.png"));
        iconObjets.put("Terre", new Image("file:src/main/resources/application/pack1/Terre.png"));
        iconObjets.put("Bois", new Image("file:src/main/resources/application/pack1/Bois.png"));
        iconObjets.put("Pierre", new Image("file:src/main/resources/application/pack1/Pierre.png"));
        iconObjets.put("Fer", new Image("file:src/main/resources/application/pack1/Fer.png"));
        iconObjets.put("Platine", new Image("file:src/main/resources/application/pack1/Platine.png"));
        iconObjets.put("Armure1", new Image("file:src/main/resources/application/arme/sprite_armure1.png"));
        iconObjets.put("Armure2", new Image("file:src/main/resources/application/arme/sprite_armure2.png"));
        iconObjets.put("Armure3", new Image("file:src/main/resources/application/arme/sprite_armure3.png"));


        iconObjets.put("inventaireMain", new Image("file:src/main/resources/application/inventaire/icon_main.png"));
        iconObjets.put("inventaireSac", new Image("file:src/main/resources/application/inventaire/inventaire_sac.png"));

        ArmeVue.initSprites();
    }
}