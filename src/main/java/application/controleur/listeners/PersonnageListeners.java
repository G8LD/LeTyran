package application.controleur.listeners;

import application.controleur.FeuDeCampControleur;
import application.modele.personnages.Joueur;
import application.modele.personnages.ennemi.Ennemi;
import application.modele.personnages.Personnage;
import application.vue.ArmeVue;
import application.vue.FeuDeCampVue;
import application.vue.PersonnageVue;

public class PersonnageListeners {

    public PersonnageListeners(Personnage perso, PersonnageVue persoVue, ArmeVue armeVue, FeuDeCampVue feuDeCampVue) {
        this(perso, persoVue, armeVue);

        ((Joueur) perso).getMortProperty().addListener(((observableValue, aBoolean, t1) -> {
            if (t1)
                feuDeCampVue.transition(true);
        }));

        //si le joueur n'avance plus pour mettre le sprite du personnage immobile
        ((Joueur) perso).getAvanceProperty().addListener((observableValue, aBoolean, t1) -> {if (!t1)
            persoVue.immobile();
        });
    }

    public PersonnageListeners(Personnage perso, PersonnageVue persoVue, ArmeVue armeVue) {
        //appel la méthode animationDeplacement à chaque fois que x change et donc que le joueur se déplace et udpate la position de son arme
        perso.getXProperty().addListener((observableValue, number, t1) -> {
            persoVue.animerDeplacement();
            armeVue.updatePositon();
        });

        perso.getYProperty().addListener((observableValue, number, t1) -> {
            armeVue.updatePositon();
        });

        //retourne le sprite du perso
        perso.getDirectionProperty().addListener((observableValue, o, t1) ->  {
                persoVue.inverserSprite();
                armeVue.inverserSprite();
        });
    }

    public PersonnageListeners(Personnage perso, PersonnageVue persoVue) {
        //appel la méthode animationDeplacement à chaque fois que x change et donc que le joueur se déplace et udpate la position de son arme
        perso.getXProperty().addListener((observableValue, number, t1) -> {
            persoVue.animerDeplacement();

        });

        //retourne le sprite du perso
        perso.getDirectionProperty().addListener((observableValue, o, t1) ->  {
            persoVue.inverserSprite();

        });
    }
}
