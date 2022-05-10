package application.controleur;

import application.modele.MapJeu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private MapJeu mapJeu;

    @FXML
    private StackPane root;
    @FXML
    private TilePane tilePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapJeu = new MapJeu();
        tilePane.setMaxHeight(MapJeu.HEIGHT*32);
        tilePane.setMaxWidth(MapJeu.WIDTH*32);
        construireMap();
    }

    private void construireMap() {
        ImageView img;
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                switch (mapJeu.getTabMap()[i][j]) {
                    case 0: img = new ImageView(new Image("file:src/main/resources/application/pack1/tile008.png")); break;
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
                tilePane.getChildren().add(img);
            }
        }
    }
}
