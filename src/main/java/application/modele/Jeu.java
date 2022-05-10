package application.modele;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Jeu {
    public Jeu(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                System.out.println("Oulalala");
            }
        });
    }
}
