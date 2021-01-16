//Codé par alan

package view;

import Sockets.Admin;
import view.style.ColorPerso;
import view.style.FontPerso;
import view.style.ImagePerso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {

    private JButton deconnection;
    private JButton management;
    private JButton creation;

    private Image image;

    private GlobalFrame frame;

    MainMenu(GlobalFrame frame) {

        this.frame = frame;

        /*Création de la fenetre principale*/

        this.setLayout(new BorderLayout(30, 30));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        /*création du bouton deconnexion*/
        deconnection = new JButton("Deconnexion");
        deconnection.addActionListener(this);
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

        management = new JButtonImage("./src/view/image/gestion.png");
        management.addActionListener(this);
        /*création du conteneur des menus*/
        JPanel menucontainer = new JPanel();
        menucontainer.setLayout(new GridLayout(1, 2, 30, 5));
        menucontainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        menucontainer.setOpaque(false);

        /*intégration*/

        deconnectionPanel.add(deconnection);
        titlecontainer.add(titre);
        menucontainer.add(creation);
        menucontainer.add(management);

        image = ImagePerso.backgroundLogo.getScaledInstance(frame.getWidth(),frame.getHeight(),Image.SCALE_FAST);

        this.add("South", deconnectionPanel);
        this.add("North", titlecontainer);
        this.add("Center", new JSeparator());
        this.add("Center", menucontainer);
        this.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image,0,0,this);
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

}
