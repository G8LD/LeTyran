package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.HashMap;

public class ModeleQuete {

    //Contient l'ensemble des objectifs à accomplir pour la quête
    private ObservableMap<String, Integer> objectifNombre;
    private StringProperty nomQuete;

    public ModeleQuete() {
        this.objectifNombre = FXCollections.observableHashMap();
        this.objectifNombre.put("Bois", 10);

    }

    public void chargerQuete(String quete) {
        this.nomQuete.setValue(quete);
        chargerObjectifs();
    }

    public void chargerObjectifs() {
        objectifNombre.clear();
    }

}
