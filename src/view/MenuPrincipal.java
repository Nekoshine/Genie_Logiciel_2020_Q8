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

public class MenuPrincipal extends JPanel implements ActionListener {
    private JPanel conteneurdeconnexion;
    private JPanel conteneurtitre;
    private JPanel conteneurmenus;

    private JButton deconnexion;
    private JButtonRedimension gestion;
    private JButtonRedimension creation;

    public MenuPrincipal() {

        /*Création de la fenetre principale*/

        this.setLayout(new BorderLayout(30, 30));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        /*création du bouton deconnexion*/
        deconnexion = new JButton("Deconnexion");
        deconnexion.setBackground(Color.red);
        deconnexion.setForeground(Color.BLACK);
        deconnexion.setPreferredSize(new Dimension(150,60));

        /*création du conteneur pour le bouton deconnexion*/
        conteneurdeconnexion = new JPanel();
        conteneurdeconnexion.setLayout(new FlowLayout(0));

        /*création du titre*/
        JLabel titre = new JLabel("MJ - Menu Principal");

        /*création du conteneur pour le titre*/
        conteneurtitre = new JPanel();
        conteneurtitre.setLayout(new FlowLayout());
        conteneurtitre.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        /*création des boutons de choix de menu*/

        creation = new JButtonRedimension(".\\src\\view\\image\\creation.png");

        gestion = new JButtonRedimension(".\\src\\view\\image\\gestion.png");

        /*création du conteneur des menus*/
        conteneurmenus = new JPanel();
        conteneurmenus.setLayout(new GridLayout(1, 2, 30, 5));
        conteneurmenus.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        /*intégration*/

        conteneurdeconnexion.add(deconnexion);
        conteneurtitre.add(titre);
        conteneurmenus.add(creation);
        conteneurmenus.add(gestion);

        this.add("South", conteneurdeconnexion);
        this.add("North", conteneurtitre);
        this.add("Center", new JSeparator());
        this.add("Center", conteneurmenus);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {}

    public class JButtonRedimension extends JButton { // !!! on doit étendre le composant dans lequel on veut insérer une image de fond

        private Image img;
        private String imageName;

        //Un constructeur pour choisir plus simplement l'image
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
