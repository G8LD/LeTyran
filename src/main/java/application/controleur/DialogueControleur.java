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

        if (nombreClick == 1) {
            this.vueDialogue.afficherFin();
        } else if(nombreClick == 2 && !this.modDialog.dernierePartie()) {
            this.vueDialogue.prochainePartie();
            this.modDialog.avancerPartie();
           nombreClick = 0;
        } else if(nombreClick == 3 || nombreClick == 2 && this.modDialog.dernierePartie()) {
            this.vueDialogue.fermer();
        }

    }
}
