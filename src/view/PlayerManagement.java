package view;

import model.EnigmaList;
import view.style.ColorPerso;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static database.DBEnigma.getEnigmas;
import static database.DBGame.getTitleGame;

public class PlayerManagement extends JPanel implements ActionListener{

    public JPanel topPan = new JPanel();
    public JPanel titlePan = new JPanel();
    public JPanel titlePanIn = new JPanel();
    public JPanel timerPan = new JPanel();
    public JPanel timerPanIn = new JPanel();
    public JPanel currentStoryPan = new JPanel();
    public JPanel currentStoryPanIn = new JPanel();
    public JPanel answersPan = new JPanel();
    public JPanel answersPanIn = new JPanel();
    public JPanel helpGMPan = new JPanel();
    public JPanel helpGMPanIn = new JPanel();
    public JPanel helpMessageGMPan = new JPanel();
    public JPanel helpButtonGMPan= new JPanel();
    public JPanel helpButtonGMPanIn = new JPanel();
    public JPanel bottomPan = new JPanel();
    public JPanel bottomPanIn = new JPanel();
    public JPanel buttonReturnPan = new JPanel();
    public JPanel buttonReturnPanIn =new JPanel();
    public JPanel buttonHint1Pan = new JPanel();
    public JPanel buttonHint1PanIn =new JPanel();
    public JPanel buttonHint2Pan = new JPanel();
    public JPanel buttonHint2PanIn =new JPanel();
    public JPanel buttonHint3Pan = new JPanel();
    public JPanel buttonHint3PanIn =new JPanel();

