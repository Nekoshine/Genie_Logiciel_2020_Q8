package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;


public class CurrentGame extends JPanel implements ActionListener {

    private JPanel firstRawPanel ;
    private JPanel currentEnigmaPanel;
    private JPanel thirdRawPanel;
    private JPanel oldEnigmaPanel;
    private JPanel hintRawPanel;
    private JPanel hintContainer1;
    private JPanel hintContainer2;
    private JPanel hintContainer3;
    private JPanel hintMJContainer;

    private JScrollPane currentEnigmaScroll;
    private JScrollPane oldEnigmaScroll;

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




    private Timer countdown;
    private int time = 3600;

    private GlobalFrame frame;

    public CurrentGame (GlobalFrame frame ){

        this.frame = frame;


        //titre + timer

        firstRawPanel = new JPanel();
        firstRawPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
        firstRawPanel.setMaximumSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),35));
        firstRawPanel.setBorder(BorderFactory.createEmptyBorder(10,10,5,5));

        titleLabel = new JLabel("Titre de l'escape game a recuperer d'une maniere ou d'une autre");
        titleLabel.setMaximumSize(new Dimension(300,20));
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        countdownLabel = new JLabel("Timer");
        countdownLabel.setMaximumSize(new Dimension(75,20));
        countdownLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        firstRawPanel.add(titleLabel);
        firstRawPanel.add(countdownLabel);


        //Enigme en cours

        currentEnigmaTextArea = new JTextArea("Recuperer le texte de l'énigme en cours");
        currentEnigmaTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        currentEnigmaTextArea.setLineWrap(true);
        currentEnigmaTextArea.setWrapStyleWord(true);
        currentEnigmaTextArea.setEditable(false);
        currentEnigmaScroll = new JScrollPane(currentEnigmaTextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        currentEnigmaScroll.getVerticalScrollBar().setUnitIncrement(20);
        currentEnigmaScroll.setBorder(BorderFactory.createEmptyBorder());

        currentEnigmaPanel = new JPanel();
        currentEnigmaPanel.setLayout(new GridLayout(1,1));
        currentEnigmaPanel.setMaximumSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),(int)((float) frame.windowSize.getHeight()*0.25)));
        currentEnigmaPanel.add(currentEnigmaTextArea);


        // Champs de reponse + bouton de confirmation

        answerTextField = new JTextField("your answer");
        answerTextField.setColumns(30);
        answerTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        answerTextField.setMaximumSize(new Dimension(60,15));

        confirmButton = new JButton("Confirmer");
        confirmButton.setBackground(ColorPerso.vert);
        confirmButton.setMaximumSize(new Dimension(30,15));

        thirdRawPanel = new JPanel();
        thirdRawPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
        thirdRawPanel.setMaximumSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),35));
        thirdRawPanel.setBorder(BorderFactory.createEmptyBorder(10,10,5,5));

        thirdRawPanel.add(answerTextField);
        thirdRawPanel.add(confirmButton);

        //anciennes enigmes

        oldEnigmaTextArea = new JTextArea("Recuperer le texte de l'énigme en cours");
        oldEnigmaTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        oldEnigmaTextArea.setLineWrap(true);
        oldEnigmaTextArea.setWrapStyleWord(true);
        oldEnigmaTextArea.setEditable(false);
        oldEnigmaScroll = new JScrollPane(oldEnigmaTextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        oldEnigmaScroll.getVerticalScrollBar().setUnitIncrement(20);
        oldEnigmaScroll.setBorder(BorderFactory.createEmptyBorder());

        oldEnigmaPanel = new JPanel();
        oldEnigmaPanel.setLayout(new GridLayout(1,1));
        oldEnigmaPanel.setMaximumSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),(int)((float) frame.windowSize.getHeight()*0.25)));
        oldEnigmaPanel.add(oldEnigmaTextArea);

        // indices

        hint1Button = new JButton("hint1");
        hint1Button.setEnabled(true);
        hint1Button.setBackground(Color.GRAY);
        hint1Button.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hintContainer1 = new JPanel();
        hintContainer1.setLayout(new GridLayout(1,1));
        hintContainer1.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.15),(int)((float) frame.windowSize.getHeight()*0.15)));
        hintContainer1.add(hint1Button);

        hint2Button = new JButton("hint2");
        hint2Button.setEnabled(false);
        hint2Button.setBackground(ColorPerso.GRAY);
        hint2Button.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hintContainer2 = new JPanel();
        hintContainer2.setLayout(new GridLayout(1,1));
        hintContainer2.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.15),(int)((float) frame.windowSize.getHeight()*0.15)));
        hintContainer2.add(hint2Button);

        hint3Button = new JButton("hint3");
        hint3Button.setEnabled(true);
        hint3Button.setBackground(ColorPerso.GRAY);
        hint3Button.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hintContainer3 = new JPanel();
        hintContainer3.setLayout(new GridLayout(1,1));
        hintContainer3.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.15),(int)((float) frame.windowSize.getHeight()*0.15)));
        hintContainer3.add(hint3Button);

        hintMJTextArea = new JTextArea("aide envoyée par le MJ");
        hintMJTextArea.setLineWrap(true);
        hintMJTextArea.setWrapStyleWord(true);
        hintMJTextArea.setEditable(false);
        hintMJTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hintMJContainer = new JPanel();
        hintMJContainer.setLayout(new GridLayout(1,1));
        hintMJContainer.setPreferredSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.30),(int)((float) frame.windowSize.getHeight()*0.15)));
        hintMJContainer.add(hintMJTextArea);

        hintRawPanel = new JPanel();
        hintRawPanel.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));

        hintRawPanel.setMaximumSize(new Dimension((int)((float) frame.windowSize.getWidth()*0.95),(int)((float) frame.windowSize.getHeight()*0.18)));
        hintRawPanel.add(hintContainer1);
        hintRawPanel.add(hintContainer2);
        hintRawPanel.add(hintContainer3);
        hintRawPanel.add(hintMJContainer);

        this.setLayout(new BoxLayout(this , BoxLayout.PAGE_AXIS));
        this.add(firstRawPanel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(currentEnigmaPanel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(thirdRawPanel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(oldEnigmaPanel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(hintRawPanel);


    }


    public void actionPerformed(ActionEvent event) {}
}
