package view.swingworkers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import launcher.Main;
import view.SignupMenu;

/**
 * Chargement de l'image de fond pour l'interface d'inscription
 */
public final class ImageLoaderSignup extends SwingWorker<BufferedImage,BufferedImage> {

    private SignupMenu panel;

    public ImageLoaderSignup(SignupMenu panel){
        this.panel = panel;
    }

    @Override
    protected BufferedImage doInBackground(){
        InputStream is  = Main.class.getResourceAsStream("/image/FondInscription.png");
        BufferedImage backgroundSignUp = null;
        try {
            backgroundSignUp = ImageIO.read(is);
            backgroundSignUp.getScaledInstance(720, 450, Image.SCALE_FAST);
            backgroundSignUp.setAccelerationPriority(1);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return backgroundSignUp;
    }

    @Override
    protected void done() {
        try{
            BufferedImage image = this.get();
            this.panel.imageLoaded(image);
        } catch (ExecutionException e) {
            System.out.println("ImageLoaderConnection ExecutionException"+ e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("ImageLoaderConnection InterruptedException"+ e.getMessage());
        }
    }

}
