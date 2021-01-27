package view;

import Sockets.Admin;
import Sockets.Client;
import database.DBEnigma;
import database.DBGame;
import database.DBRoom;
import launcher.Main;
import model.Enigma;
import model.EnigmaList;
import model.Room;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.text.Normalizer;
import java.util.ArrayList;

public class PlayerManagement extends JPanel implements ActionListener,MouseListener{

    private JPanel currentStoryPanIn = new JPanel();
    private JPanel answersPanIn = new JPanel();
    private JPanelImage mainPanel;
    private JPanel panelTitre;

    private JButton helpButtonGM;
    private JButton buttonReturn;
    private JButton buttonHint1;
    private JButton buttonHint2;
    private JButton buttonHint3;

    public GlobalFrame frame;
    private boolean boolHint1;
    private boolean boolHint2;
    private boolean boolHint3;
    private final int riddleNb;

    private JTextArea currentStory;
    private JTextArea proposition;
    private JTextArea helpMessageGM;
    private EnigmaList currentRiddles;
    private JLabel title;
    private JLabel answers;
    private JLabel labelTitre;

    private Room room;

    /**
     * Interface de suivi de partie
     * @param frame fenêtre d'affichage
     * @param room Salle associée à la partie
     * @param gameNb n° du jeu
     * @param riddleNb Nombre d'enigme
     * @param boolHint1Revealed Indice 1 révélé
     * @param boolHint2Revealed Indice 2 révélé
     * @param boolHint3Revealed Indice 3 révélé
     */

