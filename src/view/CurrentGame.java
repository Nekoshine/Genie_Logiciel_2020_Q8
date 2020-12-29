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


    private JLabel titleLabel;
    private JLabel countdownLabel;
    private JLabel currentEnigmaLabel;
    private JLabel oldEnigmaLabel;
    private JLabel hint1Label;
    private JLabel hint2Label;
    private JLabel hint3Label;
    private JLabel hintMJLabel;

    private Timer countdown;
    private int time = 3600;

    public CurrentGame (GlobalFrame frame ){
        //this.frame = frame;


        //titre + timer

        firstRawPanel = new JPanel();
        firstRawPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));

        titleLabel = new JLabel("Titre de l'escape game a recuperer d'une maniere ou d'une autre");
        titleLabel.setMaximumSize(new Dimension(300,20));
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        countdownLabel = new JLabel("Timer");
        countdownLabel.setMaximumSize(new Dimension(75,20));
        countdownLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        firstRawPanel.add(titleLabel);
        firstRawPanel.add(countdownLabel);


        //Enigme en cours

        currentEnigmaLabel = new JLabel("Recuperer le texte de l'énigme en cours");
        currentEnigmaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        currentEnigmaScroll = new JScrollPane(currentEnigmaLabel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        currentEnigmaScroll.getVerticalScrollBar().setUnitIncrement(20);
        currentEnigmaScroll.setBorder(BorderFactory.createEmptyBorder());

        currentEnigmaPanel = new JPanel();
        currentEnigmaPanel.setLayout(new GridLayout(1,1));
        currentEnigmaPanel.setMaximumSize(new Dimension(1000,500));
        currentEnigmaPanel.add(currentEnigmaLabel);


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
        thirdRawPanel.add(answerTextField);
        thirdRawPanel.add(confirmButton);

        //Enigme en cours

        oldEnigmaLabel = new JLabel("Recuperer le texte de l'énigme en cours");
        oldEnigmaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        oldEnigmaScroll = new JScrollPane(oldEnigmaLabel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        oldEnigmaScroll.getVerticalScrollBar().setUnitIncrement(20);
        oldEnigmaScroll.setBorder(BorderFactory.createEmptyBorder());

        oldEnigmaPanel = new JPanel();
        oldEnigmaPanel.setLayout(new GridLayout(1,1));
        oldEnigmaPanel.setMaximumSize(new Dimension(1000,500));
        oldEnigmaPanel.add(currentEnigmaLabel);

        // indices

        hint1Button = new JButton("hint1");
        hint1Button.setEnabled(false);
        hintContainer1.setBackground(ColorPerso.GRAY);
        hint1Button.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hintContainer1 = new JPanel();
        hintContainer1.setLayout(new GridLayout(1,1));
        hintContainer1.setMaximumSize(new Dimension(100,50));
        hintContainer1.add(hint1Button);

        hint2Button = new JButton("hint1");
        hint2Button.setEnabled(false);
        hintContainer2.setBackground(ColorPerso.GRAY);
        hint2Button.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hintContainer2 = new JPanel();
        hintContainer2.setLayout(new GridLayout(1,1));
        hintContainer2.setMaximumSize(new Dimension(100,50));
        hintContainer2.add(hint1Button);

        hint3Button = new JButton("hint1");
        hint3Button.setEnabled(false);
        hintContainer3.setBackground(ColorPerso.GRAY);
        hint3Button.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hintContainer3 = new JPanel();
        hintContainer3.setLayout(new GridLayout(1,1));
        hintContainer3.setMaximumSize(new Dimension(100,50));
        hintContainer3.add(hint1Button);

        hintMJLabel = new JLabel("aide envoyée par le MJ");
        hintMJLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        hintMJContainer = new JPanel();
        hintMJContainer.setLayout(new GridLayout(1,1));
        hintMJContainer.setMaximumSize(new Dimension(100,50));
        hintMJContainer.add(hintMJLabel);

        hintRawPanel = new JPanel();
        hintRawPanel.setLayout(new FlowLayout(FlowLayout.LEADING,30,0));
        hintRawPanel.add(hintContainer1);
        hintRawPanel.add(hintContainer2);
        hintRawPanel.add(hintContainer3);
        hintRawPanel.add(hintMJContainer);

    }


    public void actionPerformed(ActionEvent event) {}
}
