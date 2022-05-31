package application.vue;

import application.controleur.Controleur;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class VueDialogue {
    private TextFlow dialogPane;
    private Pane root;

    private String textAffiche;
    private float caractereActuel;
    private Text textEnfant;

    public VueDialogue(Pane root, TextFlow textFlow){
        this.dialogPane = textFlow;
        this.root = root;
        textAffiche = "";
        caractereActuel = 0;

        textEnfant = (Text) textFlow.getChildren().get(0);
        this.definirText("dsqdsqdqsezadsdqsdqsdqsdeaz\nzaezaeazeazesdqsdqsazeazedsqdsqdazeazesdqsdaze");

    }

    public void definirText(String txt) {
        textAffiche = txt;
        caractereActuel = 0;
    }

    public void animer(double vitesse) {
        if(caractereActuel < textAffiche.length()) {
            caractereActuel += (vitesse + 0.2);
        }

        textEnfant.setText(textAffiche.substring(0, (int)caractereActuel));

    }
}
