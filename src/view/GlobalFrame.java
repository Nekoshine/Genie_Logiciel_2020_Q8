package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GlobalFrame extends JFrame {

    public static Dimension windowSize;
    private BorderLayout mainLayout;



    public GlobalFrame() {

        this.setTitle("E-Scape Game");
        this.setVisible(true);


        RoomManagement menu = new RoomManagement();
        this.add(menu,mainLayout.CENTER);

        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        windowSize = new Dimension(854,480);
        this.setSize(windowSize);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                windowSize = getSize();
            }
        });*/




    }

    public static void main(String[] args) throws InterruptedException {
        GlobalFrame test = new GlobalFrame();

    }

}
