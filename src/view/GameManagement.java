/* Interface développée par Bastien Maubon */

package view;

import database.DBEnigma;
import database.DBGame;
import database.DBRoom;
import launcher.Main;
import model.EnigmaList;
import model.Game;
import model.GameList;
import model.Room;
import view.style.ColorPerso;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManagement extends JPanel implements ActionListener {

    public GameList ListGame;

    /*Final JPanel*/
    public JPanel windowNamePanel = new JPanel();
    public JPanel gameListPanel = new JPanel();
    public JPanel buttonAddGamePanel = new JPanel();
    public JPanel buttonReturnPanel = new JPanel();
    public JScrollPane scrollGameListPanel = new JScrollPane(gameListPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private GlobalFrame frame;
    private JButton buttonReturn;

    public JButton buttonChose;
    public JButton buttonDelete;
    public JButton buttonModify;
    public JButton buttonAddGame;


    public GameManagement(GlobalFrame frame, int roomNumber){
        this.frame = frame;
        frame.roomNumber = roomNumber;

        //recuperation des jeux du User
        this.ListGame= DBGame.getGames(Main.idUser);

        /*WindowNamePanel set up*/
        JLabel windowName = new JLabel("MJ - Gestion des Jeux");
        JPanel windowNameInsidePanel = new JPanel();
        windowNameInsidePanel.add(windowName);
        windowNameInsidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        windowNamePanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        windowNamePanel.setLayout(new BoxLayout(windowNamePanel,BoxLayout.LINE_AXIS));
        windowNamePanel.add(windowNameInsidePanel);

        scrollGameListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        scrollGameListPanel.getVerticalScrollBar().setUnitIncrement(10);

        gameListPanel.setLayout(new BoxLayout(gameListPanel, BoxLayout.PAGE_AXIS));

        int nbGames = ListGame.getSize(); //fonction pour récupérer nombre de Jeux enregistrés dans BdD

        for(int i = 0; i<nbGames; i++){

            final int y = i;
            JPanel gameInsidePanel = new JPanel();
            JPanel gameOutsidePanel = new JPanel();
            JPanel gameNbPanel = new JPanel();
            JPanel gameTitlePanel = new JPanel();

            gameInsidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            gameInsidePanel.setLayout(new BoxLayout(gameInsidePanel,BoxLayout.LINE_AXIS));

            gameOutsidePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            gameOutsidePanel.setLayout((new BoxLayout(gameOutsidePanel, BoxLayout.LINE_AXIS)));

            JLabel gameNbLabel = new JLabel("Jeu " +(i+1)+" :");
            JLabel gameTitleLabel = new JLabel(ListGame.getGame(i).getTitre()); //fonction pour récupérer le titre du jeu i

            gameNbPanel.add(gameNbLabel, BorderLayout.CENTER);
            gameTitlePanel.add(gameTitleLabel, BorderLayout.CENTER);


            gameInsidePanel.add(gameNbPanel);
            gameInsidePanel.add(gameTitlePanel);
            if(roomNumber==-1){
                JPanel buttonModifyPanel = new JPanel();
                JPanel buttonDeletePanel = new JPanel();

                buttonModify = new JButton("Modifier");
                buttonModify.setBackground(Color.orange);
                buttonModify.setOpaque(true);

                buttonDelete = new JButton("Supprimer");
                buttonDelete.setBackground(ColorPerso.rouge);
                buttonDelete.setForeground(Color.WHITE);
                buttonDelete.setOpaque(true);


                buttonModify.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Game jeuChoisi = ListGame.getGame(y);
                        //System.out.println(ListGame.getGame(y));
                        Main.ListEnigma=DBEnigma.getEnigmas(jeuChoisi.getId());
                        frame.gameCreationDisplay(frame,frame.roomNumber,jeuChoisi);
                    }
                });

                buttonDelete.addActionListener(this);

                buttonModifyPanel.add(buttonModify, BorderLayout.CENTER);
                buttonDeletePanel.add(buttonDelete, BorderLayout.CENTER);

                gameInsidePanel.add(buttonModifyPanel);
                gameInsidePanel.add(buttonDeletePanel);
            }else {
                JPanel buttonChosePanel = new JPanel();

                buttonChose = new JButton("Choisir");
                buttonChose.setBackground(ColorPerso.vert);
                buttonChose.setOpaque(true);

                buttonChose.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Main.ListRoom.findByID(roomNumber).setGame(ListGame.getGame(y));
                        frame.roomManagementDisplay(frame);

                        //si ca existe pas
                        boolean insert = DBRoom.insertRoom(Main.ListRoom.findByID(roomNumber).getId(), ListGame.getGame(y).getId());
                        if (insert == false){
                            DBRoom.majJeu(Main.ListRoom.findByID(roomNumber).getId(),ListGame.getGame(y).getId());
                        }
                    }
                });

                buttonChosePanel.add(buttonChose, BorderLayout.CENTER);

                gameInsidePanel.add(buttonChosePanel);
            }

            gameOutsidePanel.add(gameInsidePanel);

            gameListPanel.add(gameOutsidePanel);
        }

        buttonAddGame = new JButton("Créer un nouveau jeu");
        buttonAddGame.setOpaque(false);
        buttonAddGame.addActionListener(this);

        JPanel buttonAddGameInsidePanel = new JPanel();
        buttonAddGameInsidePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buttonAddGameInsidePanel.add(buttonAddGame, BorderLayout.WEST);
        buttonAddGamePanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        buttonAddGamePanel.setLayout(new BoxLayout(buttonAddGamePanel, BoxLayout.LINE_AXIS));
        buttonAddGamePanel.add(buttonAddGameInsidePanel);


        buttonReturn = new JButton("Retour");
        buttonReturn.addActionListener(this);
        buttonReturn.setBackground(ColorPerso.rouge);
        buttonReturn.setForeground(Color.white);
        buttonReturn.setOpaque(true);
        buttonReturn.addActionListener(this);
        buttonReturnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonReturnPanel.add(buttonReturn);


        Border mainEdge = BorderFactory.createEmptyBorder(10,10,10,10);
        this.setBorder(mainEdge);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(windowNamePanel);
        this.add(scrollGameListPanel);
        this.add(buttonAddGamePanel);
        this.add(buttonReturnPanel);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonReturn){
            frame.mainMenuDisplay(frame);
        }
        else if (e.getSource() == buttonAddGame){
            Main.ListEnigma= new EnigmaList();
            frame.gameCreationDisplay(frame,frame.roomNumber,null);
        }
        else if (e.getSource() == buttonChose){
            //int jeuChoisi=0;
            //DBRoom.insertRoom(frame.roomNumber,jeuChoisi);
            //ajout de la salle a la BDD
        }
    }
}