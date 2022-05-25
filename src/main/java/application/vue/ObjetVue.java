package application.vue;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.ObjetJeu;
import application.vue.controls.ObjetView;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObjetVue {

    private Environnement env;
    private ArrayList<ObjetView> objetImageView;
    private Pane root;

    public ObjetVue(Environnement env, Pane root) {
        this.env = env;
        this.objetImageView = new ArrayList<>();
        this.root = root;
        enregistrerTousLesObjets();

        this.env.getEntites().addListener(new ListChangeListener<Entite>() {
            @Override
            public void onChanged(Change<? extends Entite> change) {
                change.next();
                for(int i = 0; i < change.getRemovedSize(); i++) {
                    retirerObjet(change.getRemoved().get(i));
                }

                for(int i = 0; i < change.getAddedSize(); i++) {
                    System.out.println("on doit afficher un objet");
                    ajouterObjet(change.getAddedSubList().get(i));
                }

            }
        });
    }

    public void enregistrerTousLesObjets() {
        for(int i = 0; i < this.env.getEntites().size(); i++) {
            Entite obj = this.env.getEntites().get(i);
            ObjetView objView = new ObjetView(obj);
            objetImageView.add(objView);
            this.root.getChildren().add(objView);

        }
    }

    public void ajouterObjet(Entite objet) {
        if(objet instanceof ObjetJeu) {
            System.out.println("On affiche l'objet " + objet.toString());
            ObjetView objView = new ObjetView(objet);
            this.objetImageView.add(objView);
            this.root.getChildren().add(objView);
        }
    }

    public void retirerObjet(Entite objet) {
        if(objet instanceof ObjetJeu) {
            for (int i = 0; i < this.objetImageView.size(); i++) {
                ObjetView img = this.objetImageView.get(i);
                if (img.getObjet() == objet) {
                    this.root.getChildren().remove(img);
                }
            }
        }
    }

    public void rendreObjets() {
        for(int i = 0; i < this.objetImageView.size(); i++) {
            ImageView imgView = this.objetImageView.get(i);
            imgView.setVisible(true);
        }
    }

    public void update() {
        rendreObjets();
    }
}
