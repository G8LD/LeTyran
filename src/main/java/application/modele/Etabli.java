package application.modele;

import application.modele.armes.*;
import application.modele.objets.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Etabli {

    private int niveau;
    private Environnement env;
    private Inventaire inventaire;
    private ObservableMap<String, HashMap<Materiau, Integer>> listeMateriaux;
    private String armeSelected;

    public Etabli(Environnement env) {
        niveau = 0;
        this.env = env;
        inventaire = env.getPersonnage().getInventaire();
        initListeMateriaux();
        armeSelected = "Hache1";
        niveau++;
    }

    //TODO ajouter les autres armes
    private void initListeMateriaux() {
        listeMateriaux = FXCollections.observableHashMap();
        Materiau bois = new Bois();
        Materiau pierre = new Pierre();
        Materiau fer = new Fer();
        Materiau platine = new Platine();

        listeMateriaux.put("Hache1", new HashMap<>() {{
            put(bois, 3);
            put(pierre, 1);
        }});
        listeMateriaux.put("Pioche1", new HashMap<>() {{
            put(bois, 3);
            put(pierre, 1);
        }});
        listeMateriaux.put("Epee1", new HashMap<>() {{
            put(bois, 10);
        }});
        listeMateriaux.put("Arc1", new HashMap<>() {{
            put(bois, 15);
        }});
        listeMateriaux.put("Lance1", new HashMap<>() {{
            put(bois, 25);
            put(pierre, 10);
        }});
    }

    public void fabriquer() {
        Set listeMateriauxArme = this.listeMateriaux.get(armeSelected).entrySet();
        Iterator iterator = listeMateriauxArme.iterator();
        Map.Entry materiau;
        int cpt, i;
        while (iterator.hasNext()) {
            materiau = (Map.Entry) iterator.next();
            cpt = 0; i = 0;
            while (cpt < (int) materiau.getValue() && i < inventaire.getObjets().size()) {
                if (inventaire.getObjets().get(i).getEntite().getClass().equals(materiau.getKey().getClass())) {
                    inventaire.getObjets().remove(i);
                    cpt++;
                }
                i++;
            }
        }
        inventaire.ajouterObjet(armeCorrespondant());
    }

    public boolean peutFabriquer() {
        boolean fabricable;
        Set listeMateriaux = this.listeMateriaux.get(armeSelected).entrySet();
        Iterator iterator = listeMateriaux.iterator();
        Map.Entry materiau;
        int cpt, i;
        do {
            materiau = (Map.Entry) iterator.next();
            cpt = 0; i = 0;
            while (cpt < (int) materiau.getValue() && i < inventaire.getObjets().size()) {
                if (inventaire.getObjets().get(i).getEntite().getClass().equals(materiau.getKey().getClass()))
                    cpt++;
                i++;
            }
            if (cpt == (int) materiau.getValue())
                fabricable = true;
            else
                fabricable = false;
        } while (iterator.hasNext() && fabricable);
        return fabricable;
    }

    public String getArmeSelected() {
        return armeSelected;
    }

    public void setArmeSelected(String armeSelected) {
        this.armeSelected = armeSelected;
    }

    private Arme armeCorrespondant() {
        Arme arme;
        switch (armeSelected) {
            case "Hache1": arme = new Hache(env, 1); break;
            case "Pioche1" : arme = new Pioche(env, 1); break;
            case "Epee1" : arme = new Epee(env, 1); break;
            case "Arc1" : arme = new Arc(env, 1); break;
            case "Lance1" : arme = new Lance(env, 1); break;
            default: arme = null; break;
        }
        return arme;
    }

    public HashMap<Materiau, Integer> getListeMateriauxArmeSelected() {
        return listeMateriaux.get(armeSelected);
    }
}
