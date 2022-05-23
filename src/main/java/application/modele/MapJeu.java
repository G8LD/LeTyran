package application.modele;

import application.modele.map.Block;
import application.modele.map.Chunk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MapJeu {
    private Environnement env;
    public final static int WIDTH = 30;
    public final static int HEIGHT = 20;
    public final static int TUILE_TAILLE = 32;
    public final static int TAILLE_CHUNK = 5;
    public final static int DISTANCE_RENDU = 4;

    private HashMap<String, Chunk> mapChunks;

    private int[][] tabMap;

    public MapJeu(Environnement env) {
        this.env = env;
        this.mapChunks = new HashMap<>();
        tabMap = new int[HEIGHT][WIDTH];

        construireMap();
        chargerRenduCarte();

    }

    public HashMap<String, Chunk> getMapChunks() {
        return this.mapChunks;
    }

    public void chargerRenduCarte() {
        int joueurX = this.env.getPersonnage().getX() / TUILE_TAILLE;
        int joueurY = this.env.getPersonnage().getY() / TUILE_TAILLE;

        int minX = joueurX - DISTANCE_RENDU;
        int maxX = joueurX + DISTANCE_RENDU;
        int minY = joueurY - DISTANCE_RENDU;
        int maxY = joueurY + DISTANCE_RENDU;


        int x = minX;
        int y = minY;

        while(y > 0 && y < maxY) {
            while(x > 0 && x < maxX) {

                x++;
            }
            y++;
        }
    }

    private void ajouterChunk(int x, int y) {

        mapChunks.put(y + "_" + x, new Chunk(this.tabMap, x, y));
    }

    private void constuireChunks() {
        InputStream is = getClass().getResourceAsStream("/application/tiles/TileMapSimplifie.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        String[] tabLine;

        //boolean chunkAjouter = false
        for(int y = 0; y < tabMap.length / 5; y++) {
            for(int x = 0; x < tabMap[0].length / 5; x++) {
                System.out.println("ajout");
                this.ajouterChunk(x * 5, y * 5);
            }
        }

    }

    private void construireMap() {
        InputStream is = getClass().getResourceAsStream("/application/tiles/TileMapSimplifie.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        String[] tabLine;
        for (int i = 0; i < HEIGHT; i++) {
            try {
                line = br.readLine();
                tabLine = line.split(" ");
                for (int j = 0; j < WIDTH; j++) {
                    tabMap[i][j] = Integer.parseInt(tabLine[j]);

                    System.out.print(tabMap[i][j] + " ");
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        constuireChunks();
        for(Chunk chk : mapChunks.values()) {
            for(Block bloc : chk.getBlocs().values()) {
                //System.out.println(bloc);
            }

            //System.out.println("--------------------------------");
            //System.out.println(chk.getId());
        }
    }






    public int[][] getTabMap() {
        return tabMap;
    }
}
