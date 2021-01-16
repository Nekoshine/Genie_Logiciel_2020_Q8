package view;

import database.DBEnigma;
import database.DBGame;
import model.EnigmaList;
import model.Game;
import view.style.ColorPerso;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;


public class CurrentGame extends JPanel implements ActionListener {

    private JPanel firstRawPanel ;
    private JPanel firstRawPanelIn1;
    private JPanel firstRawPanelIn2;
    private JPanel currentEnigmaPanel;
    private JPanel currentEnigmaPanelOUT;
    private JPanel thirdRawPanel;
    private JPanel oldEnigmaPanel;
    private JPanel oldEnigmaPanelOUT;
    private JPanel hintRawPanel;
    private JPanel hintContainer1;
    private JPanel hintContainer2;
    private JPanel hintContainer3;
    private JPanel hintMJContainer;

    private JScrollPane currentEnigmaScroll;
    private JScrollPane oldEnigmaScroll;
    private JScrollPane hint1Scroll;
    private JScrollPane hint2Scroll;
    private JScrollPane hint3Scroll;

    private JTextField answerTextField;


    private JButton confirmButton;
    private JButton hint1Button;
    private JButton hint2Button;
    private JButton hint3Button;

    private JTextArea currentEnigmaTextArea;
    private JTextArea oldEnigmaTextArea;
    private JTextArea hint1TextArea;
    private JTextArea hint2TextArea;
    private JTextArea hint3TextArea;
    private JTextArea hintMJTextArea;

    private JLabel titleLabel;
    private JLabel countdownLabel;

    private int enigmalistflag = 0;
    private int timerclue1;
    private int timerclue2;
    private int timerclue3;
    private boolean ishint2present;
    private boolean ishint3present;
    private boolean isused1 = false;
    private boolean isused2 = false;
    private boolean isused3 = false;

    private EnigmaList allEnigmas;

    public ImageIcon imageIconValide;
    public  ImageIcon imageIconRefus;
    private Game game;


    private Timer countdownenigma;
    private Timer timeonenigma;

    private int time = 3600;

    private GlobalFrame frame;

    public CurrentGame (GlobalFrame frame, Game partiechoisie ){

        imageIconValide = new ImageIcon(new ImageIcon("./src/view/image/valide.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        imageIconRefus = new ImageIcon(new ImageIcon("./src/view/image/refus.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        this.frame = frame;
        allEnigmas = DBEnigma.getEnigmas(partiechoisie.getId());
        game = DBGame.getGame(partiechoisie.getId());
        timerclue1 = allEnigmas.getEnigma(enigmalistflag).getTimer1();

        ishint2present = !(allEnigmas.getEnigma(enigmalistflag).getClue2().isEmpty());
        if(ishint2present){timerclue2 = allEnigmas.getEnigma(enigmalistflag).getTimer2();}

        ishint3present = !(allEnigmas.getEnigma(enigmalistflag).getClue3().isEmpty());
        if(ishint3present){timerclue3 = allEnigmas.getEnigma(enigmalistflag).getTimer3();};


        //titre + timer

        firstRawPanel = new JPanel();
        firstRawPanel.setLayout(new FlowLayout(FlowLayout.LEFT,160,0));
        firstRawPanel.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),35));
        firstRawPanel.setBorder(BorderFactory.createEmptyBorder(10,10,5,5));

