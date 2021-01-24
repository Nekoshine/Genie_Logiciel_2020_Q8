package view.SwingWorkers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

import launcher.Main;
import view.ConnectionMenu;
import view.GlobalFrame;
import view.MainMenu;

public class ImageLoaderMainMenu extends SwingWorker<Image,Image> {

    private MainMenu panel;
    private Dimension dimension;

    public ImageLoaderMainMenu(MainMenu panel,Dimension dimension){
        this.panel = panel;
        this.dimension = dimension;

    }

    @Override
    protected Image doInBackground() {

        InputStream stream = Main.class.getResourceAsStream("/image/FondPrincipal.png");
        ImageIcon icon= null;
        Image backgroundMenu = null;

        try {
            icon = new ImageIcon(new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance((int)dimension.getWidth(),(int)dimension.getHeight()-30, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        backgroundMenu =  icon.getImage();

        return backgroundMenu;

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