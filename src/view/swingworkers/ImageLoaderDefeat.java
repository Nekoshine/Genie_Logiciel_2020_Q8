package view.swingworkers;

import launcher.Main;
import view.DefeatScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * Chargement de l'image de fond pour l'interface de defaite
 */
public final class ImageLoaderDefeat extends SwingWorker<Image,Image> {

    private DefeatScreen panel;

    public ImageLoaderDefeat(DefeatScreen panel){
        this.panel = panel;
    }

    @Override
    protected Image doInBackground() {

        InputStream stream = Main.class.getResourceAsStream("/image/defaite.jpg");
        ImageIcon icon= null;

        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(stream));
            icon = new ImageIcon(imageIcon.getImage().getScaledInstance(800,800, Image.SCALE_DEFAULT));
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
