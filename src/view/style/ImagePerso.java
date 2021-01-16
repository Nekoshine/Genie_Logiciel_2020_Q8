package view.style;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImagingOpException;

public class ImagePerso{

    public static Image backgroundConnexion;
    public static Image backgroundLogo;
    public static Image backgroundInscription;

    static{
        try{

            ImageIcon image = new ImageIcon(new ImageIcon("./src/view/image/FondConnection2.png").getImage().getScaledInstance(720, 450, Image.SCALE_DEFAULT));
            backgroundConnexion = image.getImage();

            backgroundLogo = (new ImageIcon("./src/view/image/FondPrincipal.png").getImage().getScaledInstance(1280,690,Image.SCALE_DEFAULT));

            image = new ImageIcon(new ImageIcon("./src/view/image/FondInscription.png").getImage().getScaledInstance(720, 450, Image.SCALE_DEFAULT));
            backgroundInscription = image.getImage();

        }

        catch(ImagingOpException e){
            System.out.println("Problème d'image");
        }
    }


}
