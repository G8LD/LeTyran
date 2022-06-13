package application.vue.quetes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class QueteObjectif extends HBox {


    private IntegerProperty nombreActuel;
    private IntegerProperty nombreAFaire;

    public QueteObjectif(IntegerProperty nombreActuel, IntegerProperty nombreAFaire) {
        nombreActuel = new SimpleIntegerProperty(0);
        nombreAFaire = new SimpleIntegerProperty(0);

        TextFlow text = new TextFlow();
        Text descriptif = new Text();
        descriptif.setText("FNKDFSNK");

        Text nbObjectif = new Text();

        nbObjectif.textProperty().bind(nombreActuel.asString().concat("+").concat(nombreAFaire.getValue()));

        text.getChildren().add(descriptif);
        this.getChildren().add(text);
        this.getChildren().add(nbObjectif);
    }


}
