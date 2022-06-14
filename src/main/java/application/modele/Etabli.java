package application.modele;

import application.modele.armes.*;
import application.modele.armes.arc.Arc;
import application.modele.armes.arc.Fleche;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Etabli {

    private int x;
    private int y;
    private IntegerProperty niveauProperty;
    private Environnement env;
    private Inventaire inventaire;
    private ObservableMap<String, HashMap<String, Integer>> listeMateriauxObjets;
    private HashMap<String, Integer>[] listeMateriauxEtabli;
    private String objetSelected;

    public Etabli(Environnement env) {
        x = 5;
        y = 11;
        niveauProperty = new SimpleIntegerProperty(0);
        this.env = env;
        inventaire = env.getJoueur().getInventaire();
        initListeMateriauxEtabli();
        initListeMateriauxArmes();
        objetSelected = "Etabli";
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

    private void initListeMateriauxArmes() {
        listeMateriauxObjets = FXCollections.observableHashMap();
        listeMateriauxObjets.put("Fleche1", new HashMap<>() {{
            put("Bois", 3);
        }});
        listeMateriauxObjets.put("Hache1", new HashMap<>() {{
            put("Bois", 3);
            put("Pierre", 1);
        }});
        listeMateriauxObjets.put("Pioche1", new HashMap<>() {{
            put("Bois", 3);
            put("Pierre", 1);
        }});
        listeMateriauxObjets.put("Epee1", new HashMap<>() {{
            put("Bois", 10);
        }});
        listeMateriauxObjets.put("Arc1", new HashMap<>() {{
            put("Bois", 15);
        }});
        listeMateriauxObjets.put("Lance1", new HashMap<>() {{
            put("Bois", 20);
            put("Pierre", 15);
        }});
        listeMateriauxObjets.put("Armure1", new HashMap<>() {{
            put("Bois", 20);
        }});
        listeMateriauxObjets.put("Hache2", new HashMap<>() {{
            put("Bois", 5);
            put("Fer", 5);
        }});
        listeMateriauxObjets.put("Pioche2", new HashMap<>() {{
            put("Bois", 5);
            put("Fer", 5);
        }});
        listeMateriauxObjets.put("Epee2", new HashMap<>() {{
            put("Fer", 10);
        }});
        listeMateriauxObjets.put("Arc2", new HashMap<>() {{
            put("Bois", 15);
            put("Fer", 10);
        }});
        listeMateriauxObjets.put("Lance2", new HashMap<>() {{
            put("Bois", 20);
            put("Fer", 15);
        }});
        listeMateriauxObjets.put("Armure2", new HashMap<>() {{
            put("Fer", 20);
        }});
        listeMateriauxObjets.put("Hache3", new HashMap<>() {{
            put("Bois", 10);
            put("Platine", 10);
        }});
        listeMateriauxObjets.put("Pioche3", new HashMap<>() {{
            put("Bois", 10);
            put("Platine", 10);
        }});
        listeMateriauxObjets.put("Epee3", new HashMap<>() {{
            put("Platine", 10);
        }});
        listeMateriauxObjets.put("Arc3", new HashMap<>() {{
            put("Bois", 15);
            put("Platine", 10);
        }});
        listeMateriauxObjets.put("Lance3", new HashMap<>() {{
            put("Bois", 20);
            put("Platine", 15);
        }});
        listeMateriauxObjets.put("Armure3", new HashMap<>() {{
            put("Platine", 20);
        }});
    }

    public void fabriquer() {
        Set listeMateriaux;
        if (objetSelected.equals("Etabli"))
            listeMateriaux = this.listeMateriauxEtabli[niveauProperty.getValue()].entrySet();
        else
            listeMateriaux = this.listeMateriauxObjets.get(objetSelected).entrySet();
        Iterator iterator = listeMateriaux.iterator();
        Map.Entry materiau;
        int cpt, i;
        while (iterator.hasNext()) {
            materiau = (Map.Entry) iterator.next();
            cpt = 0; i = 0;
            while (cpt < (int) materiau.getValue() && i < inventaire.getObjets().size()) {
                if (inventaire.getObjets().get(i).getEntite().getClass().getSimpleName().equals(materiau.getKey())) {
                    int quantite = inventaire.getObjets().get(i).getNombre();
                    for (int j = 0; j < quantite && cpt < (int) materiau.getValue(); j++) {
                        inventaire.getObjets().get(i).retirerDansStack();
                        cpt++;
                    }
                }
                i++;
            }
        }
        if (objetSelected.equals("Etabli"))
            niveauProperty.setValue(niveauProperty.getValue() + 1);
        else
            inventaire.ajouterObjet(armeCorrespondant());
    }

    public boolean peutFabriquer() {
        Set listeMateriaux = null;
        if (objetSelected.equals("Etabli") && niveauProperty.getValue() < 3)
            listeMateriaux = this.listeMateriauxEtabli[niveauProperty.getValue()].entrySet();
        else if (Character.getNumericValue(objetSelected.charAt(objetSelected.length() - 1)) <= niveauProperty.getValue())
            listeMateriaux = this.listeMateriauxObjets.get(objetSelected).entrySet();

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
                        for (int j = 0; j < inventaire.getObjets().get(i).getNombre() && cpt < (int) materiau.getValue(); j++)
                            cpt++;
                    i++;
                }
                fabricable = cpt == (int) materiau.getValue();
            } while (iterator.hasNext() && fabricable);
            return fabricable;
        } else
            return false;
    }

    public String getObjetSelected() {
        return objetSelected;
    }

    public void setObjetSelected(String objetSelected) {
        this.objetSelected = objetSelected;
    }

    private Entite armeCorrespondant() {
        Entite objet;
        switch (objetSelected.substring(0, objetSelected.length() - 1)) {
            case "Fleche": objet = new Fleche(); break;
            case "Hache": objet = new Hache(env, Character.getNumericValue(objetSelected.charAt(objetSelected.length()-1))); break;
            case "Pioche" : objet = new Pioche(env, Character.getNumericValue(objetSelected.charAt(objetSelected.length()-1))); break;
            case "Epee" : objet = new Epee(env, Character.getNumericValue(objetSelected.charAt(objetSelected.length()-1))); break;
            case "Arc" : objet = new Arc(env, Character.getNumericValue(objetSelected.charAt(objetSelected.length()-1))); break;
            case "Lance" : objet = new Lance(env, Character.getNumericValue(objetSelected.charAt(objetSelected.length()-1))); break;
            case "Armure" : objet = new Armure(env, Character.getNumericValue(objetSelected.charAt(objetSelected.length()-1))); break;
            default: objet = null; break;
        }
        return objet;
    }

    public Set<String> getObjetsID() {
        return listeMateriauxObjets.keySet();
    }

    public HashMap<String, Integer> getListeMateriauxObjetSelected() {
        if (objetSelected.equals("Etabli"))
            return listeMateriauxEtabli[niveauProperty.getValue()];
        else
            return listeMateriauxObjets.get(objetSelected);
    }

    public final int getNiveau() {
        return niveauProperty.getValue();
    }

    public final IntegerProperty getNiveauProperty() {
        return niveauProperty;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
