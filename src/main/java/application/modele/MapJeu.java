package application.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapJeu {
    public final static int WIDTH = 30;
    public final static int HEIGHT = 20;
    public final static int TUILE_TAILLE = 32;
    public final static int TAILLE_CHUNK = 25;
    public final static int DISTANCE_RENDU = 4;

    /*public MapJeu() {
        int joueurX = this.
        for(int x = ) {

        }
    }*/
    private int[][] tabMap;

    public MapJeu() {
        tabMap = new int[HEIGHT][WIDTH];
        construireMap();
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
                for (int j = 0; j < WIDTH; j++)
                    tabMap[i][j] = Integer.parseInt(tabLine[j]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int[][] getTabMap() {
        return tabMap;
    }
}
