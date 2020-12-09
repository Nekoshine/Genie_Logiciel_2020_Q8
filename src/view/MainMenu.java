//Codé par alan

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class MainMenu extends JPanel implements ActionListener {
    private JPanel deconnectioncontainer;
    private JPanel titlecontainer;
    private JPanel menucontainer;

    private JButton deconnection;
    private JButtonRedimension management;
    private JButtonRedimension creation;

    private GlobalFrame frame;

    public MainMenu(GlobalFrame frame) {

        this.frame = frame;

        /*Création de la fenetre principale*/

        this.setLayout(new BorderLayout(30, 30));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        /*création du bouton deconnexion*/
        deconnection = new JButton("Deconnexion");
        deconnection.setBackground(Color.red);
        deconnection.setForeground(Color.BLACK);
        deconnection.setPreferredSize(new Dimension(150,60));

        /*création du conteneur pour le bouton deconnexion*/
        deconnectioncontainer = new JPanel();
        deconnectioncontainer.setLayout(new FlowLayout(0));

        /*création du titre*/
        JLabel titre = new JLabel("MJ - Menu Principal");

        /*création du conteneur pour le titre*/
        titlecontainer = new JPanel();
        titlecontainer.setLayout(new FlowLayout());
        titlecontainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        /*création des boutons de choix de menu*/

        creation = new JButtonRedimension(".\\src\\view\\image\\creation.png");

        management = new JButtonRedimension(".\\src\\view\\image\\gestion.png");

        /*création du conteneur des menus*/
        menucontainer = new JPanel();
        menucontainer.setLayout(new GridLayout(1, 2, 30, 5));
        menucontainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        /*intégration*/

        deconnectioncontainer.add(deconnection);
        titlecontainer.add(titre);
        menucontainer.add(creation);
        menucontainer.add(management);

        this.add("South", deconnectioncontainer);
        this.add("North", titlecontainer);
        this.add("Center", new JSeparator());
        this.add("Center", menucontainer);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == management ){
            frame.roomManagementDisplay(frame);
        }
        else if(event.getSource() == creation){

        }
        else if(event.getSource() == deconnection){
            frame.ConnectionMenuDisplay(frame);
        }
    }

    public class JButtonRedimension extends JButton {

        private Image img;
        private String imageName;


        public JButtonRedimension(String path) {
            img = new ImageIcon(path).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img == null) return;
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

        }
    }

}
