package view;

import database.DBUser;
import launcher.Main;
import model.Game;
import model.Score;
import model.ScoreList;
import view.style.ColorPerso;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class Ranking extends JFrame implements ActionListener{

    private BorderLayout mainLayout;
    private BorderLayout generalLayout;
    private GridBagLayout resultLayout;

    private GridBagConstraints gbc;

    private ScoreList scoreList;

    private JPanel mainPanel;
    private JPanel rankingPanel;
    private JPanel generalPanel;
    private JPanel refreshPanel;
    private JPanel titlePanel;

    private JLabel titleLabel;

    private JScrollPane scrollPane;
    private JButton refreshButton;

    private Dimension windowSize;

    public Ranking(Game game){

        this.setSize(500,700);
        windowSize = this.getSize();

        scoreList = new ScoreList();
        scoreList.addScore(new Score(1,2,10,20000));
        scoreList.addScore(new Score(2,2,6,1000));
        scoreList.addScore(new Score(3,2,4,200));
        scoreList.addScore(new Score(4,2,6,1000));
        scoreList.addScore(new Score(5,2,4,200));
        scoreList.addScore(new Score(6,2,6,1000));
        scoreList.addScore(new Score(7,2,4,200));
        scoreList.addScore(new Score(8,2,6,1000));
        scoreList.addScore(new Score(9,2,4,200));

        generalLayout = new BorderLayout(10,10);

        mainPanel = new JPanel();
        generalPanel = new JPanel();
        rankingPanel = new JPanel();
        refreshPanel = new JPanel();
        titlePanel = new JPanel();

        titleLabel = new JLabel("MJ - Classement : "+game.getTitre());
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));


        resultLayout = new GridBagLayout();

        gbc = new GridBagConstraints();

        scrollPane = new JScrollPane(rankingPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());



        InputStream is = Main.class.getResourceAsStream("/image/logo.png");
        try {
            Image logo = ImageIO.read(is);
            this.setIconImage(logo);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        refreshButton = new JButton("Actualiser");
        refreshButton.setBackground(ColorPerso.vert);

        refreshPanel.add(refreshButton);

        mainLayout = new BorderLayout(10,10);
        rankingPanel.setLayout(resultLayout);
        rankingPanel.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));

        for (int i = 0;i< scoreList.getSize();i++){

            JPanel panel = ajoutResultat(scoreList.getScore(i),gbc);
            panel.setPreferredSize(new Dimension((int) windowSize.getWidth()-85,50));
            rankingPanel.add(panel,gbc);
            mainPanel.revalidate();
            mainPanel.repaint();

        }

        mainPanel.setLayout(mainLayout);
        mainPanel.add(scrollPane,BorderLayout.CENTER);
        mainPanel.add(refreshPanel,BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        generalPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        generalPanel.setBackground(ColorPerso.DARK_GRAY);
        generalPanel.setLayout(generalLayout);
        generalPanel.add(titlePanel,BorderLayout.PAGE_START);
        generalPanel.add(mainPanel,BorderLayout.CENTER);

        this.add(generalPanel);
        this.setTitle("Classement");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);


    }

    public JPanel ajoutResultat(Score score,GridBagConstraints gbc){

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = GridBagConstraints.REMAINDER;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(0,0,10,0);


        JPanel panelResultat = new JPanel();
        GridLayout grid = new GridLayout(1,3);

        JLabel placeLabel = new JLabel(String.valueOf(score.getId()));
        placeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        placeLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel userLabel = new JLabel(DBUser.getUser(score.getIdUser()).getLogin());
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel scoreLabel = new JLabel(score.getScore() + " pts");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        panelResultat.setLayout(grid);
        panelResultat.add(placeLabel);
        panelResultat.add(userLabel);
        panelResultat.add(scoreLabel);

        panelResultat.setBorder(BorderFactory.createLineBorder(Color.black,2));
        panelResultat.setPreferredSize(new Dimension((int) windowSize.getWidth()-85,(int) (windowSize.getHeight()* 0.2)));
        System.out.println(windowSize.getWidth());

        return  panelResultat;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshButton){
            revalidate();
            repaint();
        }
    }
}
