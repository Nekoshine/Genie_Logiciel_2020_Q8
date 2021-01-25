package view.style;

import launcher.Main;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontPerso {
    public static Font ArialBold = new Font("Arial",Font.BOLD,15);
    public static Font Oxanimum;
    public static Font SirensDEMO;
    public static Font courierNew;
    public static Font lato;

    static {
        try {
            Oxanimum = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Oxanium.ttf")).deriveFont(Font.PLAIN,15);
            courierNew = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/cour.ttf")).deriveFont(Font.PLAIN,17);
            SirensDEMO = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/SirensDEMO.otf")).deriveFont(Font.PLAIN,17);
            lato = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Lato.ttf")).deriveFont(Font.PLAIN,17);
            Oxanimum = lato;
            courierNew = lato;

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
