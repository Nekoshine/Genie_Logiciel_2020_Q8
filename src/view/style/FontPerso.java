package view.style;

import launcher.Main;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontPerso {
    public static Font ArialBold = new Font("Arial",Font.BOLD,15);
    public static Font Arial = new Font("Arial",Font.PLAIN,15);
    public static Font Oxanimum;
    public static Font SirensDEMO;

    static {
        try {
            Oxanimum = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Oxanium.ttf")).deriveFont(Font.PLAIN,15);
            SirensDEMO = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/SirensDEMO.otf")).deriveFont(Font.PLAIN,15);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
