package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManagement extends JPanel implements ActionListener {
    public JPanel windowNamePanel = new JPanel();
    public JPanel gameListPanel = new JPanel();
    public JPanel buttonAddGamePanel = new JPanel();
    public JPanel buttonReturnPanel = new JPanel();

    public GameManagement(){

        JLabel windowName = new JLabel("MJ - Gestion des Jeux");
        windowNamePanel.add(windowName);
        windowNamePanel.setBackground(Color.white);

        gameListPanel.setLayout(new BoxLayout(gameListPanel, BoxLayout.PAGE_AXIS));

        int nbGames = 3; //fonction pour récupérer nombre de Jeux enregistrés dans BdD

        for(int i = 1; i<=nbGames; i++){
            JPanel gamePanel = new JPanel();
            JPanel gameNbPanel = new JPanel();
            JPanel gameTitlePanel = new JPanel();
            JPanel buttonChosePanel = new JPanel();
            JPanel buttonModifyPanel = new JPanel();
            JPanel buttonDeletePanel = new JPanel();

            JLabel gameNbLabel = new JLabel("Jeu " + i + " :");
            JLabel gameTitleLabel = new JLabel("Titre"); //fonction pour récupérer le titre du jeu i
            JButton buttonChose = new JButton("Choisir jeu " + i);
            buttonChose.setBackground(Color.green);
            buttonChose.setOpaque(true);
            JButton buttonModify = new JButton("Modifier jeu " + i);
            buttonModify.setBackground(Color.orange);
            buttonModify.setOpaque(true);
            JButton buttonDelete = new JButton("Supprimer jeu " + i);
            buttonDelete.setBackground(Color.red);
            buttonDelete.setOpaque(true);

            gameNbPanel.add(gameNbLabel, BorderLayout.CENTER);
            gameNbPanel.setBackground(Color.white);
            gameTitlePanel.add(gameTitleLabel, BorderLayout.CENTER);
            gameTitlePanel.setBackground(Color.white);
            buttonChosePanel.add(buttonChose, BorderLayout.CENTER);
            buttonChosePanel.setBackground(Color.white);
            buttonModifyPanel.add(buttonModify, BorderLayout.CENTER);
            buttonModifyPanel.setBackground(Color.white);
            buttonDeletePanel.add(buttonDelete, BorderLayout.CENTER);
            buttonDeletePanel.setBackground(Color.white);

            buttonChose.addActionListener(this);
            buttonModify.addActionListener(this);
            buttonDelete.addActionListener(this);

            gamePanel.setLayout(new BoxLayout(gamePanel,BoxLayout.LINE_AXIS));
            gamePanel.add(gameNbPanel);
            gamePanel.add(gameTitlePanel);
            gamePanel.add(buttonChosePanel);
            gamePanel.add(buttonModifyPanel);
            gamePanel.add(buttonDeletePanel);

            gameListPanel.add(gamePanel);
        }

        JButton buttonAddGame = new JButton("Créer un nouveau jeu");
        buttonAddGamePanel.add(buttonAddGame, BorderLayout.CENTER);
        buttonAddGamePanel.setBackground(Color.white);
        buttonAddGame.addActionListener(this);
        gameListPanel.add(buttonAddGamePanel);

        JButton buttonReturn = new JButton("Retour");
        buttonReturn.setBackground(Color.blue);
        buttonReturn.addActionListener(this);
        buttonReturnPanel.add(buttonReturn, BorderLayout.WEST);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(windowNamePanel);
        this.add(gameListPanel);
        this.add(buttonReturnPanel);
    }

    public void actionPerformed(ActionEvent e) {

    }
}