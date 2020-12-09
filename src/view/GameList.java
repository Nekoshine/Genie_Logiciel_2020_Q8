/* Interface développée par Yann Dauvin */

package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameList implements ActionListener, MouseListener {

    JFrame frame;
    JPanel listPanel;
    JPanel titlePanel;
    JPanel buttonPanel;
    JPanel globlaPanel;
    JScrollPane scrollPane;
    JButton returnButton;
    JButton validateButton;
    JList liste;

    // Constructor
    GameList(String[] jeuDispo) throws IOException, FontFormatException {
        //UIManager.put("Button.background",Color.PINK);
        this.frame=new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /* Création des fonts */
        File fichierfont = new File(".\\src\\view\\font\\ABEAKRG.TTF");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fichierfont).deriveFont(0,15);

        /* Mise en place de la fenetre */
        File fichier = new File(".\\src\\view\\image\\logo.png");
        Image logo = ImageIO.read(fichier);
        frame.setIconImage(logo);
        frame.setTitle("E-Scape Game");
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(300,500));
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /* Création des Panel*/
        titlePanel = new JPanel();
        globlaPanel = new JPanel();
        buttonPanel = new JPanel();
        listPanel = new JPanel();

        /* Mise en place du titre*/
        JLabel titre = new JLabel("Liste des jeux");

        /*Création de la liste des jeu*/
        liste=new JList();
        liste = new JList(jeuDispo);

        /*Création de bouton */
        returnButton = new JButton("Retour");
        validateButton = new JButton("Valider");

        /*Ajout des elements aux differents Panel*/
        buttonPanel.add(returnButton);
        buttonPanel.add(validateButton);
        titlePanel.add(titre);
        listPanel.add(liste);

        scrollPane =new JScrollPane(listPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);


        globlaPanel.setLayout(new BorderLayout(10,10));
        globlaPanel.add(titlePanel, BorderLayout.NORTH);
        globlaPanel.add(scrollPane,BorderLayout.CENTER);
        globlaPanel.add(buttonPanel,BorderLayout.SOUTH);
        frame.add(globlaPanel);

        /* Mise en place du style */
        liste.setFont(font);
        liste.setBackground(ColorPerso.grisOriginal);
        liste.setFixedCellHeight(30);
        liste.setFixedCellWidth(250);
        titre.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black,2), new EmptyBorder(3,80,3,80)));
        returnButton.setBackground(ColorPerso.retour);
        returnButton.setForeground(Color.white);
        validateButton.setBackground(ColorPerso.lancement);
        validateButton.setForeground(Color.white);

        /* Ajout de Listener */
        returnButton.addActionListener((ActionListener) this);
        returnButton.addMouseListener((MouseListener) this);
        validateButton.addMouseListener((MouseListener) this);
        validateButton.addActionListener((ActionListener) this);
        liste.addMouseListener(this);

        frame.setSize(300, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // Driver  method
    public static void main(String[] args) throws IOException, FontFormatException {
        String jeuDispo[] = {
                "Jeu 1 : Titre du jeu 1",
                "Jeu 2 : LE TITRE",
                "Jeu 3 : éàè@êï",
                "Jeu 4 : Titre",
                "Jeu 5 : Titre",
                "Jeu 6 : Titre",
                "Jeu 7 : Titre",
                "Jeu 8 : Titre",
                "Jeu 9 : Titre",
                "Jeu 10 : Titre",
                "Jeu 11 : Titre",
                "Jeu 12 : Titre",
                "Jeu 13 : Titre",
                "Jeu 14 : Titre",
                "Jeu 15 : Titre",
                "Jeu 16 : Titre",
                "Jeu 17 : Titre",
                "Jeu 18 : Titre"
        };
        new GameList(jeuDispo);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == returnButton){
            System.out.println("retour");
            frame.dispose();
        }
        else if(e.getSource() == validateButton) {
            System.out.println((String)liste.getSelectedValue());
            frame.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==liste){
            liste.setSelectionBackground(new Color(5, 111, 127));
            liste.setSelectionForeground(Color.WHITE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==liste){
            liste.setSelectionBackground(new Color(5, 111, 127, 128));
            liste.setSelectionForeground(Color.BLACK);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()==liste){
            liste.setSelectionBackground(new Color(5, 111, 127));
            liste.setSelectionForeground(Color.WHITE);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == returnButton) {
            returnButton.setBackground(new Color(212, 99, 104));
            returnButton.setForeground(Color.BLACK);
        }
        else if(e.getSource() == validateButton) {
            validateButton.setBackground(new Color(137, 204, 98));
            validateButton.setForeground(Color.BLACK);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == returnButton) {
        returnButton.setBackground(ColorPerso.retour);
        returnButton.setForeground(Color.WHITE);
    }
        else if(e.getSource() == validateButton) {
        validateButton.setBackground(ColorPerso.lancement);
        validateButton.setForeground(Color.WHITE);
    }
}

}