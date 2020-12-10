/* Interface développée par Bastien Maubon */

package view;

import model.Game;
import model.DBGame;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static model.DBGame.getGames;

public class GameManagement extends JPanel implements ActionListener {
    /*Final JPanel*/
    public JPanel windowNamePanel = new JPanel();
    public JPanel gameListPanel = new JPanel();
    public JPanel buttonAddGamePanel = new JPanel();
    public JPanel buttonReturnPanel = new JPanel();
    public JScrollPane scrollGameListPanel = new JScrollPane(gameListPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private GlobalFrame frame;
    private JButton buttonReturn;

    private int roomNumber;

    public GameManagement(GlobalFrame frame, int roomNumber){

        this.frame = frame;
        this.roomNumber = roomNumber;

        /*WindowNamePanel set up*/
        JLabel windowName = new JLabel("MJ - Gestion des Jeux");
        windowName.setFont(FontPerso.ArialBold);
        JPanel windowNameInsidePanel = new JPanel();
        windowNameInsidePanel.add(windowName);
        windowNameInsidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        windowNamePanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        windowNamePanel.setLayout(new BoxLayout(windowNamePanel,BoxLayout.LINE_AXIS));
        windowNamePanel.add(windowNameInsidePanel);

        scrollGameListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        gameListPanel.setLayout(new BoxLayout(gameListPanel, BoxLayout.PAGE_AXIS));

        ArrayList<Game> games = getGames();


        int nbGames = games.size(); //fonction pour récupérer nombre de Jeux enregistrés dans BdD
        System.out.println("Le nombre de jeux est de " + nbGames);

        for(int i = 0; i<nbGames; i++){
            int j = i+1;
            JPanel gameInsidePanel = new JPanel();
            JPanel gameOutsidePanel = new JPanel();
            JPanel gameNbPanel = new JPanel();
            JPanel gameTitlePanel = new JPanel();
            JPanel buttonChosePanel = new JPanel();
            JPanel buttonModifyPanel = new JPanel();
            JPanel buttonDeletePanel = new JPanel();

            gameInsidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            gameInsidePanel.setLayout(new BoxLayout(gameInsidePanel,BoxLayout.LINE_AXIS));

            gameOutsidePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            gameOutsidePanel.setLayout((new BoxLayout(gameOutsidePanel, BoxLayout.LINE_AXIS)));

            Game game = games.get(i);
            String titre = game.getTitre();
            boolean isReady = game.getReady();

            JLabel gameNbLabel = new JLabel("Jeu " + j + " :");
            JLabel gameTitleLabel = new JLabel(titre); //fonction pour récupérer le titre du jeu i
            JButton buttonChose = new JButton("Choisir jeu " + j);
            //fonction jeu i ready ?
            if(isReady){
                buttonChose.setBackground(Color.green);
                buttonChose.setOpaque(true);
            }
            else{
                buttonChose.setBackground(Color.gray);
                buttonChose.setOpaque(true);
            }
            JButton buttonModify = new JButton("Modifier jeu " + j);
            buttonModify.setBackground(Color.orange);
            buttonModify.setOpaque(true);
            JButton buttonDelete = new JButton("Supprimer jeu " + j);
            buttonDelete.setBackground(ColorPerso.rouge);
            buttonDelete.setForeground(Color.WHITE);
            buttonDelete.setOpaque(true);

            gameNbPanel.add(gameNbLabel, BorderLayout.CENTER);
            gameTitlePanel.add(gameTitleLabel, BorderLayout.CENTER);
            buttonChosePanel.add(buttonChose, BorderLayout.CENTER);
            buttonModifyPanel.add(buttonModify, BorderLayout.CENTER);
            buttonDeletePanel.add(buttonDelete, BorderLayout.CENTER);

            buttonChose.addActionListener(this);
            buttonModify.addActionListener(this);
            buttonDelete.addActionListener(this);

            gameInsidePanel.add(gameNbPanel);
            gameInsidePanel.add(gameTitlePanel);
            if(roomNumber==-1){
                gameInsidePanel.add(buttonModifyPanel);
                gameInsidePanel.add(buttonDeletePanel);
            }else {
                gameInsidePanel.add(buttonChosePanel);
            }

            gameOutsidePanel.add(gameInsidePanel);

            gameListPanel.add(gameOutsidePanel);
        }

        JButton buttonAddGame = new JButton("Créer un nouveau jeu");
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
        if(roomNumber==-1) {
            this.add(buttonAddGamePanel);
        }
        this.add(buttonReturnPanel);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonReturn){
            if(roomNumber==-1) {
                frame.mainMenuDisplay(frame);
            }else{
                frame.roomManagementDisplay(frame);
            }
        }
    }
}