        firstRawPanelIn1 = new JPanel();
        firstRawPanelIn1.setLayout(new FlowLayout(FlowLayout.LEFT));
        firstRawPanelIn1.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),30));

        titleLabel = new JLabel(partiechoisie.getTitre());
        titleLabel.setMaximumSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.60),20));
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        countdownLabel = new JLabel("Timer");
        countdownLabel.setMaximumSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.20),20));
        countdownLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        firstRawPanelIn2 = new JPanel();
        firstRawPanelIn2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        firstRawPanelIn2.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.20),30));


        firstRawPanelIn1.add(titleLabel);
        firstRawPanelIn2.add(countdownLabel);
        firstRawPanel.add(firstRawPanelIn1);
        firstRawPanel.add(firstRawPanelIn2);

        //Enigme en cours

        currentEnigmaTextArea = new JTextArea(allEnigmas.getEnigma(enigmalistflag).getText());
        currentEnigmaTextArea.setLineWrap(true);
        currentEnigmaTextArea.setWrapStyleWord(true);
        currentEnigmaTextArea.setEditable(false);
        currentEnigmaScroll = new JScrollPane(currentEnigmaTextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        currentEnigmaScroll.getVerticalScrollBar().setUnitIncrement(20);
        currentEnigmaScroll.setBorder(BorderFactory.createEmptyBorder());


        currentEnigmaPanel = new JPanel();
        currentEnigmaPanel.setLayout(new GridLayout(1,1));
        currentEnigmaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        currentEnigmaPanel.add(currentEnigmaScroll);

        currentEnigmaPanelOUT = new JPanel();
        currentEnigmaPanelOUT.setLayout(new GridLayout(1,1));
        currentEnigmaPanelOUT.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),(int)((float) frame.windowSize.getHeight()*0.25)));
        currentEnigmaPanelOUT.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        currentEnigmaPanelOUT.add(currentEnigmaPanel);

        // Champs de reponse + bouton de confirmation

        answerTextField = new JTextField("your answer");
        answerTextField.setColumns(30);
        answerTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        answerTextField.setMaximumSize(new Dimension(60,15));

        confirmButton = new JButton("Confirmer");
        confirmButton.setBackground(ColorPerso.vert);
        confirmButton.setMaximumSize(new Dimension(30,15));
        confirmButton.addActionListener(this);

        thirdRawPanel = new JPanel();
        thirdRawPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
        thirdRawPanel.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),35));
        thirdRawPanel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        thirdRawPanel.add(answerTextField);
        thirdRawPanel.add(confirmButton);

        //anciennes enigmes

        oldEnigmaTextArea = new JTextArea();
        oldEnigmaTextArea.setLineWrap(true);
        oldEnigmaTextArea.setWrapStyleWord(true);
        oldEnigmaTextArea.setEditable(false);
        oldEnigmaScroll = new JScrollPane(oldEnigmaTextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        oldEnigmaScroll.getVerticalScrollBar().setUnitIncrement(20);
        oldEnigmaScroll.setBorder(BorderFactory.createEmptyBorder());

        oldEnigmaPanel = new JPanel();
        oldEnigmaPanel.setLayout(new GridLayout(1,1));
        oldEnigmaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        oldEnigmaPanel.add(oldEnigmaScroll);

        oldEnigmaPanelOUT = new JPanel();
        oldEnigmaPanelOUT.setLayout(new GridLayout(1,1));
        oldEnigmaPanelOUT.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),(int)((float) frame.windowSize.getHeight()*0.25)));
        oldEnigmaPanelOUT.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        oldEnigmaPanelOUT.add(oldEnigmaPanel);

        // indices

        hint1Button = new JButton("hint1");
        hint1Button.setEnabled(true);
        hint1Button.setBackground(Color.GRAY);
        hint1Button.addActionListener(this);

        hintContainer1 = new JPanel();
        hintContainer1.setLayout(new GridLayout(1,1));
        hintContainer1.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.25),(int)((float) frame.windowSize.getHeight()*0.15)));
        hintContainer1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hintContainer1.add(hint1Button);

        hint2Button = new JButton("hint2");
        if(ishint2present){hint2Button.setEnabled(true);}
        else{hint2Button.setEnabled(false);}
        hint2Button.setBackground(ColorPerso.GRAY);
        hint2Button.addActionListener(this);

        hintContainer2 = new JPanel();
        hintContainer2.setLayout(new GridLayout(1,1));
        hintContainer2.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.25),(int)((float) frame.windowSize.getHeight()*0.15)));
        hintContainer2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hintContainer2.add(hint2Button);

        hint3Button = new JButton("hint3");
        if(ishint3present){hint3Button.setEnabled(true);}
        else{hint3Button.setEnabled(false);}
        hint3Button.setBackground(ColorPerso.GRAY);
        hint3Button.addActionListener(this);

        hintContainer3 = new JPanel();
        hintContainer3.setLayout(new GridLayout(1,1));
        hintContainer3.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.25),(int)((float) frame.windowSize.getHeight()*0.15)));
        hintContainer3.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        hintContainer3.add(hint3Button);

        hintMJTextArea = new JTextArea("aide envoyée par le MJ");
        hintMJTextArea.setLineWrap(true);
        hintMJTextArea.setWrapStyleWord(true);
        hintMJTextArea.setEditable(false);
        hintMJTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hintMJContainer = new JPanel();
        hintMJContainer.setLayout(new GridLayout(1,1));
        hintMJContainer.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.35),(int)((float) frame.windowSize.getHeight()*0.15)));
        hintMJContainer.add(hintMJTextArea);

        hintRawPanel = new JPanel();
        hintRawPanel.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));

        hintRawPanel.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),(int)((float) frame.windowSize.getHeight()*0.18)));
        hintRawPanel.add(hintContainer1);
        hintRawPanel.add(hintContainer2);
        hintRawPanel.add(hintContainer3);
        hintRawPanel.add(hintMJContainer);

        this.setLayout(new BoxLayout(this , BoxLayout.PAGE_AXIS));
        this.add(firstRawPanel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(currentEnigmaPanelOUT);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(thirdRawPanel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(oldEnigmaPanelOUT);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(hintRawPanel);


    }


    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == hint1Button){
            hint1TextArea = new JTextArea(allEnigmas.getEnigma(enigmalistflag).getClue1());
            hint1TextArea.setLineWrap(true);
            hint1TextArea.setWrapStyleWord(true);
            hint1TextArea.setEditable(false);
            hint1Scroll = new JScrollPane(hint1TextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            hint1Scroll.getVerticalScrollBar().setUnitIncrement(20);
            hint1Scroll.setBorder(BorderFactory.createEmptyBorder());
            hintContainer1.remove(hint1Button);
            hintContainer1.add(hint1Scroll);
            isused1 = true;
            frame.revalidate();
            frame.repaint();
        }

        if (event.getSource() == hint2Button){
            if(ishint2present){
                hint2TextArea = new JTextArea(allEnigmas.getEnigma(enigmalistflag).getClue2());
                hint2TextArea.setLineWrap(true);
                hint2TextArea.setWrapStyleWord(true);
                hint2TextArea.setEditable(false);
                hint2Scroll = new JScrollPane(hint2TextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                hint2Scroll.getVerticalScrollBar().setUnitIncrement(20);
                hint2Scroll.setBorder(BorderFactory.createEmptyBorder());
                hintContainer2.remove(hint2Button);
                hintContainer2.add(hint2Scroll);
                isused2 = true;

                frame.revalidate();
                frame.repaint();
            }
        }

        if (event.getSource() == hint3Button){
            if(ishint3present) {
                hint3TextArea = new JTextArea(allEnigmas.getEnigma(enigmalistflag).getClue3());
                hint3TextArea.setLineWrap(true);
                hint3TextArea.setWrapStyleWord(true);
                hint3TextArea.setEditable(false);
                hint3Scroll = new JScrollPane(hint3TextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                hint3Scroll.getVerticalScrollBar().setUnitIncrement(20);
                hint3Scroll.setBorder(BorderFactory.createEmptyBorder());
                hintContainer3.remove(hint3Button);
                hintContainer3.add(hint3Scroll);
                isused3 = true;
                frame.revalidate();
                frame.repaint();
            }
        }

        if (event.getSource() == confirmButton){
            String answer = answerTextField.getText().toLowerCase();
            String[] possibility = allEnigmas.getEnigma(enigmalistflag).getAnswers();
            boolean find = false;
            for(int i=0;i<possibility.length;i++){
                if(answer.equals(possibility[i].toLowerCase())){
                    find = true;
                }
            }
            if (find) {
                if (enigmalistflag < allEnigmas.getSize() - 1) {

                    //maj des champs relatifs aux enigmes

                    oldEnigmaTextArea.append(allEnigmas.getEnigma(enigmalistflag).getText());
                    enigmalistflag++;
                    currentEnigmaPanel.remove(currentEnigmaTextArea);
                    currentEnigmaTextArea = new JTextArea(allEnigmas.getEnigma(enigmalistflag).getText());
                    currentEnigmaTextArea.setLineWrap(true);
                    currentEnigmaTextArea.setWrapStyleWord(true);
                    currentEnigmaTextArea.setEditable(false);
                    currentEnigmaPanel.add(currentEnigmaTextArea);

                    //maj hint 1
                    if(isused1){
                    hintContainer1.remove(hint1Scroll);
                    hintContainer1.add(hint1Button);}
                    isused1 = false;
                    timerclue1 = allEnigmas.getEnigma(enigmalistflag).getTimer1();
                    hint1Button.setEnabled(true);


                    //maj hint 2

                    if (ishint2present) {
                        if(isused2) {
                            hintContainer2.remove(hint2Scroll);
                            hintContainer2.add(hint2Button);
                        }
                    }
                    isused2 = false;
                    ishint2present = !(allEnigmas.getEnigma(enigmalistflag).getClue2().isEmpty());
                    if (ishint2present) {
                        hint2Button.setEnabled(true);
                        timerclue2 = allEnigmas.getEnigma(enigmalistflag).getTimer2();
                    } else {
                        hint3Button.setEnabled(false);
                    }

                    //maj hint 3

                    if (ishint3present) {
                        if (isused3) {
                            hintContainer3.remove(hint3Scroll);
                            hintContainer3.add(hint3Button);
                        }
                    }
                    isused3 = false;
                    ishint3present = !(allEnigmas.getEnigma(enigmalistflag).getClue3().isEmpty());
                    System.out.print(ishint3present);
                    if (ishint3present) {
                        hint3Button.setEnabled(true);
                        timerclue3 = allEnigmas.getEnigma(enigmalistflag).getTimer3();
                    } else {
                        hint3Button.setEnabled(false);
                    }

                    frame.revalidate();
                    frame.repaint();
                }
                else{JOptionPane.showMessageDialog(frame, "Vous avez réussi !!!!", "Bravo !", JOptionPane.WARNING_MESSAGE,imageIconValide);
                    frame.connectionMenuDisplay(frame);
                }

            }
            else{JOptionPane.showMessageDialog(frame, "Ce n'est pas la bonne reponse", "Raté !", JOptionPane.WARNING_MESSAGE,imageIconRefus);
            }
        }

    }

}