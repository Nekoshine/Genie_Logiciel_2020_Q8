package view.style;

import launcher.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

public class ImagePerso{

    public static BufferedImage backgroundInscription;

    static{
        try{
            //backgroundInscription = new ImageIcon(new ImageIcon("res/image/FondInscription.png").getImage()
            InputStream is = Main.class.getResourceAsStream("/res/image/FondInscription.png");
            BufferedImage image = ImageIO.read(is);
            image.getScaledInstance(720, 450, Image.SCALE_DEFAULT);
            backgroundInscription = image;


        }
        catch(ImagingOpException | IOException e){
            e.printStackTrace();
        }
    }

}