    public JScrollPane scrollCurrentStoryPanIn = new JScrollPane(currentStoryPanIn,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    public JScrollPane scrollAnswersPanIn = new JScrollPane(answersPanIn,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    public JLabel title = new JLabel();
    public JLabel timer = new JLabel();
    public JLabel currentStory = new JLabel();
    public JLabel answers = new JLabel();
    public JTextField helpMessageGM = new JTextField();
    public JButton helpButtonGM = new JButton("Envoyer");
    public JButton buttonReturn = new JButton("Return");
    public JButton buttonHint1;
    public JButton buttonHint2;
    public JButton buttonHint3;

    public GlobalFrame frame;
    public int gameNb;
    public int riddleNb;
    public boolean boolHint1;
    public boolean boolHint2;
    public boolean boolHint3;

    public PlayerManagement(GlobalFrame frame,int gameNb, int riddleNb, boolean boolHint1Revealed, boolean boolHint2Revealed,
                            boolean boolHint3Revealed){
        this.frame = frame;
        this.gameNb = gameNb;
        this.riddleNb = riddleNb;
        this.boolHint1 = boolHint1Revealed;
        this.boolHint2 = boolHint2Revealed;
        this.boolHint3 = boolHint3Revealed;

        EnigmaList currentRiddles = getEnigmas(gameNb); // la liste des énigme du jeux

        helpButtonGM.addActionListener(this);
        helpButtonGM.setBackground(Color.white);
        helpButtonGM.setForeground(Color.black);
        helpButtonGM.setOpaque(true);

        buttonReturn.addActionListener(this);
        buttonReturn.setBackground(ColorPerso.rouge);
        buttonReturn.setForeground(Color.white);
        buttonReturn.setOpaque(true);

        if(boolHint1==false){
            buttonHint1 = new JButton("Afficher l'indice 1");
            buttonHint1.addActionListener(this);
            buttonHint1.setBackground(Color.white);
            buttonHint1.setBackground(Color.black);
        }else{
            buttonHint1 = new JButton("Indice 1 déjà affiché");
            buttonHint1.setBackground(Color.gray);
            buttonHint1.setForeground(Color.black);
        }
        buttonHint1.setOpaque(true);

        if(boolHint2==false){
            buttonHint2 = new JButton("Afficher l'indice 2");
            buttonHint2.addActionListener(this);
            buttonHint2.setBackground(Color.white);
            buttonHint2.setBackground(Color.black);
        }else{
            buttonHint2 = new JButton("Indice 2 déjà affiché");
            buttonHint2.setBackground(Color.gray);
            buttonHint2.setForeground(Color.black);
        }
        buttonHint2.setOpaque(true);

        if(boolHint3==false){
            buttonHint3 = new JButton("Afficher l'indice 3");
            buttonHint3.addActionListener(this);
            buttonHint3.setBackground(Color.white);
            buttonHint3.setBackground(Color.black);
        }else{
            buttonHint3 = new JButton("Indice 3 déjà affiché");
            buttonHint3.setBackground(Color.gray);
            buttonHint3.setForeground(Color.black);
        }
        buttonHint3.setOpaque(true);

        titlePanIn.add(title);
        titlePanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        titlePan.add(titlePanIn);
        titlePan.setBorder(BorderFactory.createEmptyBorder(0,0,10,10));
        timerPanIn.add(timer);
        timerPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        timerPan.add(timerPanIn);
        timerPan.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        topPan.setLayout(new BoxLayout(topPan, BoxLayout.LINE_AXIS));
        topPan.add(titlePan);
        topPan.add(timerPan);

        //Baptiste fonction récupérer le texte de l'histoire en cours

        title.setText(getTitleGame(gameNb));
        currentStory.setText((currentRiddles.getEnigma(riddleNb - 1)).getText());

        // variante ou on prend toute l'histoire
        /*
        String currentStoryStr = "";
        for(int i = 0; i < riddleNb; i++){
            currentStoryStr = currentStoryStr + (currentRiddles.getEnigma(i)).getText();
        }*/

        // Fin fonction récupérer le texte de l'histoire en cours

        currentStoryPanIn.add(currentStory);
        scrollCurrentStoryPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        currentStoryPan.add(scrollCurrentStoryPanIn);
        currentStoryPan.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        //Baptiste fonction récupérer les réponses déjà tentées


        // Faire une action qui récupère les réponsse donné

        //Baptiste fonction récupérer la réponse attendue

        answers.setText((currentRiddles.getEnigma(riddleNb -1)).getAnswer());

        // Fin de fonction récupérer la réponse attendue

        answersPanIn.add(answers);
        scrollAnswersPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        answersPan.add(scrollAnswersPanIn);
        answersPan.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        helpMessageGMPan.add(helpMessageGM);
        helpButtonGMPanIn.add(helpButtonGM);
        helpButtonGMPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        helpButtonGMPan.add(helpButtonGMPanIn);
        helpButtonGMPan.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        helpGMPanIn.setLayout(new BoxLayout(topPan, BoxLayout.LINE_AXIS));
        helpGMPanIn.add(helpMessageGMPan);
        helpGMPanIn.add(helpButtonGMPan);
        helpGMPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        helpGMPan.add(helpGMPanIn);
        helpGMPan.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        //Baptiste fonction récupérer hint1

        if(boolHint1){
            String hint1Str = (currentRiddles.getEnigma(riddleNb - 1)).getClue1();
        }

        //Baptiste fonction récupérer hint2

        if(boolHint2){
            String hint2Str = (currentRiddles.getEnigma(riddleNb - 1)).getClue1();
        }

        //Baptiste fonction récupérer hint3

        if(boolHint3){
            String hint3Str = (currentRiddles.getEnigma(riddleNb - 1)).getClue1();
        }

        buttonReturnPanIn.add(buttonReturn);
        buttonReturnPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        buttonReturnPan.add(buttonReturnPanIn);
        buttonReturnPan.setBorder(BorderFactory.createEmptyBorder(20,0,0,20));
        buttonHint1PanIn.add(buttonHint1);
        buttonHint1PanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        buttonHint1Pan.add(buttonHint1PanIn);
        buttonHint1Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        buttonHint2PanIn.add(buttonHint2);
        buttonHint2PanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        buttonHint2Pan.add(buttonHint2PanIn);
        buttonHint2Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        buttonHint3PanIn.add(buttonHint3);
        buttonHint3PanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        buttonHint3Pan.add(buttonHint3PanIn);
        buttonHint3Pan.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        bottomPanIn.setLayout(new BoxLayout(topPan, BoxLayout.LINE_AXIS));
        bottomPanIn.add(buttonReturnPan);
        bottomPanIn.add(buttonHint1Pan);
        bottomPanIn.add(buttonHint2Pan);
        bottomPanIn.add(buttonHint3Pan);
        bottomPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        bottomPan.add(bottomPanIn);
        bottomPan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        Border mainEdge = BorderFactory.createEmptyBorder(10,10,10,10);
        this.setBorder(mainEdge);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(topPan);
        this.add(currentStoryPan);
        this.add(answersPan);
        this.add(helpGMPan);
        this.add(bottomPan);




    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==helpButtonGM){
            //Baptiste fonction envoyer le message aux joueurs
        }else if(e.getSource() == buttonReturn){
            frame.roomManagementDisplay(frame);
        }else if(e.getSource()==buttonHint1){
            //Baptiste fonction afficher indice 1 pour les joueurs

        }else if(e.getSource()==buttonHint2){

        }else if(e.getSource()==buttonHint3){

            String messageFromMJ = helpButtonGM.getText();
        }
    }
}