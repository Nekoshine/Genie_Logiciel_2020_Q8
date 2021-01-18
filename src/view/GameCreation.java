// Interface dévelopée par Cédric Plantet

package view;

import database.DBEnigma;
import database.DBGame;
import database.DBRoom;
import launcher.Main;
import model.*;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GameCreation extends JPanel implements ActionListener {

    public EnigmaList listEnigma;
    public Game game;

    private BorderLayout mainLayout;
    private BorderLayout titleLayout;
    private BorderLayout centerLayout;
    private BorderLayout newLayout;

    private GridBagLayout buttonLayout;

    private GridBagLayout gridInfo;
    private GridBagLayout gridEnigma;
    private GridBagLayout gridWin;


    private GridBagConstraints gbcEnigma;
    private GridBagConstraints gbcWin;

    private GridLayout grid;
    private GridLayout gridHint;


    private JScrollPane scrollEnigmas;


    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JPanel titleNamePanel;
    private JPanel defaultScorePanel;
    private JPanel pointsPanel;
    private JPanel winPanel;

    private JPanel centerPanel;
    private JPanel storyPanel;
    private JPanel enigmasPanel;
    private JPanel newPanel;
    private JPanel answerPanel;
    private JPanel hint1Panel;
    private JPanel hint2Panel;
    private JPanel hint3Panel;
    private JPanel buttonNewPanel;
    private JPanel winMessagePanel;



    private JButton exitButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton newButton;
    private JButton rankingButton;

    private JLabel windowName;
    private JLabel winMessageLabel;

    private JTextField initialScore;
    private JTextField points;
    private JTextField title;
    private JTextField winMessage;

    private GlobalFrame frame;

    private static volatile GameCreation INSTANCE = new GameCreation(Main.frame,0,null);

    private GameCreation(GlobalFrame frame, int roomNumber, Game game){

        this.frame = frame;
        this.game=game;
        this.listEnigma = Main.ListEnigma;

        mainLayout = new BorderLayout(10,10);
        titleLayout= new BorderLayout(10,10);
        newLayout = new BorderLayout(10,10);

        buttonNewPanel = new JPanel();

        centerLayout = new BorderLayout(30,30);

        GridBagConstraints gbcTitle = new GridBagConstraints();
        GridBagConstraints gbcScores = new GridBagConstraints();
        gbcEnigma = new GridBagConstraints();

        gridEnigma = new GridBagLayout();
        buttonLayout = new GridBagLayout();
        gridInfo = new GridBagLayout();

        grid = new GridLayout(1,0);
        gridHint = new GridLayout(2,0,10,10);

        centerPanel = new JPanel();
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        infoPanel = new JPanel();
        titleNamePanel = new JPanel();
        defaultScorePanel = new JPanel();
        pointsPanel = new JPanel();
        newPanel = new JPanel();
        enigmasPanel = new JPanel();
        winPanel = new JPanel();
        winMessagePanel = new JPanel();

        saveButton = new JButton("Enregistrer");
        exitButton = new JButton("Quitter");
        deleteButton = new JButton("Supprimer");
        newButton = new JButton("Nouvelle Enigme");
        rankingButton = new JButton("Classement");

        if(game==null) {
            title = new JTextField("Titre", 45);
            initialScore = new JTextField("Score Initial", 7);
            winMessage = new JTextField();
        }
        else {
            title = new JTextField(game.getTitre(),45);
            initialScore = new JTextField(String.valueOf(game.getScore()),7);
            winMessage = new JTextField(game.getEndMessage());
        }
        points = new JTextField("Points (Si désiré)",7);

        winMessageLabel = new JLabel("Message de fin :",SwingConstants.RIGHT);
        JPanel winLabelPanel = new JPanel();
        winLabelPanel.add(winMessageLabel);





        windowName = new JLabel("MJ - Création/Modification de Jeux",JLabel.CENTER);

        //Center

        newButton.setOpaque(false);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonNewPanel.add(newButton);

        gridWin = new GridBagLayout();
        gbcWin = new GridBagConstraints();

        gbcWin.weightx = 1;
        gbcWin.insets = new Insets(0,0,0,100);

        winPanel.setLayout(gridWin);
        gbcWin.fill = GridBagConstraints.HORIZONTAL;
        winPanel.add(winMessageLabel,gbcWin);

        gbcWin.weightx = 2;
        gbcWin.insets = new Insets(0,0,0,200);


        winMessagePanel.setLayout(gridWin);
        winMessagePanel.add(winMessage,gbcWin);

        winPanel.add(winMessagePanel,gbcWin);

        newPanel.setLayout(newLayout);
        newPanel.add(buttonNewPanel,BorderLayout.NORTH);
        newPanel.add(winPanel,BorderLayout.CENTER);
        newPanel.setBorder(BorderFactory.createEmptyBorder(0,0,25,0));

        centerPanel.setLayout(centerLayout);

        enigmasPanel.setLayout(gridEnigma);

        scrollEnigmas = new JScrollPane(enigmasPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollEnigmas.setBorder(BorderFactory.createLineBorder(Color.red));
        scrollEnigmas.getVerticalScrollBar().setUnitIncrement(20);
        scrollEnigmas.setBorder(BorderFactory.createEmptyBorder());


        centerPanel.add(newPanel,BorderLayout.SOUTH);



        /* Chargement des énigmes */

        this.createList();

        newButton.addActionListener(this);

        /* Setup Marges */

        Border listPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);


        centerPanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        centerPanel.add(scrollEnigmas,BorderLayout.CENTER);


        title.setBorder(BorderFactory.createEmptyBorder());
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setFont(FontPerso.Oxanimum);
        initialScore.setBorder(BorderFactory.createEmptyBorder());
        initialScore.setHorizontalAlignment(JTextField.CENTER);
        initialScore.setFont(FontPerso.Oxanimum);
        rankingButton.setHorizontalAlignment(JButton.CENTER);
        rankingButton.setBackground(ColorPerso.azur);
        rankingButton.setForeground(Color.white);

        if (listEnigma.getSize()==0){
            rankingButton.setEnabled(false);
            rankingButton.setBackground(Color.darkGray);
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
        defaultScorePanel.add(initialScore);
        pointsPanel.add(rankingButton);

        infoPanel.setLayout(gridInfo);
        infoPanel.add(titleNamePanel,gbcTitle);
        infoPanel.add(defaultScorePanel,gbcScores);
        infoPanel.add(pointsPanel,gbcScores);

        titleNamePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
        defaultScorePanel.setBorder(BorderFactory.createLineBorder(Color.black,2));

        // Boutons

        exitButton.setBackground(ColorPerso.azur);
        exitButton.setForeground(Color.white);
        exitButton.addActionListener(this);

        saveButton.setBackground(ColorPerso.vert);
        saveButton.setForeground(Color.white);
        saveButton.addActionListener(this);

        deleteButton.setBackground(ColorPerso.rouge);
        deleteButton.setForeground(Color.white);
        deleteButton.addActionListener(this);

        buttonPanel.setLayout(buttonLayout);
        buttonPanel.add(exitButton,gbcScores);
        buttonPanel.add(saveButton,gbcScores);
        buttonPanel.add(deleteButton,gbcScores);

        windowName.setBorder(BorderFactory.createLineBorder(Color.black,2));

        Border mainPadding = BorderFactory.createEmptyBorder(10, 10, 0, 10);
        Border buttonsPadding = BorderFactory.createEmptyBorder(5,0,10,0);
        this.setBorder(mainPadding);
        buttonPanel.setBorder(buttonsPadding);

        titlePanel.setLayout(titleLayout);
        titlePanel.add(windowName,BorderLayout.NORTH);
        titlePanel.add(infoPanel,BorderLayout.SOUTH);

        title.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
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

            }
        });


        this.setLayout(mainLayout);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(titlePanel,BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);


    }
    public static GameCreation getInstance(GlobalFrame frame, int roomNumber, Game game) {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (INSTANCE == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(INSTANCE) {
                if (INSTANCE == null) {
                    INSTANCE = new GameCreation(frame, roomNumber,game);
                }
            }
        }
        else {
            INSTANCE.frame = frame;
            if (game != null){
                INSTANCE.title.setText(game.getTitre());
                INSTANCE.initialScore.setText(String.valueOf(game.getScore()));
                INSTANCE.winMessage.setText(game.getEndMessage());
            }
            else{
                INSTANCE.title.setText("Titre");
                INSTANCE.initialScore.setText("Score Initial");
                INSTANCE.winMessage.setText("");
            }
            INSTANCE.listEnigma=Main.ListEnigma;
            INSTANCE.createList();
            if (INSTANCE.listEnigma.getSize()==0){
                INSTANCE.rankingButton.setEnabled(false);
                INSTANCE.rankingButton.setBackground(Color.darkGray);
            }
            INSTANCE.game=game;
            //INSTANCE.exitButton.setBackground(ColorPerso.rouge);
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
        story.setLineWrap(true);
        story.setWrapStyleWord(true);
        story.setFont(FontPerso.Oxanimum);
        story.setMargin(new Insets(5,5,5,5));
        story.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                listEnigma.findByID(enigme.getId()).setQuestion(story.getText());
            }
        });


        // Answer Components

        JTextField answer = new JTextField(enigme.getAnswer());
        answer.setBorder(BorderFactory.createLineBorder(Color.black,2));
        answer.setFont(FontPerso.Oxanimum);
        answer.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                listEnigma.findByID(enigme.getId()).setAnswer(answer.getText());
            }
        });

        // Timer Components


        JTextField time1;

        if (enigme.getTimer1()==-1){
            time1 = new JTextField("Timer 1 (en s)");
        }
        else{
            time1 = new JTextField(String.valueOf(enigme.getTimer1()));
        }

        time1.setFont(FontPerso.Oxanimum);

        JTextField time2;

        if (enigme.getTimer2()==-1){
            time2 = new JTextField("Timer 2 (en s)");
        }
        else{
            time2 = new JTextField(String.valueOf(enigme.getTimer2()));
        }

        time2.setFont(FontPerso.Oxanimum);

        JTextField time3;

        if (enigme.getTimer3()==-1){
            time3 = new JTextField("Timer 3 (en s)");

        }
        else{
            time3 = new JTextField(String.valueOf(enigme.getTimer3()));
        }

        time3.setFont(FontPerso.Oxanimum);

        // Clue Components

        JTextField hint1 = new JTextField(enigme.getClue1());
        hint1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hint1.setFont(FontPerso.Oxanimum);
        hint1.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try{
                    int i = Integer.parseInt(time1.getText());
                    listEnigma.findByID(enigme.getId()).setClue1(new Hint(hint1.getText(), Integer.parseInt(time1.getText())));
                }
                catch (Exception ex){
                    System.out.println("Le timer n'est pas un entier");
                }
            }
        });

        JTextField hint2 = new JTextField(enigme.getClue2());
        hint2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hint2.setFont(FontPerso.Oxanimum);
        hint2.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try{
                    int i = Integer.parseInt(time2.getText());
                    listEnigma.findByID(enigme.getId()).setClue2(new Hint(hint2.getText(), Integer.parseInt(time2.getText())));
                }
                catch (Exception ex){
                    System.out.println("Le timer n'est pas un entier");
                }
            }
        });

        JTextField hint3 = new JTextField(enigme.getClue3());
        hint3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hint3.setFont(FontPerso.Oxanimum);
        hint3.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try{
                    int i = Integer.parseInt(time3.getText());
                    listEnigma.findByID(enigme.getId()).setClue3(new Hint(hint3.getText(), Integer.parseInt(time3.getText())));
                }
                catch (Exception ex){
                    System.out.println("Le timer n'est pas un entier");
                }
            }
        });

        /* Caret Listener Time */

        time1.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try{
                    int i = Integer.parseInt(time1.getText());
                    listEnigma.findByID(enigme.getId()).setClue1(new Hint(hint1.getText(), Integer.parseInt(time1.getText())));
                }
                catch (Exception ex){
                    System.out.println("Le timer n'est pas un entier");
                }
            }
        });

        time2.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try{
                    int i = Integer.parseInt(time2.getText());
                    listEnigma.findByID(enigme.getId()).setClue2(new Hint(hint2.getText(), Integer.parseInt(time2.getText())));
                }
                catch (Exception ex){
                    System.out.println("Le timer n'est pas un entier");
                }
            }
        });

        time3.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try{
                    int i = Integer.parseInt(time3.getText());
                    listEnigma.findByID(enigme.getId()).setClue3(new Hint(hint3.getText(), Integer.parseInt(time3.getText())));
                }
                catch (Exception ex){
                    System.out.println("Le timer n'est pas un entier");
                }
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
        listEnigma.addEnigma(listEnigma.getSize()+1,"Enigme","Réponse","indice 1",-1,"indice 2",-1,"indice 3",-1);
        this.createList();
        frame.gameCreationDisplay(frame,frame.roomNumber,game);
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

        }
        else if (e.getSource()== exitButton){
            frame.gameManagementDisplay(frame,frame.roomNumber);
        }
        else if (e.getSource()==deleteButton){
            listEnigma.removeEnigma(listEnigma.getSize()-1);
            frame.gameCreationDisplay(frame,frame.roomNumber,game);

        }
        else if (e.getSource()==saveButton){
            Enigma a = null;
            for(int i=0;i<listEnigma.getSize();i++) {
                a = listEnigma.getEnigma(i);
                String clue1 = a.getClue1();
                int timer1 = a.getTimer1();
                if (clue1.equals("indice 1") || timer1 == -1){
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
            int idUser = Main.idUser;
            int timer = 0;
            boolean ready = true;
            String endMessage = winMessage.getText();
            if(game!=null){
                DBGame. majGame(game.getId(), titre,score,timer,ready,endMessage);
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
                if (clue1.equals("indice 1")){
                    clue1=null;

                }
                if (clue2==null ||clue2.equals("indice 2")){
                    clue2=null;
                }
                if (clue3==null || clue3.equals("indice 3")){
                    clue3=null;
                }
                int timer1 = enigme.getTimer1();
                int timer2 = enigme.getTimer2();
                int timer3 = enigme.getTimer3();

                if(DBEnigma.isInDB(id)){
                    DBEnigma.majEnigma(id, text,answer, clue1, timer1,clue2, timer2,clue3, timer3);
                }
                else{
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
        }
    }
}