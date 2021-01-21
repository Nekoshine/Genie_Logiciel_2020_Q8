package view.SwingWorkers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import launcher.Main;
import view.MainMenu;

public class ImageLoaderMainMenu extends SwingWorker<Image,Image> {

    private MainMenu panel;
    private Dimension dimension;

    public ImageLoaderMainMenu(MainMenu panel,Dimension dimension){
        this.panel = panel;
        this.dimension = dimension;

    }

    @Override
    protected BufferedImage doInBackground() {
        InputStream is = Main.class.getResourceAsStream("/image/FondPrincipal.png");
        //ImageIcon imageIcon = new ImageIcon(new ImageIcon(is).getImage().getScaledInstance((int)dimension.getWidth(),(int)dimension.getHeight()-30, Image.SCALE_DEFAULT));
        BufferedImage backgroundConnexion = null;
        try {
            backgroundConnexion = ImageIO.read(is);
            backgroundConnexion.getScaledInstance((int)dimension.getWidth(),(int)dimension.getHeight()-30, Image.SCALE_DEFAULT);
            backgroundConnexion.setAccelerationPriority(1);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return backgroundConnexion;
    }

    @Override
    protected void done() {
        try{
            Image image = get();
            panel.imageLoaded(image);

        }
        catch (Exception e){

        };
    }

}
