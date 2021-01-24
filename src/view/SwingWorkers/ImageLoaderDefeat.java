package view.SwingWorkers;

import launcher.Main;
import view.Defeatscreennocompetitive;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class ImageLoaderDefeat extends SwingWorker<Image,Image> {

    private Defeatscreennocompetitive panel;

    public ImageLoaderDefeat(Defeatscreennocompetitive panel){
        this.panel = panel;
    }

    @Override
    protected Image doInBackground() throws Exception {

        InputStream stream = Main.class.getResourceAsStream("/image/defaite.jpg");
        ImageIcon icon= null;
        Image backgroundMenu = null;

        try {
            icon = new ImageIcon(new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(800,800, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        backgroundMenu =  icon.getImage();

        return backgroundMenu;

    }

    @Override
    protected void done() {
        try {
            Image image = get();
            panel.imageLoaded(image);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
