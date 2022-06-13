package application.vue;

import application.modele.quetes.BaseQuete;
import application.modele.quetes.ObjectifNombreNecessaire;
import application.modele.quetes.QueteType;
import application.vue.quetes.QueteObjectif;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VueQuetes {


    private TextFlow paneQuete;
    private VBox vboxObjectifs;

    public VueQuetes(TextFlow paneQuete) {
        this.paneQuete = paneQuete;
        this.vboxObjectifs = (VBox)this.paneQuete.getChildren().get(0);
    }

    public void initialiserQuete(BaseQuete quete) {
        this.ajouterObjectifs(quete.recupererListeObjectifs());
    }

    public void ajouterObjectifs(HashMap<QueteType.TYPE_QUETE, HashMap<String, ObjectifNombreNecessaire>> objectifs) {

        Iterator iterationQueteTYpe = objectifs.entrySet().iterator();

        while(iterationQueteTYpe.hasNext()) {
            Map.Entry<QueteType.TYPE_QUETE, HashMap<String, ObjectifNombreNecessaire>> objectif = (Map.Entry)iterationQueteTYpe.next();

        }
        //this.vboxObjectifs.getChildren().add(new QueteObjectif(objectifs))
    }




}
