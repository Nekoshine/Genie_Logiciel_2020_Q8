package view.style;

import java.awt.*;

/**
 * Définition de couleur personnel
 */
public final class ColorPerso extends Color {

    public static final Color jaune = new Color(224,206,0);
    public static final Color jauneHoover = new Color(212, 194, 0);

    public static final Color vert = new Color(19,165,25);
    public static final Color vertHoover = new Color(17, 140, 21);

    public static final Color grisClair = new Color(211,211,211);
    public static final Color grisFonce = new Color(100,100,100);
    public static final Color grisOriginal = new Color(238,238,238);

    public static final Color rouge = new Color(180,40,20);
    public static final Color rougeHoover = new Color(156,36,17);

    public static final Color azur = new Color(30,127,203);
    public static final Color azurHoover = new Color(27, 113, 179);
    public static final Color bleu = new Color(207, 231, 255);

    ColorPerso(int r, int g, int b) {
        super(r, g, b);
    }

}
