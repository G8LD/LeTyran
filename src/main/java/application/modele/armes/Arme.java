package application.modele.armes;

public abstract class Arme {

    private int qualite;
    private int degats;
    private int distance;

    public Arme(int qualite, int degats, int distance) {
        this.qualite = qualite;
        this.degats = degats;
        this.distance = distance;
    }

    public abstract void frapper();


    public int getDegats() {
        return this.degats;
    }
    public int setDegats(int nbDegat) {
        return this.degats=nbDegat;
    }

    public int getQualite() {
        return this.qualite;
    }
}
