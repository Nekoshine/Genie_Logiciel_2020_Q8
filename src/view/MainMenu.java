//Codé par alan

package view;

import Sockets.Admin;
import database.DBRoom;
import launcher.Main;
import view.SwingWorkers.ImageLoaderMainMenu;
import view.style.ColorPerso;
import view.style.FontPerso;
import view.style.ImagePerso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MainMenu extends JPanel implements ActionListener, MouseListener {

    private JButton deconnection;
    public JButtonImage management;
    public JButtonImage creation;
    private static volatile MainMenu INSTANCE = new MainMenu(Main.frame);

    private Image backgroundLogo;

    private GlobalFrame frame;

    private MainMenu(GlobalFrame frame) {

        this.frame = frame;
        //this.mainMenu = this;

        new ImageLoaderMainMenu(this,frame.getSize()).execute();

        /*Création de la fenetre principale*/

        this.setLayout(new BorderLayout(30, 30));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        /*création du bouton deconnexion*/
        deconnection = new JButton("Deconnexion");
        deconnection.addActionListener(this);
        deconnection.addMouseListener(this);
        deconnection.setBackground(ColorPerso.rouge);
        deconnection.setForeground(Color.WHITE);
        deconnection.setPreferredSize(new Dimension(150,60));

        /*création du conteneur pour le bouton deconnexion*/
        JPanel deconnectionPanel = new JPanel();
        deconnectionPanel.setLayout(new FlowLayout(0));
        deconnectionPanel.setOpaque(false);

        /*création du titre*/
        JLabel titre = new JLabel("MJ - Menu Principal");
        titre.setFont(FontPerso.ArialBold);

        /*création du conteneur pour le titre*/
        JPanel titlecontainer = new JPanel();
        titlecontainer.setLayout(new FlowLayout());
        titlecontainer.setBorder(BorderFactory.createLineBorder(Color.black,2));

        /*création des boutons de choix de menu*/

        creation = new JButtonImage("./src/view/image/creation.png");
        creation.addActionListener(this);
        creation.addMouseListener(this);

        management = new JButtonImage("./src/view/image/gestion.png");
        management.addActionListener(this);
        management.addMouseListener(this);
        /*création du conteneur des menus*/
        JPanel menucontainer = new JPanel();
        menucontainer.setLayout(new GridLayout(1, 2, 30, 5));
        menucontainer.setBorder(BorderFactory.createEmptyBorder((int)((float) frame.getHeight()*0.20), 30, 30, 30));
        menucontainer.setOpaque(false);

        /*intégration*/

        deconnectionPanel.add(deconnection);
        titlecontainer.add(titre);
        menucontainer.add(creation);
        menucontainer.add(management);


        this.add("South", deconnectionPanel);
        this.add("North", titlecontainer);
        this.add("Center", new JSeparator());
        this.add("Center", menucontainer);
        this.setVisible(true);

    }

    public final static MainMenu getInstance(GlobalFrame frame) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (INSTANCE == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(INSTANCE) {
                if (INSTANCE == null) {
                    INSTANCE = new MainMenu(frame);
                }
            }
        }
        else {
            INSTANCE.frame=frame;
            new ImageLoaderMainMenu(INSTANCE,frame.getSize()).execute();
            INSTANCE.deconnection.setBackground(ColorPerso.rouge);
        }
        return INSTANCE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundLogo != null){
            System.out.println(frame.getWidth());
            g.drawImage(backgroundLogo,0,0,this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == management ){
            frame.roomManagementDisplay(frame);
        }
        else if(e.getSource() == creation){
            frame.gameManagementDisplay(frame, -1);

        }
        else if(e.getSource() == deconnection){
            frame.connectionMenuDisplay(frame);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == management ){
            management.setBackground("./src/view/image/gestionHoover.png");
        }
        else if(e.getSource() == creation){
            creation.setBackground("./src/view/image/creationHoover.png");
        }
        else if(e.getSource() == deconnection){
            deconnection.setBackground(ColorPerso.rougeHoover);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == management ){
            management.setBackground("./src/view/image/gestion.png");
        }
        else if(e.getSource() == creation){
            creation.setBackground("./src/view/image/creation.png");
        }
        else if(e.getSource() == deconnection){
            deconnection.setBackground(ColorPerso.rouge);
        }

    }

    public void imageLoaded(Image backgroundLogo) {
        this.backgroundLogo = backgroundLogo;
        repaint();
    }
}
