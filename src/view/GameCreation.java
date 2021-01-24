// Interface dévelopée par Cédric Plantet

package view;

import database.DBEnigma;
import database.DBGame;
import launcher.Main;
import model.Enigma;
import model.EnigmaList;
import model.Game;
import model.Hint;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class GameCreation extends JPanel implements ActionListener, MouseListener, FocusListener {

    public EnigmaList listEnigma;
    public Game game;


    private final GridLayout gridHint;

    private final JScrollPane scrollEnigmas;

    private final JPanel centerPanel;
    private final JPanel enigmasPanel;
    private final JPanel newPanel;


    private final JButton exitButton;
    private final JButton saveButton;
    private final JButton deleteButton;
    private final JButton newButton;
    private final JButton rankingButton;

    private final JTextField initialScore;
    private final JTextField title;
    private final JTextField winMessage;

    private GlobalFrame frame;

    private static volatile GameCreation INSTANCE = new GameCreation(Main.frame,null);

    private GameCreation(GlobalFrame frame, Game game){

        this.frame = frame;
        this.game=game;
        this.listEnigma = Main.ListEnigma;

        BorderLayout mainLayout = new BorderLayout(10, 10);
        BorderLayout titleLayout = new BorderLayout(10, 10);
        BorderLayout newLayout = new BorderLayout(10, 10);

        JPanel buttonNewPanel = new JPanel();

        BorderLayout centerLayout = new BorderLayout(30, 30);

        GridBagConstraints gbcTitle = new GridBagConstraints();
        GridBagConstraints gbcScores = new GridBagConstraints();

        GridBagLayout gridEnigma = new GridBagLayout();
        GridBagLayout buttonLayout = new GridBagLayout();
        GridBagLayout gridInfo = new GridBagLayout();

        GridLayout grid = new GridLayout(1, 0);
        gridHint = new GridLayout(2,0,10,10);

        centerPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel titleNamePanel = new JPanel();
        JPanel defaultScorePanel = new JPanel();
        JPanel pointsPanel = new JPanel();
        newPanel = new JPanel();
        enigmasPanel = new JPanel();
        JPanel winPanel = new JPanel();
        JPanel winMessagePanel = new JPanel();
        JPanel namePanel = new JPanel();

        saveButton = new JButton("Enregistrer");
        exitButton = new JButton("Quitter");
        deleteButton = new JButton("Supprimer");
        newButton = new JButton("Nouvelle Enigme");
        rankingButton = new JButton("Classement");

        if(game==null) {
            title = new JTextField("Titre du jeu", 50);
            initialScore = new JTextField("Score Initial", 7);
            winMessage = new JTextField();
        }
        else {
            title = new JTextField(game.getTitre(),45);
            initialScore = new JTextField(String.valueOf(game.getScore()),7);
            winMessage = new JTextField(game.getEndMessage());
        }
        title.addFocusListener(this);
        initialScore.addFocusListener(this);
        winMessage.addFocusListener(this);

        JLabel winMessageLabel = new JLabel("Message de fin :", SwingConstants.RIGHT);
        JPanel winLabelPanel = new JPanel();
        winLabelPanel.add(winMessageLabel);


        JLabel windowName = new JLabel("MJ - Création/Modification de Jeux", JLabel.CENTER);
        namePanel.add(windowName);
        namePanel.setBackground(ColorPerso.grisOriginal);
        namePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));

        //Center

        newButton.setOpaque(true);
        newButton.setBackground(ColorPerso.grisFonce);
        newButton.setForeground(Color.white);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        newButton.setPreferredSize(new Dimension(210,45));
        buttonNewPanel.setLayout(new FlowLayout(1));
        buttonNewPanel.add(newButton);

        GridBagLayout gridWin = new GridBagLayout();
        GridBagConstraints gbcWin = new GridBagConstraints();

        gbcWin.weightx = 1;
        gbcWin.insets = new Insets(0,0,0,100);

        winPanel.setLayout(gridWin);
        gbcWin.fill = GridBagConstraints.HORIZONTAL;
        winPanel.add(winMessageLabel, gbcWin);

        gbcWin.weightx = 2;
        gbcWin.insets = new Insets(0,0,0,200);


        winMessagePanel.setLayout(gridWin);
        winMessagePanel.add(winMessage, gbcWin);

        winPanel.add(winMessagePanel, gbcWin);

        newPanel.setLayout(newLayout);
        newPanel.add(buttonNewPanel,BorderLayout.NORTH);
        newPanel.add(winPanel,BorderLayout.CENTER);
        newPanel.setBorder(BorderFactory.createEmptyBorder(0,0,25,0));

        centerPanel.setLayout(centerLayout);

        enigmasPanel.setLayout(gridEnigma);

        scrollEnigmas = new JScrollPane(enigmasPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollEnigmas.getVerticalScrollBar().setUnitIncrement(20);
        scrollEnigmas.setBorder(BorderFactory.createEmptyBorder());


        centerPanel.add(newPanel,BorderLayout.SOUTH);



        /* Chargement des énigmes */

        this.createList();

        newButton.addActionListener(this);
        newButton.addMouseListener(this);

        /* Setup Marges */

        centerPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        centerPanel.add(scrollEnigmas,BorderLayout.CENTER);


        title.setBorder(BorderFactory.createEmptyBorder());
        title.setHorizontalAlignment(JTextField.CENTER);

        initialScore.setBorder(BorderFactory.createEmptyBorder());
        initialScore.setHorizontalAlignment(JTextField.CENTER);

        rankingButton.setHorizontalAlignment(JButton.CENTER);
        rankingButton.setForeground(Color.white);
        rankingButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        rankingButton.addMouseListener(this);
        rankingButton.addActionListener(this);

        if (listEnigma.getSize()==0){
            rankingButton.setEnabled(false);
            rankingButton.setBackground(Color.darkGray);
        }
        else{
            rankingButton.setEnabled(true);
            rankingButton.setBackground(ColorPerso.azur);
        }

        gbcTitle.weightx = 2;
        gbcTitle.insets = new Insets(0,0,0,20);
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;


        gbcScores.weightx =1;
        gbcScores.insets = new Insets(0,10,0,10);
        gbcScores.fill = GridBagConstraints.HORIZONTAL;


        titleNamePanel.setLayout(grid);
        defaultScorePanel.setLayout(grid);
        pointsPanel.setLayout(grid);

        titleNamePanel.add(title);
        titleNamePanel.setOpaque(false);
        defaultScorePanel.add(initialScore);
        defaultScorePanel.setOpaque(false);
        pointsPanel.add(rankingButton);
        pointsPanel.setOpaque(false);

        infoPanel.setLayout(gridInfo);
        infoPanel.add(titleNamePanel,gbcTitle);
        infoPanel.add(defaultScorePanel,gbcScores);
        infoPanel.add(pointsPanel,gbcScores);
        infoPanel.setOpaque(false);

        titleNamePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        defaultScorePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));


        // Boutons

        exitButton.setBackground(ColorPerso.azur);
        exitButton.setForeground(Color.white);
        exitButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        exitButton.addActionListener(this);

        exitButton.addMouseListener(this);

        saveButton.setBackground(ColorPerso.vert);
        saveButton.setForeground(Color.white);
        saveButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        saveButton.addActionListener(this);
        saveButton.addMouseListener(this);

        deleteButton.setBackground(ColorPerso.rouge);
        deleteButton.setForeground(Color.white);
        deleteButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        deleteButton.addActionListener(this);
        deleteButton.addMouseListener(this);

        buttonPanel.setLayout(buttonLayout);
        buttonPanel.add(exitButton,gbcScores);
        buttonPanel.add(saveButton,gbcScores);
        buttonPanel.add(deleteButton,gbcScores);
        buttonPanel.setOpaque(false);


        Border mainPadding = BorderFactory.createEmptyBorder(10, 10, 0, 10);
        Border buttonsPadding = BorderFactory.createEmptyBorder(5,0,10,0);
        this.setBorder(mainPadding);
        buttonPanel.setBorder(buttonsPadding);

        titlePanel.setLayout(titleLayout);
        titlePanel.add(namePanel,BorderLayout.NORTH);
        titlePanel.setOpaque(false);
        titlePanel.add(infoPanel,BorderLayout.SOUTH);

        title.addCaretListener(e -> {

            if (e.getSource()==title){
                if(game!=null) {
                    game.setTitre(title.getText());
                }
            }
            else
                try{
                    game.setScore(Integer.parseInt(initialScore.getText()));
                }
                catch (Exception exception){
                    System.out.println("Le score n'est pas un entier");
                }

        });


        this.setLayout(mainLayout);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(titlePanel,BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.setBackground(ColorPerso.darkGray);

        this.setVisible(true);


    }
    public static GameCreation getInstance(GlobalFrame frame, Game game) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (INSTANCE == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(INSTANCE) {
                if (INSTANCE == null) {
                    INSTANCE = new GameCreation(frame,game);
                }
            }
        }
        else {
            INSTANCE.frame = frame;
            INSTANCE.rankingButton.setBackground(ColorPerso.azur);
            INSTANCE.exitButton.setBackground(ColorPerso.azur);
            if (game != null){
                INSTANCE.title.setText(game.getTitre());
                INSTANCE.initialScore.setText(String.valueOf(game.getScore()));
                INSTANCE.winMessage.setText(game.getEndMessage());

                INSTANCE.title.setForeground(Color.black);
                INSTANCE.title.setFont(INSTANCE.title.getFont().deriveFont(Font.PLAIN));
                INSTANCE.initialScore.setForeground(Color.black);
                INSTANCE.initialScore.setFont(INSTANCE.initialScore.getFont().deriveFont(Font.PLAIN));
                INSTANCE.winMessage.setForeground(Color.black);
                INSTANCE.winMessage.setFont(INSTANCE.winMessage.getFont().deriveFont(Font.PLAIN));
            }
            else{
                INSTANCE.title.setText("Titre du jeu");
                INSTANCE.initialScore.setText("Score Initial");
                INSTANCE.winMessage.setText("Fin de l'histoire");

                INSTANCE.title.setForeground(Color.gray);
                INSTANCE.title.setFont(INSTANCE.title.getFont().deriveFont(Font.ITALIC));
                INSTANCE.initialScore.setForeground(Color.gray);
                INSTANCE.initialScore.setFont(INSTANCE.initialScore.getFont().deriveFont(Font.ITALIC));
                INSTANCE.winMessage.setForeground(Color.gray);
                INSTANCE.winMessage.setFont(INSTANCE.winMessage.getFont().deriveFont(Font.ITALIC));
            }
            INSTANCE.listEnigma=Main.ListEnigma;
            INSTANCE.createList();
            if (INSTANCE.listEnigma.getSize()==0){
                INSTANCE.rankingButton.setEnabled(false);
                INSTANCE.rankingButton.setBackground(Color.darkGray);
            }
            else {
                INSTANCE.rankingButton.setEnabled(true);
                INSTANCE.rankingButton.setBackground(ColorPerso.azur);
            }
            INSTANCE.game=game;
        }
        return INSTANCE;
    }


    JPanel ajoutEnigme(Enigma enigme,GridBagConstraints gbcEnigma){

        /* Gridbag Constraints */

        gbcEnigma.gridy = GridBagConstraints.RELATIVE;
        gbcEnigma.fill = GridBagConstraints.HORIZONTAL;
        gbcEnigma.weightx = 1;
        gbcEnigma.gridx = 0;


        /* Ajout Panel */

        JPanel enigmaPan = new JPanel();

        JPanel storyPanel = new JPanel();
        JPanel infoEngimaPanel = new JPanel();
        JPanel hint1Panel = new JPanel();
        JPanel hint2Panel = new JPanel();
        JPanel hint3Panel = new JPanel();

        JScrollPane scrollStory;

        BorderLayout enigmaInfoLayout = new BorderLayout(10,10);

        JTextArea story = new JTextArea(enigme.getText());
        story.setFont(FontPerso.courierNew);
        if(story.getText().equals("Énigme précédée de son histoire")){
            story.setForeground(Color.gray);
            story.setFont(story.getFont().deriveFont(Font.ITALIC));
        }
        story.setLineWrap(true);
        story.setWrapStyleWord(true);
        story.setMargin(new Insets(5,5,5,5));
        story.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(story.getText().equals("Énigme précédée de son histoire")){
                    story.setText("");
                    story.setForeground(Color.black);
                    story.setFont(story.getFont().deriveFont(Font.PLAIN));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(story.getText().equals("")){
                    story.setText("Énigme précédée de son histoire");
                    story.setForeground(Color.gray);
                    story.setFont(story.getFont().deriveFont(Font.ITALIC));
                }
            }
        });
        story.addCaretListener(e -> listEnigma.findByID(enigme.getId()).setQuestion(story.getText()));


        // Answer Components

        JTextField answer = new JTextField(enigme.getAnswer());
        answer.setBorder(BorderFactory.createLineBorder(Color.black,2));
        if(answer.getText().equals("Réponse(s) à l'énigme séparée par \" \\ \"")){
            answer.setForeground(Color.gray);
            answer.setFont(story.getFont().deriveFont(Font.ITALIC));
        }
        answer.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(answer.getText().equals("Réponse(s) à l'énigme séparée par \" \\ \"")){
                    answer.setText("");
                    answer.setForeground(Color.black);
                    answer.setFont(answer.getFont().deriveFont(Font.PLAIN));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(answer.getText().equals("")){
                    answer.setText("Réponse(s) à l'énigme séparée par \" \\ \"");
                    answer.setForeground(Color.gray);
                    answer.setFont(answer.getFont().deriveFont(Font.ITALIC));
                }
            }
        });
        answer.addCaretListener(e -> listEnigma.findByID(enigme.getId()).setAnswer(answer.getText()));

        // Timer Components


        JTextField time1;

        if (enigme.getTimer1()==-1){
            time1 = new JTextField("Temps (en s) au bout duquel l'indice n°1 est débloqué");
            time1.setForeground(Color.gray);
            time1.setFont(answer.getFont().deriveFont(Font.ITALIC));
        }
        else{
            time1 = new JTextField(String.valueOf(enigme.getTimer1()));
        }

        JTextField time2;

        if (enigme.getTimer2()==-1){
            time2 = new JTextField("Temps (en s) au bout duquel l'indice n°2 est débloqué");
            time2.setForeground(Color.gray);
            time2.setFont(answer.getFont().deriveFont(Font.ITALIC));
        }
        else{
            time2 = new JTextField(String.valueOf(enigme.getTimer2()));
        }

        JTextField time3;

        if (enigme.getTimer3()==-1){
            time3 = new JTextField("Temps (en s) au bout duquel l'indice n°3 est débloqué");
            time3.setForeground(Color.gray);
            time3.setFont(answer.getFont().deriveFont(Font.ITALIC));

        }
        else{
            time3 = new JTextField(String.valueOf(enigme.getTimer3()));
        }

        time1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(time1.getText().equals("Temps (en s) au bout duquel l'indice n°1 est débloqué")){
                    time1.setText("");
                    time1.setForeground(Color.black);
                    time1.setFont(time1.getFont().deriveFont(Font.PLAIN));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(time1.getText().equals("")){
                    time1.setText("Temps (en s) au bout duquel l'indice n°1 est débloqué");
                    time1.setForeground(Color.gray);
                    time1.setFont(time1.getFont().deriveFont(Font.ITALIC));
                }
            }
        });

        time2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(time2.getText().equals("Temps (en s) au bout duquel l'indice n°2 est débloqué")){
                    time2.setText("");
                    time2.setForeground(Color.black);
                    time2.setFont(time2.getFont().deriveFont(Font.PLAIN));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(time2.getText().equals("")){
                    time2.setText("Temps (en s) au bout duquel l'indice n°2 est débloqué");
                    time2.setForeground(Color.gray);
                    time2.setFont(time2.getFont().deriveFont(Font.ITALIC));
                }
            }
        });

        time3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(time3.getText().equals("Temps (en s) au bout duquel l'indice n°3 est débloqué")){
                    time3.setText("");
                    time3.setForeground(Color.black);
                    time3.setFont(time3.getFont().deriveFont(Font.PLAIN));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(time3.getText().equals("")){
                    time3.setText("Temps (en s) au bout duquel l'indice n°3 est débloqué");
                    time3.setForeground(Color.gray);
                    time3.setFont(time3.getFont().deriveFont(Font.ITALIC));
                }
            }
        });

        // Clue Components

        JTextField hint1 = new JTextField(enigme.getClue1());
        hint1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        if(hint1.getText().equals("1er indice")){
            hint1.setForeground(Color.gray);
            hint1.setFont(hint1.getFont().deriveFont(Font.ITALIC));
        }
        hint1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(hint1.getText().equals("1er indice")){
                    hint1.setText("");
                    hint1.setForeground(Color.black);
                    hint1.setFont(hint1.getFont().deriveFont(Font.PLAIN));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(hint1.getText().equals("")){
                    hint1.setText("1er indice");
                    hint1.setForeground(Color.gray);
                    hint1.setFont(hint1.getFont().deriveFont(Font.ITALIC));
                }
            }
        });
        hint1.addCaretListener(e -> {
            try{
                listEnigma.findByID(enigme.getId()).setClue1(new Hint(hint1.getText(), Integer.parseInt(time1.getText())));
            }
            catch (Exception ex){
                System.out.println("Le timer n'est pas un entier");
            }
        });

        JTextField hint2 = new JTextField(enigme.getClue2());
        hint2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        if(hint2.getText().equals("2eme indice")){
            hint2.setForeground(Color.gray);
            hint2.setFont(hint1.getFont().deriveFont(Font.ITALIC));
        }
        hint2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(hint2.getText().equals("2eme indice")){
                    hint2.setText("");
                    hint2.setForeground(Color.black);
                    hint2.setFont(hint2.getFont().deriveFont(Font.PLAIN));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(hint2.getText().equals("")){
                    hint2.setText("2eme indice");
                    hint2.setForeground(Color.gray);
                    hint2.setFont(hint2.getFont().deriveFont(Font.ITALIC));
                }
            }
        });
        hint2.addCaretListener(e -> {
            try{
                listEnigma.findByID(enigme.getId()).setClue2(new Hint(hint2.getText(), Integer.parseInt(time2.getText())));
            }
            catch (Exception ex){
                System.out.println("Le timer n'est pas un entier");
            }
        });

        JTextField hint3 = new JTextField(enigme.getClue3());
        hint3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        if(hint3.getText().equals("3eme indice")){
            hint3.setForeground(Color.gray);
            hint3.setFont(hint1.getFont().deriveFont(Font.ITALIC));
        }
        hint3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(hint3.getText().equals("3eme indice")){
                    hint3.setText("");
                    hint3.setForeground(Color.black);
                    hint3.setFont(hint3.getFont().deriveFont(Font.PLAIN));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(hint3.getText().equals("")){
                    hint3.setText("3eme indice");
                    hint3.setForeground(Color.gray);
                    hint3.setFont(hint3.getFont().deriveFont(Font.ITALIC));
                }
            }
        });
        hint3.addCaretListener(e -> {
            try{
                listEnigma.findByID(enigme.getId()).setClue3(new Hint(hint3.getText(), Integer.parseInt(time3.getText())));
            }
            catch (Exception ex){
                System.out.println("Le timer n'est pas un entier");
            }
        });

        /* Caret Listener Time */

        time1.addCaretListener(e -> {
            try{
                listEnigma.findByID(enigme.getId()).setClue1(new Hint(hint1.getText(), Integer.parseInt(time1.getText())));
            }
            catch (Exception ex){
                System.out.println("Le timer n'est pas un entier");
            }
        });

        time2.addCaretListener(e -> {
            try{
                listEnigma.findByID(enigme.getId()).setClue2(new Hint(hint2.getText(), Integer.parseInt(time2.getText())));
            }
            catch (Exception ex){
                System.out.println("Le timer n'est pas un entier");
            }
        });

        time3.addCaretListener(e -> {
            try{
                listEnigma.findByID(enigme.getId()).setClue3(new Hint(hint3.getText(), Integer.parseInt(time3.getText())));
            }
            catch (Exception ex){
                System.out.println("Le timer n'est pas un entier");
            }
        });




        /* Layout PanelEnigme */


        GridLayout grille = new GridLayout(1,4,20,0);
        /* Construction Panel */


        storyPanel.add(story);
        storyPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));


        hint1Panel.add(hint1);
        hint1Panel.add(time1);
        hint1Panel.setLayout(gridHint);
        time1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        //hint1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));


        hint2Panel.add(hint2);
        hint2Panel.add(time2);
        hint2Panel.setLayout(gridHint);
        time2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        //hint2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));


        hint3Panel.add(hint3);
        hint3Panel.add(time3);
        hint3Panel.setLayout(gridHint);
        time3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        //hint3Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));


        infoEngimaPanel.add(answer);
        infoEngimaPanel.add(hint1Panel);
        infoEngimaPanel.add(hint2Panel);
        infoEngimaPanel.add(hint3Panel);
        infoEngimaPanel.setLayout(grille);

        scrollStory = new JScrollPane(story,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollStory.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        enigmaPan.setLayout(enigmaInfoLayout);
        enigmaPan.add(scrollStory,BorderLayout.CENTER);
        enigmaPan.add(infoEngimaPanel,BorderLayout.SOUTH);


        return enigmaPan;


    }


    private void majEnigma() {
        listEnigma.addEnigma(-1,"Énigme précédée de son histoire","Réponse(s) à l'énigme séparée par \" \\ \"","1er indice",-1,"2eme indice",-1,"3eme indice",-1);
        this.createList();
        frame.gameCreationDisplay(frame,game);
    }

    public void createList(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,20,30);
        enigmasPanel.removeAll();

        for (int i = 0; i < listEnigma.getSize(); i++) {
            centerPanel.remove(newPanel);
            JPanel panelEnigme = this.ajoutEnigme(listEnigma.getEnigma(i), gbc);
            panelEnigme.setPreferredSize(new Dimension(centerPanel.getWidth()-45, 300));
            enigmasPanel.add(panelEnigme, gbc);
            centerPanel.add(newPanel,BorderLayout.PAGE_END);
            centerPanel.revalidate();
            centerPanel.repaint();
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==newButton){
            this.majEnigma();
            scrollEnigmas.getVerticalScrollBar().setValue(scrollEnigmas.getVerticalScrollBar().getMaximum());
        }
        else if (e.getSource()== exitButton){
            frame.gameManagementDisplay(frame,frame.roomNumber);
        }
        else if(e.getSource() == rankingButton){
            new Ranking(this.game,null);
        }
        else if (e.getSource()==deleteButton){
            DBEnigma.deleteEnigma( listEnigma.getLastEnigma().getId());
            listEnigma.removeEnigma(listEnigma.getSize()-1);
            frame.gameCreationDisplay(frame,game);

        }
        else if (e.getSource()==saveButton){
            Enigma a = null;
            for(int i=0;i<listEnigma.getSize();i++) {
                a = listEnigma.getEnigma(i);
                String clue1 = a.getClue1();
                int timer1 = a.getTimer1();
                System.out.println("indice : "+clue1 + "| timer : " +timer1);
                if (clue1.equals("1er indice") || timer1 == -1){
                    JOptionPane.showMessageDialog(frame, "Un engime doit avoir au moins un indice associé a un timer", "", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            boolean ajout = false;
            String titre = title.getText();
            int score = 0;
            try{
                score = Integer.parseInt(initialScore.getText());
            }
            catch (Exception exception){
                System.out.println("Le score n'est pas un entier");
            }
            int idUser = Main.idAdmin;
            int timer = 0;
            boolean ready = true;
            String endMessage = winMessage.getText();
            if(game!=null){
                DBGame.majGame(game.getId(), titre,score,timer,ready,endMessage);
            }
            else{
                int id = DBGame.insertGame(titre,score,idUser,timer,ready,endMessage);
                game = new Game(id,titre,score,idUser,timer,ready,endMessage);
                ajout = true;

            }

            Enigma enigme = null;
            for(int i=0;i<listEnigma.getSize();i++){
                enigme = listEnigma.getEnigma(i);
                int id = enigme.getId();
                String text = enigme.getText();
                System.out.println();
                String answer = enigme.getAnswer();
                String clue1 = enigme.getClue1();
                String clue2 = enigme.getClue2();
                String clue3 = enigme.getClue3();
                if (clue1.equals("1er indice")){
                    clue1=null;

                }
                if (clue2==null ||clue2.equals("2eme indice")){
                    clue2=null;
                }
                if (clue3==null || clue3.equals("3eme indice")){
                    clue3=null;
                }
                int timer1 = enigme.getTimer1();
                int timer2 = enigme.getTimer2();
                int timer3 = enigme.getTimer3();

                if(DBEnigma.isInDB(id)){
                    System.out.println("MAJ");
                    DBEnigma.majEnigma(id, text,answer, clue1, timer1,clue2, timer2,clue3, timer3);
                }
                else{
                    System.out.println("INSERT");
                    DBEnigma.insertEnigma(game.getId(),text,answer, clue1, timer1,clue2, timer2,clue3, timer3);
                }
            }
            String message;
            if(ajout){
                message = "Ajout effectué";
            }
            else{
                message = "Mise à jour effectuée";
            }
            JOptionPane.showMessageDialog(frame, message, "", JOptionPane.INFORMATION_MESSAGE);
            frame.gameCreationDisplay(frame,game);
        }
        else if(e.getSource()==rankingButton){
            System.out.println("nb enigme : "+listEnigma.getSize());
            for(int i=0;i<listEnigma.getSize();i++){
                System.out.println("i :"+i);
                System.out.println("id : "+listEnigma.getEnigma(i).getId());
                System.out.println("reponse : "+listEnigma.getEnigma(i).getAnswer());
            }
            System.out.println("---------------------------------");
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
        if(e.getSource()==exitButton){
            exitButton.setBackground(ColorPerso.azurHoover);
        }
        else if(e.getSource()==saveButton){
            saveButton.setBackground(ColorPerso.vertHoover);
        }
        else if(e.getSource()==deleteButton){
            deleteButton.setBackground(ColorPerso.rougeHoover);
        }
        else if(e.getSource()==newButton){
            newButton.setBackground(Color.black);
            newButton.setForeground(Color.white);
            newButton.setOpaque(true);
        }
        else if(e.getSource()==rankingButton && rankingButton.isEnabled()){
            rankingButton.setBackground(ColorPerso.azurHoover);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==exitButton){
            exitButton.setBackground(ColorPerso.azur);
        }
        else if(e.getSource()==saveButton){
            saveButton.setBackground(ColorPerso.vert);
        }
        else if(e.getSource()==deleteButton){
            deleteButton.setBackground(ColorPerso.rouge);
        }
        else if(e.getSource()==newButton){
            newButton.setForeground(ColorPerso.white);
            newButton.setBackground(ColorPerso.grisFonce);
        }
        else if(e.getSource()==rankingButton && rankingButton.isEnabled()){
            rankingButton.setBackground(ColorPerso.azur);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==title){
            if(title.getText().equals("Titre du jeu")){
                title.setText("");
                title.setForeground(Color.black);
                title.setFont(title.getFont().deriveFont(Font.PLAIN));
            }
        }
        else if(e.getSource()==initialScore){
            if(initialScore.getText().equals("Score Initial")){
                initialScore.setText("");
                initialScore.setForeground(Color.black);
                initialScore.setFont(initialScore.getFont().deriveFont(Font.PLAIN));
            }
        }
        else if(e.getSource()==winMessage){
            if(winMessage.getText().equals("Fin de l'histoire")) {
                winMessage.setText("");
                winMessage.setForeground(Color.black);
                winMessage.setFont(winMessage.getFont().deriveFont(Font.PLAIN));
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource()==title){
            if(title.getText().equals("")){
                title.setText("Titre du jeu");
                title.setForeground(Color.gray);
                title.setFont(title.getFont().deriveFont(Font.ITALIC));
            }
        }
        else if(e.getSource()==initialScore){
            if(initialScore.getText().equals("")){
                initialScore.setText("Score Initial");
                initialScore.setForeground(Color.gray);
                initialScore.setFont(initialScore.getFont().deriveFont(Font.ITALIC));
            }
        }
        else if(e.getSource()==winMessage){
            if(winMessage.getText().equals("")) {
                winMessage.setText("Fin de l'histoire");
                winMessage.setForeground(Color.gray);
                winMessage.setFont(winMessage.getFont().deriveFont(Font.ITALIC));
            }
        }
    }
}