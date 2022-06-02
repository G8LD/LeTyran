package application.controleur.events;

import application.vue.VueDialogue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;

public class DialogueListener implements EventHandler<MouseEvent> {
    private VueDialogue vueDialogue;

    public DialogueListener(VueDialogue vueDialogue) {
        this.vueDialogue = vueDialogue;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        this.vueDialogue.accelererOuFermer();
    }
}
