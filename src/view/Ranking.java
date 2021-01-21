package view;

import database.DBUser;
import model.Score;
import model.ScoreList;
import view.style.ColorPerso;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
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

        File fichier = new File("./res/image/logo.png");
        try {
            Image logo = ImageIO.read(fichier);
            this.setIconImage(logo);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        closeButton = new JButton("Fermer");
        closeButton.setBackground(ColorPerso.rouge);

        closePanel.add(closeButton);

        mainLayout = new BorderLayout();

        for (int i = 0;i< scoreList.getSize();i++){

            JPanel panel = ajoutResultat(scoreList.getScore(i),gbc);
            rankingPanel.add(panel,gbc);
            mainPanel.revalidate();
            mainPanel.repaint();

        }

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
        this.setResizable(false);
        this.setVisible(true);


    }

    public JPanel ajoutResultat(Score score,GridBagConstraints gbc){

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridy = score.getId() - 1;
        gbc.gridx = 0;

        JPanel panelResultat = new JPanel();
        GridLayout grid = new GridLayout(1,3);

        JLabel placeLabel = new JLabel(String.valueOf(score.getId()));
        placeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        placeLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel userLabel = new JLabel(DBUser.getUser(score.getIdUser()).getLogin());
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel scoreLabel = new JLabel(String.valueOf(score.getScore()));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        panelResultat.setLayout(grid);
        panelResultat.add(placeLabel);
        panelResultat.add(userLabel);
        panelResultat.add(scoreLabel);

        panelResultat.setBorder(BorderFactory.createLineBorder(Color.black,2));

        return  panelResultat;

    }




}
