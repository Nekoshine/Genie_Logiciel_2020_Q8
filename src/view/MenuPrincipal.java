package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class MenuPrincipal extends JFrame implements ActionListener {
    public MenuPrincipal() {
        super("menu principal");
        this.setLayout(new BorderLayout());

        /*Création de la fenetre principale*/
        JPanel principal = new JPanel();
        principal.setLayout(new BorderLayout(30, 30));
        principal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        /*création du bouton deconnexion*/
        JButton deconnexion = new JButton("Deconnexion");
        deconnexion.setBackground(Color.red);
        deconnexion.setForeground(Color.BLACK);

        /*création du conteneur pour le bouton deconnexion*/
        JPanel conteneurdeconnexion = new JPanel();
        conteneurdeconnexion.setLayout(new FlowLayout(0));

        /*création du titre*/
        JLabel titre = new JLabel("ceci est le titre de ce test");

        /*création du conteneur pour le titre*/

        JPanel conteneurtitre = new JPanel();
        conteneurtitre.setLayout(new FlowLayout());
        conteneurtitre.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        /*création des boutons de choix de menu*/
        JButton creation = new JButton("Création de jeux");
        creation.setBackground(Color.BLACK);
        creation.setForeground(Color.WHITE);
        JButton gestion = new JButton("Gestion des salles");
        gestion.setBackground(Color.GRAY);
        creation.setForeground(Color.WHITE);

        /*création du conteneur des menus*/

        JPanel conteneurmenus = new JPanel();
        conteneurmenus.setLayout(new GridLayout(1, 2, 30, 5));
        conteneurmenus.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        /*intégration*/

        conteneurdeconnexion.add(deconnexion);
        conteneurtitre.add(titre);
        conteneurmenus.add(creation);
        conteneurmenus.add(gestion);

        principal.add("South", conteneurdeconnexion);
        principal.add("North", conteneurtitre);
        principal.add("Center", new JSeparator());
        principal.add("Center", conteneurmenus);

        this.add("Center", principal);
        this.setDefaultCloseOperation(3);
        this.setMinimumSize(new Dimension(800, 400));
        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
