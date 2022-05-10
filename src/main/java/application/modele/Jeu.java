package application.modele;

public class Jeu {

    private Personnage personnage;
    private MapJeu mapJeu;

    public Jeu() {
        personnage = new Personnage();
        mapJeu = new MapJeu();
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public MapJeu getMapJeu() {
        return mapJeu;
    }
}
