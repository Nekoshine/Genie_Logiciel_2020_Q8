package view;

import database.DBUser;
import sockets.Admin;
import database.DBEnigma;
import database.DBGame;
import database.DBRoom;
import launcher.Main;
import model.EnigmaList;
import model.Room;
import sockets.Client;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.Normalizer;
import java.util.ArrayList;

public class PlayerManagement extends JPanel implements ActionListener,MouseListener{

    private JPanel currentStoryPanIn = new JPanel();
    private JPanel answersPanIn = new JPanel();
    private JPanelImage mainPanel;
    private JPanel panelTitre;
    private JPanel currentStoryPan;

    private JScrollPane scrollCurrentStoryPanIn;

    private JButton helpButtonGM;
    private JButton buttonReturn;
    private JButton buttonHint1;
    private JButton buttonHint2;
    private JButton buttonHint3;

    public GlobalFrame frame;
    private boolean boolHint1;
    private boolean boolHint2;
    private boolean boolHint3;
    private int riddleNb;

    private Timer countdownGame;
    private int countdownvalue;

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
        this.countdownvalue = Admin.recupTimer(room.getUserInside());
        this.countdownvalue = Admin.recupTimer(room.getUserInside());

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
        timer.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
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
        currentStory.setText((currentRiddles.getEnigma(riddleNb - 1)).getQuestion());
        currentStory.setFont(FontPerso.courierNew);
        currentStory.setEditable(false);

        scrollCurrentStoryPanIn = new JScrollPane(currentStory,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollCurrentStoryPanIn.setPreferredSize(new Dimension(width-20,(height-90)*50/100));
        scrollCurrentStoryPanIn.getVerticalScrollBar().setUnitIncrement(10);
        scrollCurrentStoryPanIn.setBorder(BorderFactory.createEmptyBorder());

        currentStoryPan = new JPanel();
        currentStoryPan.setLayout(new GridLayout(1,1));
        currentStoryPan.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        currentStoryPan.setPreferredSize(new Dimension((int)((float) width*0.95),(int)((float) height*0.25)));
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
        proposition.setFont(FontPerso.lato);
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
        helpMessageGM.setFont(FontPerso.lato);
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
        hint1Text.setFont(FontPerso.lato);
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
        hint2Text.setFont(FontPerso.lato);
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
        hint3Text.setFont(FontPerso.lato);
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

        //Timer


        ActionListener countdowngameTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                int seconde = 0;
                int minute = 0;

                countdownvalue--;
                seconde = countdownvalue % 60;
                minute = (countdownvalue - seconde) / 60;
                if (seconde < 10) {
                    timer.setText(minute + ":0" + seconde);/* rafraichir le label */
                    timer.setForeground(Color.RED);
                } else {
                    timer.setText(minute + ":" + seconde);/* rafraichir le label */
                    timer.setForeground(Color.RED);
                }
                frame.revalidate();
                frame.repaint();

                if (countdownvalue == 0) {
                    countdownGame.stop();
                    if(Client.envoieFinPartie(DBUser.getUser(room.getUserInside()).getLogin(),room)){
                        frame.connectionMenuDisplay(frame);
                    }
                    frame.defeatscreenDisplay(frame);
                }
            }
        };
        countdownGame = new Timer(1000, countdowngameTask);





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
        countdownGame.start();

        GridBagConstraints gbcglobal = new GridBagConstraints();

        gbcglobal.weighty = 1;
        gbcglobal.weightx = 3;
        gbcglobal.gridy = 0;
        gbcglobal.fill = GridBagConstraints.BOTH;
        gbcglobal.insets = new Insets(20,20,0,0);
        mainPanel.add(topPan, gbcglobal);

        gbcglobal.weighty = 6;
        gbcglobal.gridy = 1;
        gbcglobal.insets = new Insets(0,10,0,10);
        mainPanel.add(currentStoryPan, gbcglobal);

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
                boolean find = false;
                String reponse = Admin.recepAnswerJoueur(room.getUserInside());
                System.out.println("coucouc c'est la reponse "+reponse);
                String reponseSansAccent = removeAccents(reponse.toLowerCase());
                System.out.println("coucou c'est la reponseSansAccent "+reponseSansAccent);
                String answer = answers.getText().substring(21);
                String[] possibility = answer.split(" / ");

                for(int i=0;i<possibility.length;i++){
                    System.out.println("Réponse1 : "+possibility[i]);
                    if (reponseSansAccent.equals(removeAccents(possibility[i]).toLowerCase())) {
                        find = true;
                        break;
                    }
                }
                if(!find){
                    possibility = answer.split("/");

                    for(int i=0;i<possibility.length;i++){
                        System.out.println("Réponse2 : "+possibility[i]);
                        if (reponseSansAccent.equals(removeAccents(possibility[i]).toLowerCase())) {
                            find = true;
                            break;
                        }
                    }
                }
                System.out.println("j'ai "+find+" la reponse");
                ArrayList<String> tab = Main.answers.get(room.getUserInside());

                if(tab==null){
                    tab= new ArrayList<>();
                }

                if(find){
                    Main.answers.remove(room.getUserInside());
                    if (frame.getContentPane() instanceof PlayerManagement) {
                        frame.playerManagementDisplay(frame, room, gameNb, riddleNb + 1, false, false, false);
                    }
                    else {
                        frame.playerManagement = new PlayerManagement(frame, room, gameNb, riddleNb + 1, false, false, false);
                    }
                    proposition.setText("Réponses tentées jusqu'ici : \n");
                }
                else {
                    tab.add(reponse);
                    proposition.append("\n" + reponse);
                }


            }
        };
        Thread ta = new Thread(runnable);
        ta.start();
        frame.revalidate();
        frame.repaint();
    }

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
            buttonHint1.setEnabled(false);
        }else if(e.getSource()==buttonHint2){
            boolHint2=true;
            buttonHint2.setText("Afficher indice 2 ");
            Admin.envoiAideJoueur(null,2,room.getUserInside());
            buttonHint2.setBackground(Color.DARK_GRAY);
            buttonHint2.setEnabled(false);

        }else if(e.getSource()==buttonHint3){
            boolHint3=true;
            buttonHint3.setText("Afficher indice 3 ");
            Admin.envoiAideJoueur(null,3,room.getUserInside());
            buttonHint3.setBackground(Color.DARK_GRAY);
            buttonHint3.setEnabled(false);

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
            if (buttonHint1.getBackground() != Color.DARK_GRAY) {
                buttonHint1.setBackground(ColorPerso.grisClair);
                buttonHint1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
        }
        else if (e.getSource() == buttonHint2){
            if (buttonHint1.getBackground() != Color.DARK_GRAY) {
                buttonHint2.setBackground(ColorPerso.grisClair);
                buttonHint2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
        }
        else if (e.getSource() == buttonHint3){
            if (buttonHint1.getBackground() != Color.DARK_GRAY) {
                buttonHint3.setBackground(ColorPerso.grisClair);
                buttonHint3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
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
            if (buttonHint1.getBackground() != Color.DARK_GRAY) {
                buttonHint1.setBackground(ColorPerso.white);
                buttonHint1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }
        }
        else if (e.getSource() == buttonHint2){
            if (buttonHint1.getBackground() != Color.DARK_GRAY) {
                buttonHint2.setBackground(ColorPerso.white);
                buttonHint2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }
        }
        else if (e.getSource() == buttonHint3){
            if (buttonHint1.getBackground() != Color.DARK_GRAY) {
                buttonHint3.setBackground(ColorPerso.white);
                buttonHint3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }
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