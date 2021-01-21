package view;

import database.DBEnigma;
import database.DBGame;
import launcher.Main;
import model.Enigma;
import model.EnigmaList;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerManagement extends JPanel implements ActionListener{

    private JPanel currentStoryPanIn = new JPanel();
    private JPanel answersPanIn = new JPanel();

    private JButton helpButtonGM;
    private JButton buttonReturn;
    private JButton buttonHint1;
    private JButton buttonHint2;
    private JButton buttonHint3;

    public GlobalFrame frame;
    private boolean boolHint1;
    private boolean boolHint2;
    private boolean boolHint3;

    private JTextArea currentStory;
    private EnigmaList currentRiddles;
    private JLabel title;
    private JLabel answers;

    private static volatile PlayerManagement INSTANCE = new PlayerManagement(Main.frame,-1,1,false,false,false);

    public PlayerManagement(GlobalFrame frame,int gameNb, int riddleNb, boolean boolHint1Revealed, boolean boolHint2Revealed, boolean boolHint3Revealed){
        this.frame = frame;
        this.boolHint1 = boolHint1Revealed;
        this.boolHint2 = boolHint2Revealed;
        this.boolHint3 = boolHint3Revealed;

        int width = (int) frame.windowSize.getWidth();
        int height = (int) frame.windowSize.getHeight();

        if(gameNb!=-1) {
            currentRiddles = DBEnigma.getEnigmas(gameNb); // la liste des énigmes du jeu
        }
        else{
            currentRiddles = new EnigmaList();
            currentRiddles.addEnigma(new Enigma(1,1,"","","",1,"",1,"",3));
        }
        helpButtonGM = new JButton("Envoyer");
        helpButtonGM.addActionListener(this);
        helpButtonGM.setBackground(Color.LIGHT_GRAY);
        helpButtonGM.setForeground(Color.black);
        helpButtonGM.setOpaque(true);

        buttonReturn = new JButton("Retour");
        buttonReturn.addActionListener(this);
        buttonReturn.setBackground(ColorPerso.rouge);
        buttonReturn.setForeground(Color.white);
        buttonReturn.setOpaque(true);

        buttonHint1 = this.hintButton(1);
        buttonHint2 = this.hintButton(2);
        buttonHint3 = this.hintButton(3);

        title = new JLabel();
        title.setText(DBGame.getTitleGame(gameNb));

        JPanel titlePanIn = new JPanel();
        titlePanIn.setPreferredSize(new Dimension((int) ((width-40)*0.7),(int) ((height-90)*0.06)));
        titlePanIn.setBackground(Color.LIGHT_GRAY);
        titlePanIn.add(title, CENTER_ALIGNMENT);
        titlePanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel titlePan = new JPanel();
        titlePan.setBackground(ColorPerso.darkGray);
        titlePan.setBorder(BorderFactory.createEmptyBorder(0,0,10,10));
        titlePan.add(titlePanIn);
        JLabel timer = new JLabel();
        JPanel timerPanIn = new JPanel();
        timerPanIn.setPreferredSize(new Dimension((int)((width-40)*0.3),(int) ((height-90)*0.06)));
        timerPanIn.setBackground(Color.LIGHT_GRAY);
        timerPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        timerPanIn.add(timer);
        JPanel timerPan = new JPanel();
        timerPan.setBackground(ColorPerso.darkGray);
        timerPan.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        timerPan.add(timerPanIn);
        JPanel topPan = new JPanel();
        topPan.setBackground(ColorPerso.darkGray);
        topPan.setLayout(new BoxLayout(topPan, BoxLayout.LINE_AXIS));
        topPan.add(titlePan);
        topPan.add(timerPan);

        currentStory = new JTextArea();
        currentStory.setLineWrap(true);
        currentStory.setWrapStyleWord(true);
        currentStory.setBackground(Color.LIGHT_GRAY);
        currentStory.setFont(FontPerso.Oxanimum);
        currentStory.setEditable(false);
        currentStory.setPreferredSize(new Dimension(width-20,(height-90)*22/100-10));
        currentStory.setText((currentRiddles.getEnigma(riddleNb - 1)).getText());


        JScrollPane scrollCurrentStoryPanIn = new JScrollPane(currentStoryPanIn,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCurrentStoryPanIn.setPreferredSize(new Dimension(width-20,(height-90)*22/100));
        scrollCurrentStoryPanIn.setBackground(Color.LIGHT_GRAY);
        currentStoryPanIn.setBackground(Color.LIGHT_GRAY);
        currentStoryPanIn.add(currentStory);
        scrollCurrentStoryPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel currentStoryPan = new JPanel();
        currentStoryPan.setBackground(ColorPerso.darkGray);
        currentStoryPan.add(scrollCurrentStoryPanIn);
        currentStoryPan.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));

        answers = new JLabel();
        answers.setText((currentRiddles.getEnigma(riddleNb -1)).getAnswer());

        JScrollPane scrollAnswersPanIn = new JScrollPane(answersPanIn,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollAnswersPanIn.setPreferredSize(new Dimension((int) (width-20),(int) ((height-90)*0.235)));
        scrollAnswersPanIn.setBackground(Color.LIGHT_GRAY);
        answersPanIn.setBackground(Color.LIGHT_GRAY);
        answersPanIn.add(answers);
        scrollAnswersPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel answersPan = new JPanel();
        answersPan.setBackground(ColorPerso.darkGray);
        answersPan.add(scrollAnswersPanIn);
        answersPan.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));

        JTextArea helpMessageGM = new JTextArea();
        helpMessageGM.setLineWrap(true);
        helpMessageGM.setWrapStyleWord(true);
        helpMessageGM.setFont(FontPerso.Oxanimum);
        helpMessageGM.setPreferredSize(new Dimension((int) (width-200-helpButtonGM.getWidth()), (int) ((height-150)*0.235)));
        JPanel helpMessageGMPan = new JPanel();
        helpMessageGMPan.setBackground(Color.LIGHT_GRAY);
        helpMessageGMPan.add(helpMessageGM);
        JPanel helpButtonGMPanIn = new JPanel();
        helpButtonGMPanIn.setBackground(Color.white);
        helpButtonGMPanIn.add(helpButtonGM);
        helpButtonGMPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel helpButtonGMPan = new JPanel();
        helpButtonGMPan.setBackground(Color.LIGHT_GRAY);
        helpButtonGMPan.add(helpButtonGMPanIn);
        helpButtonGMPan.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JPanel helpGMPanIn = new JPanel();
        helpGMPanIn.setBackground(Color.LIGHT_GRAY);
        helpGMPanIn.setPreferredSize(new Dimension((int) (width-20), (int) ((height-90)*0.235)));
        helpGMPanIn.setLayout(new BoxLayout(helpGMPanIn, BoxLayout.LINE_AXIS));
        helpGMPanIn.add(helpMessageGMPan);
        helpGMPanIn.add(helpButtonGMPan);
        helpGMPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel helpGMPan = new JPanel();
        helpGMPan.setBackground(ColorPerso.darkGray);
        helpGMPan.add(helpGMPanIn);
        helpGMPan.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        JPanel buttonReturnPanIn = new JPanel();
        buttonReturnPanIn.setBackground(Color.white);
        buttonReturnPanIn.add(buttonReturn);
        buttonReturnPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel buttonReturnPan = new JPanel();
        buttonReturnPan.setBackground(Color.LIGHT_GRAY);
        buttonReturnPan.add(buttonReturnPanIn);
        buttonReturnPan.setBorder(BorderFactory.createEmptyBorder(20,0,0,20));
        JPanel buttonHint1PanIn = new JPanel();
        buttonHint1PanIn.setBackground(Color.white);
        buttonHint1PanIn.add(buttonHint1);
        buttonHint1PanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel buttonHint1Pan = new JPanel();
        buttonHint1Pan.setBackground(Color.LIGHT_GRAY);
        buttonHint1Pan.add(buttonHint1PanIn);
        buttonHint1Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        JPanel buttonHint2PanIn = new JPanel();
        buttonHint2PanIn.setBackground(Color.white);
        buttonHint2PanIn.add(buttonHint2);
        buttonHint2PanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel buttonHint2Pan = new JPanel();
        buttonHint2Pan.setBackground(Color.LIGHT_GRAY);
        buttonHint2Pan.add(buttonHint2PanIn);
        buttonHint2Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        JPanel buttonHint3PanIn = new JPanel();
        buttonHint3PanIn.setBackground(Color.white);
        buttonHint3PanIn.add(buttonHint3);
        buttonHint3PanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JPanel buttonHint3Pan = new JPanel();
        buttonHint3Pan.setBackground(Color.LIGHT_GRAY);
        buttonHint3Pan.add(buttonHint3PanIn);
        buttonHint3Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        JPanel bottomPan = new JPanel();
        bottomPan.setBackground(ColorPerso.darkGray);
        bottomPan.setPreferredSize(new Dimension((int) (width-20),(int) ((height)*0.235)));
        JPanel bottomPanIn = new JPanel();
        bottomPanIn.setBackground(Color.LIGHT_GRAY);
        bottomPanIn.setLayout(new BoxLayout(bottomPanIn, BoxLayout.LINE_AXIS));
        bottomPanIn.add(buttonReturnPan);
        bottomPanIn.add(buttonHint1Pan);
        bottomPanIn.add(buttonHint2Pan);
        bottomPanIn.add(buttonHint3Pan);
        bottomPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        bottomPan.add(bottomPanIn);
        bottomPan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        Border mainEdge = BorderFactory.createEmptyBorder(10,10,10,10);
        this.setBorder(mainEdge);
        this.setBackground(ColorPerso.darkGray);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(topPan);
        this.add(currentStoryPan);
        this.add(answersPan);
        this.add(helpGMPan);
        this.add(bottomPan);

    }

    public static PlayerManagement getInstance(GlobalFrame frame,int gameNb, int riddleNb, boolean boolHint1Revealed, boolean boolHint2Revealed,
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
                    INSTANCE = new PlayerManagement(frame,gameNb, riddleNb, boolHint1Revealed, boolHint2Revealed,
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
            INSTANCE.currentRiddles = DBEnigma.getEnigmas(gameNb);
            INSTANCE.currentStory.setText((INSTANCE.currentRiddles.getEnigma(riddleNb - 1)).getText());
            INSTANCE.answers.setText((INSTANCE.currentRiddles.getEnigma(riddleNb -1)).getAnswer());

            if(INSTANCE.boolHint1){
                INSTANCE.buttonHint1.setText("Indice 1 déjà affiché");
            }
            else{
                INSTANCE.buttonHint1.setText("Afficher l'indice 1");
            }
            if(INSTANCE.boolHint2){
                INSTANCE.buttonHint2.setText("Indice 2 déjà affiché");
            }
            else{
                INSTANCE.buttonHint2.setText("Afficher l'indice 2");
            }
            if(INSTANCE.boolHint3){
                INSTANCE.buttonHint3.setText("Indice 3 déjà affiché");
            }
            else{
                INSTANCE.buttonHint3.setText("Afficher l'indice 3");
            }
        }
        return INSTANCE;
    }

    private JButton hintButton(int i){
        JButton button;
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
            button = new JButton("Afficher l'indice "+i);
            button.addActionListener(this);
            button.setBackground(Color.white);
        }else{
            button = new JButton("Indice " + i + " déjà affiché");
            button.setBackground(Color.lightGray);
        }
        button.setForeground(Color.black);
        button.setOpaque(true);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==helpButtonGM){
            //Baptiste fonction envoyer le message aux joueurs
            String messageFromMJ = helpButtonGM.getText();
        }else if(e.getSource() == buttonReturn){
            frame.roomManagementDisplay(frame);
        }else if(e.getSource()==buttonHint1){
            boolHint1=true;
            buttonHint1.setText("Indice 1 déjà affiché");
        }else if(e.getSource()==buttonHint2){
            boolHint2=true;
            buttonHint2.setText("Indice 2 déjà affiché");
        }else if(e.getSource()==buttonHint3){
            boolHint3=true;
            buttonHint3.setText("Indice 3 déjà affiché");
        }
    }
}