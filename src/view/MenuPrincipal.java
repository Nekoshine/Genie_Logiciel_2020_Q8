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
    public JPanel conteneurdeconnexion = new JPanel();
    public JPanel conteneurtitre = new JPanel();
    public JPanel conteneurmenus = new JPanel();
    public JButton deconnexion = new JButton("Deconnexion");
    public JButtonRedimension gestion = new JButtonRedimension("C:\\Users\\alans\\Pictures\\test\\gestion.png");
    public JButtonRedimension creation = new JButtonRedimension("C:\\Users\\alans\\Pictures\\test\\creation.png");

    public MenuPrincipal() {

        /*Création de la fenetre principale*/

        this.setLayout(new BorderLayout(30, 30));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        /*création du bouton deconnexion*/

        deconnexion.setBackground(Color.red);
        deconnexion.setForeground(Color.BLACK);
        deconnexion.setPreferredSize(new Dimension(150,60));

        /*création du conteneur pour le bouton deconnexion*/

        conteneurdeconnexion.setLayout(new FlowLayout(0));

        /*création du titre*/
        JLabel titre = new JLabel("ceci est le titre de ce test");

        /*création du conteneur pour le titre*/

        conteneurtitre.setLayout(new FlowLayout());
        conteneurtitre.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        /*création des boutons de choix de menu*/

        creation.setBackground(Color.BLACK);
        creation.setForeground(Color.WHITE);
        gestion.setBackground(Color.GRAY);
        gestion.setForeground(Color.WHITE);

        /*création du conteneur des menus*/

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
