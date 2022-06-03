package application.controleur;

import application.modele.ModeleDialogue;
import application.vue.VueDialogue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class DialogueControleur implements EventHandler<MouseEvent> {

    private VueDialogue vueDialogue;
    private ModeleDialogue modDialog;
    private int nombreClick = 0;

    public DialogueControleur(VueDialogue vueDialogue, ModeleDialogue modeleDialogue) {
        this.vueDialogue = vueDialogue;
        this.modDialog = modeleDialogue;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {


        nombreClick++;

        if(nombreClick == 3) {
            this.vueDialogue.fermer();
        } else {
            this.vueDialogue.afficherDebut();
        }


        if (nombreClick == 1) {
            this.vueDialogue.afficherFin();
        } else if(nombreClick == 2 && !this.modDialog.dernierePartie()) {
            this.modDialog.avancerPartie();
        }

    }
}
