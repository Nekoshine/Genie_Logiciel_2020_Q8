package view.style;

import launcher.Main;

import java.awt.*;
import java.io.IOException;

/**
*Définition de police personnel
 */
public final class FontPerso {
    public static Font ArialBold = new Font("Arial",Font.BOLD,15);
    public static Font SirensDEMO;
    public static Font courierNew;
    public static Font lato;

    static {
        try {
            courierNew = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/cour.ttf")).deriveFont(Font.PLAIN,17);
            SirensDEMO = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/SirensDEMO.otf")).deriveFont(Font.PLAIN,17);
            lato = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Lato.ttf")).deriveFont(Font.PLAIN,17);

        } catch (FontFormatException e) {
            System.out.println("FontPerso FontFormatException "+e.getMessage());
        } catch (IOException e) {
            System.out.println("FontPerso IOException "+e.getMessage());
        }
    }
}
