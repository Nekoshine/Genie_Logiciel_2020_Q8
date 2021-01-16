package view;

import database.DBEnigma;
import database.DBGame;
import database.DBRoom;
import launcher.Main;
import model.EnigmaList;
import model.Game;
import model.GameList;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GameManagement extends JPanel implements ActionListener {

    private final GameList ListGame;


    /* Panel */
    private JPanel listPanel;
    private JPanel newButtonPanel;
    private JPanel gamePanel;

    /* Boutons */
    private JButton returnButton;
    private JButton newButton;

    private GlobalFrame frame;

    GameManagement(GlobalFrame frame, int roomNumber){

        this.frame = frame;

        /* Provenance */
        frame.roomNumber = roomNumber;

        /* Recuperation des jeux du User */
        this.ListGame= DBGame.getGames(Main.idUser);

        /* Déclaration JPanel - JScrollPane */
        listPanel = new JPanel();
        gamePanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel returnPanel = new JPanel();
        newButtonPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(gamePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        /* Déclaration Layouts */
        BorderLayout mainLayout = new BorderLayout(10, 10);
        BorderLayout centerLayout = new BorderLayout(4, 4);
        FlowLayout decoLayout = new FlowLayout(FlowLayout.LEADING);
        GridBagLayout listLayout = new GridBagLayout();
        gamePanel.setLayout(listLayout);

        /* Contraintes GridBag */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);

        /* Déclaration Boutons */
        returnButton = new JButton("Retour");
        returnButton.setBackground(ColorPerso.rouge);
        returnButton.setForeground(Color.white);

        newButton = new JButton("Créer un nouveau jeu");
        newButton.setBackground(Color.GRAY);
        newButton.setOpaque(false);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newButton.addActionListener(this);

        /* Affichage des salles */
        this.CreateList();

        /* Setup Marges */
        Border mainPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border listPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(mainPadding);
        listPanel.setBorder(listPadding);

        /* Setup listPanel */
        listPanel.setLayout(centerLayout);
        listPanel.add(scrollPane,BorderLayout.CENTER);
        listPanel.add(newButtonPanel, BorderLayout.PAGE_END);
        listPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));

        newButtonPanel.add(newButton);

        /* Setup Titre */
        JLabel titre = new JLabel("MJ - Gestion des Jeux");
        titre.setFont(FontPerso.ArialBold);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        titlePanel.add(titre);

        /* Setup bouton retour */
        returnPanel.setLayout(decoLayout);
        returnPanel.setBackground(ColorPerso.gris);
        returnPanel.add(returnButton);
        returnButton.addActionListener(this);

        /* Setup Fenêtre gestion des salles */
        this.setLayout(mainLayout);
        this.setBackground(ColorPerso.gris);
        this.add(listPanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(returnPanel, BorderLayout.PAGE_END);
        this.setVisible(true);


    }

    private JPanel ajoutJeu(Game jeu, GridBagConstraints gbc, int i) {

        /* Contraintes GridBag */
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridy = jeu.getId() - 1;
        gbc.gridx = 0;

        /* Ajout Panel */
        JPanel panelSalle = new JPanel();

        /* Construction Panel Salle */
        GridLayout grille = new GridLayout(1,4,20,0);


        JLabel nomSalle = new JLabel("Jeu  " + i + " :");
        nomSalle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nomJeu = new JLabel(jeu.getTitre());
        nomJeu.setHorizontalAlignment(SwingConstants.CENTER);

        panelSalle.add(nomSalle);
        panelSalle.add(nomJeu);
        if(frame.roomNumber==-1) {
            JButton buttonModify = new JButton("Modifier");
            buttonModify.setBackground(ColorPerso.jaune);
            buttonModify.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game jeuChoisi = ListGame.findByID(jeu.getId());
                    Main.ListEnigma= DBEnigma.getEnigmas(jeuChoisi.getId());
                    frame.gameCreationDisplay(frame,frame.roomNumber,jeuChoisi);
                }
            });

            JButton buttonDelete = new JButton("Supprimer");
            buttonDelete.setBackground(ColorPerso.rouge);
            buttonDelete.setForeground(Color.white);
            buttonDelete.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game jeuChoisi = ListGame.findByID(jeu.getId());
                    DBGame.deleteGame(jeuChoisi.getId());
                }
            });

            panelSalle.add(buttonModify);
            panelSalle.add(buttonDelete);
        }
        else {

            JButton boutonChoix = new JButton("Choisir");
            boutonChoix.setBackground(ColorPerso.vert);
            boutonChoix.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.ListRoom.findByID(frame.roomNumber).setGame(ListGame.findByID(jeu.getId()));
                    frame.roomManagementDisplay(frame);

                    int idRoom = Main.ListRoom.findByID(frame.roomNumber).getId();
                    int idGame =jeu.getId();
                    if(DBRoom.isInDB(idRoom,idGame)) {
                        DBRoom.insertRoom(Main.ListRoom.findByID(frame.roomNumber).getId(), jeu.getId());
                    }
                    else{
                        DBRoom.majGame(Main.ListRoom.findByID(frame.roomNumber).getId(),jeu.getId());

                    }

                }
            });
            panelSalle.add(boutonChoix);

        }
        panelSalle.setLayout(grille);



        /* Configuration panelSalle */
        panelSalle.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

        return panelSalle;

    }

    private void CreateList() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);
        int nbGames = ListGame.getSize();
        for(int i = 0; i<nbGames; i++){
            listPanel.remove(newButtonPanel);
            JPanel panelGame = this.ajoutJeu(ListGame.getGame(i), gbc,i+1);
            panelGame.setPreferredSize(new Dimension(listPanel.getWidth() - 45, 75));
            gamePanel.add(panelGame, gbc);
            listPanel.add(newButtonPanel, BorderLayout.PAGE_END);
            listPanel.revalidate();
            listPanel.repaint();
        }

        listPanel.revalidate();
        listPanel.repaint();
        frame.repaint();

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton){
            if (frame.roomNumber==-1){
                frame.mainMenuDisplay(frame);
            }
            else {
                frame.roomManagementDisplay(frame);
            }
        }
        else if (e.getSource() == newButton){
            Main.ListEnigma= new EnigmaList();
            frame.gameCreationDisplay(frame,frame.roomNumber,null);
        }
    }
}
