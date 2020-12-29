package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

class FontPerso {
    static Font ArialBold = new Font("Arial",Font.BOLD,15);
    static Font Arial = new Font("Arial",Font.PLAIN,15);
    static Font Oxanimum;
    static Font SirensDEMO;

    static {
        try {
            Oxanimum = Font.createFont(Font.TRUETYPE_FONT, new File("./src/view/font/Oxanium.ttf")).deriveFont(Font.PLAIN,15);
            SirensDEMO = Font.createFont(Font.TRUETYPE_FONT, new File("./src/view/font/SirensDEMO.otf")).deriveFont(Font.PLAIN,15);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
