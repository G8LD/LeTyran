package application.modele.map;

import application.modele.MapJeu;

import java.util.HashMap;

public class Chunk {
    private int x,y;
    private String id;
    private HashMap<String, Block> blocsChunk;
    private int[][] tabMap;

    public Chunk(int[][]map, int x, int y) {


        blocsChunk = new HashMap<>();
        this.x = x;
        this.y = y;
        this.tabMap = map;

        this.id = y + "_" + x;

        //System.out.println("Position du chunk y" + this.y + " x " + this.x);

        this.remplir();


    }


    public void ajouterBlock(int x, int y) {
        //System.out.println(this.x + "_" + this.y + "_" + this.x + "_" + this.y);
        int coordX, coordY;
        coordX = x;
        coordY = y ;

        //System.out.println(coordY + "_" + coordX);
        blocsChunk.put(x + "_" + y, new Block(coordX, coordY, tabMap[coordY][coordX]));
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "Chunk " + id;
    }

    public HashMap<String, Block> getBlocs() {
        return  this.blocsChunk;
    }

    public void remplir() {
        int blocY = this.y;
        int blocX;

        System.out.println("Position du chunk y" + this.y + " x " + this.x);

        while(blocY < MapJeu.TAILLE_CHUNK + this.y && blocY < MapJeu.HEIGHT - 1) {
            blocX = this.x;


            while(blocX < MapJeu.TAILLE_CHUNK + this.x && x < MapJeu.WIDTH - 1) {

                System.out.println("y :" + blocY + " x: " + blocX);

                this.ajouterBlock(blocX, blocY);
                blocX++;
            }

            blocY++;
        }
        /*while(y < MapJeu.TAILLE_CHUNK && y < tabMap.length - 1) {
            x = 0;
            while(x < MapJeu.TAILLE_CHUNK && x < tabMap[0].length - 1) {
                int positionCalculeX = this.x + x;
                int positionCalculeY = this.y + y;
                //System.out.println("positon x " + x + " position y" + y);
                if(positionCalculeX > 0 && positionCalculeX < tabMap.length) {
                    if(positionCalculeY > 0 && positionCalculeY < tabMap[0].length) {
                        this.ajouterBlock(positionCalculeX, positionCalculeY);
                    }
                }
                x++;
            }
            y++;
        }*/
        /*for(int y = 0; y < MapJeu.TAILLE_CHUNK; y++) {
            for(int x = 0; x < MapJeu.TAILLE_CHUNK; x++) {
                int positionCalculeX = this.x + x;
                int positionCalculeY = this.y + y;
                //System.out.println("positon x " + x + " position y" + y);
                if(positionCalculeX > 0 && positionCalculeX < tabMap.length) {
                    if(positionCalculeY > 0 && positionCalculeY < tabMap[0].length) {
                        this.ajouterBlock(positionCalculeX, positionCalculeY);
                    }
                }
            }
        }*/
    }
}
