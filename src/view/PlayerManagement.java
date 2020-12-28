package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerManagement extends JPanel implements ActionListener{

    public JPanel topPan = new JPanel();
    public JPanel titlePan = new JPanel();
    public JPanel titlePanIn = new JPanel();
    public JPanel timerPan = new JPanel();
    public JPanel timerPanIn = new JPanel();
    public JPanel currentStoryPan = new JPanel();
    public JPanel currentStoryPanIn = new JPanel();
    public JPanel middlePan = new JPanel();
    public JPanel answerPan = new JPanel();
    public JPanel answerPanIn = new JPanel();
    public JPanel buttonValidatePan = new JPanel();
    public JPanel buttonValidatePanIn = new JPanel();
    public JPanel formerStoryPan = new JPanel();
    public JPanel formerStoryPanIn = new JPanel();
    public JPanel helperPan = new JPanel();
    public JPanel hint1Pan = new JPanel();
    public JPanel hint1PanIn = new JPanel();
    public JPanel hint2Pan = new JPanel();
    public JPanel hint2PanIn = new JPanel();
    public JPanel hint3Pan = new JPanel();
    public JPanel hint3PanIn = new JPanel();
    public JPanel helpGMPan = new JPanel();
    public JPanel helpGMPanIn = new JPanel();

    public JScrollPane scrollCurrentStoryPanIn = new JScrollPane(currentStoryPanIn,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    public JScrollPane scrollFormerStoryPanIn = new JScrollPane(formerStoryPanIn,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    public JLabel title = new JLabel("Titre");
    public JLabel timer = new JLabel("Timer");
    public JLabel currentStory = new JLabel("Current Story");
    public JTextField answer = new JTextField("Your Answer");
    public JButton buttonValidate = new JButton("check my answer");
    public JLabel formerStory = new JLabel("Former Story");
    public JLabel hint1 = new JLabel("Hint 1");
    public JLabel hint2 = new JLabel("Hint 2");
    public JLabel hint3 = new JLabel("Hint 3");
    public  JLabel helpGM;

    private GlobalFrame frame;
    private int gameNb;
    private int riddleNb;

    public PlayerManagement(GlobalFrame frame,int gameNb, int riddleNb, boolean boolHint1, boolean boolHint2,
                            boolean boolHint3, String stringHelp){
        this.frame = frame;
        this.gameNb = gameNb;
        this.riddleNb = riddleNb;

        titlePanIn.add(title);
        titlePanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        titlePan.add(titlePan);
        titlePan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        timerPanIn.add(timer);
        timerPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        timerPan.add(timerPanIn);
        timerPan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        topPan.setLayout(new BoxLayout(topPan,BoxLayout.LINE_AXIS));
        topPan.add(titlePan);
        topPan.add(timerPan);

        //Baptiste fonction récupérer histoire en cours
        currentStoryPanIn.add(currentStory);
        currentStoryPanIn.setBorder((BorderFactory.createLineBorder(Color.BLACK,2)));
        currentStoryPan.add(currentStoryPanIn);
        currentStoryPan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        answerPanIn.add(answer);
        answerPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        answerPan.add(answerPanIn);
        answerPan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        buttonValidatePanIn.add(buttonValidate);
        buttonValidatePanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        buttonValidatePan.add(buttonValidatePanIn);
        buttonValidatePan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        middlePan.setLayout(new BoxLayout(middlePan, BoxLayout.LINE_AXIS));
        middlePan.add(answerPan);
        middlePan.add(buttonValidatePan);

        //Baptiste fonction récupérer toutes les anciennes énigmes
        formerStoryPanIn.add(formerStory);
        formerStoryPanIn.setBorder((BorderFactory.createLineBorder(Color.BLACK,2)));
        formerStoryPan.add(formerStoryPanIn);
        formerStoryPan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        if(boolHint1==true){
            //Baptiste fonction récupérer hint1
        }else{
            hint1PanIn.setBackground(Color.GRAY);
        }
        hint1PanIn.add(hint1);
        hint1PanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hint1Pan.add(hint1PanIn);
        hint1Pan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        if(boolHint2==true){
            //Baptiste fonction récupérer hint2
        }else{
            hint2PanIn.setBackground(Color.GRAY);
        }
        hint2PanIn.add(hint2);
        hint2PanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hint2Pan.add(hint2PanIn);
        hint2Pan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        if(boolHint3==true){
            //Baptiste fonction récupérer hint3
        }else{
            hint3PanIn.setBackground(Color.GRAY);
        }
        hint3PanIn.add(hint3);
        hint3PanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hint3Pan.add(hint3PanIn);
        hint3Pan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        if(stringHelp!=""){
            helpGM = new JLabel(stringHelp);
        }else{
            helpGM = new JLabel("Aide du MJ");
            helpGM.setBackground(Color.GRAY);
        }
        helpGMPanIn.add(helpGM);
        helpGMPanIn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        helpGMPan.add(helpGMPanIn);
        helpGMPan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        helperPan.setLayout(new BoxLayout(helperPan, BoxLayout.LINE_AXIS));
        helperPan.add(hint1Pan);
        helperPan.add(hint2Pan);
        helperPan.add(hint3Pan);
        helperPan.add(helpGMPan);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(topPan);
        this.add(currentStoryPan);
        this.add(middlePan);
        this.add(formerStoryPan);
        this.add(helperPan);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonValidate){
            //Baptiste fonction rechercher la solution
        }
    }
}