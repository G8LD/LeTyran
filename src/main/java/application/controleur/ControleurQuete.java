package application.controleur;

import application.modele.ModeleQuetes;
import application.modele.ObjetInventaire;
import application.modele.quetes.ObjectifNombreNecessaire;
import application.modele.quetes.QueteType;
import application.vue.VueQuetes;
import javafx.scene.text.TextFlow;

import java.util.HashMap;

public class ControleurQuete {
    private ModeleQuetes modeleQuetes;
    private VueQuetes vueQuetes;

    public ControleurQuete(Controleur controleur, TextFlow conteneurQuete) {
        this.modeleQuetes = new ModeleQuetes();
        this.vueQuetes = new VueQuetes(conteneurQuete);

        this.vueQuetes.initialiserQuete(this.modeleQuetes.getQueteActuel());

    }

    public void objetAEteRetireeInventaire(ObjetInventaire obj) {
        HashMap<String, ObjectifNombreNecessaire> objectifs = this.modeleQuetes.getQueteActuel().listeObjectifs.get(QueteType.TYPE_QUETE.RAMASSER);
        String nomEntite = obj.getEntite().getClass().getSimpleName();
        if(objectifs.get(nomEntite) != null) {
            objectifs.get(nomEntite).retirerNombreObjectif(obj.getStackActuelProperty().getValue());
        }

    }

    public void objetAEteAjouteeInventaire(ObjetInventaire obj) {
        HashMap<String, ObjectifNombreNecessaire> objectifs = this.modeleQuetes.getQueteActuel().listeObjectifs.get(QueteType.TYPE_QUETE.RAMASSER);
        String nomEntite = obj.getEntite().getClass().getSimpleName();
        if(objectifs.get(nomEntite) != null) {
            objectifs.get(nomEntite).ajouterNombreObjectif(obj.getStackActuelProperty().getValue());
        }

    }


}
