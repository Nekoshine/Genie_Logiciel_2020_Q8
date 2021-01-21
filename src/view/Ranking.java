package view;

import database.DBUser;
import model.Score;
import model.ScoreList;
import view.style.ColorPerso;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ranking extends JFrame {

    private BorderLayout mainLayout;
    private GridBagLayout resultLayout;

    private GridBagConstraints gbc;

    private ScoreList scoreList;

    private JPanel mainPanel;
    private JPanel rankingPanel;
    private JPanel generalPanel;
    private JPanel closePanel;

    private JButton closeButton;

    public Ranking(){

        scoreList = new ScoreList();

        mainPanel = new JPanel();
        generalPanel = new JPanel();
        rankingPanel = new JPanel();
        closePanel = new JPanel();

        closeButton = new JButton("Fermer");
        closeButton.setBackground(ColorPerso.rouge);

        closePanel.add(closeButton);

        mainLayout = new BorderLayout();

        mainPanel.setLayout(mainLayout);
        mainPanel.add(rankingPanel,BorderLayout.CENTER);
        mainPanel.add(closePanel,BorderLayout.SOUTH);
        mainPanel.setSize(this.getWidth()-40,this.getHeight()-40);

        generalPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        generalPanel.setBackground(ColorPerso.DARK_GRAY);
        generalPanel.add(mainPanel);

        this.add(generalPanel);
        this.setSize(400,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);


    }

    void ajoutResultat(Score score,GridBagConstraints gbc){

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridy = score.getId() - 1;
        gbc.gridx = 0;

        JPanel panelResultat = new JPanel();
        GridLayout grid = new GridLayout(1,3);

        JLabel placeLabel = new JLabel(String.valueOf(score.getId()));
        placeLabel.setHorizontalAlignment(SwingConstants.CENTER);



        JLabel userLabel = new JLabel(DBUser.getUser(score.getIdUser()).getLogin());
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel scoreLabel = new JLabel(String.valueOf(score.getScore()));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);





    }




}
