package application.controleur;

import application.modele.Jeu;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.StackPane;

import static application.controleur.Controleur.TUILE_TAILLE;

public class AnimationDeplacementJoueur extends AnimationTimer {

    private Jeu jeu;
    private StackPane spritesJoueur;

    private long lastUpdate;
    private long latence;
    private double decalage;
    private boolean running;

    public AnimationDeplacementJoueur(Jeu jeu, StackPane SpritesJoueur) {
        this.jeu = jeu;
        this.spritesJoueur = SpritesJoueur;
        this.lastUpdate = 0;
        this.latence = 25_000_000;
        this.decalage = TUILE_TAILLE - TUILE_TAILLE/4;
        this.running = false;
    }

    @Override
    public void start() {
        super.start();
        running = true;
    }

    @Override
    public void stop() {
        super.stop();
        running = false;
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= latence) {
            animation();
            lastUpdate = now;
        } else if (decalage < 0) {
            //immobile();
            decalage = TUILE_TAILLE - TUILE_TAILLE/4;
            stop();
        }

    }

    private void animation() {
        int i = 0;
        while (!spritesJoueur.getChildren().get(i).isVisible()) i++;
        spritesJoueur.getChildren().get(i).setVisible(false);

        switch (jeu.getPersonnage().getDirection()) {
            case Gauche:
                spritesJoueur.setTranslateX(jeu.getPersonnage().getX() * (TUILE_TAILLE) + decalage);
                if (i == 1) spritesJoueur.getChildren().get(2).setVisible(true);
                else spritesJoueur.getChildren().get(1).setVisible(true);
                break;
            case Droit:
                spritesJoueur.setTranslateX(jeu.getPersonnage().getX() * (TUILE_TAILLE) - decalage);
                if (i == 4) spritesJoueur.getChildren().get(5).setVisible(true);
                else spritesJoueur.getChildren().get(4).setVisible(true);
                break;
            default: break;
        }
        decalage-= TUILE_TAILLE/4;
    }

    public void immobile() {
        for (int i = 0; i  < spritesJoueur.getChildren().size(); i++)
            spritesJoueur.getChildren().get(i).setVisible(false);

        switch (jeu.getPersonnage().getDirection()) {
            case Gauche : spritesJoueur.getChildren().get(0).setVisible(true); break;
            case Droit : spritesJoueur.getChildren().get(3).setVisible(true); break;
            default: break;
        }
    }

    public boolean isRunning() {
        return running;
    }
}
