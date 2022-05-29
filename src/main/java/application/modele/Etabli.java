package application.modele;

import application.modele.armes.Arme;
import application.modele.armes.Hache;
import application.modele.armes.Pioche;
import application.modele.objets.Bois;
import application.modele.objets.Materiau;
import application.modele.objets.Pierre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Etabli {

    private Inventaire inventaire;
    private ObservableMap<Arme, HashMap<Materiau, Integer>> listeMateriaux;
    private Arme armeSelected;
    private boolean fabricable;

    public Etabli(Inventaire inventaire) {
        this.inventaire = inventaire;
        fabricable = false;
        initListeMateriaux();
        armeSelected = armeCorrespondant("Hache1");
    }

    //TODO ajouter les autres armes
    private void initListeMateriaux() {
        listeMateriaux = FXCollections.observableHashMap();
        listeMateriaux.put(new Hache(1), new HashMap<>() {{
            put(new Bois(), 3);
            put(new Pierre(), 1);
        }});
        listeMateriaux.put(new Pioche(1), new HashMap<>() {{
            put(new Bois(), 3);
            put(new Pierre(), 1);
        }});
        listeMateriaux.put(new Pioche(1), new HashMap<>() {{
            put(new Bois(), 10);
        }});
    }

    public void fabriquer() {
        Set listeMateriaux = this.listeMateriaux.get(armeSelected).entrySet();
        Iterator iterator = listeMateriaux.iterator();
        Map.Entry materiau;
        while (iterator.hasNext()) {
            materiau = (Map.Entry) iterator.next();
            for (int i = 0; i < (int) materiau.getValue(); i++) {
                //TODO retirer les materiaux de l'inventaire
            }
        }

        //TODO ajouter l'arme dans l'inventaire du perso
    }

    private void peutFabriquer() {
        if (armeSelected == null)
            fabricable = false;
        else {
            Set listeMateriaux = this.listeMateriaux.get(armeSelected).entrySet();
            Iterator iterator = listeMateriaux.iterator();
            do {
                //TODO vérifie que le joueur a les matériaux nécessaire
                iterator.next();
            } while (iterator.hasNext() && fabricable);
            fabricable = true;
        }
    }

    public String getArmeSelectedNom() {
        return armeSelected.getClass().getSimpleName() + armeSelected.getQualite();
    }

    public void setArmeSelected(String armeSelected) {
        this.armeSelected = armeCorrespondant(armeSelected);
        peutFabriquer();
    }

    private Arme armeCorrespondant(String nomArme) {
        for (Arme arme : listeMateriaux.keySet()) {
            if (nomArme.equals(arme.getClass().getSimpleName() + arme.getQualite()))
                return arme;
        }
        return null;
    }

    public boolean getFabricable() {
        return fabricable;
    }

    public HashMap<Materiau, Integer> getListeMateriauxArmeSelected() {
        return listeMateriaux.get(armeSelected);
    }

    public ObservableMap<Arme, HashMap<Materiau, Integer>> getListeMateriaux() {
        return listeMateriaux;
    }
}
