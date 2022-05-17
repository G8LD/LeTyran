package application.modele.objets;

public abstract class Minerai {
    private int pv;


    public Minerai(int pv){
        this.pv=pv;
    }

    public int getPv() {
        return pv;
    }

    public int décrementerVie(int attaqueArme){
        this.pv-=attaqueArme;
        return this.pv;
    }

    public boolean estCassé(int pv){
        if(this.pv<=0) {
            return true;
        }
        else
            return false;
    }





    /*
    public void recolteMinerai(int pv){
        if (estCassé(pv));
    }
    */

    }


