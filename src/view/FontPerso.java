package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontPerso {
    static Font ArialBold = new Font("Arial",Font.BOLD,15);
    static Font Arial = new Font("Arial",Font.PLAIN,15);
    static Font Oxanimum;

    static {
        try {
            Oxanimum = Font.createFont(Font.TRUETYPE_FONT, new File(".\\src\\view\\font\\Oxanium.ttf")).deriveFont(Font.PLAIN,15);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
