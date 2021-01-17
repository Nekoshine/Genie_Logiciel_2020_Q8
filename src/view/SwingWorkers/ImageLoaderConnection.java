package view.SwingWorkers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.Buffer;
import view.ConnectionMenu;

public class ImageLoaderConnection extends SwingWorker<BufferedImage,BufferedImage> {

    private ConnectionMenu panel;

    public ImageLoaderConnection(ConnectionMenu panel){
        this.panel = panel;
    }

    @Override
    protected BufferedImage doInBackground() throws Exception {
        File file = new File("./src/view/image/FondConnection2.png");
        InputStream is = new FileInputStream(file);
        BufferedImage backgroundConnexion = ImageIO.read(is);
        backgroundConnexion.getScaledInstance(720,450,Image.SCALE_FAST);
        backgroundConnexion.setAccelerationPriority(1);
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
