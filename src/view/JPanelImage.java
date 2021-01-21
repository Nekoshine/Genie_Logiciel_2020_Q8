package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JPanelImage extends JPanel {
    private BufferedImage img;
    private float ratio;
    private AffineTransform aTransform;

    JPanelImage(InputStream path, Dimension windowSize) {
        super();
        aTransform = new AffineTransform();
        try {
            img = ImageIO.read(path);
            img.getScaledInstance(windowSize.width,windowSize.height,Image.SCALE_FAST);
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
        //g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawRenderedImage(img,null);

    }

    public void setBackground(InputStream path){
        aTransform = new AffineTransform();
        try {
            img = ImageIO.read(path);
            ratio = (float)img.getWidth(null)/(float)img.getHeight(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setOpaque(true);
    }
}