    public PlayerManagement(GlobalFrame frame, Room room, int gameNb, int riddleNb, boolean boolHint1Revealed, boolean boolHint2Revealed, boolean boolHint3Revealed){
        this.frame = frame;
        this.boolHint1 = boolHint1Revealed;
        this.boolHint2 = boolHint2Revealed;
        this.boolHint3 = boolHint3Revealed;
        this.room = DBRoom.getRooms(Main.idAdmin).findByID(room.getId());
        this.riddleNb=riddleNb;

        int width = (int) frame.windowSize.getWidth();
        int height = (int) frame.windowSize.getHeight();

        mainPanel = new JPanelImage(Main.class.getResourceAsStream("/image/FondPrincipal.png"), GlobalFrame.windowSize);

        panelTitre = new JPanel();
        labelTitre = new JLabel("MJ - Suivi des joueurs");
        panelTitre.setLayout(new FlowLayout(1));
        panelTitre.add(labelTitre);
        panelTitre.setBorder(BorderFactory.createLineBorder(Color.black,2));


        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        currentRiddles = DBEnigma.getEnigmas(gameNb); // la liste des énigmes du jeu

        helpButtonGM = new JButton("Envoyer");
        helpButtonGM.addActionListener(this);
        helpButtonGM.setBackground(Color.white);
        helpButtonGM.setPreferredSize(new Dimension(110,30));
        helpButtonGM.setForeground(Color.black);
        helpButtonGM.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        helpButtonGM.setOpaque(true);

        buttonReturn = new JButton("Retour");
        buttonReturn.addActionListener(this);
        buttonReturn.setBackground(ColorPerso.rouge);
        buttonReturn.setForeground(Color.white);
        buttonReturn.setPreferredSize(new Dimension(100,30));
        buttonReturn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        buttonReturn.setOpaque(true);

        buttonHint1 = this.hintButton(1);
        buttonHint1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        buttonHint2 = this.hintButton(2);
        buttonHint2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        buttonHint3 = this.hintButton(3);
        buttonHint3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        title = new JLabel();
        title.setText("<html><body><u>" + DBGame.getTitleGame(gameNb) + "</u></body></html>");
        title.setOpaque(false);
        title.setAlignmentX(JLabel.LEFT);

        JPanel titlePanIn = new JPanel();
        titlePanIn.setPreferredSize(new Dimension((int) ((width-40)*0.7),(int) ((height-90)*0.06)));
        titlePanIn.setOpaque(false);
        titlePanIn.setLayout(new FlowLayout(0));
        titlePanIn.add(title);
        //titlePanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        JPanel titlePan = new JPanel();
        titlePan.setOpaque(false);
        titlePan.setBorder(BorderFactory.createEmptyBorder(0,50,0,10));
        titlePan.add(titlePanIn);

        JLabel timer = new JLabel();
        JPanel timerPanIn = new JPanel();
        timerPanIn.setOpaque(false);
        timerPanIn.setPreferredSize(new Dimension((int)((width-40)*0.3),(int) ((height-90)*0.06)));
        timerPanIn.setBackground(Color.LIGHT_GRAY);
        //timerPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        timerPanIn.add(timer);

        JPanel timerPan = new JPanel();
        timerPan.setOpaque(false);
        timerPan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        timerPan.add(timerPanIn);

        JPanel topPan = new JPanel();
        topPan.setOpaque(false);
        topPan.setLayout(new BoxLayout(topPan, BoxLayout.LINE_AXIS));
        topPan.add(titlePan);
        topPan.add(timerPan);

        currentStory = new JTextArea();
        currentStory.setLineWrap(true);
        currentStory.setWrapStyleWord(true);
        currentStory.setFont(FontPerso.courierNew);
        currentStory.setEditable(false);
        currentStory.setPreferredSize(new Dimension(width-20,(height-90)*50/100-10));
        currentStory.setText((currentRiddles.getEnigma(riddleNb - 1)).getText());
        currentStory.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));


        JScrollPane scrollCurrentStoryPanIn = new JScrollPane(currentStoryPanIn,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //scrollCurrentStoryPanIn.setPreferredSize(new Dimension(width-20,(height-90)*50/100));
        scrollCurrentStoryPanIn.getVerticalScrollBar().setUnitIncrement(10);
        currentStoryPanIn.add(currentStory);
        scrollCurrentStoryPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        currentStoryPanIn.setBackground(Color.white);
        JPanel currentStoryPan = new JPanel();
        currentStoryPan.setBackground(ColorPerso.darkGray);
        currentStoryPan.setLayout(new FlowLayout(1));
        currentStoryPan.add(scrollCurrentStoryPanIn);
        //currentStoryPan.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));

        JPanel panelAnswer = new JPanel();
        panelAnswer.setLayout(new BorderLayout());
        panelAnswer.setOpaque(false);
        answers = new JLabel();
        answers.setText("Réponse à l'énigme : " + (currentRiddles.getEnigma(riddleNb -1)).getAnswer());
        answers.setAlignmentY(SwingConstants.CENTER);
        answers.setAlignmentX(SwingConstants.CENTER);
        answers.setOpaque(false);
        panelAnswer.add(answers,BorderLayout.CENTER);


        proposition = new JTextArea("Réponses tentées jusqu'ici : \n");
        proposition.setAlignmentX(SwingConstants.CENTER);
        proposition.setFont(FontPerso.Oxanimum);
        proposition.setEditable(false);
        proposition.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        afficheReponse();

        JScrollPane scrollAnswersPanIn = new JScrollPane(proposition,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollAnswersPanIn.setPreferredSize(new Dimension((int) (width-20),(int) ((height-90)*0.10)));
        scrollAnswersPanIn.getVerticalScrollBar().setUnitIncrement(10);
        scrollAnswersPanIn.setBorder(BorderFactory.createLineBorder(Color.black,2));

        answersPanIn.setLayout(new GridLayout(1,2,200,0));
        answersPanIn.add(scrollAnswersPanIn);
        answersPanIn.add(panelAnswer);
        answersPanIn.setOpaque(false);
        scrollAnswersPanIn.setBorder(BorderFactory.createEmptyBorder());

        JPanel answersPan = new JPanel();
        answersPan.setLayout(new BorderLayout());
        answersPan.add(answersPanIn,BorderLayout.CENTER);
        answersPan.setOpaque(false);
        //answersPan.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));

        helpMessageGM = new JTextArea("Vous pouvez envoyez de l'aide au joueur ici");
        helpMessageGM.setLineWrap(true);
        helpMessageGM.setWrapStyleWord(true);
        if(helpMessageGM.getText().equals("Vous pouvez envoyez de l'aide au joueur ici")){
            helpMessageGM.setForeground(Color.gray);
            helpMessageGM.setFont(helpMessageGM.getFont().deriveFont(Font.ITALIC));
        }
        helpMessageGM.setFont(FontPerso.Oxanimum);
        helpMessageGM.setPreferredSize(new Dimension((int) (width-200-helpButtonGM.getWidth()), (int) ((height-150)*0.40)));
        helpMessageGM.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(helpMessageGM.getText().equals("Vous pouvez envoyez de l'aide au joueur ici")){
                    helpMessageGM.setText("");
                    helpMessageGM.setForeground(Color.black);
                    helpMessageGM.setFont(helpMessageGM.getFont().deriveFont(Font.PLAIN));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(helpMessageGM.getText().equals("")){
                    helpMessageGM.setText("Vous pouvez envoyez de l'aide au joueur ici");
                    helpMessageGM.setForeground(Color.gray);
                    helpMessageGM.setFont(helpMessageGM.getFont().deriveFont(Font.ITALIC));
                }
            }
        });



        JPanel helpMessageGMPan = new JPanel();
        helpMessageGMPan.setBackground(Color.LIGHT_GRAY);
        helpMessageGMPan.setLayout(new FlowLayout(1));
        helpMessageGMPan.add(helpMessageGM);
        helpMessageGMPan.setBorder(BorderFactory.createLineBorder(Color.black,2));


        JPanel helpButtonGMPanIn = new JPanel();
        helpButtonGMPanIn.setLayout(new FlowLayout(1));
        helpButtonGMPanIn.add(helpButtonGM);
        helpButtonGMPanIn.setOpaque(false);
        JPanel helpButtonGMPan = new JPanel();
        helpButtonGMPan.setBackground(Color.LIGHT_GRAY);
        helpButtonGMPan.add(helpButtonGMPanIn);
        helpButtonGMPan.setOpaque(false);
        helpButtonGMPan.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JScrollPane scrollHelp = new JScrollPane(helpMessageGM,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollHelp.setBorder(BorderFactory.createLineBorder(Color.black,2));

        scrollHelp.getVerticalScrollBar().setUnitIncrement(10);

        JPanel helpGMPanIn = new JPanel();
        helpGMPanIn.setBackground(Color.LIGHT_GRAY);
        //helpGMPanIn.setPreferredSize(new Dimension((int) (width-20), (int) ((height-90)*0.40)));
        helpGMPanIn.setLayout(new BoxLayout(helpGMPanIn, BoxLayout.PAGE_AXIS));
        helpGMPanIn.add(scrollHelp);
        helpGMPanIn.add(helpButtonGMPan);
        helpGMPanIn.setOpaque(false);


        JPanel helpGMPan = new JPanel();
        helpGMPan.setBackground(ColorPerso.darkGray);
        helpGMPan.setLayout(new FlowLayout(1));
        helpGMPan.add(helpGMPanIn);
        //helpGMPan.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        //helpGMPan.setPreferredSize(new Dimension(width-20,(height-90)*40/100));

        JPanel buttonReturnPanIn = new JPanel();
        buttonReturnPanIn.setLayout(new FlowLayout(0));
        buttonReturnPanIn.setOpaque(false);
        buttonReturnPanIn.add(buttonReturn);


        JPanel buttonReturnPan = new JPanel();
        buttonReturnPan.setBackground(Color.LIGHT_GRAY);
        buttonReturnPan.add(buttonReturnPanIn);
        buttonReturnPan.setBorder(BorderFactory.createEmptyBorder(20,0,0,20));


        JPanel buttonHint1PanIn = new JPanel();
        buttonHint1PanIn.setBackground(Color.LIGHT_GRAY);

        JTextArea hint1Text = new JTextArea();
        hint1Text.setLineWrap(true);
        hint1Text.setWrapStyleWord(true);
        hint1Text.setAlignmentX(SwingConstants.CENTER);
        hint1Text.setAlignmentY(SwingConstants.CENTER);
        hint1Text.setBackground(Color.LIGHT_GRAY);
        //hint1Text.setColumns(15);
        hint1Text.setFont(FontPerso.Oxanimum);
        hint1Text.setEditable(false);
        hint1Text.setText(currentRiddles.getEnigma(riddleNb-1).getClue1());




        JScrollPane scrollHint1TextPan = new JScrollPane(hint1Text, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollHint1TextPan.getVerticalScrollBar().setUnitIncrement(10);
        scrollHint1TextPan.setOpaque(false);
        scrollHint1TextPan.setBorder(BorderFactory.createLineBorder(Color.black));
        scrollHint1TextPan.getVerticalScrollBar().setVisible(false);

        JPanel hint1TextPan = new JPanel();
        hint1TextPan.setLayout(new BorderLayout());
        hint1TextPan.setBackground(Color.lightGray);
        hint1TextPan.add(scrollHint1TextPan,BorderLayout.CENTER);

        JPanel hint1ButtonPan = new JPanel();
        hint1ButtonPan.setLayout(new FlowLayout(1));
        hint1ButtonPan.add(buttonHint1);
        hint1ButtonPan.setBackground(Color.lightGray);
        buttonHint1PanIn.setLayout(new BoxLayout(buttonHint1PanIn, BoxLayout.PAGE_AXIS));
        buttonHint1PanIn.add(scrollHint1TextPan);
        buttonHint1PanIn.add(hint1ButtonPan);
        buttonHint1PanIn.setAlignmentY(SwingConstants.CENTER);


        JPanel buttonHint1Pan = new JPanel();
        buttonHint1Pan.setBackground(Color.LIGHT_GRAY);
        buttonHint1Pan.add(buttonHint1PanIn);
        buttonHint1Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

        JPanel buttonHint2PanIn = new JPanel();
        buttonHint2PanIn.setBackground(Color.LIGHT_GRAY);

        JTextArea hint2Text = new JTextArea();
        hint2Text.setLineWrap(true);
        hint2Text.setWrapStyleWord(true);
        hint2Text.setAlignmentX(SwingConstants.CENTER);
        hint2Text.setAlignmentY(SwingConstants.CENTER);
        hint2Text.setBackground(Color.LIGHT_GRAY);
        hint2Text.setFont(FontPerso.Oxanimum);
        hint2Text.setEditable(false);
        //hint2Text.setColumns(15);
        hint2Text.setText(currentRiddles.getEnigma(riddleNb-1).getClue2());



        JScrollPane scrollHint2TextPan = new JScrollPane(hint2Text, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollHint2TextPan.getVerticalScrollBar().setUnitIncrement(10);
        scrollHint2TextPan.setOpaque(false);
        scrollHint2TextPan.setBorder(BorderFactory.createLineBorder(Color.black));
        scrollHint2TextPan.getVerticalScrollBar().setVisible(false);

        JPanel hint2TextPan = new JPanel();
        hint2TextPan.setLayout(new BorderLayout());
        hint2TextPan.setBackground(Color.lightGray);
        hint2TextPan.add(scrollHint2TextPan,BorderLayout.CENTER);

        JPanel hint2ButtonPan = new JPanel();
        hint2ButtonPan.setLayout(new FlowLayout(1));
        hint2ButtonPan.add(buttonHint2);
        hint2ButtonPan.setBackground(Color.lightGray);
        buttonHint2PanIn.setLayout(new BoxLayout(buttonHint2PanIn, BoxLayout.PAGE_AXIS));
        buttonHint2PanIn.add(scrollHint2TextPan);
        buttonHint2PanIn.add(hint2ButtonPan);
        buttonHint2PanIn.setAlignmentY(SwingConstants.CENTER);


        JPanel buttonHint2Pan = new JPanel();
        buttonHint2Pan.setBackground(Color.LIGHT_GRAY);
        buttonHint2Pan.add(buttonHint2PanIn);
        buttonHint2Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

        JPanel buttonHint3PanIn = new JPanel();
        buttonHint3PanIn.setBackground(Color.LIGHT_GRAY);
        JTextArea hint3Text = new JTextArea();
        hint3Text.setLineWrap(true);
        hint3Text.setAlignmentX(SwingConstants.CENTER);
        hint3Text.setAlignmentY(SwingConstants.CENTER);
        hint3Text.setWrapStyleWord(true);
        hint3Text.setBackground(Color.LIGHT_GRAY);
        hint3Text.setAlignmentX(SwingConstants.CENTER);
        //hint3Text.setColumns(15);
        hint3Text.setFont(FontPerso.Oxanimum);
        hint3Text.setEditable(false);
        hint3Text.setText(currentRiddles.getEnigma(riddleNb-1).getClue3());


        JScrollPane scrollHint3TextPan = new JScrollPane(hint3Text, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollHint3TextPan.getVerticalScrollBar().setUnitIncrement(10);
        scrollHint3TextPan.setOpaque(false);
        scrollHint3TextPan.setBorder(BorderFactory.createLineBorder(Color.black));
        scrollHint3TextPan.getVerticalScrollBar().setVisible(false);

        JPanel hint3TextPan = new JPanel();
        hint3TextPan.setLayout(new BorderLayout());
        hint3TextPan.setBackground(Color.lightGray);
        hint3TextPan.add(scrollHint3TextPan,BorderLayout.CENTER);


        JPanel hint3ButtonPan = new JPanel();
        hint3ButtonPan.setLayout(new FlowLayout(1));
        hint3ButtonPan.add(buttonHint3);
        hint3ButtonPan.setBackground(Color.lightGray);
        buttonHint3PanIn.setLayout(new BoxLayout(buttonHint3PanIn, BoxLayout.PAGE_AXIS));
        buttonHint3PanIn.add(scrollHint3TextPan);
        buttonHint3PanIn.add(hint3ButtonPan);
        buttonHint3PanIn.setAlignmentY(SwingConstants.CENTER);


        JPanel buttonHint3Pan = new JPanel();
        buttonHint3Pan.setBackground(Color.LIGHT_GRAY);
        buttonHint3Pan.add(buttonHint3PanIn);
        buttonHint3Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));


        JPanel bottomPan = new JPanel();
        bottomPan.setBackground(ColorPerso.darkGray);
        //bottomPan.setPreferredSize(new Dimension((int) (width-20),(int) ((height)*0.40)));
        JPanel bottomPanIn = new JPanel();
        bottomPanIn.setBackground(Color.LIGHT_GRAY);
        bottomPanIn.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,50,0,50);
        gbc.gridx =1;
        gbc.gridy=1;
        gbc.weighty =2;

        bottomPanIn.add(scrollHint1TextPan,gbc);
        gbc.gridx =2;
        bottomPanIn.add(scrollHint2TextPan,gbc);
        gbc.gridx =3;
        bottomPanIn.add(scrollHint3TextPan,gbc);
        gbc.weighty = 1;
        gbc.insets = new Insets(0,50,0,50);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 2;
        gbc.gridx =1;
        bottomPanIn.add(hint1ButtonPan,gbc);
        gbc.gridx =2;
        bottomPanIn.add(hint2ButtonPan,gbc);
        gbc.gridx =3;
        bottomPanIn.add(hint3ButtonPan,gbc);

        bottomPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        buttonHint1.addMouseListener(this);
        buttonHint2.addMouseListener(this);
        buttonHint3.addMouseListener(this);
        helpButtonGM.addMouseListener(this);
        buttonReturn.addMouseListener(this);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.revalidate();
                frame.repaint();
            }
        });

        this.setLayout(new BorderLayout(10,10));
        this.add(panelTitre,BorderLayout.PAGE_START);
        this.add(mainPanel,BorderLayout.CENTER);
        this.add(buttonReturnPanIn,BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.setBackground(ColorPerso.darkGray);

        GridBagConstraints gbcglobal = new GridBagConstraints();

        gbcglobal.weighty = 1;
        gbcglobal.weightx = 3;
        gbcglobal.gridy = 0;
        gbcglobal.fill = GridBagConstraints.BOTH;
        gbcglobal.insets = new Insets(20,20,0,0);
        mainPanel.add(topPan, gbcglobal);

        gbcglobal.weighty = 6;
        gbcglobal.gridy = 1;
        gbcglobal.insets = new Insets(0,20,0,20);
        mainPanel.add(scrollCurrentStoryPanIn, gbcglobal);

        gbcglobal.weighty = 3;
        gbcglobal.weightx = 1;
        gbcglobal.gridy = 2;
        gbcglobal.insets = new Insets(30, 200,0,200);
        mainPanel.add(answersPan, gbcglobal);

        gbcglobal.weighty = 2;
        gbcglobal.gridy = 3;

        gbcglobal.insets = new Insets(30,60,0,60);
        mainPanel.add(helpGMPanIn, gbcglobal);

        gbcglobal.weighty = 2;
        gbcglobal.weightx = 3;
        gbcglobal.gridy = 4;
        gbcglobal.fill = GridBagConstraints.VERTICAL;
        gbcglobal.insets = new Insets(10,20,20,20);
        mainPanel.add(bottomPanIn, gbcglobal);

        Runnable runnable = () -> {
            while (true) {
                String reponse = Admin.recepAnswerJoueur(room.getUserInside());
                ArrayList<String> tab = Main.answers.get(room.getUserInside());
                if(tab==null){
                    tab= new ArrayList<>();
                }
                tab.add(reponse);
                System.out.println("Coucou c'est la réponse : "+reponse);
                Main.answers.put(room.getUserInside(), tab);

                if (removeAccents(reponse).equals(removeAccents(answers.getText().toLowerCase()))) {
                    Main.answers.remove(room.getUserInside());

                    if (frame.getContentPane() instanceof PlayerManagement) {
                        frame.playerManagementDisplay(frame, room, gameNb, riddleNb + 1, false, false, false);
                    }
                }

                proposition.append("\n" + reponse);
            }
        };
        Thread ta = new Thread(runnable);
        ta.start();
    }

  /*  public static PlayerManagement getInstance(GlobalFrame frame,Room room,int gameNb, int riddleNb, boolean boolHint1Revealed, boolean boolHint2Revealed,
                                               boolean boolHint3Revealed){
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (INSTANCE == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(INSTANCE) {
                if (INSTANCE == null) {
                    INSTANCE = new PlayerManagement(frame,room,gameNb, riddleNb, boolHint1Revealed, boolHint2Revealed,
                    boolHint3Revealed);
                }
            }
        }
        else {
            INSTANCE.frame=frame;
            INSTANCE.boolHint1 = boolHint1Revealed;
            INSTANCE.boolHint2 = boolHint2Revealed;
            INSTANCE.boolHint3 = boolHint3Revealed;

            INSTANCE.title.setText(DBGame.getTitleGame(gameNb));
            if(room!=null) {
                System.out.println("id de ladmin "+Main.idAdmin);
                System.out.println("room id"+room.getId());
                INSTANCE.room = DBRoom.getRooms(Main.idAdmin).findByID(room.getId());
            }
            INSTANCE.currentRiddles = DBEnigma.getEnigmas(gameNb);
            INSTANCE.currentStory.setText((INSTANCE.currentRiddles.getEnigma(riddleNb - 1)).getText());
            INSTANCE.answers.setText((INSTANCE.currentRiddles.getEnigma(riddleNb -1)).getAnswer());

            if(INSTANCE.boolHint1){
                INSTANCE.buttonHint1.setText("Indice 1 déjà affiché");
                INSTANCE.buttonHint1.setBackground(Color.lightGray);
            }DBEnigma.getEnigmas(room.getGame().getId()).getEnigma(enigmalistflag).getClue1()
            else{
                INSTANCE.buttonHint1.setText("Afficher l'indice 1");
                INSTANCE.buttonHint1.setBackground(Color.white);
            }
            if(INSTANCE.boolHint2){
                INSTANCE.buttonHint2.setText("Indice 2 déjà affiché");
                INSTANCE.buttonHint2.setBackground(Color.lightGray);
            }
            else{
                INSTANCE.buttonHint2.setText("Afficher l'indice 2");
                INSTANCE.buttonHint2.setBackground(Color.white);
            }
            if(INSTANCE.boolHint3){
                INSTANCE.buttonHint3.setText("Indice 3 déjà affiché");
                INSTANCE.buttonHint3.setBackground(Color.lightGray);
            }
            else{
                INSTANCE.buttonHint3.setText("Afficher l'indice 3");
                INSTANCE.buttonHint3.setBackground(Color.white);
            }
            INSTANCE.helpMessageGM.setText("");
        }



        return INSTANCE;
    }*/

    /**
     * Création ou mise à jour des boutons
     * @param i : indice du bouton
     * @return Retourne le bouton après modification
     */

    private JButton hintButton(int i){
        JButton button = null;
        boolean revealed = false;
        if(i==1){
            revealed=boolHint1;
        }
        else if (i==2){
            revealed=boolHint2;
        }
        else{
            revealed=boolHint3;
        }
        if(!revealed){
            if(room == null){
                button = new JButton("Envoyer l'indice " + i);
            }else{
                if(i==1) {
                    button = new JButton("Afficher indice 1 ");
                }else if(i==2){
                    button = new JButton("Afficher indice 2 ");
                }else if(i==3){
                    button = new JButton("Afficher indice 3 ");
                }
            }
            button.addActionListener(this);
            button.setBackground(Color.white);
        }else{
            button.setBackground(Color.lightGray);
        }
        button.setForeground(Color.black);
        button.setOpaque(true);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==helpButtonGM){
            //Baptiste fonction envoyer le message aux joueurs
            String messageFromMJ = helpMessageGM.getText();
            System.out.println(messageFromMJ);
            Admin.envoiAideJoueur(messageFromMJ,0,room.getUserInside());
            System.out.println("send");
        }else if(e.getSource() == buttonReturn){
            frame.roomManagementDisplay(frame);
        }else if(e.getSource()==buttonHint1){
            boolHint1=true;
            buttonHint1.setText("Afficher indice 1");
            Admin.envoiAideJoueur(null,1,room.getUserInside());
            buttonHint1.setBackground(Color.DARK_GRAY);
        }else if(e.getSource()==buttonHint2){
            boolHint2=true;
            buttonHint2.setText("Afficher indice 2 ");
            Admin.envoiAideJoueur(null,2,room.getUserInside());
            buttonHint2.setBackground(Color.DARK_GRAY);
        }else if(e.getSource()==buttonHint3){
            boolHint3=true;
            buttonHint3.setText("Afficher indice 3 ");
            Admin.envoiAideJoueur(null,3,room.getUserInside());
            buttonHint3.setBackground(Color.DARK_GRAY);
        }
    }

    /**
     * Permet de normaliser les réponses
     * @param text Texte à normaliser
     * @return Retourne une chaîne de caractères sans accents
     */

    public static String removeAccents(String text) {
        return text == null ? null :
                Normalizer.normalize(text, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    /**
     * Affiche les réponses ayant été rentrées par le joueur
     */
    public void afficheReponse(){

        ArrayList<String> tab = Main.answers.get(room.getUserInside());
        if (tab != null) {
            for (int i = 0; i < tab.size(); i++) {
                if(tab.get(i)!=null) {
                    proposition.append("\n" + tab.get(i));
                }
            }
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
        if (e.getSource() == buttonHint1){
            buttonHint1.setBackground(ColorPerso.grisClair);
            buttonHint1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
        else if (e.getSource() == buttonHint2){
            buttonHint2.setBackground(ColorPerso.grisClair);
            buttonHint2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
        else if (e.getSource() == buttonHint3){
            buttonHint3.setBackground(ColorPerso.grisClair);
            buttonHint3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
        else if (e.getSource()==buttonReturn){
            buttonReturn.setBackground(ColorPerso.rougeHoover);
            buttonReturn.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
        else if (e.getSource()==helpButtonGM){
            helpButtonGM.setBackground(ColorPerso.grisClair);
            helpButtonGM.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == buttonHint1){
            buttonHint1.setBackground(ColorPerso.white);
            buttonHint1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }
        else if (e.getSource() == buttonHint2){
            buttonHint2.setBackground(ColorPerso.white);
            buttonHint2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }
        else if (e.getSource() == buttonHint3){
            buttonHint3.setBackground(ColorPerso.white);
            buttonHint3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }
        else if (e.getSource()==buttonReturn){
            buttonReturn.setBackground(ColorPerso.rouge);
            buttonReturn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }
        else if (e.getSource()==helpButtonGM){
            helpButtonGM.setBackground(ColorPerso.white);
            helpButtonGM.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }
    }
}