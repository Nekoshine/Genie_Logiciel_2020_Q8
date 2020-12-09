package view;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

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
    GameList() throws IOException, FontFormatException {
        this.frame=new JFrame();

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
        String jeuDispo[] = {"Jeu 1 : Titre du jeu 1","Jeu 2 : LE TITRE", "Jeu 3 : éàè@êï"};
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

        globlaPanel.setLayout(new BorderLayout());
        globlaPanel.add(titlePanel, BorderLayout.NORTH);
        globlaPanel.add(scrollPane,BorderLayout.CENTER);
        globlaPanel.add(buttonPanel,BorderLayout.SOUTH);
        frame.add(globlaPanel);

        /* Mise en place du style */
        liste.setFont(font);
        liste.setBackground(ColorPerso.grisOriginal);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        returnButton.setBackground(new Color(180,40,20));
        returnButton.setForeground(Color.white);
        validateButton.setBackground(new Color(6, 153, 6));
        validateButton.setForeground(Color.white);

        /* Ajout de Listener */
        returnButton.addActionListener((ActionListener) this);
        returnButton.addMouseListener((MouseListener) this);
        validateButton.addMouseListener((MouseListener) this);
        validateButton.addActionListener((ActionListener) this);

        frame.setSize(300, 500);
        frame.setVisible(true);
    }

    // Driver  method
    public static void main(String[] args) throws IOException, FontFormatException {
        new GameList();
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

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == returnButton) {
            returnButton.setBackground(new Color(234, 12, 223));
        }
        else if(e.getSource() == validateButton) {
            validateButton.setBackground(new Color(12, 75, 234));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == returnButton) {
        returnButton.setBackground(new Color(180,40,20));
    }
        else if(e.getSource() == validateButton) {
        validateButton.setBackground(new Color(6, 153, 6));
    }
}

}