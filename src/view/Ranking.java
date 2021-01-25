package view;

import database.DBScore;
import database.DBUser;
import launcher.Main;
import model.Game;
import model.Score;
import model.ScoreList;
import model.User;
import view.style.ColorPerso;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

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

    private Icon iconOr;
    private Icon iconArgent;
    private Icon iconBronze;

    private User user;

    public Ranking(Game game,User user){

        this.setSize(500,700);
        windowSize = this.getSize();

        this.user = user;

        InputStream isOr = Main.class.getResourceAsStream("/image/or.png");
        InputStream isArgent = Main.class.getResourceAsStream("/image/argent.png");
        InputStream isBronze = Main.class.getResourceAsStream("/image/bronze.png");

        BufferedImage or = null;
        BufferedImage argent = null;
        BufferedImage bronze = null;

        try {
            or = ImageIO.read(isOr);
            argent = ImageIO.read(isArgent);
            bronze = ImageIO.read(isBronze);

        } catch (IOException e) {
            e.printStackTrace();
        }

        or.getScaledInstance(10,10,Image.SCALE_DEFAULT);
        argent.getScaledInstance(10,10,Image.SCALE_DEFAULT);
        bronze.getScaledInstance(10,10,Image.SCALE_DEFAULT);

        iconOr = new ImageIcon(or);
        iconArgent = new ImageIcon(argent);
        iconBronze = new ImageIcon(bronze);

        scoreList = DBScore.getScoreFromGame(game.getId());

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

            JPanel panel = ajoutResultat(scoreList.getScore(i),gbc,i);
            if (i==0 || i==1 || i==2)
                panel.setPreferredSize(new Dimension((int) windowSize.getWidth()-85,75));
            else
                panel.setPreferredSize(new Dimension((int) windowSize.getWidth() - 85, 50));

            rankingPanel.add(panel,gbc);
            mainPanel.revalidate();
            mainPanel.repaint();

        }

        mainPanel.setLayout(mainLayout);
        mainPanel.add(scrollPane,BorderLayout.CENTER);
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

    public JPanel ajoutResultat(Score score,GridBagConstraints gbc,int i){

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = GridBagConstraints.REMAINDER;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(0,0,10,0);


        JPanel panelResultat = new JPanel();
        GridLayout grid = new GridLayout(1,3);

        JLabel placeLabel = new JLabel();

        if (i==0){
            placeLabel.setIcon(iconOr);
        }
        else if (i==1){
            placeLabel.setIcon(iconArgent);
        }
        else if (i==2){
            placeLabel.setIcon(iconBronze);
        }

        else{
            int rank = i+1;
            placeLabel.setText("<html><body><strong>" + rank +"</strong></body></html>");
        }

        placeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel userLabel = new JLabel(DBUser.getUser(score.getIdUser()).getLogin());
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel scoreLabel = new JLabel(score.getScore() + " pts");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        if (user !=null){
            if (DBUser.getUser(score.getIdUser()).getLogin().equals(user.getLogin())){
                panelResultat.setBackground(ColorPerso.bleu);
            }
        }

        panelResultat.setLayout(grid);
        panelResultat.add(placeLabel);
        panelResultat.add(userLabel);
        panelResultat.add(scoreLabel);

        panelResultat.setBorder(BorderFactory.createLineBorder(Color.black,2));
        panelResultat.setPreferredSize(new Dimension((int) windowSize.getWidth()-85,40));
        System.out.println(windowSize.getWidth());

        return panelResultat;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshButton){
            revalidate();
            repaint();
        }
    }
}
