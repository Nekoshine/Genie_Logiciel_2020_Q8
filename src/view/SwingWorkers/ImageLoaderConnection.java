package view.SwingWorkers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import launcher.Main;
import view.ConnectionMenu;

public class ImageLoaderConnection extends SwingWorker<BufferedImage,BufferedImage> {

    private ConnectionMenu panel;

    public ImageLoaderConnection(ConnectionMenu panel){
        this.panel = panel;
    }

    @Override
    protected BufferedImage doInBackground() throws URISyntaxException {
        InputStream is  = Main.class.getResourceAsStream("/res/image/FondConnection2.png");
        BufferedImage backgroundConnexion = null;
        try {
            backgroundConnexion = ImageIO.read(is);
            backgroundConnexion.getScaledInstance(720, 450, Image.SCALE_FAST);
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
            BufferedImage image = get();
            panel.imageLoaded(image);
        }
        catch (Exception e){

        };
    }

}
