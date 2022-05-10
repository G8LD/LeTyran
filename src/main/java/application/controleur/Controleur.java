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
        construireMap();
    }

    private void construireMap() {
        ImageView img;
        for (int i = 0; i < MapJeu.HEIGHT; i++) {
            for (int j = 0; j < MapJeu.WIDTH; j++) {
                switch (mapJeu) {
                    case 0:
                        img = new ImageView(new Image("file:src/main/resources/application/sprite/decor/LGrass5.png"));
                        break;
                }
                tilePane.getChildren().add(img);
            }
        }
    }
}
