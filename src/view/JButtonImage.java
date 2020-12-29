package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JButtonImage extends JButton {
    private BufferedImage img;
    private float ratio;
    private AffineTransform aTransform;

    JButtonImage(String path) {
        super();
        aTransform = new AffineTransform();
        try {
            img = ImageIO.read(new File(path));
            ratio = (float)img.getWidth(null)/(float)img.getHeight(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (img == null) return;
        float largeur = (float)this.getWidth();
        aTransform.setToIdentity();
        aTransform.scale(largeur/(float)img.getWidth(null),largeur/(float)img.getWidth(null));
        this.setSize(Math.round(largeur),Math.round(largeur/ratio));
        //g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawRenderedImage(img,aTransform);

    }
}