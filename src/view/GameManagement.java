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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameManagement extends JPanel implements ActionListener, MouseListener {

    private GameList ListGame;


    /* Panel */
    private final JPanel listPanel;
    private final JPanel newButtonPanel;
    private final JPanel gamePanel;

    /* Boutons */
    private final JButton returnButton;
    private JButton newButton;

    private GlobalFrame frame;

    private static volatile GameManagement INSTANCE = new GameManagement(Main.frame,-1);

    private GameManagement(GlobalFrame frame, int roomNumber){

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

        if(frame.roomNumber==-1) {
            newButton = new JButton("Créer un nouveau jeu");
            newButton.setBackground(Color.GRAY);
            newButton.setOpaque(false);
            newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            newButton.addActionListener(this);
            newButton.addMouseListener(this);
        }

        this.createList();

        /* Setup Marges */
        Border mainPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border listPadding = BorderFactory.createEmptyBorder(20, 10, 10, 10);
        this.setBorder(mainPadding);
        listPanel.setBorder(listPadding);

        /* Setup listPanel */
        listPanel.setLayout(centerLayout);
        listPanel.add(scrollPane,BorderLayout.CENTER);
        listPanel.add(newButtonPanel, BorderLayout.PAGE_END);
        listPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));

        if(frame.roomNumber==-1) {
            newButtonPanel.add(newButton);
        }
        /* Setup Titre */
        JLabel titre = new JLabel("MJ - Gestion des Jeux");
        titre.setFont(FontPerso.ArialBold);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        titlePanel.add(titre);

        /* Setup bouton retour */
        returnPanel.setLayout(decoLayout);
        returnPanel.setBackground(ColorPerso.darkGray);
        returnPanel.add(returnButton);
        returnButton.addActionListener(this);
        returnButton.addMouseListener(this);

        this.setLayout(mainLayout);
        this.setBackground(ColorPerso.darkGray);
        this.add(listPanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.add(titlePanel, BorderLayout.PAGE_START);
        this.add(returnPanel, BorderLayout.PAGE_END);
        this.setVisible(true);


    }

    public static GameManagement getInstance(GlobalFrame frame, int roomNumber) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (INSTANCE == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(INSTANCE) {
                if (INSTANCE == null) {
                    INSTANCE = new GameManagement(frame, roomNumber);
                }
            }
        }
        else {
            INSTANCE.frame=frame;
            INSTANCE.frame.roomNumber=roomNumber;
            INSTANCE.ListGame= DBGame.getGames(Main.idUser);
            INSTANCE.createList();
            if(roomNumber==-1){
                INSTANCE.newButton.setVisible(true);
            }
            else{
                INSTANCE.newButton.setVisible(false);
            }
            INSTANCE.returnButton.setBackground(ColorPerso.rouge);
        }
        return INSTANCE;
    }

    private JPanel ajoutJeu(Game jeu, GridBagConstraints gbc, int i) {

        /* Contraintes GridBag */
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridy = jeu.getId() - 1;
        gbc.gridx = 0;

        /* Ajout Panel */
        JPanel panelJeu = new JPanel();

        GridLayout grille = new GridLayout(1,4,20,0);


        JLabel numJeu = new JLabel("Jeu  " + i + " :");
        numJeu.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nomJeu = new JLabel(jeu.getTitre());
        nomJeu.setHorizontalAlignment(SwingConstants.CENTER);

        panelJeu.add(numJeu);
        panelJeu.add(nomJeu);
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
            buttonModify.addMouseListener(new MouseListener() {
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
                    buttonModify.setBackground(ColorPerso.jauneHoover);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    buttonModify.setBackground(ColorPerso.jaune);
                }
            });

            JButton buttonDelete = new JButton("Supprimer");
            buttonDelete.setBackground(ColorPerso.rouge);
            buttonDelete.setForeground(Color.white);
            buttonDelete.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game jeuChoisi = ListGame.findByID(jeu.getId());
                    if(!DBGame.deleteGame(jeuChoisi.getId())){
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(frame, "Le jeu ne peut pas être supprimé car il est utilisé par une salle", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    Main.frame.setContentPane(getInstance(frame,frame.roomNumber));
                }
            });
            buttonDelete.addMouseListener(new MouseListener() {
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
                    buttonDelete.setBackground(ColorPerso.rougeHoover);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    buttonDelete.setBackground(ColorPerso.rouge);
                }
            });

            panelJeu.add(buttonModify);
            panelJeu.add(buttonDelete);
        }
        else {

            BorderLayout checkLayout = new BorderLayout();
            JButton boutonChoix = new JButton("Choisir");
            JPanel checkPanel = new JPanel();
            JCheckBox competitionCheck = new JCheckBox("Mode Compétitif");
            checkPanel.add(Box.createVerticalGlue());
            checkPanel.add(competitionCheck, BorderLayout.CENTER);
            checkPanel.add(Box.createVerticalGlue());

            boutonChoix.setBackground(ColorPerso.vert);
            boutonChoix.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(competitionCheck.isSelected());
                    Game oldGame = Main.ListRoom.findByID(frame.roomNumber).getGame();
                    int idOldGame=0;
                    if(oldGame!=null) {
                        //recuperation de l'ancien jeu si on ne créé pas une salle
                        idOldGame = oldGame.getId();
                    }

                    //mise a jour dans la liste
                    Main.ListRoom.findByID(frame.roomNumber).setGame(ListGame.findByID(jeu.getId()));

                    int idRoom = Main.ListRoom.findByID(frame.roomNumber).getId();

                    if(DBRoom.isInDB(idRoom,idOldGame)) {
                        DBRoom.majRoom(Main.ListRoom.findByID(frame.roomNumber).getId(),jeu.getId(),competitionCheck.isSelected());
                    }
                    else{
                        DBRoom.insertRoom(Main.ListRoom.findByID(frame.roomNumber).getId(), jeu.getId(),competitionCheck.isSelected());
                    }
                    frame.roomManagementDisplay(frame);

                }
            });
            boutonChoix.addMouseListener(new MouseListener() {
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
                    boutonChoix.setBackground(ColorPerso.vertHoover);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    boutonChoix.setBackground(ColorPerso.vert);
                }
            });
            panelJeu.add(boutonChoix);
            panelJeu.add(checkPanel);

        }
        panelJeu.setLayout(grille);

        /* Configuration panelSalle */
        panelJeu.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

        return panelJeu;

    }

    private void createList() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7,15,7,30);
        int nbGames = ListGame.getSize();
        gamePanel.removeAll();

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
        if (e.getSource()==returnButton) {
            returnButton.setBackground(ColorPerso.rougeHoover);
        }
        else if(e.getSource()==newButton){
            newButton.setBackground(Color.BLACK);
            newButton.setOpaque(true);
            newButton.setForeground(Color.white);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==returnButton) {
            returnButton.setBackground(ColorPerso.rouge);
        }
        else if(e.getSource()==newButton){
            newButton.setOpaque(false);
            newButton.setForeground(Color.black);
        }
    }
}
