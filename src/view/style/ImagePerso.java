package view.style;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.*;

public class ImagePerso{

    public static BufferedImage backgroundConnexion;
    public static BufferedImage backgroundLogo;
    public static ImageIcon backgroundInscription;

    static{
        try{

            File file = new File("./src/view/image/FondConnection2.png");
            InputStream is = new FileInputStream(file);
            backgroundConnexion = ImageIO.read(is);
            backgroundConnexion.getScaledInstance(720,450,Image.SCALE_FAST);
            backgroundConnexion.setAccelerationPriority(1);


            file = new File("./src/view/image/FondPrincipal.png");
            is = new FileInputStream(file);
            backgroundLogo = ImageIO.read(is);
            backgroundLogo.setAccelerationPriority(1);



            backgroundInscription = new ImageIcon(new ImageIcon("./src/view/image/FondInscription.png").getImage().getScaledInstance(720, 450, Image.SCALE_DEFAULT));


        }

        catch(ImagingOpException | FileNotFoundException e){
            System.out.println("Problème d'image");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
