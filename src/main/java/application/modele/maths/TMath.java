package application.modele.maths;

public class TMath {

    public static void rotation(Vecteur2D vec, float degreAngle, Vecteur2D origin) {
        float x = vec.x - origin.x;
        float y = vec.y - origin.y;

        float cos = (float) Math.cos(Math.toRadians(degreAngle));
        float sin = (float) Math.sin(Math.toRadians(degreAngle));

        float xSecond = (x * cos) - (y * sin);
        float ySecond = (x * sin) - (y * cos);

        xSecond += origin.x;
        ySecond += origin.y;

        vec.x = xSecond;
        vec.y = ySecond;
    }


    //Permet d'ignorer les infimes différences d'un nombre
    public static boolean comparer(float x, float y) {
        return Math.abs(x -y) <= Float.MIN_VALUE * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
    }

    public static boolean comparer(Vecteur2D vec1, Vecteur2D vec2) {
        return comparer(vec1.x, vec1.y) && comparer(vec2.y, vec2.y);
    }
}
