package application.modele;

import application.modele.armes.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Etabli {

    private IntegerProperty niveauProperty;
    private Environnement env;
    private Inventaire inventaire;
    private ObservableMap<String, HashMap<String, Integer>> listeMateriauxArmes;
    private HashMap<String, Integer>[] listeMateriauxEtabli;
    private String armeSelected;

    public Etabli(Environnement env) {
        niveauProperty = new SimpleIntegerProperty(0);
        this.env = env;
        inventaire = env.getPersonnage().getInventaire();
        initListeMateriauxEtabli();
        initListeMateriauxArmes();
        armeSelected = "Etabli";
    }

    private void initListeMateriauxEtabli() {
        listeMateriauxEtabli = new HashMap[3];
        listeMateriauxEtabli[0] = new HashMap<>() {{
            put("Pierre", 2);
        }};
        listeMateriauxEtabli[1] = new HashMap<>() {{
            put("Fer", 2);
        }};
        listeMateriauxEtabli[2] = new HashMap<>() {{
            put("Platine", 2);
        }};
    }

    //TODO ajouter les autres armes
    private void initListeMateriauxArmes() {
        listeMateriauxArmes = FXCollections.observableHashMap();
        listeMateriauxArmes.put("Hache1", new HashMap<>() {{
            put("Bois", 3);
            put("Pierre", 1);
        }});
        listeMateriauxArmes.put("Pioche1", new HashMap<>() {{
            put("Bois", 3);
            put("Pierre", 1);
        }});
        listeMateriauxArmes.put("Epee1", new HashMap<>() {{
            put("Bois", 10);
        }});
        listeMateriauxArmes.put("Arc1", new HashMap<>() {{
            put("Bois", 15);
        }});
        listeMateriauxArmes.put("Lance1", new HashMap<>() {{
            put("Bois", 25);
            put("Pierre", 10);
        }});
    }

    public void fabriquer() {
        Set listeMateriaux;
        if (armeSelected.equals("Etabli"))
            listeMateriaux = this.listeMateriauxEtabli[niveauProperty.getValue()].entrySet();
        else
            listeMateriaux = this.listeMateriauxArmes.get(armeSelected).entrySet();
        Iterator iterator = listeMateriaux.iterator();
        Map.Entry materiau;
        int cpt, i;
        while (iterator.hasNext()) {
            materiau = (Map.Entry) iterator.next();
            cpt = 0; i = 0;
            while (cpt < (int) materiau.getValue() && i < inventaire.getObjets().size()) {
                if (inventaire.getObjets().get(i).getEntite().getClass().getSimpleName().equals(materiau.getKey())) {
                    inventaire.getObjets().remove(i);
                    cpt++;
                }
                i++;
            }
        }
        if (armeSelected.equals("Etabli"))
            niveauProperty.setValue(niveauProperty.getValue() + 1);
        else
            inventaire.ajouterObjet(armeCorrespondant());
    }

    public boolean peutFabriquer() {
        Set listeMateriaux = null;
        if (armeSelected.equals("Etabli"))
            listeMateriaux = this.listeMateriauxEtabli[niveauProperty.getValue()].entrySet();
        else if (Character.getNumericValue(armeSelected.charAt(armeSelected.length() - 1)) <= niveauProperty.getValue())
            listeMateriaux = this.listeMateriauxArmes.get(armeSelected).entrySet();

        if (listeMateriaux != null) {
            boolean fabricable;
            Iterator iterator = listeMateriaux.iterator();
            Map.Entry materiau;
            int cpt, i;
            do {
                materiau = (Map.Entry) iterator.next();
                cpt = 0;
                i = 0;
                while (cpt < (int) materiau.getValue() && i < inventaire.getObjets().size()) {
                    if (inventaire.getObjets().get(i).getEntite().getClass().getSimpleName().equals(materiau.getKey()))
                        cpt++;
                    i++;
                }
                if (cpt == (int) materiau.getValue())
                    fabricable = true;
                else
                    fabricable = false;
            } while (iterator.hasNext() && fabricable);
            return fabricable;
        } else
            return false;
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

    public Set<String> getListeMateriauxArmesID() {
        return listeMateriauxArmes.keySet();
    }

    public HashMap<String, Integer> getListeMateriauxArmeSelected() {
        if (armeSelected.equals("Etabli"))
            return listeMateriauxEtabli[niveauProperty.getValue()];
        else
            return listeMateriauxArmes.get(armeSelected);
    }

    public final int getNiveau() {
        return niveauProperty.getValue();
    }

    public final IntegerProperty getNiveauProperty() {
        return niveauProperty;
    }
}
