package view.style;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.*;

public class ImagePerso{

    public static ImageIcon backgroundInscription;

    static{
        try{
            backgroundInscription = new ImageIcon(new ImageIcon("./src/view/image/FondInscription.png").getImage().getScaledInstance(720, 450, Image.SCALE_DEFAULT));


        }
        catch(ImagingOpException e){
            e.printStackTrace();
        }
    }

}
