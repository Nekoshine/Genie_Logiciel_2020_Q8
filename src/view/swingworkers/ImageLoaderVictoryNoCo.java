package view.swingworkers;

import launcher.Main;
import view.VictoryScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * Chargement de l'image de fond pour l'interface de victoire
 */
public final class ImageLoaderVictoryNoCo extends SwingWorker<Image,Image> {

    private VictoryScreen panel;

    public ImageLoaderVictoryNoCo(VictoryScreen panel){
        this.panel = panel;
    }

    @Override
    protected Image doInBackground() {

        InputStream stream = Main.class.getResourceAsStream("/image/victoire.png");
        ImageIcon icon= null;

        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(stream));
            icon = new ImageIcon(imageIcon.getImage().getScaledInstance(900,600, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return icon.getImage();

    }

    @Override
    protected void done() {
        try {
            Image image = this.get();
            this.panel.imageLoaded(image);

        } catch (InterruptedException e) {
            System.out.println("ImageLoaderDefeat InterruptedException"+ e.getMessage());
        } catch (ExecutionException e) {
            System.out.println("ImageLoaderDefeat ExecutionException"+ e.getMessage());
        }

    }
}
