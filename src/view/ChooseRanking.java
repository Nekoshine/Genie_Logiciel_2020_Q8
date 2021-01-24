package view;

import database.DBUser;
import launcher.Main;
import model.Game;
import model.RoomList;
import model.User;
import view.style.ColorPerso;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class ChooseRanking extends JFrame {

    private JPanel generalPan;
    private JPanel mainPanel;
    private JPanel titlePanel;

    private JLabel titleLabel;
    private JScrollPane scrollPane;
    private GridBagLayout gameLayout;
    private GridBagConstraints gbc;

    private User user;


    public ChooseRanking(RoomList roomList, User user){

        this.user = user;

        BorderLayout mainLayout = new BorderLayout(10,10);
        generalPan = new JPanel();
        mainPanel = new JPanel();
        titlePanel = new JPanel();

        titleLabel = new JLabel("Choix du jeu");

        scrollPane = new JScrollPane(mainPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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

        titlePanel.setLayout(new FlowLayout(1));
        titlePanel.add(titleLabel);
        titleLabel.setBackground(ColorPerso.grisOriginal);
        titleLabel.setAlignmentY(SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(360,25));
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);


        gbc = new GridBagConstraints();
        gameLayout = new GridBagLayout();

        gbc.insets = new Insets(5,5,10,10);

        mainPanel.setLayout(gameLayout);
        mainPanel.setBackground(Color.lightGray);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));



        for (int i=0;i< roomList.getSize();i++){

            JPanel panel = ajoutJeu(roomList.getRoom(i).getGame(),gbc);
            mainPanel.add(panel,gbc);
            mainPanel.revalidate();
            mainPanel.repaint();

        }

        generalPan.setLayout(mainLayout);
        generalPan.add(titleLabel,BorderLayout.PAGE_START);
        generalPan.add(mainPanel, BorderLayout.CENTER);
        generalPan.setBackground(ColorPerso.darkGray);
        generalPan.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        this.add(generalPan);

        this.setSize(400,550);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);



    }

    public JPanel ajoutJeu(Game game,GridBagConstraints gbc){

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridx = GridBagConstraints.REMAINDER;

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);

        JPanel panelGame = new JPanel();
        JButton gameButton = new JButton(game.getTitre());

        gameButton.setPreferredSize(new Dimension(250,75));
        gameButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        gameButton.setBackground(ColorPerso.jaune);
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Ranking ranking = new Ranking(game,user);
            }
        });

        panelGame.setLayout(flowLayout);
        panelGame.add(gameButton);
        panelGame.setOpaque(false);

        return panelGame;
    }

}
