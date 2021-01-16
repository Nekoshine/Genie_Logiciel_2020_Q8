package view.style;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImagingOpException;

public class ImagePerso{

    public static Image backgroundMenu;

    static{
        try{

            ImageIcon image = new ImageIcon(new ImageIcon("./src/view/image/FondConnection.png").getImage().getScaledInstance(720, 450, Image.SCALE_DEFAULT));
            backgroundMenu = image.getImage();

        }

        catch(ImagingOpException e){
            System.out.println("Problème d'image");
        }
    }


}
