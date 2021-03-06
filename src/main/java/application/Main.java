package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static application.controleur.Controleur.TUILE_TAILLE;
import static application.modele.MapJeu.HEIGHT;
import static application.modele.MapJeu.WIDTH;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            StackPane root = FXMLLoader.load(getClass().getResource("vue.fxml"));
            Scene scene = new Scene(root, WIDTH*TUILE_TAILLE,HEIGHT*TUILE_TAILLE);
            root.requestFocus();
            primaryStage.setTitle("Le Tyran");
            primaryStage.setResizable(false);
            //primaryStage.setMaximized(true);
            primaryStage.setScene(scene);

            //Jeu jeu = new Jeu(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
